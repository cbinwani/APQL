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

package org.apromore.dao;

import org.apromore.dao.model.Folder;
import org.apromore.dao.model.Group;
import org.apromore.dao.model.GroupFolder;
import org.apromore.dao.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Interface domain model Data access object GroupFolder.
 *
 * @author <a href="mailto:simon.raboczi@uqconnect.edu.au">Simon Raboczi</a>
 */
@Repository
public interface GroupFolderRepository extends JpaRepository<GroupFolder, Integer> {

    /**
     * Find the Group and Folder combination.
     *
     * @param group the group that has access
     * @param folder the folder we are looking in
     * @return the permissions for that user in the FolderUser.
     */
    GroupFolder findByGroupAndFolder(final Group group, final Folder folder);

    /**
     * @param folderId
     * @return all groups containing the folder identified by <var>folderId</var>
     */
    @Query("SELECT gf FROM GroupFolder gf WHERE (gf.folder.id = ?1)")
    List<GroupFolder> findByFolderId(final Integer folderId);

    /**
     * Returns a list of Folder Users for the folder and user combination.
     *
     * @param parentFolderId the parent folder Id
     * @param userGuid the Users Row Globally unique Id
     * @return the list of found records
     */
    @Query("SELECT gf FROM GroupFolder gf JOIN gf.folder f JOIN gf.group g1 LEFT JOIN f.parentFolder f1, " +
           "               User u JOIN u.groups g2 " +
           "WHERE ((?1 = 0 AND f1 IS NULL) OR (f1.id = ?1)) AND (u.rowGuid = ?2) AND (g1 = g2) order by f.name asc")
    List<GroupFolder> findByParentFolderAndUser(final Integer parentFolderId, final String userGuid);
}
