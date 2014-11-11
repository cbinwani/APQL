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

package org.apromore.service;

import org.apromore.exception.ExceptionSearchForSimilar;
import org.apromore.model.ParametersType;
import org.apromore.model.ProcessSummariesType;

/**
 * Interface for the Similarity Service. Defines all the methods that will do the majority of the work for
 * the Apromore application.
 *
 * @author <a href="mailto:cam.james@gmail.com">Cameron James</a>
 */
public interface SimilarityService {

    /**
     * Search for similar processes.
     * @param processId      the processId
     * @param versionName    the name of the version we are looking for
     * @param latestVersions are we looking at the latest version or all processes
     * @param folderId       what folder are we going to search in.
     * @param userId         the user running the search. make sure we only see his processes.
     * @param method         the search algorithm
     * @param params         the params used for the
     * @return the processSummaryTypes for the found models
     * @throws ExceptionSearchForSimilar
     */
    ProcessSummariesType SearchForSimilarProcesses(Integer processId, String versionName, Boolean latestVersions, Integer folderId,
        String userId, String method, ParametersType params) throws ExceptionSearchForSimilar;


}
