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

package org.apromore.canoniser.bpmn.bpmn;

// Java 2 Standard packages
import javax.xml.namespace.QName;

// Local packages
import org.apromore.canoniser.bpmn.cpf.CpfObjectType;
import org.apromore.canoniser.exception.CanoniserException;
import org.omg.spec.bpmn._20100524.model.TDataStoreReference;

/**
 * BPMN Data Store Reference with canonisation methods.
 *
 * @author <a href="mailto:simon.raboczi@uqconnect.edu.au">Simon Raboczi</a>
 */
public class BpmnDataStoreReference extends TDataStoreReference {

    /** No-arg constructor. */
    public BpmnDataStoreReference() { }

    /**
     * Construct a BPMN Data StoreReference corresponding to a CPF Object.
     *
     * @param cpfObject  a CPF object
     * @param initializer  BPMN document construction state
     * @throws CanoniserException  if the data object can't be constructed
     */
    public BpmnDataStoreReference(final CpfObjectType cpfObject, final Initializer initializer) throws CanoniserException {

        initializer.populateFlowElement(this, cpfObject);

        QName dataStore = cpfObject.getDataStore();
        assert dataStore != null : "CPF Object " + cpfObject.getId() + " doesn't have a data store set";
        setDataStoreRef(dataStore);
    }
}
