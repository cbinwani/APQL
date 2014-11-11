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
import org.apromore.anf.DocumentationType;
import org.apromore.canoniser.bpmn.IdFactory;
import org.omg.spec.bpmn._20100524.model.TBaseElement;
import org.omg.spec.bpmn._20100524.model.TDocumentation;

/**
 * ANF 0.3 documentation annotation element.
 *
 * @author <a href="mailto:simon.raboczi@uqconnect.edu.au">Simon Raboczi</a>
 */
public class AnfDocumentationType extends DocumentationType {

    /** No-arg constructor. */
    public AnfDocumentationType() { }

    /**
     * Construct an ANF documentation annotation for a BPMN Documentation element.
     *
     * @param bpmnDocumentation  a BPMN Documentation element
     * @param parent  the parent element of <code>bpmnDocumentation</code>
     * @param anfIdFactory  generator for identifiers
     */
    public AnfDocumentationType(final TDocumentation bpmnDocumentation,
                                final TBaseElement   parent,
                                final IdFactory      anfIdFactory) {

        setId(anfIdFactory.newId(bpmnDocumentation.getId()));
        setCpfId(parent.getId());  // TODO - process through cpfIdFactory instead

        getDocumentation().addAll(bpmnDocumentation.getContent());
    }
}
