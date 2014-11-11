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

package org.apromore.util;

import org.apromore.exception.RepositoryException;
import org.apromore.graph.canonical.CPFEdge;
import org.apromore.graph.canonical.CPFNode;
import org.apromore.service.model.FragmentNode;
import org.jbpt.algo.tree.rpst.IRPSTNode;
import org.jbpt.algo.tree.rpst.RPST;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;

/**
 * Constructs a tree of the Fragments used by the RPST.
 */
public class MutableTreeConstructor {

    private static final Logger LOGGER = LoggerFactory.getLogger(MutableTreeConstructor.class);

    public FragmentNode construct(RPST<CPFEdge, CPFNode> rpst) throws RepositoryException {
        return constructTree(rpst.getRoot(), null, rpst);
    }

    private FragmentNode constructTree(IRPSTNode<CPFEdge, CPFNode> root, FragmentNode parent, RPST<CPFEdge, CPFNode> rpst)
            throws RepositoryException {
        FragmentNode rf;
        if (root != null) {
            rf = new FragmentNode();
            rf.setType(root.getType());

            if (parent != null) {
                parent.getChildren().add(rf);
                rf.setParent(parent);
            }

            for (CPFEdge e : root.getFragment()) {
                CPFEdge newEdge = rf.addEdge(e.getOriginalId(), e.getSource(), e.getTarget());
                newEdge.setId(e.getId());
            }

            rf.setEntry(root.getEntry());
            rf.setExit(root.getExit());

            Set<IRPSTNode<CPFEdge, CPFNode>> cs = rpst.getChildren(root);
            for (IRPSTNode<CPFEdge, CPFNode> c : cs) {
                if (!c.getFragment().isEmpty()) {
                    constructTree(c, rf, rpst);
                }
            }
        } else {
            LOGGER.error("Can not construct tree with a null node.");
            throw new RepositoryException("Can not construct tree with a null node.");
        }

        return rf;
    }

}
