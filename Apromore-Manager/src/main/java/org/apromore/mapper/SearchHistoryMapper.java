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

import java.util.ArrayList;
import java.util.List;

import org.apromore.dao.model.SearchHistory;
import org.apromore.model.SearchHistoriesType;

/**
 * Mapper helper class to convert from the DAO Model to the Webservice Model.
 *
 * @author <a href="mailto:cam.james@gmail.com">Cameron James</a>
 * @since 1.0
 */
public class SearchHistoryMapper {

    /**
     * Convert from the WS (SearchHistoriesType) to the DB model (SearchHistory).
     *
     * @param srhTypes the list of SearchHistoriesType from the WebService
     * @return the set of SearchHistory dao model populated.
     */
    public static List<SearchHistory> convertFromSearchHistoriesType(List<SearchHistoriesType> srhTypes) {
        List<SearchHistory> searches = new ArrayList<>();
        for (SearchHistoriesType srhType : srhTypes) {
            SearchHistory sh = new SearchHistory();
            sh.setIndex(srhType.getNum());
            sh.setSearch(srhType.getSearch());
            searches.add(sh);
        }
        return searches;
    }

}
