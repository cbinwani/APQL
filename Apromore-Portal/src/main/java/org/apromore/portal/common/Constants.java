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

package org.apromore.portal.common;

public final class Constants {
    // repository portal details
    public static final String PROPERTY_FILE = "apromore.properties";

    // max number of searches kept for users
    public static final Integer maxSearches = 10;

    public static final String EVENT_QUEUE_REFRESH_SCREEN = "UI_UPDATES";
    public static final String EVENT_MESSAGE_SAVE = "SaveEvent";

    // colors and style used in the table view
    // #E5E5E5 light gray
    // #ACC6E4 blue

    public static final String TOOLBARBUTTON_STYLE = "font-size:12px";
    public static final String FONT_BOLD = "font-weight:bold";
    public static final String FOLDER = "background-color:#FFFFEE";
    public static final String UNSELECTED_VERSION = "background-color:#E5E5E5" + ";" + TOOLBARBUTTON_STYLE;
    public static final String SELECTED_PROCESS = "background-color:#ACC6E4" + ";" + TOOLBARBUTTON_STYLE;

    public static final String NO_ANNOTATIONS = "-- no annotations --";
    public static final String INITIAL_ANNOTATION = "Original";
    public static final String ANNOTATIONS = "Annotations";
    public static final String CANONICAL = "Canonical";

    public static final String RELEASE_NOTES = "http://apromore-build.qut.edu.au/confluence/display/AP/Release+Notes";
    public static final String MORE_INFO = "http://apromore.org/";
    public static final String WEB_DAV = "http://apromore.qut.edu.au/filestore/dav";
    public static final String DEVELOPER_RESOURCES = "http://apromore-build.qut.edu.au/";

    public static final String FOLDER_ICON = "/img/icon/folder-22x22.png";
    public static final String PROCESS_ICON = "/img/icon/bpmn-22x22.png";
    public static final String CLUSTER_ICON = "/img/icon/cluster-22x22.png";

    public static final String STAR_FULL_ICON = "/img/selectAll-12.png";
    public static final String STAR_BLK_ICON = "/img/unselectAll-12.png";
    public static final String STAR_MID_ICON = "/img/revertSelection-12.png";
    public static final String ANNOTATIONS_ONLY = "notationsOnly";

    public static final String INITIAL_VERSION = "1.0";
    public static final String dateFormat = "yyyy/MM/dd hh:mm a";

}
