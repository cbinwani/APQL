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

package org.apromore.canoniser.pnml.internal.pnml2canonical;

import org.apromore.cpf.ANDJoinType;
import org.apromore.cpf.ANDSplitType;
import org.apromore.cpf.EdgeType;
import org.apromore.cpf.TaskType;
import org.apromore.cpf.XORJoinType;
import org.apromore.cpf.XORSplitType;
import org.apromore.pnml.TransitionType;

public class TranslateSplitJoins {
    DataHandler data;
    long ids;
    ANDJoinType andjoin = new ANDJoinType();
    ANDSplitType andsplit = new ANDSplitType();
    XORJoinType xorjoin = new XORJoinType();
    XORSplitType xorsplit = new XORSplitType();

    public void setValues(DataHandler data, long ids) {
        this.data = data;
        this.ids = ids;
    }

    public void translateAndSplitJoins(TransitionType tra) {
        TaskType task = new TaskType();
        data.put_objectmap(String.valueOf(ids), task);
        andjoin.setId(String.valueOf(ids++));
        andjoin.setName(tra.getName().getText());
        andsplit.setId(String.valueOf(ids++));
        andsplit.setName(tra.getName().getText());
        task.setId(String.valueOf(ids++));
        task.setName(tra.getName().getText());
        EdgeType join = new EdgeType();
        join.setId(String.valueOf(ids++));
        join.setSourceId(andjoin.getId());
        join.setTargetId(task.getId());
        EdgeType split = new EdgeType();
        split.setId(String.valueOf(ids++));
        split.setSourceId(task.getId());
        split.setTargetId(andsplit.getId());
        andjoin.setOriginalID(tra.getId());
        andsplit.setOriginalID(tra.getId());
        data.getNet().getNode().add(andjoin);
        data.getNet().getEdge().add(join);
        data.getNet().getNode().add(task);
        data.getNet().getEdge().add(split);
        data.getNet().getNode().add(andsplit);
        data.put_andsplitjoinmap(String.valueOf(tra.getId()),
                String.valueOf(andsplit.getId()));
    }

    public void translateXorSplitJoins(TransitionType tra) {
        TaskType task = new TaskType();
        data.put_objectmap(String.valueOf(ids), task);
        xorjoin.setId(String.valueOf(ids++));
        xorjoin.setName(tra.getName().getText());
        xorsplit.setId(String.valueOf(ids++));
        xorsplit.setName(tra.getName().getText());
        task.setId(String.valueOf(ids++));
        task.setName(tra.getName().getText());
        EdgeType join = new EdgeType();
        join.setId(String.valueOf(ids++));
        join.setSourceId(xorjoin.getId());
        join.setTargetId(task.getId());
        EdgeType split = new EdgeType();
        split.setId(String.valueOf(ids++));
        split.setSourceId(task.getId());
        split.setTargetId(xorsplit.getId());

        xorjoin.setOriginalID(tra.getId());
        xorsplit.setOriginalID(tra.getId());
        data.getNet().getNode().add(xorjoin);
        data.getNet().getEdge().add(join);
        data.getNet().getNode().add(task);
        data.getNet().getEdge().add(split);
        data.getNet().getNode().add(xorsplit);
        data.put_andsplitjoinmap(String.valueOf(tra.getId()),
                String.valueOf(xorsplit.getId()));
    }

    public void translateAndJoinXorSplit(TransitionType tra) {
        TaskType task = new TaskType();
        data.put_objectmap(String.valueOf(ids), task);
        andjoin.setId(String.valueOf(ids++));
        andjoin.setName(tra.getName().getText());
        xorsplit.setId(String.valueOf(ids++));
        xorsplit.setName(tra.getName().getText());
        task.setId(String.valueOf(ids++));
        task.setName(tra.getName().getText());
        EdgeType join = new EdgeType();
        join.setId(String.valueOf(ids++));
        join.setSourceId(andjoin.getId());
        join.setTargetId(task.getId());
        EdgeType split = new EdgeType();
        split.setId(String.valueOf(ids++));
        split.setSourceId(task.getId());
        split.setTargetId(xorsplit.getId());

        andjoin.setOriginalID(tra.getId());
        xorsplit.setOriginalID(tra.getId());
        data.getNet().getNode().add(andjoin);
        data.getNet().getEdge().add(join);
        data.getNet().getNode().add(task);
        data.getNet().getEdge().add(split);
        data.getNet().getNode().add(xorsplit);
        data.put_andsplitjoinmap(String.valueOf(tra.getId()),
                String.valueOf(xorsplit.getId()));
    }

    public void translateXorJoinAndSplit(TransitionType tra) {
        TaskType task = new TaskType();
        data.put_objectmap(String.valueOf(ids), task);
        xorjoin.setId(String.valueOf(ids++));
        xorjoin.setName(tra.getName().getText());
        andsplit.setId(String.valueOf(ids++));
        andsplit.setName(tra.getName().getText());
        task.setId(String.valueOf(ids++));
        task.setName(tra.getName().getText());
        EdgeType join = new EdgeType();
        join.setId(String.valueOf(ids++));
        join.setSourceId(xorjoin.getId());
        join.setTargetId(task.getId());
        EdgeType split = new EdgeType();
        split.setId(String.valueOf(ids++));
        split.setSourceId(task.getId());
        split.setTargetId(andsplit.getId());

        xorjoin.setOriginalID(tra.getId());
        andsplit.setOriginalID(tra.getId());
        data.getNet().getNode().add(xorjoin);
        data.getNet().getEdge().add(join);
        data.getNet().getNode().add(task);
        data.getNet().getEdge().add(split);
        data.getNet().getNode().add(andsplit);
        data.put_andsplitjoinmap(String.valueOf(tra.getId()),
                String.valueOf(andsplit.getId()));
    }

    public long getIds() {
        return ids;
    }
}
