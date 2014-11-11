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

package org.apromore.graph.canonical;

/**
 * Interface class for {@link CPFAttribute}
 * 
 * @author Felix Mannhardt
 */
public interface IAttribute {

    /**
     * Set the simple text value of this attribute.
     * @param value
     */
    void setValue(String value);

    /**
     * Get the simple text value of this attribute.
     * @return
     */
    String getValue();


    /**
     * Set the complex XML object of this attribute. Object should be of type {@link org.w3c.dom.Element} in case of a namespace less XML or
     * {@link javax.xml.bind.JAXBElement} in case of XML with a namespace. The caller should know how do deal with this element.
     * @param any {@link org.w3c.dom.Element} or {@link javax.xml.bind.JAXBElement}
     */
    void setAny(Object any);

    /**
     * Get the complex XML object of this attribute. Object should be of type {@link org.w3c.dom.Element} in case of a namespace less XML or
     * {@link javax.xml.bind.JAXBElement} in case of XML with a namespace. The caller should know how to deal with this element.
     * @return {@link org.w3c.dom.Element} or {@link javax.xml.bind.JAXBElement}
     */
    Object getAny();

}
