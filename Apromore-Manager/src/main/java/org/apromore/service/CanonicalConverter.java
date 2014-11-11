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

import org.apromore.cpf.CanonicalProcessType;
import org.apromore.graph.canonical.Canonical;

/**
 * An Adapter class used to convert between two different formats of the same data.
 * The code that uses this can not be changed to use just one.
 *
 * @author <a href="mailto:cam.james@gmail.com">Cameron James</a>
 */
public interface CanonicalConverter {

    /**
     * Converts the Data between the two types, from jaxb to graph.
     * @param cpt the JAXB Object.
     * @return the canonical Object.
     */
    Canonical convert(CanonicalProcessType cpt);

    /**
     * Converts the data between the type types, from graph to jaxb.
     * @param canonical the RPST Graph.
     * @return
     */
    CanonicalProcessType convert(Canonical canonical);

}
