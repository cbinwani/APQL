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

import java.util.List;

import org.apromore.dao.dataObject.FragmentVersionDO;

/**
 * Interface domain model Data access object FragmentVersion.
 * @author <a href="mailto:cam.james@gmail.com">Cameron James</a>
 * @version 1.0
 * @see org.apromore.dao.model.FragmentVersion
 */
public interface FragmentVersionRepositoryCustom {

    /* ************************** JPA Methods here ******************************* */


    /* ************************** JDBC Template / native SQL Queries ******************************* */

    /**
     * Finds all the similar fragments by there size.
     * @param minSize the min size we are looking for
     * @param maxSize the max size we are looking for
     * @return the list of found fragment versions
     */
    List<FragmentVersionDO> getFragmentsBetweenSize(int minSize, int maxSize);
}
