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

import static javax.persistence.GenerationType.IDENTITY;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.eclipse.persistence.annotations.Cache;
import org.eclipse.persistence.annotations.CacheCoordinationType;
import org.springframework.beans.factory.annotation.Configurable;

/**
 * Stores the process in apromore.
 * @author <a href="mailto:cam.james@gmail.com">Cameron James</a>
 */
@Entity
@Table(name = "process",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"id"}),
                @UniqueConstraint(columnNames = {"name", "folderId"})
        }
)
@Configurable("process")
@Cache(expiry = 180000, size = 5000, coordinationType = CacheCoordinationType.INVALIDATE_CHANGED_OBJECTS)
public class Process implements Serializable {

    private Integer id;
    private String name;
    private String domain;
    private String ranking;
    private String createDate;
    private boolean publicModel;

    private User user;
    private Folder folder;
    private NativeType nativeType;

    private Set<GroupProcess> groupProcesses = new HashSet<>();
    private List<ProcessBranch> processBranches = new ArrayList<>();


    /**
     * Default constructor.
     */
    public Process() {
        super();
    }

    public Process(Integer processId) {
        id = processId;
    }


    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }



    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(final String newName) {
        this.name = newName;
    }

    @Column(name = "domain")
    public String getDomain() {
        return domain;
    }

    public void setDomain(final String newDomain) {
        this.domain = newDomain;
    }

    @Column(name = "ranking", length = 10)
    public String getRanking() {
        return this.ranking;
    }

    public void setRanking(final String newRanking) {
        this.ranking = newRanking;
    }

    @Column(name = "public_model")
    public boolean getPublicModel() {
        return this.publicModel;
    }

    public void setPublicModel(final boolean newPublicModel) {
        this.publicModel = newPublicModel;
    }

    @Column(name = "createDate")
    public String getCreateDate() {
        return this.createDate;
    }

    public void setCreateDate(final String newCreationDate) {
        this.createDate = newCreationDate;
    }


    @ManyToOne
    @JoinColumn(name = "folderId")
    public Folder getFolder() {
        return this.folder;
    }

    public void setFolder(final Folder newFolder) {
        this.folder = newFolder;
    }

    @ManyToOne
    @JoinColumn(name = "nativeTypeId")
    public NativeType getNativeType() {
        return this.nativeType;
    }

    public void setNativeType(final NativeType newNativeType) {
        this.nativeType = newNativeType;
    }

    @ManyToOne
    @JoinColumn(name = "owner")
    public User getUser() {
        return this.user;
    }

    public void setUser(final User newUser) {
        this.user = newUser;
    }


    @OneToMany(mappedBy = "process", cascade = CascadeType.ALL, orphanRemoval = true)
    @OrderBy("id")
    public List<ProcessBranch> getProcessBranches() {
        return this.processBranches;
    }

    public void setProcessBranches(final List<ProcessBranch> newProcessBranches) {
        this.processBranches = newProcessBranches;
    }


    @OneToMany(mappedBy = "process", cascade = CascadeType.ALL, orphanRemoval = true)
    public Set<GroupProcess> getGroupProcesses() {
        return this.groupProcesses;
    }

    public void setGroupProcesses(Set<GroupProcess> newGroupProcesses) {
        this.groupProcesses = newGroupProcesses;
    }
}
