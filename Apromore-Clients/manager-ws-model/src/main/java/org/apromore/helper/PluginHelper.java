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

package org.apromore.helper;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.activation.DataHandler;

import org.apromore.model.ObjectFactory;
import org.apromore.model.PluginInfo;
import org.apromore.model.PluginMessages;
import org.apromore.model.PluginParameter;
import org.apromore.model.PluginParameters;
import org.apromore.plugin.Plugin;
import org.apromore.plugin.PluginRequest;
import org.apromore.plugin.PluginResult;
import org.apromore.plugin.message.PluginMessage;
import org.apromore.plugin.message.PluginMessageImpl;
import org.apromore.plugin.property.ParameterType;
import org.apromore.plugin.property.RequestParameterType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Helps converting the XML representation of Messages and Properties of {@link PluginResult} and {@link PluginRequest} forth and back.
 *
 * @author <a href="mailto:felix.mannhardt@smail.wir.h-brs.de">Felix Mannhardt (Bonn-Rhein-Sieg University oAS)</a>
 *
 */
public final class PluginHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(PluginHelper.class);

    private static final ObjectFactory OBJECT_FACTORY = new ObjectFactory();

    private PluginHelper() {
        super();
    }

    /**
     * Converts the XML representation of Plugin properties to a Set of RequestPropertyType for usage in Plugins
     *
     * @param xmlProperties from web service
     * @return Set of RequestPropertyType
     */
    public static Set<RequestParameterType<?>> convertToRequestParameters(final PluginParameters xmlProperties) {
        Set<RequestParameterType<?>> properties = new HashSet<RequestParameterType<?>>();
        if (xmlProperties != null) {
            for (PluginParameter xmlProp : xmlProperties.getParameter()) {
                RequestParameterType<?> requestParam = convertToRequestParameter(xmlProp);
                if (requestParam != null) {
                    properties.add(requestParam);
                }
            }
        }
        return properties;
    }

    /**
     * Converts the XML representation of a Plug-in property to a RequestPropertyType for usage in Plug-ins
     *
     * @param xmlProp single property from web service
     * @return null if conversion failed
     */
    public static RequestParameterType<?> convertToRequestParameter(final PluginParameter xmlProp) {
        String clazz = xmlProp.getClazz();
        if (clazz != null && xmlProp.getId() != null && xmlProp.getValue() != null) {
            try {
                // Look if class is valid
                ParameterType.class.getClassLoader().loadClass(clazz);
                return createPluginProperty(xmlProp);
            } catch (ClassNotFoundException e) {
                LOGGER.error("Could not convert request property", e);
            }
        }
        return null;
    }

    private static RequestParameterType<?> createPluginProperty(final PluginParameter xmlProp) {
        Object value = xmlProp.getValue();
        Object convertedValue = null;
        if (value instanceof DataHandler) {
            try {
                convertedValue = ((DataHandler) value).getInputStream();
            } catch (IOException e) {
                LOGGER.error("Could not convert InputStream property", e);
            }
        } else {
            convertedValue = value;
        }
        if (convertedValue != null) {
            return new RequestParameterType<Object>(xmlProp.getId(), convertedValue);
        } else {
            return null;
        }
    }

    /**
     * Converts the PropertyType of a Plugin to XML representation for transport through web service
     *
     * @param pluginProperties from Plugin
     * @return XML representation for use in web service
     */
    public static PluginParameters convertFromPluginParameters(final Set<? extends ParameterType<?>> pluginProperties) {
        PluginParameters xmlProperties = OBJECT_FACTORY.createPluginParameters();
        for (ParameterType<?> p : pluginProperties) {
            PluginParameter xmlProp = OBJECT_FACTORY.createPluginParameter();
            xmlProp.setId(p.getId());
            xmlProp.setName(p.getName());
            xmlProp.setDescription(p.getDescription());
            xmlProp.setIsMandatory(p.isMandatory());
            xmlProp.setClazz(p.getValueType().getCanonicalName());
            if (p.getValue() instanceof InputStream) {
                // Property will be rendered as FileInput
                xmlProp.setValue(null);
            } else {
                xmlProp.setValue(p.getValue());
            }
            xmlProp.setCategory(p.getCategory());
            xmlProperties.getParameter().add(xmlProp);
        }
        return xmlProperties;
    }

    public static PluginMessages convertFromPluginMessages(final List<? extends PluginMessage> messages) {
        PluginMessages xmlMessages = OBJECT_FACTORY.createPluginMessages();
        for (PluginMessage msg : messages) {
            org.apromore.model.PluginMessage xmlMessage = OBJECT_FACTORY.createPluginMessage();
            xmlMessage.setValue(msg.getMessage());
            xmlMessages.getMessage().add(xmlMessage);
        }
        return xmlMessages;
    }

    public static List<PluginMessage> convertToPluginMessages(final PluginMessages message) {
        List<PluginMessage> messageList = new ArrayList<PluginMessage>();
        if (message != null) {
            for (org.apromore.model.PluginMessage msg : message.getMessage()) {
                messageList.add(new PluginMessageImpl(msg.getValue()));
            }
        }
        return messageList;
    }

    public static PluginInfo convertPluginInfo(final Plugin plugin) {
        PluginInfo pluginInfo = new PluginInfo();
        pluginInfo.setName(plugin.getName());
        pluginInfo.setVersion(plugin.getVersion());
        pluginInfo.setDescription(plugin.getDescription());
        pluginInfo.setType(plugin.getType());
        pluginInfo.setAuthor(plugin.getAuthor());
        pluginInfo.setEmail(plugin.getEMail());
        return pluginInfo;
    }

}
