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

package org.apromore.toolbox.clustering.dissimilarity.measure;

import org.apromore.graph.canonical.Canonical;
import org.apromore.toolbox.clustering.dissimilarity.GEDMatrixCalc;
import org.jbpt.algo.graph.GraphEditDistance;

/**
 * First attempt at using the GED Matrix calc in the jBPT Library.
 *
 * @author <a href="mailto:cam.james@gmail.com">Cameron James</a>
 */
public class jBPTGEDCalc implements GEDMatrixCalc {

    private double threshold;

    public jBPTGEDCalc(double threshold) {
        this.threshold = threshold;
    }

    @Override
    public String getName() {
        return "jBPTGEDCalc";
    }

    @Override
    public double compute(Canonical graph1, Canonical graph2) {
        if (graph1 != null && graph2 != null) {
            return new GraphEditDistance<>(graph1, graph2).getDistance();
        } else {
            return -1d;
        }
    }

    @Override
    public boolean isAboveThreshold(double disim) {
        return disim > threshold;
    }
}
