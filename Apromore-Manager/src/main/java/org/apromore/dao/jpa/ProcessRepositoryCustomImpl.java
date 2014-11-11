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

package org.apromore.dao.jpa;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

import org.apromore.dao.ProcessRepositoryCustom;
import org.apromore.dao.model.Process;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * implementation of the org.apromore.dao.ProcessDao interface.
 *
 * @author <a href="mailto:cam.james@gmail.com">Cameron James</a>
 * @since 1.0
 */
public class ProcessRepositoryCustomImpl implements ProcessRepositoryCustom {

    @PersistenceContext
    private EntityManager em;

    @Resource
    private JdbcTemplate jdbcTemplate;


    private static final String GET_ALL_PROCESSES_JPA = "SELECT p FROM Process p ";
    private static final String GET_ALL_PROCESSES_FOLDER_JPA = "SELECT p FROM Process p JOIN p.folder f ";
    private static final String GET_ALL_PUBLIC_JPA = "p.publicModel = true ";
    private static final String GET_ALL_FOLDER_JPA = "f.id = ";
    private static final String GET_ALL_SORT_JPA = " ORDER by p.id";


    /* ************************** JPA Methods here ******************************* */

    /**
     * @see org.apromore.dao.ProcessRepositoryCustom#findAllProcesses(String)
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Process> findAllProcesses(final String conditions) {
        StringBuilder strQry = new StringBuilder(0);
        strQry.append(GET_ALL_PROCESSES_JPA);
        if (conditions != null && !conditions.isEmpty()) {
            strQry.append(" WHERE ").append(conditions);
            strQry.append(" AND ").append(GET_ALL_PUBLIC_JPA);
        } else {
            strQry.append(" WHERE ").append(GET_ALL_PUBLIC_JPA);
        }
        strQry.append(GET_ALL_SORT_JPA);

        Query query = em.createQuery(strQry.toString());
        return query.getResultList();
    }

    /**
     * @see org.apromore.dao.ProcessRepositoryCustom#findAllProcessesByFolder(Integer, String)
     * {@inheritDoc}
     */
    @Override
    @SuppressWarnings("unchecked")
    public List<Process> findAllProcessesByFolder(final Integer folderId, final String conditions) {
        boolean whereAdded = false;
        StringBuilder strQry = new StringBuilder(0);
        strQry.append(GET_ALL_PROCESSES_FOLDER_JPA);
        if (conditions != null && !conditions.isEmpty()) {
            strQry.append(" WHERE ").append(conditions);
            strQry.append(" AND ").append(GET_ALL_PUBLIC_JPA);
            whereAdded = true;
        } //else {
        //   strQry.append(" WHERE ").append(GET_ALL_PUBLIC_JPA);
        //}
        if (whereAdded) {
            strQry.append(" AND ");
        } else {
            strQry.append(" WHERE ");
        }
        strQry.append(GET_ALL_FOLDER_JPA).append(folderId);
        strQry.append(GET_ALL_SORT_JPA);

        Query query = em.createQuery(strQry.toString());
        return query.getResultList();
    }

    /* ************************** JDBC Template / native SQL Queries ******************************* */

}
