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
import javax.xml.bind.annotation.XmlRegistry;

/**
 * Element factory for an ANF 0.3 object model with convenience methods.
 *
 * @author <a href="mailto:simon.raboczi@uqconnect.edu.au">Simon Raboczi</a>
 */
@XmlRegistry
public class ObjectFactory extends org.apromore.anf.ObjectFactory {

    @Override
    public AnfAnnotationsType createAnnotationsType() {
        return new AnfAnnotationsType();
    }

    @Override
    public AnfDocumentationType createDocumentationType() {
        return new AnfDocumentationType();
    }

    @Override
    public AnfGraphicsType createGraphicsType() {
        return new AnfGraphicsType();
    }

    @Override
    public AnfPositionType createPositionType() {
        return new AnfPositionType();
    }

    @Override
    public AnfSizeType createSizeType() {
        return new AnfSizeType();
    }
}
