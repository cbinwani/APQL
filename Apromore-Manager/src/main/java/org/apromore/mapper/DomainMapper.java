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

import org.apromore.model.DomainsType;

import java.util.List;

/**
 * Mapper helper class to convert from the DAO Model to the Webservice Model.
 *
 * @author <a href="mailto:cam.james@gmail.com">Cameron James</a>
 * @since 1.0
 */
public class DomainMapper {

    /**
     * Convert from the a list (String) to the WS model (DomainsType).
     *
     * @param domains the list of SearchHistoriesType from the WebService
     * @return the DomainsType ready for transport to the calling system.
     */
    public static DomainsType convertFromDomains(List<String> domains) {
        DomainsType types = new DomainsType();
        for (String domain : domains) {
            types.getDomain().add(domain);
        }
        return types;
    }

}
