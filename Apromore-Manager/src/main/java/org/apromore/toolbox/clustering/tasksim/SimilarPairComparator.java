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

package org.apromore.toolbox.clustering.tasksim;

import java.util.Comparator;

public class SimilarPairComparator implements Comparator<SimilarPair> {

	@Override
	public int compare(SimilarPair o1, SimilarPair o2) {
		if (o1.getSim() > o2.getSim()) {
			return 1;
		} else if (o1.getSim() < o2.getSim()) {
			return -1;
		} else {
			return 0;
		}
	}
}
