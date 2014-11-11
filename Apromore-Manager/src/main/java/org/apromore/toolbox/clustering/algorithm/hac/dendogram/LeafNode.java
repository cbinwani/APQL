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

package org.apromore.toolbox.clustering.algorithm.hac.dendogram;

import java.util.LinkedList;
import java.util.List;

public class LeafNode implements Node {

    private Integer index;
    private Integer value;

    protected List<Integer> children;

    public LeafNode(int index) {
        this.index = index;
        this.children = new LinkedList<Integer>();
        this.value = null;
    }

    public LeafNode(int index, Integer value) {
        this.index = index;
        this.children = new LinkedList<Integer>();
        this.children.add(value);
        this.value = value;
    }

    public int getIndex() {
        return index;
    }

    public Node getFirst() {
        return null;
    }

    public Node getSecond() {
        return null;
    }

    public String toString() {
        return String.format("Leaf (%s): %s", value, children);
    }

    public List<Integer> getChildren() {
        return children;
    }
}
