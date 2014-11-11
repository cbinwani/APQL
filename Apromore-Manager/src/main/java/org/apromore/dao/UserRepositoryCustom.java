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

package org.apromore.dao;

import org.apromore.dao.model.User;

/**
 * Interface domain model Data access object Workspace.
 *
 * @see org.apromore.dao.model.Workspace
 * @author <a href="mailto:cam.james@gmail.com">Igor Goldobin</a>
 * @version 1.0
 */
public interface UserRepositoryCustom {

    /* ************************** JPA Methods here ******************************* */

    /**
     * Attempts to find the User and check their login details.
     * @param username the username.
     * @param password the password.
     * @return The user if found with a correct password or null.
     */
    User login(final String username, final String password);

    /**
     * Attempts to find the User with the passed in email.
     * @param email the email of the user.
     * @return The user if found with a correct password or null.
     */
    User findUserByEmail(final String email);

    /**
     * Check if user has specific permission.
     * @param userId, the id of the user we are searching for.
     * @param permissionId the id of the permission we are searching for.
     * @return true ro false.
     */
    boolean hasAccess(String userId, String permissionId);



    /* ************************** JDBC Template / native SQL Queries ******************************* */

}
