package org.apromore.canoniser.epml;

import static org.junit.Assert.assertFalse;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import javax.xml.bind.JAXBException;
import javax.xml.bind.PropertyException;

import org.apromore.anf.ANFSchema;
import org.apromore.anf.AnnotationsType;
import org.apromore.canoniser.exception.CanoniserException;
import org.apromore.cpf.CPFSchema;
import org.apromore.cpf.CanonicalProcessType;
import org.apromore.plugin.PluginRequestImpl;
import org.junit.Test;
import org.xml.sax.SAXException;

public class OrIntgTest {

    @Test
    public void testCanonise() throws CanoniserException, PropertyException, JAXBException, SAXException, IOException {
        EPML20Canoniser c = new EPML20Canoniser();
        ArrayList<AnnotationsType> anfList = new ArrayList<>();
        ArrayList<CanonicalProcessType> cpfList = new ArrayList<>();
        c.canonise(ClassLoader.getSystemResourceAsStream("EPML/or.epml"), anfList, cpfList, new PluginRequestImpl());

        assertFalse(anfList.isEmpty());
        assertFalse(cpfList.isEmpty());

        try (FileOutputStream canonicalFormat = new FileOutputStream("target/or.cpf")) {
            CPFSchema.marshalCanonicalFormat(canonicalFormat, cpfList.get(0), true);
        }
    }

    @Test
    public void testDeCanonise() throws CanoniserException, JAXBException, SAXException, IOException {
        EPML20Canoniser c = new EPML20Canoniser();

        try (OutputStream epmlStream = new FileOutputStream(new File("target/or.epml"));
                InputStream cpfStream = ClassLoader.getSystemResourceAsStream("CPF/or.cpf");
                InputStream anfStream = ClassLoader.getSystemResourceAsStream("ANF/or.anf");) {
            c.deCanonise(CPFSchema.unmarshalCanonicalFormat(cpfStream, true).getValue(), ANFSchema.unmarshalAnnotationFormat(anfStream, true)
                    .getValue(), epmlStream, new PluginRequestImpl());
            epmlStream.flush();
        }

    }
}
