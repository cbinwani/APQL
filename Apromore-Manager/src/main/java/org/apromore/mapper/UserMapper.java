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

package org.apromore.mapper;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apromore.dao.model.Membership;
import org.apromore.dao.model.Permission;
import org.apromore.dao.model.Role;
import org.apromore.dao.model.User;
import org.apromore.model.*;
import org.apromore.security.util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Mapper helper class to convert from the DAO Model to the Webservice Model.
 *
 * @author <a href="mailto:cam.james@gmail.com">Cameron James</a>
 * @since 1.0
 */
public class UserMapper {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserMapper.class.getName());

    /**
     * Convert the List of Users to a UserNamesType Webservice object.
     * @param users the list of Users
     * @return the UsernameType object
     */
    public static UsernamesType convertUsernameTypes(List<User> users) {
        UsernamesType userNames = new UsernamesType();
        for (User usr : users) {
            userNames.getUsername().add(usr.getUsername());
        }
        return userNames;
    }

    /**
     * Convert a user object to a UserType Webservice object.
     * @param user the DB User Model
     * @return the Webservice UserType
     */
    public static UserType convertUserTypes(User user) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        
        UserType userType = new UserType();
        userType.setId(user.getRowGuid());
        userType.setLastName(user.getLastName());
        userType.setFirstName(user.getFirstName());
        userType.setUsername(user.getUsername());
        if (user.getLastActivityDate() != null) {
            userType.setLastActivityDate(formatter.format(user.getLastActivityDate()));
        }

        for (Role role : user.getRoles()) {
            RoleType newRole = new RoleType();
            newRole.setId(role.getRowGuid());
            newRole.setName(role.getName());            
            userType.getRoles().add(newRole);

            for (Permission permission : role.getPermissions()) {
                PermissionType permissionType = new PermissionType();
                permissionType.setId(permission.getRowGuid());
                permissionType.setName(permission.getName());
                
                if (!userType.getPermissions().contains(permissionType)){
                    userType.getPermissions().add(permissionType);
                }
            }
        }

        Membership membership = user.getMembership();
        if (membership != null){
            MembershipType membershipType = new MembershipType();
            membershipType.setEmail(membership.getEmail());
            membershipType.setApproved(membership.getIsApproved());
            membershipType.setLocked(membership.getIsLocked());
            membershipType.setFailedLogins(membership.getFailedPasswordAttempts());
            membershipType.setFailedAnswers(membership.getFailedAnswerAttempts());
            userType.setMembership(membershipType);
        }
        
        return userType;
    }
    
    

    /**
     * Convert from the WS (UserType) to the DB model (User).
     * @param userType the userType from the WebService
     * @return the User dao model populated.
     */
    public static User convertFromUserType(UserType userType) {
        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        Date date = null;
        if (userType.getLastActivityDate() != null && !userType.getLastActivityDate().equals("")) {
            try {
                date = formatter.parse(userType.getLastActivityDate());
            } catch (ParseException ex) {
                LOGGER.error("Last Activity Date: " + userType.getLastActivityDate() + " could not be parsed.");
            }
        }

        User user = new User();
        user.setLastName(userType.getLastName());
        user.setFirstName(userType.getFirstName());
        user.setUsername(userType.getUsername());
        user.setRowGuid(userType.getId());
        if (date != null){
            user.setLastActivityDate(date);
        }
        if (user.getSearchHistories() != null) {
            user.setSearchHistories(SearchHistoryMapper.convertFromSearchHistoriesType(userType.getSearchHistories()));
        }
        
        Membership membership = new Membership();
        if (userType.getMembership() != null) {
            membership.setSalt("username");
            membership.setDateCreated(new Date());
            membership.setEmail(userType.getMembership().getEmail());
            if (userType.getMembership().getPassword() != null) {
                membership.setPassword(SecurityUtil.hashPassword(userType.getMembership().getPassword()));
            }
            membership.setQuestion(userType.getMembership().getPasswordQuestion());
            membership.setAnswer(userType.getMembership().getPasswordAnswer());
            membership.setFailedPasswordAttempts(0);
            membership.setFailedAnswerAttempts(0);
            membership.setUser(user);

            user.setMembership(membership);
        }

        for (RoleType roleType : userType.getRoles()){
            Role role = new Role();
            role.setRowGuid(roleType.getId());
            role.setName(roleType.getName());
            user.getRoles().add(role);
        }

        return user;
    }

}
