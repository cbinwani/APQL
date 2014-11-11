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

package org.apromore.portal.dialogController.similarityclusters.renderer;

import java.text.NumberFormat;

import org.apromore.model.ClusterSummaryType;
import org.apromore.portal.common.Constants;
import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.ListitemRenderer;

/**
 * Responsible for rendering one row of the cluster result listbox defined in 'similarityClustersListbox.zul'.
 *
 * @author <a href="mailto:felix.mannhardt@smail.wir.h-brs.de">Felix Mannhardt</a>
 */
public class SimilarityClustersItemRenderer implements ListitemRenderer {

    /* (non-Javadoc)
      * @see org.zkoss.zul.ListitemRenderer#render(org.zkoss.zul.Listitem, java.lang.Object, int)
      */
    @Override
    public void render(Listitem listItem, Object obj, int index) {
        renderSimilarityCluster(listItem, (ClusterSummaryType) obj);
    }

    private void renderSimilarityCluster(Listitem listItem, final ClusterSummaryType obj) {
        listItem.appendChild(renderClusterImage());
        listItem.appendChild(renderClusterName(obj));
        listItem.appendChild(renderClusterId(obj));
        listItem.appendChild(renderClusterSize(obj));
        listItem.appendChild(renderAvgFragmentSize(obj));
        listItem.appendChild(renderRefactoringGain(obj));
        listItem.appendChild(renderStandardizationEffort(obj));
    }

    private Listcell renderClusterImage() {
        Listcell lc = new Listcell();
        lc.appendChild(new Image(Constants.CLUSTER_ICON));
        return lc;
    }

    private Listcell renderClusterId(final ClusterSummaryType obj) {
        Listcell lc = new Listcell();
        lc.appendChild(new Label(obj.getClusterId().toString()));
        return lc;
    }

    private Listcell renderClusterName(final ClusterSummaryType obj) {
        Listcell lc = new Listcell();
        lc.appendChild(new Label(obj.getClusterLabel()));
        return lc;
    }

    private Listcell renderClusterSize(final ClusterSummaryType obj) {
        Listcell lc = new Listcell();
        lc.appendChild(new Label(String.valueOf(obj.getClusterSize())));
        return lc;
    }

    private Listcell renderAvgFragmentSize(final ClusterSummaryType obj) {
        NumberFormat numberInstance = NumberFormat.getNumberInstance();
        numberInstance.setMaximumFractionDigits(2);
        String fragmentSize = numberInstance.format(obj.getAvgFragmentSize());
        Listcell lc = new Listcell();
        lc.appendChild(new Label(fragmentSize));
        return lc;
    }

    private Listcell renderRefactoringGain(final ClusterSummaryType obj) {
        Listcell lc = new Listcell();
        lc.appendChild(new Label(String.valueOf(obj.getRefactoringGain())));
        return lc;
    }

    private Listcell renderStandardizationEffort(final ClusterSummaryType obj) {
        NumberFormat numberInstance = NumberFormat.getNumberInstance();
        numberInstance.setMaximumFractionDigits(2);
        String standardizationEffort = numberInstance.format(obj.getStandardizationEffort());
        Listcell lc = new Listcell();
        lc.appendChild(new Label(standardizationEffort));
        return lc;
    }

}
