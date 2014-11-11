package org.apromore.metric.size;

/**
 * Test that the Metric: Size.
 *
 * @author <a href="mailto:cam.james@gmail.com">Cameron James</a>
 */
public class SizeMetricCalculationUnitTest {

//    Xpdl2EpmlPreProcessor xpdl2EpmlPostProcessor;
//
//    @Test
//    public void testProcessAnnotation() throws Exception {
//        xpdl2EpmlPostProcessor = new Xpdl2EpmlPreProcessor();
//
//        CanonicalProcessType cpf = buildCPF();
//        AnnotationsType anf = buildANF();
//
//        xpdl2EpmlPostProcessor.processAnnotation(cpf, anf);
//
//        assertThat(anf.getAnnotation().size(), equalTo(3));
//
//        assertThat(((GraphicsType) anf.getAnnotation().get(0)).getPosition().size(),  equalTo(1));
//        assertThat(((GraphicsType) anf.getAnnotation().get(0)).getPosition().get(0).getX().doubleValue(),  equalTo(60.0));
//        assertThat(((GraphicsType) anf.getAnnotation().get(0)).getPosition().get(0).getY().doubleValue(),  equalTo(80.0));
//
//        assertThat(((GraphicsType) anf.getAnnotation().get(1)).getPosition().size(),  equalTo(1));
//        assertThat(((GraphicsType) anf.getAnnotation().get(1)).getPosition().get(0).getX().doubleValue(),  equalTo(160.0));
//        assertThat(((GraphicsType) anf.getAnnotation().get(1)).getPosition().get(0).getY().doubleValue(),  equalTo(180.0));
//
//        assertThat(((GraphicsType) anf.getAnnotation().get(2)).getPosition().size(),  equalTo(2));
//        assertThat(((GraphicsType) anf.getAnnotation().get(2)).getPosition().get(0).getX().doubleValue(),  equalTo(100.5));
//        assertThat(((GraphicsType) anf.getAnnotation().get(2)).getPosition().get(0).getY().doubleValue(),  equalTo(100.5));
//        assertThat(((GraphicsType) anf.getAnnotation().get(2)).getPosition().get(1).getX().doubleValue(),  equalTo(200.5));
//        assertThat(((GraphicsType) anf.getAnnotation().get(2)).getPosition().get(1).getY().doubleValue(),  equalTo(200.5));
//    }
//
//
//    private CanonicalProcessType buildCPF() {
//        CanonicalProcessType cpf = new CanonicalProcessType();
//
//        NetType net = new NetType();
//
//        EventType event = new EventType();
//        event.setId("1");
//        event.setOriginalID("1");
//        event.setName("event");
//
//        TaskType task = new TaskType();
//        task.setId("2");
//        task.setOriginalID("2");
//        task.setName("task");
//
//        EdgeType edge = new EdgeType();
//        edge.setId("3");
//        edge.setOriginalID("3");
//        edge.setSourceId("1");
//        edge.setTargetId("2");
//
//        net.getNode().add(event);
//        net.getNode().add(task);
//        net.getEdge().add(edge);
//        cpf.getNet().add(net);
//
//        return cpf;
//    }
//
//
//    private AnnotationsType buildANF() {
//        AnnotationsType anf = new AnnotationsType();
//        SizeType size = getSizeType();
//
//        GraphicsType event = new GraphicsType();
//        event.setId("4");
//        event.setCpfId("1");
//        event.setSize(size);
//        event.getPosition().add(getPositionType(100, 100));
//
//        GraphicsType task = new GraphicsType();
//        task.setId("5");
//        task.setCpfId("2");
//        task.setSize(size);
//        task.getPosition().add(getPositionType(200, 200));
//
//        GraphicsType edge = new GraphicsType();
//        edge.setId("6");
//        edge.setCpfId("3");
//        edge.setSize(size);
//        edge.getPosition().add(getPositionType(100, 100));
//        edge.getPosition().add(getPositionType(200, 200));
//
//        anf.getAnnotation().add(event);
//        anf.getAnnotation().add(task);
//        anf.getAnnotation().add(edge);
//
//        return anf;
//    }
//
//
//    private PositionType getPositionType(int x, int y) {
//        PositionType position = new PositionType();
//        position.setX(new BigDecimal(x));
//        position.setY(new BigDecimal(y));
//        return position;
//    }
//
//    private SizeType getSizeType() {
//        SizeType size = new SizeType();
//        size.setHeight(new BigDecimal(1));
//        size.setWidth(new BigDecimal(1));
//        return size;
//    }

}
