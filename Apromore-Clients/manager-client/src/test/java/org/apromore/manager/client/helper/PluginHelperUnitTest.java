package org.apromore.manager.client.helper;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.activation.DataHandler;
import javax.mail.util.ByteArrayDataSource;

import org.apromore.helper.PluginHelper;
import org.apromore.model.PluginMessages;
import org.apromore.model.PluginParameter;
import org.apromore.model.PluginParameters;
import org.apromore.plugin.Plugin;
import org.apromore.plugin.message.PluginMessage;
import org.apromore.plugin.property.PluginParameterType;
import org.apromore.plugin.property.RequestParameterType;
import org.junit.Test;

public class PluginHelperUnitTest {

    @Test
    public void testConvertToRequestProperties() throws IOException {
        PluginParameters xmlProperties = new PluginParameters();

        PluginParameter prop1 = new PluginParameter();
        prop1.setClazz(Integer.class.getName());
        prop1.setId("test");
        prop1.setValue(new Integer(2));

        PluginParameter prop2 = new PluginParameter();
        prop2.setClazz(InputStream.class.getName());
        prop2.setId("test2");
        byte[] byteInput = new byte[20];

        prop2.setValue(new DataHandler(new ByteArrayDataSource(new ByteArrayInputStream(byteInput), "application/octet-stream")));
        xmlProperties.getParameter().add(prop1);
        xmlProperties.getParameter().add(prop2);

        Set<RequestParameterType<?>> requestProperties = PluginHelper.convertToRequestParameters(xmlProperties);
        assertNotNull(requestProperties);
        assertTrue(requestProperties.size() == 2);

        RequestParameterType<?> rProp1 = getPropertyById("test", requestProperties);
        RequestParameterType<?> rProp2 = getPropertyById("test2", requestProperties);

        assertNotNull(rProp1);
        assertEquals(new Integer(2), rProp1.getValue());
        assertNotNull(rProp2);
        assertTrue(rProp2.getValue() instanceof InputStream);
    }

    private static RequestParameterType<?> getPropertyById(final String id, final Set<RequestParameterType<?>> requestProperties) {
        for (RequestParameterType<?> prop: requestProperties) {
            if (id.equals(prop.getId())) {
                return prop;
            }
        }
        fail("Property not found "+id);
        return null;
    }

    @Test
    public void testConvertToRequestProperty() {
        PluginParameter xmlProp = new PluginParameter();
        assertNull(PluginHelper.convertToRequestParameter(xmlProp));
        xmlProp.setClazz(String.class.getCanonicalName());
        assertNull(PluginHelper.convertToRequestParameter(xmlProp));

        PluginParameter xmlProp2 = new PluginParameter();
        xmlProp2.setId("test");
        assertNull(PluginHelper.convertToRequestParameter(xmlProp2));

        PluginParameter xmlProp3 = new PluginParameter();
        xmlProp3.setId("test");
        xmlProp3.setClazz(String.class.getCanonicalName());
        xmlProp3.setValue("test");
        assertNotNull(PluginHelper.convertToRequestParameter(xmlProp3));
    }

    @Test
    public void testConvertFromPluginProperties() {
        HashSet<PluginParameterType<?>> hashSet = new HashSet<PluginParameterType<?>>();
        hashSet.add(new PluginParameterType<InputStream>("test", "testName", InputStream.class, "testDescr", true));
        hashSet.add(new PluginParameterType<String>("test2", "testName", String.class, "testDescr", false));
        PluginParameters pluginProperties = PluginHelper.convertFromPluginParameters(hashSet);
        assertNotNull(pluginProperties);

        assertEquals(2, pluginProperties.getParameter().size());

        PluginParameter prop1 = getPropertyById("test", pluginProperties.getParameter());
        PluginParameter prop2 = getPropertyById("test2", pluginProperties.getParameter());

        assertNotNull(prop1);
        assertEquals(InputStream.class.getName(), prop1.getClazz());
        assertEquals("testName", prop1.getName());
        assertEquals("testDescr", prop1.getDescription());
        assertTrue(prop1.isIsMandatory());
        assertNotNull(prop2);
        assertEquals("testName", prop2.getName());
        assertEquals("testDescr", prop2.getDescription());
        assertFalse(prop2.isIsMandatory());
        assertEquals(String.class.getName(), prop2.getClazz());
    }

    private static PluginParameter getPropertyById(final String id, final List<PluginParameter> propertyList) {
        for (PluginParameter prop: propertyList) {
            if (id.equals(prop.getId())) {
                return prop;
            }
        }
        fail("Property not found "+id);
        return null;
    }

    @Test
    public void testConvertFromPluginMessages() {
        assertNotNull(PluginHelper.convertFromPluginMessages(new ArrayList<PluginMessage>()));
    }

    @Test
    public void testConvertToPluginMessages() {
        assertNotNull(PluginHelper.convertToPluginMessages(new PluginMessages()));
    }

    @Test
    public void testConvertPluginInfo() {
        assertNotNull(PluginHelper.convertPluginInfo(new Plugin() {

            @Override
            public String getVersion() {
                return "";
            }

            @Override
            public String getType() {
                return "";
            }

            @Override
            public String getName() {
                return "";
            }

            @Override
            public String getDescription() {
                return "";
            }

            @Override
            public String getAuthor() {
                return "";
            }

            @Override
            public String getEMail() {
                return "";
            }
        }));
    }

}
