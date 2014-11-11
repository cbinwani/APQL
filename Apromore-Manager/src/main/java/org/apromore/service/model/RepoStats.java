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

package org.apromore.service.model;

/**
 * @author Chathura Ekanayake
 */
public class RepoStats {

    private int totalVertices;
    private int totalEdges;
    private int storedVertices;
    private int storedEdges;


    public int getTotalVertices() {
        return totalVertices;
    }

    public void setTotalVertices(int totalVertices) {
        this.totalVertices = totalVertices;
    }

    public int getTotalEdges() {
        return totalEdges;
    }

    public void setTotalEdges(int totalEdges) {
        this.totalEdges = totalEdges;
    }

    public int getStoredVertices() {
        return storedVertices;
    }

    public void setStoredVertices(int storedVertices) {
        this.storedVertices = storedVertices;
    }

    public int getStoredEdges() {
        return storedEdges;
    }

    public void setStoredEdges(int storedEdges) {
        this.storedEdges = storedEdges;
    }
}
