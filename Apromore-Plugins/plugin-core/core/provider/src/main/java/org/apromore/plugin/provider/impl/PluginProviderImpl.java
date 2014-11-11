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
package org.apromore.plugin.provider.impl;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import javax.annotation.Resource;

import org.apromore.plugin.Plugin;
import org.apromore.plugin.exception.PluginNotFoundException;
import org.apromore.plugin.provider.PluginProvider;
import org.apromore.plugin.provider.PluginProviderHelper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Default OSGi based implementation of the {@link PluginProvider}
 *
 * @author Felix Mannhardt (Bonn-Rhein-Sieg University oAS)
 */
@Component
public class PluginProviderImpl implements PluginProvider {

    /**
     * Will be injected by Eclipse Blueprint OSGi Framework at runtime
     */
	@Resource
    private Set<Plugin> pluginSet;

    // Getter and Setter need to be public for DI
    public Set<Plugin> getPluginSet() {
        return pluginSet;
    }

    public void setPluginList(final Set<Plugin> pluginSet) {
        this.pluginSet = pluginSet;
    }

    /*
     * (non-Javadoc)
     *
     * @see org.apromore.plugin.provider.PluginProvider#listAll()
     */
    @Override
    public Set<Plugin> listAll() {
        return Collections.unmodifiableSet(getPluginSet());
    }

    /*
     * (non-Javadoc)
     *
     * @see org.apromore.plugin.provider.PluginProvider#listByType(java.lang.String)
     */
    @Override
    public Set<Plugin> listByType(final String type) {
        return Collections.unmodifiableSet(findAllPlugin(null, type, null));
    }

    /*
     * (non-Javadoc)
     *
     * @see org.apromore.plugin.provider.PluginProvider#listByName(java.lang.String)
     */
    @Override
    public Set<Plugin> listByName(final String name) {
        return Collections.unmodifiableSet(findAllPlugin(name, null, null));
    }

    /* (non-Javadoc)
     * @see org.apromore.plugin.provider.PluginProvider#findByName(java.lang.String)
     */
    @Override
    public Plugin findByName(final String name) throws PluginNotFoundException {
        return findByNameAndVersion(name, null);
    }

    /*
     * (non-Javadoc)
     *
     * @see org.apromore.plugin.provider.PluginProvider#findByNameAndVersion(java .lang.String, java.lang.String)
     */
    @Override
    public Plugin findByNameAndVersion(final String name, final String version) throws PluginNotFoundException {
        final Set<Plugin> resultList = findAllPlugin(name, null, version);
        Iterator<Plugin> iterator = resultList.iterator();
        if (iterator.hasNext()) {
            // Return first one found
            return iterator.next();
        }
        throw new PluginNotFoundException("Could not find plugin with name: " + ((name != null) ? name : "null") + " version: "
                + ((version != null) ? version : "null"));
    }

    private Set<Plugin> findAllPlugin(final String name, final String type, final String version) {

        final Set<Plugin> resultList = new HashSet<Plugin>();

        for (final Plugin c : getPluginSet()) {
            if (PluginProviderHelper.compareNullable(type, c.getType()) && PluginProviderHelper.compareNullable(name, c.getName())
                    && PluginProviderHelper.compareNullable(version, c.getVersion())) {
                resultList.add(c);
            }
        }

        return resultList;
    }

}
