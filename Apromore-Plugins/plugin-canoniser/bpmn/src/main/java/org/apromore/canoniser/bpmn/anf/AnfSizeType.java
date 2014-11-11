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

package org.apromore.canoniser.bpmn.anf;

// Java 2 Standard packages
import java.math.BigDecimal;

// Local packages
import org.apromore.anf.SizeType;
import org.omg.spec.dd._20100524.dc.Bounds;

/**
 * ANF 0.3 size element.
 *
 * @author <a href="mailto:simon.raboczi@uqconnect.edu.au">Simon Raboczi</a>
 */
public class AnfSizeType extends SizeType {

    /** No-arg constructor. */
    public AnfSizeType() { }

    /**
     * Construct a size corresponding to an OMG DC Bounds.
     *
     * @param bounds  an OMG DC Bounds
     */
    public AnfSizeType(final Bounds bounds) {
        setWidth(new BigDecimal(bounds.getWidth()));
        setHeight(new BigDecimal(bounds.getHeight()));
    }
}
