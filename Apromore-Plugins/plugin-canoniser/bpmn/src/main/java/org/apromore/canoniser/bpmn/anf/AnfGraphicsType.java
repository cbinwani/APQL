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

// Local packages
import org.apromore.anf.GraphicsType;
import org.apromore.canoniser.bpmn.IdFactory;
import org.omg.spec.bpmn._20100524.di.BPMNEdge;
import org.omg.spec.bpmn._20100524.di.BPMNShape;
import org.omg.spec.dd._20100524.dc.Point;

/**
 * ANF 0.3 annotation element.
 *
 * @author <a href="mailto:simon.raboczi@uqconnect.edu.au">Simon Raboczi</a>
 */
public class AnfGraphicsType extends GraphicsType {

    /** No-arg constructor. */
    public AnfGraphicsType() { }

    /**
     * Construct a graphics annotation for a BPMNDI Edge.
     *
     * @param edge  a BPMNDI Edge
     * @param anfIdFactory  generator for identifiers
     */
    public AnfGraphicsType(final BPMNEdge edge, final IdFactory anfIdFactory) {
        setId(anfIdFactory.newId(edge.getId()));
        setCpfId(edge.getBpmnElement().getLocalPart());  // TODO - process through cpfIdFactory instead

        // Each waypoint becomes a position
        for (Point waypoint : edge.getWaypoint()) {
            getPosition().add(new AnfPositionType(waypoint));
        }
    }

    /**
     * Construct an annotation for a BPMNDI Shape.
     *
     * @param shape  a BPMNDI Shape
     * @param anfIdFactory  generator for identifiers
     */
    public AnfGraphicsType(final BPMNShape shape, final IdFactory anfIdFactory) {
        setId(anfIdFactory.newId(shape.getId()));
        setCpfId(shape.getBpmnElement().getLocalPart());  // TODO - process through cpfIdFactory instead

        // The bounds become a position and size
        getPosition().add(new AnfPositionType(shape.getBounds()));
        setSize(new AnfSizeType(shape.getBounds()));
    }
}
