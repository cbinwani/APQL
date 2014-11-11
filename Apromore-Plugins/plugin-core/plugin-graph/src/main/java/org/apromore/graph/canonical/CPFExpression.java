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

package org.apromore.graph.canonical;

/**
 * Expression data used by some of the nodes to store extra info.
 *
 * @author <a href="mailto:cam.james@gmail.com>Cameron James</a>
 */
public class CPFExpression implements IExpression {

    private String description;
    private String language;
    private String expression;
    private String returnType;

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String newDescription) {
        description = newDescription;
    }

    @Override
    public String getLanguage() {
        return language;
    }

    @Override
    public void setLanguage(String newLanguage) {
        language = newLanguage;
    }

    @Override
    public String getExpression() {
        return expression;
    }

    @Override
    public void setExpression(String newExpression) {
        expression = newExpression;
    }

    @Override
    public String getReturnType() {
        return returnType;
    }

    @Override
    public void setReturnType(String newReturnType) {
        returnType = newReturnType;
    }
}
