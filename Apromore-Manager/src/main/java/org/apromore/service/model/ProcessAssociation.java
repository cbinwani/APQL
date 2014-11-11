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

/**
 *
 */
package org.apromore.service.model;

/**
 * <a href="mailto:chathura.ekanayake@gmail.com">Chathura C. Ekanayake</a>
 */
public class ProcessAssociation {

    private Integer processVersionId;
    private String processVersionNumber;
    private String processBranchName;
    private Integer processId;
    private String processName;


    public Integer getProcessId() {
        return processId;
    }

    public void setProcessId(final Integer newProcessId) {
        this.processId = newProcessId;
    }

    public Integer getProcessVersionId() {
        return processVersionId;
    }

    public void setProcessVersionId(final Integer newProcessVersionId) {
        this.processVersionId = newProcessVersionId;
    }

    public String getProcessVersionNumber() {
        return processVersionNumber;
    }

    public void setProcessVersionNumber(final String newProcessVersionNumber) {
        this.processVersionNumber = newProcessVersionNumber;
    }

    public String getProcessBranchName() {
        return processBranchName;
    }

    public void setProcessBranchName(final String newProcessBranchName) {
        this.processBranchName = newProcessBranchName;
    }

    public String getProcessName() {
        return processName;
    }

    public void setProcessName(final String newProcessName) {
        this.processName = newProcessName;
    }
}
