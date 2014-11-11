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

package org.apromore.plugin.provider;

import java.util.HashSet;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.SimpleBeanDefinitionRegistry;
import org.springframework.context.annotation.ClassPathBeanDefinitionScanner;
import org.springframework.core.type.filter.AssignableTypeFilter;
import org.springframework.core.type.filter.TypeFilter;

/**
 * Helper class for all Plugin Providers
 *
 * @author <a href="mailto:felix.mannhardt@smail.wir.h-brs.de">Felix Mannhardt (Bonn-Rhein-Sieg University oAS)</a>
 *
 */
public final class PluginProviderHelper {

    private static final Logger LOGGER = LoggerFactory.getLogger(PluginProviderHelper.class);

    private PluginProviderHelper() {
    }

    public static <T> Set<T> findPluginsByClass(final Class<T> clazz, final String namespaceFilter) {
        Set<T> canoniserList = new HashSet<T>();
        Class<?>[] classes = PluginProviderHelper.getAllClassesImplementingInterfaceUsingSpring(clazz, namespaceFilter);
        for (int i = 0; i < classes.length; i++) {
            Class<?> canoniserClass = classes[i];
            try {
                Object canoniser = canoniserClass.newInstance();
                if (clazz.isInstance(canoniser)) {
                    canoniserList.add(clazz.cast(canoniser));
                }
            } catch (InstantiationException | IllegalAccessException e) {
                LOGGER.warn("Could not instantiate "+clazz.getName()+": "+canoniserClass.getName());
            }
        }
        return canoniserList;
    }

    public static Class<?>[] getAllClassesImplementingInterfaceUsingSpring(final Class<?> clazz, final String namespaceFilter) {
        BeanDefinitionRegistry beanRegistry = new SimpleBeanDefinitionRegistry();
        ClassPathBeanDefinitionScanner scanner = new ClassPathBeanDefinitionScanner(beanRegistry, false);

        TypeFilter typeFilter = new AssignableTypeFilter(clazz);
        scanner.addIncludeFilter(typeFilter);
        scanner.setIncludeAnnotationConfig(false);
        scanner.scan(namespaceFilter);
        String[] beans = beanRegistry.getBeanDefinitionNames();
        Class<?>[] classes = new Class<?>[beans.length];
        for (int i = 0; i < beans.length; i ++) {
            BeanDefinition def = beanRegistry.getBeanDefinition(beans[i]);
            try {
                classes[i] = PluginProviderHelper.class.getClassLoader().loadClass(def.getBeanClassName());
            } catch (ClassNotFoundException e) {
                LOGGER.warn("Could not find class: "+beans[i]);
            }
        }
        return classes;
    }

    public static boolean compareNullable(final String expectedType, final String actualType) {
        return expectedType == null ? true : expectedType.equals(actualType);
    }


}
