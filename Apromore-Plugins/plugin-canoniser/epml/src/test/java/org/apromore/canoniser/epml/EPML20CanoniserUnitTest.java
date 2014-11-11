package org.apromore.canoniser.epml;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.io.File;
import java.io.FileNotFoundException;
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
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

public class EPML20CanoniserUnitTest {

    private EPML20Canoniser epml20Canoniser;

    @Before
    public void setUp() {
        epml20Canoniser = new EPML20Canoniser();
    }

    @Test
    public void testEPML20Canoniser() {
        assertNotNull(epml20Canoniser);
    }

    @Test
    public void testCanonise() throws CanoniserException, FileNotFoundException, IOException, PropertyException, JAXBException, SAXException {
        EPML20Canoniser c = new EPML20Canoniser();
        ArrayList<AnnotationsType> anfList = new ArrayList<>();
        ArrayList<CanonicalProcessType> cpfList = new ArrayList<>();
        c.canonise(ClassLoader.getSystemResourceAsStream("EPML/Basic.epml"), anfList, cpfList, new PluginRequestImpl());

        assertFalse(anfList.isEmpty());
        assertFalse(cpfList.isEmpty());

        try (FileOutputStream canonicalFormat = new FileOutputStream("target/Basic.cpf")) {
            CPFSchema.marshalCanonicalFormat(canonicalFormat, cpfList.get(0), true);
        }
    }

    @Test
    public void testDeCanonise() throws CanoniserException, JAXBException, SAXException, IOException {
        EPML20Canoniser c = new EPML20Canoniser();

        try (OutputStream epmlStream = new FileOutputStream(new File("target/Basic.epml"));
                InputStream cpfStream = ClassLoader.getSystemResourceAsStream("CPF/Basic.cpf");
                InputStream anfStream = ClassLoader.getSystemResourceAsStream("ANF/Basic.anf");) {
            c.deCanonise(CPFSchema.unmarshalCanonicalFormat(cpfStream, true).getValue(), ANFSchema.unmarshalAnnotationFormat(anfStream, true)
                    .getValue(), epmlStream, new PluginRequestImpl());
            epmlStream.flush();
        }

    }

    @Test
    public void testGetNativeType() {
        assertEquals("EPML 2.0", epml20Canoniser.getNativeType());
    }

    @Test
    public void testGetName() {
        assertNotNull(epml20Canoniser.getName());
    }

    @Test
    public void testGetVersion() {
        assertNotNull(epml20Canoniser.getVersion());
    }

    @Test
    public void testGetType() {
        assertEquals("Canoniser", epml20Canoniser.getType());
    }

    @Test
    public void testGetDescription() {
        assertNotNull(epml20Canoniser.getDescription());
    }

    @Test
    public void testGetAuthor() {
        assertNotNull(epml20Canoniser.getAuthor());
    }

}
