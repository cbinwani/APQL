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
package org.apromore.plugin;

import java.util.Set;

import org.apromore.plugin.property.ParameterType;

/**
 * A {@link ParameterAwarePlugin} defines a Set of parameters that may or must be provided by the caller for proper operation of the {@link Plugin}.
 *
 * @author <a href="mailto:felix.mannhardt@smail.wir.h-brs.de">Felix Mannhardt (Bonn-Rhein-Sieg University oAS)</a>
 *
 */
public interface ParameterAwarePlugin extends Plugin {

    /**
     * Returns the Set of all available parameters for this {@link Plugin}.
     *
     * @return Set of {@link ParameterType} that contains both mandatory and optional parameters.
     */
    Set<ParameterType<?>> getAvailableParameters();

    /**
     * Returns the Set of all required parameters for this {@link Plugin}.
     *
     * @return Set of {@link ParameterType} that are mandatory
     */
    Set<ParameterType<?>> getMandatoryParameters();

    /**
     * Returns the Set of all optional parameters for this {@link Plugin}. That should be the result of {@link #getAvailableParameters()} minus the result of
     * {@link #getMandatoryParameters()}.
     *
     * @return Set of {@link ParameterType} that are optional
     */
    Set<ParameterType<?>> getOptionalParameters();

}
