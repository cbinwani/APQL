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

package org.apromore.dao.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheCoordinationType;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * Stores the process in apromore.
 * @author Cameron James
 */
@Entity
@Table(name = "permission",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"id"}),
                @UniqueConstraint(columnNames = {"permission_name"}),
                @UniqueConstraint(columnNames = {"row_guid"})
        }
)
@Configurable("user")
@Cache(expiry = 180000, size = 1000, coordinationType = CacheCoordinationType.INVALIDATE_CHANGED_OBJECTS)
public class Permission implements Serializable {

    private Integer id;
    private String rowGuid = UUID.randomUUID().toString();
    private String name;
    private String description;

    private Set<Role> roles = new HashSet<>();


    /**
     * Default Constructor.
     */
    public Permission() {
    }

    /**
     * Get the Primary Key for the Object.
     * @return Returns the Id.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return id;
    }

    /**
     * Set the id for the Object.
     * @param newId The role name to set.
     */
    public void setId(final Integer newId) {
        this.id = newId;
    }

    /**
     * Get the row unique identifier for the Object.
     * @return Returns the row unique identifier.
     */
    @Column(name = "row_guid", unique = true, nullable = false, length = 256)
    public String getRowGuid() {
        return rowGuid;
    }

    /**
     * Set the row unique identifier for the Object.
     * @param newRowGuid The row unique identifier to set.
     */
    public void setRowGuid(final String newRowGuid) {
        this.rowGuid = newRowGuid;
    }

    /**
     * Get the role name for the Object.
     * @return Returns the role name.
     */
    @Column(name = "permission_name")
    public String getName() {
        return name;
    }

    /**
     * Set the role name for the Object.
     * @param newName The role name to set.
     */
    public void setName(final String newName) {
        this.name = newName;
    }

    /**
     * Get the description for the Object.
     * @return Returns the description description.
     */
    @Column(name = "permission_description")
    public String getDescription() {
        return description;
    }

    /**
     * Set the description for the Object.
     * @param newDescription The permission description to set.
     */
    public void setDescription(final String newDescription) {
        this.description = newDescription;
    }

    /**
     * Getter for the roles collection.
     * @return Returns the roles.
     */
    @ManyToMany(mappedBy = "permissions", cascade = {CascadeType.ALL, CascadeType.ALL})
    public Set<Role> getRoles() {
        return roles;
    }

    /**
     * Setter for the roles Collection.
     * @param newRoles The roles to set.
     */
    public void setRoles(final Set<Role> newRoles) {
        this.roles = newRoles;
    }
}
