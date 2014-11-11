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

// Local packages
import org.apromore.canoniser.bpmn.cpf.CpfTaskType;
import org.apromore.canoniser.exception.CanoniserException;
import org.omg.spec.bpmn._20100524.model.TSubProcess;

/**
 * BPMN SubProcess element with canonisation methods.
 *
 * @author <a href="mailto:simon.raboczi@uqconnect.edu.au">Simon Raboczi</a>
 */
public class BpmnSubProcess extends TSubProcess {

    /** No-arg constructor. */
    public BpmnSubProcess() { }

    /**
     * Construct a BPMN SubProcess corresponding to a CPF Task.
     *
     * @param task  a CPF Task with a defined subnet
     * @param initializer  BPMN document construction state
     * @throws CanoniserException  if the subprocess can't be constructed
     */
    public BpmnSubProcess(final CpfTaskType task,
                          final Initializer initializer) throws CanoniserException {

        // Ensure that the CPF task has its subnet identifier set
        if (task.getSubnetId() == null) {
            throw new CanoniserException("Can't create a BPMN SubProcess from the CPF Task " + task.getId() + " which has no subnet");
        }

        initializer.populateActivity(this, task);
        setTriggeredByEvent(task.isTriggeredByEvent());
        initializer.populateProcess(new ProcessWrapper(this, "subprocess"), initializer.findNet(task.getSubnetId()));
    }
}
