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
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheCoordinationType;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * Stores the process in apromore.
 * @author Cameron James
 */
@Entity
@Table(name = "folder",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"id"}),
                @UniqueConstraint(columnNames = {"folder_name"})
        }
)
@Configurable("folder")
@Cache(expiry = 180000, size = 1000, coordinationType = CacheCoordinationType.INVALIDATE_CHANGED_OBJECTS)
public class Folder implements Serializable {

    private Integer id;
    private String name;
    private String description;
    private Date dateCreated;
    private Date dateModified;

    private User createdBy;
    private User modifiedBy;
    private Workspace workspace;
    private Folder parentFolder;

    private Set<Process> processes = new HashSet<>();
    private Set<Folder> subFolders = new HashSet<>();


    /**
     * Default Constructor.
     */
    public Folder() {
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
     * Get the role name for the Object.
     * @return Returns the role name.
     */
    @Column(name = "folder_name", unique = true, nullable = false, length = 45)
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
    @Column(name = "folder_description")
    public String getDescription() {
        return description;
    }

    /**
     * Set the description for the Object.
     * @param newDescription The folder description to set.
     */
    public void setDescription(final String newDescription) {
        this.description = newDescription;
    }

    /**
     * Get the workspace for the Object.
     * @return Returns the workspace.
     */
    @ManyToOne
    @JoinColumn(name = "workspaceId")
    public Workspace getWorkspace() {
        return this.workspace;
    }

    /**
     * Set the workspace for the Object.
     * @param newWorkspace The workspace to set.
     */
    public void setWorkspace(final Workspace newWorkspace) {
        this.workspace = newWorkspace;
    }

    /**
     * Get the created by for the Object.
     * @return Returns the createdBy.
     */
    @ManyToOne
    @JoinColumn(name = "creatorId")
    public User getCreatedBy() {
        return this.createdBy;
    }

    /**
     * Set the created by for the Object.
     * @param newCreatedBy The created by to set.
     */
    public void setCreatedBy(final User newCreatedBy) {
        this.createdBy = newCreatedBy;
    }

    /**
     * Get the modified by for the Object.
     * @return Returns the modified by.
     */
    @ManyToOne
    @JoinColumn(name = "modifiedById")
    public User getModifiedBy() {
        return this.modifiedBy;
    }

    /**
     * Set the modified by for the Object.
     * @param newModifiedBy The modified by to set.
     */
    public void setModifiedBy(final User newModifiedBy) {
        this.modifiedBy = newModifiedBy;
    }

    /**
     * Get the date created for the Object.
     * @return Returns the date created.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "date_created")
    public Date getDateCreated() {
        return dateCreated;
    }

    /**
     * Set the date created for the Object.
     * @param newDateCreated The date created to set.
     */
    public void setDateCreated(final Date newDateCreated) {
        this.dateCreated = newDateCreated;
    }

    /**
     * Get the date modified for the Object.
     * @return Returns the date modified.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "date_modified")
    public Date getDateModified() {
        return dateModified;
    }

    /**
     * Set the date modified for the Object.
     * @param newDateModified The date modified to set.
     */
    public void setDateModified(final Date newDateModified) {
        this.dateModified = newDateModified;
    }

    /**
     * Get the parent folder for the Object.
     * @return Returns the parent folder.
     */
    @ManyToOne
    @JoinColumn(name = "parentId")
    public Folder getParentFolder() {
        return this.parentFolder;
    }

    /**
     * Set the parent folder for the Object.
     * @param newParentFolder The parent folder to set.
     */
    public void setParentFolder(final Folder newParentFolder) {
        this.parentFolder = newParentFolder;
    }

    @OneToMany(mappedBy = "folder", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Process> getProcesses() {
        return this.processes;
    }

    public void setProcesses(Set<Process> processes) {
        this.processes = processes;
    }

    @OneToMany(mappedBy = "parentFolder", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Folder> getSubFolders() {
        return this.subFolders;
    }

    public void setSubFolders(Set<Folder> newSubFolders) {
        this.subFolders = newSubFolders;
    }

}
