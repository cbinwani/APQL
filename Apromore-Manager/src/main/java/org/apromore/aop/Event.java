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

package org.apromore.aop;

import org.apromore.dao.model.HistoryEnum;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Mark a method to audit it using {@link org.apromore.aop.HistoryEventAspect}.
 * Here is a code sample for method audit :
 * <pre>
 * <code>
 * &#064;(message = "START_OPERATION")
 *  public int startOperation(String arg1, String arg2) { ... }
 * </code>
 * </pre>
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Event {

    /**
     * <p>
     * PLEASE only use variables from HistoryEnum.
     * </p>
     * <p>
     * Sample :<code>"HistoryEnum.START_OP"</code>
     * </p>
     */
    HistoryEnum message();

}
