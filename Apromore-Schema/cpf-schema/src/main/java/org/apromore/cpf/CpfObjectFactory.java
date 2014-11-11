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

package org.apromore.cpf;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * Factory for CPF classes.
 *
 * This factory produces CPF elements with the following extensions:
 * <ul>
 * <li><var>CancellationRefType</var>s have equals and hashcode methods overridden so that they 
 *   can be managed properly in collections.</li>
 * </ul>
 */
@XmlRegistry
public class CpfObjectFactory extends ObjectFactory {

    private final static QName _CanonicalProcess_QNAME = new QName("http://www.apromore.org/CPF", "CanonicalProcess");

    /**
     * Singleton instance.
     */
    private static CpfObjectFactory instance;

    /**
     * @return a singleton instance
     */
    public static  CpfObjectFactory getInstance() {
        if (instance == null) {
            instance = new CpfObjectFactory();  // lazy initialization of the singleton instance
        }
        return instance;
    }

    /**
     * @return a {@link CancellationRefType} whose equals and hashcode methods are a function
     *   of its {@link CancellationRefType#getRefId}
     */
    @Override
    public CancellationRefType createCancellationRefType() {
        return new CancellationRefType() {

            /**
             * {@inheritDoc}
             *
             * Equality is based on this object's {@link getRefId}.
             */
            @Override
            public boolean equals(Object other) {
                if (other instanceof CancellationRefType) {
                    CancellationRefType otherCancellationRef = (CancellationRefType) other;
                    if (getRefId() == null) {
                        return otherCancellationRef.getRefId() == null;
                    } else {
                        return getRefId().equals(otherCancellationRef.getRefId());
                    }
                } else {
                    return false;  // never equal to anything that lacks a refId
                }
            }

            /**
             * @{inheritDoc}
             *
             * The hashcode is identical to that of this object's {@link getRefId}
             */
            @Override
            public int hashCode() {
                return getRefId() == null ? 0 : getRefId().hashCode();
            }
        };
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link CanonicalProcessType }{@code >}}
     * 
     */
    @Override
    @XmlElementDecl(namespace = "http://www.apromore.org/CPF", name = "CanonicalProcess")
    public JAXBElement<CanonicalProcessType> createCanonicalProcess(CanonicalProcessType value) {
        return new JAXBElement<CanonicalProcessType>(_CanonicalProcess_QNAME, CanonicalProcessType.class, null, value);
    }
}
