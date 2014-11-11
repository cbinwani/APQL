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
 * Default implementation of an CPF attribute.
 * 
 * @author Felix Mannhardt
 *
 */
public class CPFAttribute implements IAttribute {
    
    private String value;
    private Object any;

    public CPFAttribute(String value, Object any) {
        this.value = value;
        this.any = any;
    }

    @Override
    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public String getValue() {
        return this.value;
    }

    @Override
    public void setAny(Object any) {
        this.any = any;
    }

    @Override
    public java.lang.Object getAny() {
        return this.any;
    }

}
