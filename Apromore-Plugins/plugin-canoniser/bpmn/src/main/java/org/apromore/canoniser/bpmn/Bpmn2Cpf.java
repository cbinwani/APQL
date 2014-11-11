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

package org.apromore.canoniser.bpmn;

// Java 2 Standard packages

import org.apromore.anf.AnnotationsType;
import org.apromore.canoniser.bpmn.cpf.CpfCanonicalProcessType;
import org.apromore.canoniser.exception.CanoniserException;
import org.apromore.cpf.CanonicalProcessType;
import org.apromore.plugin.PluginResult;
import org.apromore.plugin.message.PluginMessage;
import org.xml.sax.SAXException;

import javax.xml.bind.JAXBException;
import java.util.ArrayList;
import java.util.List;

// Local packages

/**
 * Command line tool for canonizing BPMN files.
 *
 * @author <a href="mailto:simon.raboczi@uqconnect.edu.au">Simon Raboczi</a>
 */
public class Bpmn2Cpf {

    /**
     * Application entry point.
     *
     * @param arg  command line arguments, currently ignored
     */
    public static void main(String[] arg) throws CanoniserException, JAXBException, SAXException {

        List<AnnotationsType> anfs = new ArrayList<>();
        List<CanonicalProcessType> cpfs = new ArrayList<>();

        // Read BPMN from the input stream
        PluginResult result = new BPMN20Canoniser().canonise(System.in, anfs, cpfs, null);

        // Write the cpf to the output stream
        ((CpfCanonicalProcessType) cpfs.get(0)).marshal(System.out, true);

        // Write warnings to the error stream
        for (PluginMessage message : result.getPluginMessage()) {
            System.err.println(message.getMessage());
        }
    }
}
