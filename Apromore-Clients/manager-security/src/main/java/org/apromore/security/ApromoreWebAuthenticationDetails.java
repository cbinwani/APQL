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

package org.apromore.security;

import javax.servlet.http.HttpServletRequest;

import org.apromore.model.UserType;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetails;

/**
 * Used to store the extra info needed to allow Apromore to work correctly.
 *
 * @author <a href="mailto:cam.james@gmail.com">Cameron James</a>
 */
public class ApromoreWebAuthenticationDetails extends WebAuthenticationDetails {

    private final UserType userDetails;

    /**
     * Records the remote address and will also set the session Id if a session
     * already exists (it won't create one).
     *
     * @param request that the authentication request was received from
     */
    public ApromoreWebAuthenticationDetails(final HttpServletRequest request, final UsernamePasswordAuthenticationToken authRequest) {
        super(request);
        this.userDetails = (UserType) authRequest.getDetails();
    }

    /**
     * Used to get the User type used by Apromore.
     * @return the UserType details
     */
    public UserType getUserDetails() {
        return userDetails;
    }

    /**
     * {@inheritDoc}
     *
     * @return as the superclass, plus the Apromore-specific user details
     */
    @Override
    public String toString() {
        return super.toString() + "; UserDetails=" + userDetails;
    }
}
