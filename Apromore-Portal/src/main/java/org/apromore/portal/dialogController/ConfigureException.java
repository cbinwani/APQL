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

package org.apromore.portal.dialogController;

/**
 * A process model could not be configured.
 *
 * This class is guaranteed to have a human-legible detail message suitable for an error dialog.
 */
public class ConfigureException extends Exception {

    /**
     * @param message  detail message
     * @param cause  the throwable which caused configuration to fail
     */
    ConfigureException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * @param message  detail message
     */
    ConfigureException(final String message) {
        super(message);
    }
}
