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

package org.apromore.plugin.property;

import org.apromore.plugin.PluginRequest;

/**
 * Parameter used in a {@link PluginRequest}
 *
 * @author <a href="mailto:felix.mannhardt@smail.wir.h-brs.de">Felix Mannhardt (Bonn-Rhein-Sieg University oAS)</a>
 *
 * @param <T> type of {@link ParameterType}
 */
public class RequestParameterType<T> extends PluginParameterType<T> {

    /**
     * Create a new {@link RequestParameterType} just by ID and set a value.
     *
     * @param id
     *            of the parameter
     * @param value
     *            of the parameter
     */
    public RequestParameterType(final String id, final T value) {
        super(id, null, null, null, value);
    }

}
