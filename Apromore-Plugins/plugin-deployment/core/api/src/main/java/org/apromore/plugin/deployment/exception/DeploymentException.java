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
package org.apromore.plugin.deployment.exception;

import org.apromore.plugin.exception.PluginException;

/**
 * Thrown in case of an error during process deployment
 *
 * @author <a href="mailto:felix.mannhardt@smail.wir.h-brs.de">Felix Mannhardt</a>
 *
 */
public class DeploymentException extends PluginException {

    private static final long serialVersionUID = 8233843735433262065L;

    public DeploymentException() {
        super();
    }

    public DeploymentException(final String message, final Throwable cause, final boolean enableSuppression, final boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public DeploymentException(final String message, final Throwable cause) {
        super(message, cause);
    }

    public DeploymentException(final String message) {
        super(message);
    }

    public DeploymentException(final Throwable cause) {
        super(cause);
    }


}
