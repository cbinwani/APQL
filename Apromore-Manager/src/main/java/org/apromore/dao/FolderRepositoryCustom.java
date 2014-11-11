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

import org.apromore.dao.dataObject.FolderTreeNode;
import org.apromore.dao.model.Process;
import org.apromore.dao.model.ProcessModelVersion;

import java.util.List;

/**
 * Interface domain model Data access object Workspace.
 *
 * @see org.apromore.dao.model.Workspace
 * @author <a href="mailto:cam.james@gmail.com">Igor Goldobin</a>
 * @version 1.0
 */
public interface FolderRepositoryCustom {

    /**
     * Get the Folder tree by the Users Id and Parent Folder.
     * @param parentFolderId the parent's folders Id
     * @param userId the users Id.
     * @return the list of folder tree Nodes
     */
    List<FolderTreeNode> getFolderTreeByUser(int parentFolderId, String userId);

    /**
     * Get all the ProcessModelVersion in a folder and all of it's sub folders to the end of the tree.
     * @param parentFolderId the parent's folders Id
     * @param userId the users Id.
     * @return the list of ProcessModelVersion.
     */
    List<ProcessModelVersion> getProcessModelVersionByFolderUserRecursive(int parentFolderId, String userId);


    /**
     * Get all the processes in a folder and all of it's sub folders to the end of the tree.
     * @param parentFolderId the parent's folders Id
     * @param userId the users Id.
     * @return the list of ProcessModelVersion.
     */
    List<Process> getProcessByFolderUserRecursive(Integer parentFolderId, String userId);

}
