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

import org.apromore.toolbox.clustering.dissimilarity.DissimilarityCalc;
import org.apromore.toolbox.clustering.dissimilarity.algorithm.SimpleGEDDeterministicGreedy;
import org.apromore.toolbox.clustering.dissimilarity.model.SimpleGraph;

public class SimpleGEDDeterministicGreedyCalc implements DissimilarityCalc {

    private double threshold;

    static double ledcutoff = 0.5;

    static double usepuredistance = 0.0;
    static double prunewhen = 100.0;
    static double pruneto = 10.0;
    static double useepsilon = 0.0;

    static double vweight = 1.0;
    static double sweight = 1.0;
    static double eweight = 1.0;

    static SimpleGEDDeterministicGreedy gedepc = new SimpleGEDDeterministicGreedy();

    public SimpleGEDDeterministicGreedyCalc(double threshold, double ledc) {
        ledcutoff = ledc;
        Object weights[] = {"vweight", vweight, "sweight", sweight, "eweight", eweight, "ledcutoff", ledcutoff, "usepuredistance", usepuredistance, "prunewhen", prunewhen, "pruneto", pruneto, "useepsilon", useepsilon};
        gedepc.setWeight(weights);

        this.threshold = threshold;
    }

    @Override
    public String getName() {
        return "SimpleGEDDeterministicGreedyCalc";
    }

    @Override
    public double compute(SimpleGraph graph1, SimpleGraph graph2) {
        gedepc.resetDeterminismFlag();
        return gedepc.compute(graph1, graph2);
    }

    @Override
    public boolean isAboveThreshold(double disim) {
        return disim > threshold;
    }

    public boolean isDeterministicGED() {
        return gedepc.isDeterministic();
    }

}
