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

package org.apromore.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * /**
 * Thrown if a <code>ApromoreRemoteAuthenticationManager</code> cannot validate the presented authentication request.
 * <p>
 * This is thrown rather than the normal <code>AuthenticationException</code> because
 * <code>AuthenticationException</code> contains additional properties which may cause issues for
 * the remoting protocol.
 *
 * @author <a href="mailto:cam.james@gmail.com">Cameron James</a>
 */
public class ApromoreRemoteAuthenticationException extends AuthenticationException {

    /**
     * Constructs a <code>ApromoreRemoteAuthenticationException</code> with the
     * specified message and no root cause.
     *
     * @param msg the detail message
     */
    public ApromoreRemoteAuthenticationException(String msg) {
        super(msg);
    }
}
