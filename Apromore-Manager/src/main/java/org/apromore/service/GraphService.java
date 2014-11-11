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

import org.apromore.graph.canonical.Canonical;

/**
 * Interface for the Graphing Service. Defines all the methods that will do the majority of the work for
 * the Apromore application.
 *
 * @author <a href="mailto:cam.james@gmail.com">Cameron James</a>
 */
public interface GraphService {

    /**
     * Fill the Nodes by Fragment.
     * @param procModelGraph the graph we are building.
     * @param fragmentURI the fragment URI.
     */
    Canonical fillNodesByFragment(Canonical procModelGraph, String fragmentURI);

    /**
     * Fill the Nodes by Fragment.
     * @param procModelGraph the graph we are building.
     * @param fragmentURI the fragment URI.
     */
    Canonical fillEdgesByFragmentURI(Canonical procModelGraph, String fragmentURI);
}
