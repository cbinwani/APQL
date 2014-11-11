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

package org.apromore.service;

import org.apromore.dao.model.HistoryEvent;
import org.apromore.dao.model.HistoryEnum;
import org.apromore.dao.model.StatusEnum;

/**
 * Interface for the Clustering Service. Defines all the methods that will do the majority of the work for
 * the Apromore application.
 *
 * @author <a href="mailto:cam.james@gmail.com">Cameron James</a>
 */
public interface HistoryEventService {

    /**
     * Adds a new HistoryEvent Auditable to the DB.
     * @param status the status start, end, or error
     * @param historyType the event Type
     * @return the newly created history record.
     */
    HistoryEvent addNewEvent(final StatusEnum status, final HistoryEnum historyType);

    /**
     * Returns the latest HistoryEvent Auditable for a particuler type.
     * @param historyType the type of history.
     * @return the HistoryEvent record or null
     */
    HistoryEvent findLatestHistoryEventType(final HistoryEnum historyType);
}
