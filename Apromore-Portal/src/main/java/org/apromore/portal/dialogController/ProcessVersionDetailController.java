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

package org.apromore.portal.dialogController;

import java.util.ArrayList;
import java.util.List;

import org.apromore.model.ProcessSummaryType;
import org.apromore.model.VersionSummaryType;
import org.apromore.portal.dialogController.dto.VersionDetailType;
import org.apromore.portal.dialogController.renderer.VersionSummaryItemRenderer;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.South;

public class ProcessVersionDetailController extends BaseDetailController {

    private static final long serialVersionUID = 3661234712204860492L;

    private final Listbox listBox;

    public ProcessVersionDetailController(MainController mainController) {
        super(mainController);

        listBox = ((Listbox) Executions.createComponents("macros/detail/processVersionsDetail.zul", getMainController(), null));
        listBox.setItemRenderer(new VersionSummaryItemRenderer(mainController));
        listBox.setModel(new ListModelList());

        ((South) getMainController().getFellow("leftSouthPanel")).setTitle("Process Details");
        ((South) getMainController().getFellow("leftInnerSouthPanel")).setOpen(false);

        appendChild(listBox);
    }

    @SuppressWarnings("unchecked")
    public void displayProcessVersions(ProcessSummaryType data) {
        getListModel().clearSelection();
        getListModel().clear();
        List<VersionSummaryType> versionSummaries = data.getVersionSummaries();
        List<VersionDetailType> details = new ArrayList<>();
        for (VersionSummaryType version : data.getVersionSummaries()) {
            details.add(new VersionDetailType(data, version));
        }
        getListModel().addAll(details);
        if (versionSummaries.size() > 0) {
            getListModel().addToSelection(versionSummaries.get(versionSummaries.size() - 1));
        }
    }

    protected ListModelList getListModel() {
        return (ListModelList) listBox.getListModel();
    }

    public void clearProcessVersions() {
        getListModel().clear();
    }

    public VersionSummaryType getSelectedVersion() {
        if (getListModel().getSelection().size() == 1) {
            Object obj = getListModel().getSelection().iterator().next();
            if (obj instanceof VersionSummaryType) {
                return (VersionSummaryType) obj;
            } else if (obj instanceof VersionDetailType) {
                return ((VersionDetailType) obj).getVersion();
            }
            return null;
        } else {
            return null;
        }
    }

}
