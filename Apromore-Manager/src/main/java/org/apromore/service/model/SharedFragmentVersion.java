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

/**
 * @author Chathura Ekanayake
 */
public class SharedFragmentVersion {

    private int fragmentVersionid;
    private int numberOfUses;


    /**
     * Defualt Constructor.
     */
    public SharedFragmentVersion() {
    }

    /**
     * Defualt Constructor.
     */
    public SharedFragmentVersion(int versionId, int num) {
        this.fragmentVersionid = versionId;
        this.numberOfUses = num;
    }

    /**
     * Defualt Constructor.
     */
    public SharedFragmentVersion(String versionId, Long num) {
        this.fragmentVersionid = Integer.valueOf(versionId);
        this.numberOfUses = num.intValue();
    }

    public int getFragmentVersionid() {
        return fragmentVersionid;
    }

    public void setFragmentVersionid(int fragmentVersionid) {
        this.fragmentVersionid = fragmentVersionid;
    }

    public int getNumberOfUses() {
        return numberOfUses;
    }

    public void setNumberOfUses(int numberOfUses) {
        this.numberOfUses = numberOfUses;
    }
}
