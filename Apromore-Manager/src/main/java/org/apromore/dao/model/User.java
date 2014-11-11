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

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheCoordinationType;
import org.springframework.beans.factory.annotation.Configurable;

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
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;

/**
 * Stores the process in apromore.
 * @author Cameron James
 */
@Entity
@Table(name = "user",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"id"}),
                @UniqueConstraint(columnNames = {"row_guid"}),
                @UniqueConstraint(columnNames = {"username"})
        }
)
@Configurable("user")
@Cache(expiry = 180000, size = 100, coordinationType = CacheCoordinationType.INVALIDATE_CHANGED_OBJECTS)
public class User implements Serializable {

    private Integer id;
    private String rowGuid = UUID.randomUUID().toString();
    private String username;
    private String firstName;
    private String lastName;
    private Date dateCreated;
    private Date lastActivityDate;
    private Group group;

    private Membership membership = new Membership();

    private Set<Group> groups = new HashSet<>();
    private Set<Role> roles = new HashSet<>();
    private Set<Workspace> workspaces = new HashSet<>();
    private Set<Folder> foldersForCreatorId = new HashSet<>();
    private Set<Folder> foldersForModifiedById = new HashSet<>();
    private Set<Process> processes = new HashSet<>();
    private List<SearchHistory> searchHistories = new ArrayList<>();
    private List<HistoryEvent> historyEvents = new ArrayList<>();


    /**
     * Default Constructor.
     */
    public User() {
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
    @Column(name = "row_guid", unique = true)
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
     * Get the username for the Object.
     * @return Returns the username.
     */
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    /**
     * Set the username for the Object.
     * @param newUsername The username to set.
     */
    public void setUsername(final String newUsername) {
        this.username = newUsername;
    }

    /**
     * Get the first name for the Object.
     * @return Returns the first name.
     */
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    /**
     * Set the first name for the Object.
     * @param newFirstName The first name to set.
     */
    public void setFirstName(final String newFirstName) {
        this.firstName = newFirstName;
    }

    /**
     * Get the last name for the Object.
     * @return Returns the last name.
     */
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    /**
     * Set the last name for the Object.
     * @param newLastName The last name to set.
     */
    public void setLastName(final String newLastName) {
        this.lastName = newLastName;
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
     * Get the last activity date for the Object.
     * @return Returns the last activity date.
     */
    @Temporal(TemporalType.DATE)
    @Column(name = "last_activity_date")
    public Date getLastActivityDate() {
        return lastActivityDate;
    }

    /**
     * Set the last activity date for the Object.
     * @param newLastActivityDate The last activity date to set.
     */
    public void setLastActivityDate(final Date newLastActivityDate) {
        this.lastActivityDate = newLastActivityDate;
    }

    /**
     * @return the user's personal access control group
     */
    @ManyToOne(optional = false)
    @JoinColumn(name = "groupId")
    public Group getGroup() {
        return group;
    }

    /**
     * @param the user's personal access control group
     */
    public void setGroup(final Group newGroup) {
        this.group = newGroup;
    }

    /**
     * @return all the access control groups of which this user is a member
     */
    @ManyToMany
    @JoinTable(name = "user_group",
        joinColumns        = @JoinColumn(name = "userId",  referencedColumnName = "id"),
        inverseJoinColumns = @JoinColumn(name = "groupId", referencedColumnName = "id"))
    public Set<Group> getGroups() {
        return groups;
    }

    /**
     * @param newGroups  all the access control groups of which this user should become a member
     */
    public void setGroups(final Set<Group> newGroups) {
        this.groups = newGroups;
    }

    /**
     * Get the membership for the Object.
     * @return Returns the membership.
     */
    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL)
    public Membership getMembership() {
        return this.membership;
    }

    /**
     * Set the membership for the Object.
     * @param newMembership The membership to set.
     */
    public void setMembership(final Membership newMembership) {
        this.membership = newMembership;
    }


    /**
     * Getter for the role collection.
     * @return Returns the roles.
     */
    @ManyToMany(mappedBy = "users")
    public Set<Role> getRoles() {
        return this.roles;
    }

    /**
     * Setter for the role Collection.
     * @param newRoles The roles to set.
     */
    public void setRoles(final Set<Role> newRoles) {
        this.roles = newRoles;
    }

    @OneToMany(mappedBy = "createdBy")
    public Set<Workspace> getWorkspaces() {
        return this.workspaces;
    }

    public void setWorkspaces(Set<Workspace> workspaces) {
        this.workspaces = workspaces;
    }

    @OneToMany(mappedBy = "createdBy")
    public Set<Folder> getFoldersForCreatorId() {
        return this.foldersForCreatorId;
    }

    public void setFoldersForCreatorId(Set<Folder> foldersForCreatorId) {
        this.foldersForCreatorId = foldersForCreatorId;
    }

    @OneToMany(mappedBy = "modifiedBy")
    public Set<Folder> getFoldersForModifiedById() {
        return this.foldersForModifiedById;
    }

    public void setFoldersForModifiedById(Set<Folder> foldersForModifiedById) {
        this.foldersForModifiedById = foldersForModifiedById;
    }

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<Process> getProcesses() {
        return this.processes;
    }

    public void setProcesses(Set<Process> processes) {
        this.processes = processes;
    }

    /**
     * Get the searchHistories for the Object.
     * @return Returns the searchHistories.
     */
    @OneToMany(mappedBy = "user")
    @OrderBy("index ASC")
    public List<SearchHistory> getSearchHistories() {
        return this.searchHistories;
    }

    /**
     * Set the searchHistories for the Object.
     * @param newSearchHistories The searchHistories to set.
     */
    public void setSearchHistories(final List<SearchHistory> newSearchHistories) {
        this.searchHistories = newSearchHistories;
    }

    public void addSearchHistory(SearchHistory newSearchHistory) {
        this.searchHistories.add(newSearchHistory);
    }

    /**
     * Get the historyEvents for the Object.
     * @return Returns the historyEvents.
     */
    @OneToMany(mappedBy = "user")
    public List<HistoryEvent> getHistoryEvents() {
        return this.historyEvents;
    }

    /**
     * Set the historyEvents for the Object.
     * @param newHistoryEvents The historyEvents to set.
     */
    public void setHistoryEvents(final List<HistoryEvent> newHistoryEvents) {
        this.historyEvents = newHistoryEvents;
    }

    public void addHistoryEvent(HistoryEvent newHistoryEvent) {
        this.historyEvents.add(newHistoryEvent);
    }
}
