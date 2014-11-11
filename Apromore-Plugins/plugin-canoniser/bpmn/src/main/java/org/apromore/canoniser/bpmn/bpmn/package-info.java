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

/**
 * JAXB classes for BPMN, instrumented for decanonization.
 *
 * @author <a href="mailto:simon.raboczi@uqconnect.edu.au">Simon Raboczi</a>
 */

@javax.xml.bind.annotation.XmlSchema(
    xmlns = {
        @javax.xml.bind.annotation.XmlNs(prefix = "anf",    namespaceURI = "http://www.apromore.org/ANF"),
        @javax.xml.bind.annotation.XmlNs(prefix = "bpmn",   namespaceURI = "http://www.omg.org/spec/BPMN/20100524/MODEL"),
        @javax.xml.bind.annotation.XmlNs(prefix = "bpmndi", namespaceURI = "http://www.omg.org/spec/BPMN/20100524/DI"),
        @javax.xml.bind.annotation.XmlNs(prefix = "cpf",    namespaceURI = "http://www.apromore.org/CPF"),
        @javax.xml.bind.annotation.XmlNs(prefix = "omgdi",  namespaceURI = "http://www.omg.org/spec/DD/20100524/DI"),
        @javax.xml.bind.annotation.XmlNs(prefix = "omgdc",  namespaceURI = "http://www.omg.org/spec/DD/20100524/DC"),
        @javax.xml.bind.annotation.XmlNs(prefix = "xsi",    namespaceURI = "http://www.w3.org/2001/XMLSchema-instance")
    },
    elementFormDefault = javax.xml.bind.annotation.XmlNsForm.QUALIFIED)

package org.apromore.canoniser.bpmn.bpmn;
