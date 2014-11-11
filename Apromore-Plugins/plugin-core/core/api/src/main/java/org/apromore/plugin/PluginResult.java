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

import java.util.List;

import org.apromore.plugin.message.PluginMessage;

/**
 * Common result interface for all Plugins. The basic version just contains a way of reading the list of {@link PluginMessage}. Plugin APIs may extend
 * this interface to add their own results. Look at the Canoniser API for an example of extending this interface.
 *
 * @author <a href="mailto:felix.mannhardt@smail.wir.h-brs.de">Felix Mannhardt (Bonn-Rhein-Sieg University oAS)</a>
 *
 */
public interface PluginResult {

    /**
     * Get a list of messages that were added during the operation.
     *
     * @return List of {@link PluginMessage}
     */
    List<PluginMessage> getPluginMessage();

}
