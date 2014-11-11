package org.apromore.portal.common;

import org.apromore.model.AnnotationsType;
import org.apromore.model.ProcessSummaryType;
import org.apromore.model.VersionSummaryType;
import org.apromore.plugin.property.RequestParameterType;
import org.apromore.portal.dialogController.MainController;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by corno on 19/08/2014.
 */
public class TabListitem extends Listitem {
    private ProcessSummaryType pst;
    private VersionSummaryType vst;
    private List<Boolean> attributesToShow;
    private static final String imageProcess="img/icon/bpmn-22x22.png";

    public TabListitem(ProcessSummaryType pst, VersionSummaryType vst, List<Boolean> attributesToShow){
        this.pst=pst;
        this.vst=vst;
        this.attributesToShow=attributesToShow;
        buildListitem();
        addEventListener(Events.ON_DOUBLE_CLICK,new EventListener<Event>() {
                    private MainController mainController = MainController.getController();

                    @Override
                    public void onEvent(Event event) throws Exception {
                        AnnotationsType annotation = new AnnotationsType();
                        annotation.getAnnotationName().add(TabListitem.this.pst.getOriginalNativeType());
                        if (annotation!=null) {
                            mainController.editProcess(TabListitem.this.pst, TabListitem.this.vst, TabListitem.this.pst.getOriginalNativeType(), annotation.getAnnotationName().get(0),
                                    "false", new HashSet<RequestParameterType<?>>());
                        } else {
                            mainController.editProcess(TabListitem.this.pst, TabListitem.this.vst, TabListitem.this.pst.getOriginalNativeType(), null, "false", new HashSet<RequestParameterType<?>>());
                        }
                    }
                });
    }

    public TabListitem(){
        buildListitem();
    }

    public ProcessSummaryType getProcessSummaryType(){
        return pst;
    }

    public List<VersionSummaryType> getVersionSummaryType(){
        List<VersionSummaryType> list=new LinkedList<>();
        list.add(vst);
        return list;
    }

    private void buildListitem(){
        Listcell image=new Listcell();
        image.setImage(imageProcess);
        Listcell name=new Listcell();
        Listcell id=new Listcell();
        Listcell nativeType=new Listcell();
        Listcell domain=new Listcell();
        Listcell ranking=new Listcell();
        Listcell version=new Listcell();
        Listcell branch=new Listcell();
        Listcell owner=new Listcell();

        if(attributesToShow!=null) {
            if (attributesToShow.get(0))
                name.setLabel(pst.getName());
            if (attributesToShow.get(1))
                id.setLabel(pst.getId() + "");
            if (attributesToShow.get(2))
                nativeType.setLabel(pst.getOriginalNativeType());
            if (attributesToShow.get(3))
                domain.setLabel(pst.getDomain());
            if (attributesToShow.get(4))
                ranking.setLabel(pst.getRanking());
            if (attributesToShow.get(5))
                version.setLabel(vst.getVersionNumber());
            if (attributesToShow.get(6))
                branch.setLabel(vst.getName());
            if (attributesToShow.get(7))
                owner.setLabel(pst.getOwner());
        }
        if(attributesToShow!=null) {
            appendChild(image);
            appendChild(name);
            appendChild(id);
            appendChild(nativeType);
            appendChild(domain);
            appendChild(ranking);
            appendChild(version);
            appendChild(branch);
            appendChild(owner);
        }
    }
}
