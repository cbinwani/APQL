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

package org.apromore.portal.util;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Helps with debugging and seeing the data travel between services.
 */
public class StreamUtil {

    /**
     * Convert a InputStream to a String
     */
    public static String convertStreamToString(InputStream is) {
        return convert(is);
    }

    /**
     * Convert a DataHandler to a String
     */
    public static String convertStreamToString(DataHandler dh) {
        try {
            return convert(dh.getInputStream());
        } catch (IOException e) {
            return "error in readin the DataHandler: " + e.toString();
        }
    }

    /**
     * Convert a DataHandler to a String
     */
    public static String convertStreamToString(DataSource ds) {
        try {
            return convert(ds.getInputStream());
        } catch (IOException e) {
            return "error in readin the DataSource: " + e.toString();
        }
    }


    /* Does the work. */
    private static String convert(InputStream is) {
        try {
            if (is != null) {
                StringBuilder sb = new StringBuilder();
                try {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
                    String line;

                        while ((line = reader.readLine()) != null)
                            sb.append(line).append("\n");
                } finally {
                    is.close();
                }
                return sb.toString();
            }
        } catch (IOException e) {
            return "error in readin the input streams: " + e.toString();
        }
        return "";
    }
}
