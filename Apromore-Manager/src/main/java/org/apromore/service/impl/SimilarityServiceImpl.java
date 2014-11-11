/*
 * Copyright © 2009-2014 The Apromore Initiative.
 *
 * This file is part of "Apromore".
 *
 * "Apromore" is free software; you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation; either version 3 of the License, or
 * (at your option) any later version.
 *
 * "Apromore" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.
 * If not, see <http://www.gnu.org/licenses/lgpl-3.0.html>.
 */

package org.apromore.service.impl;

import org.apromore.cpf.CanonicalProcessType;
import org.apromore.dao.FolderRepository;
import org.apromore.dao.ProcessModelVersionRepository;
import org.apromore.dao.model.ProcessModelVersion;
import org.apromore.exception.ExceptionSearchForSimilar;
import org.apromore.exception.SerializationException;
import org.apromore.model.ParameterType;
import org.apromore.model.ParametersType;
import org.apromore.model.ProcessSummariesType;
import org.apromore.model.ProcessVersionType;
import org.apromore.model.ProcessVersionsType;
import org.apromore.service.CanoniserService;
import org.apromore.service.SimilarityService;
import org.apromore.service.helper.UserInterfaceHelper;
import org.apromore.service.model.ToolboxData;
import org.apromore.toolbox.similaritySearch.tools.SearchForSimilarProcesses;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.inject.Inject;
import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Implementation of the SimilarityService Contract.
 *
 * @author <a href="mailto:cam.james@gmail.com">Cameron James</a>
 */
@Service
@Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.DEFAULT, readOnly = true, rollbackFor = Exception.class)
public class SimilarityServiceImpl implements SimilarityService {

    private static final Logger LOGGER = LoggerFactory.getLogger(SimilarityServiceImpl.class);

    private ProcessModelVersionRepository processModelVersionRepo;
    private FolderRepository folderRepo;
    private CanoniserService canoniserSrv;
    private UserInterfaceHelper ui;


    /**
     * Default Constructor allowing Spring to Autowire for testing and normal use.
     *
     * @param processModelVersionRepository Process Model Version Repository.
     * @param folderRepository he folder repository.
     * @param uiHelper user interface helper
     */
    @Inject
    public SimilarityServiceImpl(final ProcessModelVersionRepository processModelVersionRepository, final FolderRepository folderRepository,
             final CanoniserService canoniserService, final UserInterfaceHelper uiHelper) {
        processModelVersionRepo = processModelVersionRepository;
        folderRepo = folderRepository;
        canoniserSrv = canoniserService;
        ui = uiHelper;
    }


    /**
     * @see org.apromore.service.SimilarityService#SearchForSimilarProcesses(Integer, String, Boolean, Integer, String, String, org.apromore.model.ParametersType)
     *      {@inheritDoc}
     */
    public ProcessSummariesType SearchForSimilarProcesses(final Integer processId, final String branchName, final Boolean latestVersions,
            final Integer folderId, final String userId, final String method, final ParametersType params) throws ExceptionSearchForSimilar {
        LOGGER.debug("Starting Similarity Search...");

        ProcessVersionsType similarProcesses = null;
        ProcessModelVersion query = processModelVersionRepo.getLatestProcessModelVersion(processId, branchName);
        List<ProcessModelVersion> models = getProcessModelVersionsToSearchAgainst(folderId, userId, latestVersions);

        try {
            ToolboxData data = convertModelsToCPT(models, query);
            data = getParametersForSearch(data, method, params);
            similarProcesses = performSearch(data);
            if (similarProcesses.getProcessVersion().size() == 0) {
                LOGGER.info("Process model " + query.getProcessBranch().getProcess().getId() + " version " +
                        query.getVersionNumber() + " probably faulty");
            }
        } catch (Exception se) {
            LOGGER.error("Failed to perform the similarity search.", se);
        }

        return ui.buildProcessSummaryList(userId, folderId, similarProcesses);
    }

    private List<ProcessModelVersion> getProcessModelVersionsToSearchAgainst(int folderId, String userGuid, Boolean latestVersions) {
        List<ProcessModelVersion> models;
        if (folderId == 0) {
            // We are searching the whole Repo.
            if (latestVersions) {
                models = processModelVersionRepo.getLatestProcessModelVersions();
            } else {
                models = processModelVersionRepo.findAll();
            }
        } else {
            // We have a folder, Get all processes in the folder and in all folders underneath this one.
            models = new ArrayList<>();
            models.addAll(folderRepo.getProcessModelVersionByFolderUserRecursive(folderId, userGuid));
        }
        return models;
    }


    /* Responsible for getting all the Models and converting them to CPT internal format */
    private ToolboxData convertModelsToCPT(List<ProcessModelVersion> models, ProcessModelVersion query)
            throws SerializationException, JAXBException {
        LOGGER.debug("Loading Data for search!");
        ToolboxData data = new ToolboxData();

        data.setOrigin(canoniserSrv.XMLtoCPF(query.getCanonicalDocument().getContent()));
        for (ProcessModelVersion pmv : models) {
            data.addModel(pmv, canoniserSrv.XMLtoCPF(pmv.getCanonicalDocument().getContent()));
        }

        LOGGER.debug("Data Loaded for all models!");
        return data;
    }


    /* Loads the Parameters used for the Search */
    private ToolboxData getParametersForSearch(ToolboxData data, String method, ParametersType params) {
        data.setAlgorithm(method);

        for (ParameterType p : params.getParameter()) {
            if (ToolboxData.MODEL_THRESHOLD.equals(p.getName())) {
                data.setModelthreshold(p.getValue());
            } else if (ToolboxData.LABEL_THRESHOLD.equals(p.getName())) {
                data.setLabelthreshold(p.getValue());
            } else if (ToolboxData.CONTEXT_THRESHOLD.equals(p.getName())) {
                data.setContextthreshold(p.getValue());
            } else if (ToolboxData.SKIP_N_WEIGHT.equals(p.getName())) {
                data.setSkipnweight(p.getValue());
            } else if (ToolboxData.SUB_N_WEIGHT.equals(p.getName())) {
                data.setSubnweight(p.getValue());
            } else if (ToolboxData.SKIP_E_WEIGHT.equals(p.getName())) {
                data.setSkipeweight(p.getValue());
            }
        }

        return data;
    }


    /* Does the similarity search. */
    private ProcessVersionsType performSearch(ToolboxData data) {
        double similarity;
        ProcessVersionType processVersion;
        ProcessVersionsType similarProcesses = new ProcessVersionsType();

        for (Map.Entry<ProcessModelVersion, CanonicalProcessType> e : data.getModel().entrySet()) {
            similarity = SearchForSimilarProcesses.findProcessesSimilarity(
                    data.getOrigin(), e.getValue(), data.getAlgorithm(), data.getLabelthreshold(), data.getContextthreshold(),
                    data.getSkipnweight(), data.getSubnweight(), data.getSkipeweight());
            if (similarity >= data.getModelthreshold()) {
                processVersion = new ProcessVersionType();
                processVersion.setProcessId(e.getKey().getProcessBranch().getProcess().getId());
                processVersion.setVersionName(e.getKey().getProcessBranch().getBranchName());
                processVersion.setScore(similarity);
                similarProcesses.getProcessVersion().add(processVersion);
            }
        }
        return similarProcesses;
    }

}
