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

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;
import java.io.Serializable;
import java.util.Date;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Stores the history of when stuff happens in apromore.
 *
 * @author <a href="mailto:cam.james@gmail.com">Cameron James</a>
 */
@Entity
@Table(name = "history_event")
@Configurable("history_event")
@Cache(expiry = 180000, size = 100, coordinationType = CacheCoordinationType.INVALIDATE_CHANGED_OBJECTS)
public class HistoryEvent implements Serializable {

    private Integer id;
    private StatusEnum status;
    private HistoryEnum type;
    private Date occurDate;

    private User user;


    /**
     * Default Constructor.
     */
    public HistoryEvent() { }



    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    public Integer getId() {
        return this.id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }


    @Column(name = "type", unique = false, nullable = true, length = 50)
    @Enumerated(EnumType.STRING)
    public HistoryEnum getType() {
        return type;
    }

    public void setType(final HistoryEnum newType) {
        this.type = newType;
    }

    @Column(name = "status", unique = false, nullable = true, length = 50)
    @Enumerated(EnumType.STRING)
    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(final StatusEnum newStatus) {
        this.status = newStatus;
    }

    @Column(name = "occurDate", nullable = true)
    @Temporal(TemporalType.TIMESTAMP)
    public Date getOccurDate() {
        return this.occurDate;
    }

    public void setOccurDate(final Date newOccurDate) {
        this.occurDate = newOccurDate;
    }

    @ManyToOne
    @JoinColumn(name = "userId", nullable = true)
    public User getUser() {
        return this.user;
    }

    public void setUser(final User newUser) {
        this.user = newUser;
    }
}
