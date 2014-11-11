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

package org.apromore.toolbox.similaritySearch.tools;

import org.apromore.cpf.CanonicalProcessType;
import org.apromore.toolbox.similaritySearch.algorithms.FindModelSimilarity;
import org.apromore.toolbox.similaritySearch.common.CPFModelParser;
import org.apromore.toolbox.similaritySearch.common.IdGeneratorHelper;
import org.apromore.toolbox.similaritySearch.graph.Graph;

public class SearchForSimilarProcesses {

    /**
     * Finds the Processes Similarity.
     * @param search    the Canonical Process Type
     * @param d         The Canonical Process Type
     * @param algorithm the search Algorithm
     * @param param     the search parameters
     * @return the similarity between processes
     */
    public static double findProcessesSimilarity(CanonicalProcessType search, CanonicalProcessType d, String algorithm, double... param) {
        if (search.getNet().size() == 0 || d.getNet().size() == 0) {
            return 0;
        }

        Graph searchGraph = CPFModelParser.readModel(search);
        searchGraph.setIdGenerator(new IdGeneratorHelper());
        searchGraph.removeEmptyNodes();

        double similarity = 0;
        for (Graph dbGraph : CPFModelParser.readModels(d)) {
            dbGraph.setIdGenerator(new IdGeneratorHelper());
            dbGraph.removeEmptyNodes();

            double netSimilarity = FindModelSimilarity.findProcessSimilarity(searchGraph, dbGraph, algorithm, param);
            if (netSimilarity > similarity) {
                similarity = netSimilarity;
            }
        }
        return similarity;
    }

}
