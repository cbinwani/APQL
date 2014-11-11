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

import org.apromore.graph.canonical.Canonical;
import org.apromore.util.IDGenerator;
import org.jbpt.algo.tree.tctree.TCType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

public class FragmentNode extends Canonical {

    private String fragmentId;
    private TCType type = null;
    private Collection<FragmentNode> children = new ArrayList<>();
    private FragmentNode parent = null;
    private String fragmentCode;

    public FragmentNode() {
        super();
        this.setUri(UUID.randomUUID().toString());
        fragmentId = "F" + IDGenerator.generateID();
    }

    public String getFragmentId() {
        return fragmentId;
    }

    public void setFragmentId(String fragmentId) {
        this.fragmentId = fragmentId;
    }

    public String getFragmentCode() {
        return fragmentCode;
    }

    public void setFragmentCode(String fragmentCode) {
        this.fragmentCode = fragmentCode;
    }

    public TCType getType() {
        return type;
    }

    public void setType(TCType type) {
        this.type = type;
    }

    public Collection<FragmentNode> getChildren() {
        return children;
    }

    public void setChildren(Collection<FragmentNode> children) {
        this.children = children;
    }

    public FragmentNode getParent() {
        return parent;
    }

    public void setParent(FragmentNode parent) {
        this.parent = parent;
    }

}
