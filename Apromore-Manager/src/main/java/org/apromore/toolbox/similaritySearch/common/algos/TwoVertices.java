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

package org.apromore.toolbox.similaritySearch.common.algos;

public class TwoVertices implements Comparable<TwoVertices> {
    public String v1;
    public String v2;
    public double weight;

    public TwoVertices(String v1, String v2, double weight) {
        this.v1 = v1;
        this.v2 = v2;
        this.weight = weight;
    }

    public String toString() {
        return "(" + v1 + "," + v2 + "," + weight + ")";
    }

    public boolean equals(Object pair2) {
        return pair2 instanceof TwoVertices ? (v1.equals(((TwoVertices) pair2).v1) && v2.equals(((TwoVertices) pair2).v2)) : false;
    }

    public int hashCode(){
        return v1.hashCode() + v2.hashCode();
    }

    public int compareTo(TwoVertices o) {
        return equals(o) ? 0 : 1;
    }
}