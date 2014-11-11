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

import org.zkoss.zul.Image;
import org.zkoss.zul.Label;
import org.zkoss.zul.Window;

public class ShortMessageController extends Window {

    private Label message;
    private Image image;

    public ShortMessageController(Window win) {
        this.message = (Label) win.getFellow("message");
        this.image = (Image) win.getFellow("image");
    }

    public void displayMessage(String mes) {
        this.message.setValue(mes);
        this.image.setVisible(false);
    }

    public void eraseMessage() {
        this.message.setValue("no messages");
        this.image.setVisible(false);
    }
}
