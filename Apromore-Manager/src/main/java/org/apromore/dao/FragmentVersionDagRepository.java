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

import org.apromore.dao.model.FragmentVersion;
import org.apromore.dao.model.FragmentVersionDag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * Interface domain model Data access object FragmentVersionDag.
 * @author <a href="mailto:cam.james@gmail.com">Cameron James</a>
 * @version 1.0
 * @see org.apromore.dao.model.FragmentVersionDag
 */
@Repository
public interface FragmentVersionDagRepository extends JpaRepository<FragmentVersionDag, Integer>, FragmentVersionDagRepositoryCustom {

    /**
     * Returns a single FragmentVersionDag based on the fragment version uri.
     * @param uri the Fragment uri
     * @return the found FragmentVersionDag
     */
    @Query("SELECT fvd FROM FragmentVersionDag fvd WHERE fvd.fragmentVersion.uri = ?1")
    FragmentVersionDag findFragmentVersionDagByURI(String uri);

    /**
     * Returns all the child mappings for the FragmentId.
     * @param fragmentId the fragment id
     * @return the list of child fragments
     */
    @Query("SELECT fvd FROM FragmentVersionDag fvd WHERE fvd.fragmentVersion.id = ?1")
    List<FragmentVersionDag> getChildMappings(Integer fragmentId);

    /**
     * Delete all the Child relationships for this Fragment Version.
     * @param fragmentVersion the fragment version we want to remove all the children
     */
    @Modifying
    @Query("DELETE FROM FragmentVersionDag fvd WHERE fvd.fragmentVersion = ?1")
    void deleteChildRelationships(FragmentVersion fragmentVersion);
}
