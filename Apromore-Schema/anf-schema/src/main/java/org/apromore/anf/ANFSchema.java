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

package org.apromore.anf;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.InputStream;
import java.io.OutputStream;

import org.apromore.anf.cache.CachedJaxbContext;
import org.xml.sax.SAXException;

/**
 * Utilities for working with the CPF (Validation/Parsing)
 *
 * @author Felix Mannhardt (Bonn-Rhein-Sieg University oAS)
 */
public class ANFSchema {

    public static final String ANF_CONTEXT = "org.apromore.anf";

    private static final String ANF_SCHEMA_LOCATION = "/xsd/anf_0.3.xsd";

    /**
     * Schema of ANF
     *
     * @return Schema
     * @throws SAXException
     */
    public static Schema getANFSchema() throws SAXException {
        SchemaFactory sf = SchemaFactory.newInstance(javax.xml.XMLConstants.W3C_XML_SCHEMA_NS_URI);
        return sf.newSchema(ANFSchema.class.getResource(ANF_SCHEMA_LOCATION));
    }

    /**
     * Validator for ANF
     *
     * @return Validator
     * @throws SAXException
     */
    public static Validator getANFValidator() throws SAXException {
        return getANFSchema().newValidator();
    }

    /**
     * Marshal the Annotations Format into the provided OutputStream.
     *
     * @param annotationFormat
     * @param anf
     * @param isValidating
     * @throws JAXBException
     * @throws SAXException
     */
    public static void marshalAnnotationFormat(final OutputStream annotationFormat, final AnnotationsType anf, final boolean isValidating)
            throws JAXBException, SAXException {
        final JAXBContext context = CachedJaxbContext.getJaxbContext(ANF_CONTEXT, ObjectFactory.class.getClassLoader());
                //JAXBContext.newInstance(ANF_CONTEXT, ObjectFactory.class.getClassLoader());
        final Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        if (isValidating) {
            marshaller.setSchema(getANFSchema());
        }
        final JAXBElement<AnnotationsType> anfElement = new ObjectFactory().createAnnotations(anf);
        marshaller.marshal(anfElement, annotationFormat);
    }

    /**
     * Unmarshal the Annotations Format from the provided InputStream.
     *
     * @param annotationsFormat
     * @param isValidating
     * @return
     * @throws JAXBException
     * @throws SAXException
     */
    @SuppressWarnings("unchecked")
    public static JAXBElement<AnnotationsType> unmarshalAnnotationFormat(final InputStream annotationsFormat, final boolean isValidating)
            throws JAXBException, SAXException {
        final JAXBContext jc = CachedJaxbContext.getJaxbContext(ANF_CONTEXT, ObjectFactory.class.getClassLoader());
                //JAXBContext.newInstance(ANF_CONTEXT, ObjectFactory.class.getClassLoader());
        final Unmarshaller u = jc.createUnmarshaller();
        if (isValidating) {
            u.setSchema(getANFSchema());
        }
        return (JAXBElement<AnnotationsType>) u.unmarshal(annotationsFormat);
    }

}
