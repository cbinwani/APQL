package org.apromore.graph;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Iterator;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apromore.cpf.CPFSchema;
import org.apromore.cpf.CanonicalProcessType;
import org.apromore.cpf.EdgeType;
import org.apromore.cpf.EventType;
import org.apromore.cpf.NetType;
import org.apromore.cpf.ResourceTypeRefType;
import org.apromore.cpf.ResourceTypeType;
import org.apromore.cpf.TaskType;
import org.apromore.graph.canonical.CPFNode;
import org.apromore.graph.canonical.Canonical;
import org.apromore.graph.canonical.ICPFResource;
import org.apromore.graph.canonical.ICPFResourceReference;
import org.apromore.graph.canonical.IEdge;
import org.apromore.graph.canonical.converter.CanonicalToGraph;
import org.apromore.graph.canonical.converter.GraphToCanonical;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.xml.sax.SAXException;

/**
 * Test the Canonical to Graph converter and the Graph to Canonical Converter.
 * @author <a href="mailto:cam.james@gmail.com">Cameron James</a>
 */

public class CanonicalGraphConverterUnitTest {

    private final static String CANONICAL_MODELS_DIR = "CPF_models/";

    private CanonicalToGraph c2g;
    private GraphToCanonical g2c;
    private Unmarshaller unmarshaller;


    @Before
    public void setup() throws JAXBException, SAXException {
        c2g = new CanonicalToGraph();
        g2c = new GraphToCanonical();

        JAXBContext jaxbContext = JAXBContext.newInstance(CanonicalProcessType.class);
        unmarshaller = jaxbContext.createUnmarshaller();
        unmarshaller.setSchema(CPFSchema.getCPFSchema());
    }


    @Test
    public void testSingleNetTwoNodesOneEdge() throws Exception {
        CanonicalProcessType cpf = newInstance(CANONICAL_MODELS_DIR + "test1.cpf");

        MatcherAssert.assertThat(cpf.getNet().size(), Matchers.equalTo(1));
        MatcherAssert.assertThat(cpf.getNet().get(0).getNode().size(), Matchers.equalTo(2));
        MatcherAssert.assertThat(cpf.getNet().get(0).getEdge().size(), Matchers.equalTo(1));

        Canonical outputCanonical = c2g.convert(cpf);

        MatcherAssert.assertThat(outputCanonical.getNodes().size(), Matchers.equalTo(2));
        MatcherAssert.assertThat(outputCanonical.getEdges().size(), Matchers.equalTo(1));

        CanonicalProcessType outputCpf = g2c.convert(outputCanonical);

        MatcherAssert.assertThat(outputCpf.getNet().size(), Matchers.equalTo(1));
        MatcherAssert.assertThat(outputCpf.getNet().get(0).getNode().size(), Matchers.equalTo(2));
        MatcherAssert.assertThat(outputCpf.getNet().get(0).getEdge().size(), Matchers.equalTo(1));
    }

    @Test
    public void testSingleNetWithResource() throws Exception {
        CanonicalProcessType cpf = newInstance(CANONICAL_MODELS_DIR + "test2.cpf");

        MatcherAssert.assertThat(cpf.getResourceType().size(), Matchers.equalTo(1));
        MatcherAssert.assertThat(cpf.getNet().size(), Matchers.equalTo(1));
        MatcherAssert.assertThat(cpf.getNet().get(0).getNode().size(), Matchers.equalTo(3));
        MatcherAssert.assertThat(cpf.getNet().get(0).getEdge().size(), Matchers.equalTo(2));

        Canonical outputCanonical = c2g.convert(cpf);

        MatcherAssert.assertThat(outputCanonical.getResources().size(), Matchers.equalTo(1));
        MatcherAssert.assertThat(outputCanonical.getNodes().size(), Matchers.equalTo(3));
        MatcherAssert.assertThat(outputCanonical.getEdges().size(), Matchers.equalTo(2));

        CanonicalProcessType convertedCpf = g2c.convert(outputCanonical);

        MatcherAssert.assertThat(convertedCpf.getResourceType().size(), Matchers.equalTo(1));
        MatcherAssert.assertThat(convertedCpf.getNet().size(), Matchers.equalTo(1));
        MatcherAssert.assertThat(convertedCpf.getNet().get(0).getNode().size(), Matchers.equalTo(3));
        MatcherAssert.assertThat(convertedCpf.getNet().get(0).getEdge().size(), Matchers.equalTo(2));
    }

    @Test
    public void testProcessWithSubProcess() throws Exception {
        CanonicalProcessType cpf = newInstance(CANONICAL_MODELS_DIR + "test4.cpf");

        MatcherAssert.assertThat(cpf.getNet().size(), Matchers.equalTo(2));
        MatcherAssert.assertThat(cpf.getNet().get(0).getNode().size(), Matchers.equalTo(3));
        MatcherAssert.assertThat(cpf.getNet().get(0).getEdge().size(), Matchers.equalTo(2));

        Canonical outputCanonical = c2g.convert(cpf);

        MatcherAssert.assertThat(outputCanonical.getNodes().size(), Matchers.equalTo(6));
        MatcherAssert.assertThat(outputCanonical.getEdges().size(), Matchers.equalTo(4));

        CanonicalProcessType convertedCpf = g2c.convert(outputCanonical);

        MatcherAssert.assertThat(convertedCpf.getNet().size(), Matchers.equalTo(2));
        MatcherAssert.assertThat(convertedCpf.getNet().get(0).getNode().size(), Matchers.equalTo(3));
        MatcherAssert.assertThat(convertedCpf.getNet().get(0).getEdge().size(), Matchers.equalTo(2));
    }

    //@Ignore("Passes under J2SE 1.7, fails under J2SE 1.8")
    @Test
    public void testFullCanoniserTest() throws Exception {
        CanonicalProcessType cpf = newInstance(CANONICAL_MODELS_DIR + "test5.cpf");

        MatcherAssert.assertThat(cpf.getNet().size(), Matchers.equalTo(9));
        MatcherAssert.assertThat(cpf.getNet().get(0).getNode().size(), Matchers.equalTo(11));
        MatcherAssert.assertThat(cpf.getNet().get(0).getEdge().size(), Matchers.equalTo(13));
        MatcherAssert.assertThat(cpf.getNet().get(0).getObject().size(), Matchers.equalTo(7));
        MatcherAssert.assertThat(cpf.getResourceType().size(), Matchers.equalTo(45));

        Canonical outputCanonical = c2g.convert(cpf);

        MatcherAssert.assertThat(outputCanonical.getNodes().size(), Matchers.equalTo(137));
        MatcherAssert.assertThat(outputCanonical.getEdges().size(), Matchers.equalTo(164));
        MatcherAssert.assertThat(outputCanonical.getObjects().size(), Matchers.equalTo(51));
        MatcherAssert.assertThat(outputCanonical.getResources().size(), Matchers.equalTo(45));

        CanonicalProcessType convertedCpf = g2c.convert(outputCanonical);

        MatcherAssert.assertThat(convertedCpf.getNet().size(), Matchers.equalTo(9));
        MatcherAssert.assertThat(convertedCpf.getNet().get(0).getNode().size(), Matchers.equalTo(11));
        MatcherAssert.assertThat(convertedCpf.getNet().get(0).getEdge().size(), Matchers.equalTo(13));
        MatcherAssert.assertThat(convertedCpf.getNet().get(0).getObject().size(), Matchers.equalTo(7));
        MatcherAssert.assertThat(convertedCpf.getResourceType().size(), Matchers.equalTo(45));
    }


    //@Ignore("Passes under J2SE 1.7, fails under J2SE 1.8")
    @Test
    public void testTwoNetsWithResourcesObjectTreeCorrectToCanonical() throws Exception {
        CanonicalProcessType cpf = newInstance(CANONICAL_MODELS_DIR + "test3.cpf");

        MatcherAssert.assertThat(cpf.getResourceType().size(), Matchers.equalTo(2));
        MatcherAssert.assertThat(cpf.getNet().size(), Matchers.equalTo(2));

        ResourceTypeType resource = cpf.getResourceType().get(0);
        MatcherAssert.assertThat(resource.getId(), Matchers.equalTo("c7"));
        MatcherAssert.assertThat(resource.getName(), Matchers.equalTo("P1"));
        resource = cpf.getResourceType().get(1);
        MatcherAssert.assertThat(resource.getId(), Matchers.equalTo("c17"));
        MatcherAssert.assertThat(resource.getName(), Matchers.equalTo("P2"));

        // ****** First Net  ******* //
        NetType net1 = cpf.getNet().get(0);
        MatcherAssert.assertThat(net1.getId(), Matchers.equalTo("c6"));
        MatcherAssert.assertThat(net1.getNode().size(), Matchers.equalTo(3));
        MatcherAssert.assertThat(net1.getEdge().size(), Matchers.equalTo(2));

        EventType event = (EventType) net1.getNode().get(0);
        MatcherAssert.assertThat(event.getId(), Matchers.equalTo("c1"));
        MatcherAssert.assertThat(event.getName(), Matchers.equalTo("S1"));
        MatcherAssert.assertThat(event.getResourceTypeRef().size(), Matchers.equalTo(1));
        ResourceTypeRefType resourceRef = event.getResourceTypeRef().get(0);
        MatcherAssert.assertThat(resourceRef.getId(), Matchers.equalTo("c8"));
        MatcherAssert.assertThat(resourceRef.getResourceTypeId(), Matchers.equalTo("c7"));

        TaskType task = (TaskType) net1.getNode().get(1);
        MatcherAssert.assertThat(task.getId(), Matchers.equalTo("c2"));
        MatcherAssert.assertThat(task.getName(), Matchers.equalTo("T1"));
        MatcherAssert.assertThat(task.getResourceTypeRef().size(), Matchers.equalTo(1));
        resourceRef = task.getResourceTypeRef().get(0);
        MatcherAssert.assertThat(resourceRef.getId(), Matchers.equalTo("c9"));
        MatcherAssert.assertThat(resourceRef.getResourceTypeId(), Matchers.equalTo("c7"));

        event = (EventType) net1.getNode().get(2);
        MatcherAssert.assertThat(event.getId(), Matchers.equalTo("c3"));
        MatcherAssert.assertThat(event.getName(), Matchers.equalTo("E1"));
        MatcherAssert.assertThat(event.getResourceTypeRef().size(), Matchers.equalTo(1));
        resourceRef = event.getResourceTypeRef().get(0);
        MatcherAssert.assertThat(resourceRef.getId(), Matchers.equalTo("c10"));
        MatcherAssert.assertThat(resourceRef.getResourceTypeId(), Matchers.equalTo("c7"));

        EdgeType edge = net1.getEdge().get(0);
        MatcherAssert.assertThat(edge.getId(), Matchers.equalTo("c4"));
        MatcherAssert.assertThat(edge.getSourceId(), Matchers.equalTo("c1"));
        MatcherAssert.assertThat(edge.getTargetId(), Matchers.equalTo("c2"));
        edge = net1.getEdge().get(1);
        MatcherAssert.assertThat(edge.getId(), Matchers.equalTo("c5"));
        MatcherAssert.assertThat(edge.getSourceId(), Matchers.equalTo("c2"));
        MatcherAssert.assertThat(edge.getTargetId(), Matchers.equalTo("c3"));

        // ****** Seconds Net  ******* //
        NetType net2 = cpf.getNet().get(1);
        MatcherAssert.assertThat(net2.getId(), Matchers.equalTo("c16"));
        MatcherAssert.assertThat(net2.getNode().size(), Matchers.equalTo(3));
        MatcherAssert.assertThat(net2.getEdge().size(), Matchers.equalTo(2));

        event = (EventType) net2.getNode().get(0);
        MatcherAssert.assertThat(event.getId(), Matchers.equalTo("c11"));
        MatcherAssert.assertThat(event.getName(), Matchers.equalTo("S2"));
        MatcherAssert.assertThat(event.getResourceTypeRef().size(), Matchers.equalTo(1));
        resourceRef = event.getResourceTypeRef().get(0);
        MatcherAssert.assertThat(resourceRef.getId(), Matchers.equalTo("c18"));
        MatcherAssert.assertThat(resourceRef.getResourceTypeId(), Matchers.equalTo("c17"));

        task = (TaskType) net2.getNode().get(1);
        MatcherAssert.assertThat(task.getId(), Matchers.equalTo("c12"));
        MatcherAssert.assertThat(task.getName(), Matchers.equalTo("T2"));
        MatcherAssert.assertThat(task.getResourceTypeRef().size(), Matchers.equalTo(1));
        resourceRef = task.getResourceTypeRef().get(0);
        MatcherAssert.assertThat(resourceRef.getId(), Matchers.equalTo("c19"));
        MatcherAssert.assertThat(resourceRef.getResourceTypeId(), Matchers.equalTo("c17"));

        event = (EventType) net2.getNode().get(2);
        MatcherAssert.assertThat(event.getId(), Matchers.equalTo("c13"));
        MatcherAssert.assertThat(event.getName(), Matchers.equalTo("E2"));
        MatcherAssert.assertThat(event.getResourceTypeRef().size(), Matchers.equalTo(1));
        resourceRef = event.getResourceTypeRef().get(0);
        MatcherAssert.assertThat(resourceRef.getId(), Matchers.equalTo("c20"));
        MatcherAssert.assertThat(resourceRef.getResourceTypeId(), Matchers.equalTo("c17"));

        edge = net2.getEdge().get(0);
        MatcherAssert.assertThat(edge.getId(), Matchers.equalTo("c14"));
        MatcherAssert.assertThat(edge.getSourceId(), Matchers.equalTo("c11"));
        MatcherAssert.assertThat(edge.getTargetId(), Matchers.equalTo("c12"));
        edge = net2.getEdge().get(1);
        MatcherAssert.assertThat(edge.getId(), Matchers.equalTo("c15"));
        MatcherAssert.assertThat(edge.getSourceId(), Matchers.equalTo("c12"));
        MatcherAssert.assertThat(edge.getTargetId(), Matchers.equalTo("c13"));


        // ****** Convert and Check the same structure  ******* //
        Canonical outputCanonical = c2g.convert(cpf);

        MatcherAssert.assertThat(outputCanonical.getResources().size(), Matchers.equalTo(2));
        MatcherAssert.assertThat(outputCanonical.getNodes().size(), Matchers.equalTo(6));
        MatcherAssert.assertThat(outputCanonical.getEdges().size(), Matchers.equalTo(4));

        Iterator resItor = outputCanonical.getResources().iterator();
        ICPFResource cpfResource = (ICPFResource) resItor.next();
        MatcherAssert.assertThat(cpfResource.getId(), Matchers.equalTo("c17"));
        MatcherAssert.assertThat(cpfResource.getName(), Matchers.equalTo("P2"));
        cpfResource = (ICPFResource) resItor.next();
        MatcherAssert.assertThat(cpfResource.getId(), Matchers.equalTo("c7"));
        MatcherAssert.assertThat(cpfResource.getName(), Matchers.equalTo("P1"));

        CPFNode node1 = outputCanonical.getNode("c1");
        MatcherAssert.assertThat(node1.getId(), Matchers.equalTo("c1"));
        MatcherAssert.assertThat(node1.getName(), Matchers.equalTo("S1"));
        MatcherAssert.assertThat(node1.getResourceReferences().size(), Matchers.equalTo(1));
        ICPFResourceReference cpfResourceRef = node1.getResourceReferences().iterator().next();
        MatcherAssert.assertThat(cpfResourceRef.getId(), Matchers.equalTo("c8"));
        MatcherAssert.assertThat(cpfResourceRef.getResourceId(), Matchers.equalTo("c7"));

        CPFNode node2 = outputCanonical.getNode("c2");
        MatcherAssert.assertThat(node2.getId(), Matchers.equalTo("c2"));
        MatcherAssert.assertThat(node2.getName(), Matchers.equalTo("T1"));
        MatcherAssert.assertThat(node2.getResourceReferences().size(), Matchers.equalTo(1));
        cpfResourceRef = node2.getResourceReferences().iterator().next();
        MatcherAssert.assertThat(cpfResourceRef.getId(), Matchers.equalTo("c9"));
        MatcherAssert.assertThat(cpfResourceRef.getResourceId(), Matchers.equalTo("c7"));

        CPFNode node3 = outputCanonical.getNode("c3");
        MatcherAssert.assertThat(node3.getId(), Matchers.equalTo("c3"));
        MatcherAssert.assertThat(node3.getName(), Matchers.equalTo("E1"));
        MatcherAssert.assertThat(node3.getResourceReferences().size(), Matchers.equalTo(1));
        cpfResourceRef = node3.getResourceReferences().iterator().next();
        MatcherAssert.assertThat(cpfResourceRef.getId(), Matchers.equalTo("c10"));
        MatcherAssert.assertThat(cpfResourceRef.getResourceId(), Matchers.equalTo("c7"));

        CPFNode node11 = outputCanonical.getNode("c11");
        MatcherAssert.assertThat(node11.getId(), Matchers.equalTo("c11"));
        MatcherAssert.assertThat(node11.getName(), Matchers.equalTo("S2"));
        MatcherAssert.assertThat(node11.getResourceReferences().size(), Matchers.equalTo(1));
        cpfResourceRef = node11.getResourceReferences().iterator().next();
        MatcherAssert.assertThat(cpfResourceRef.getId(), Matchers.equalTo("c18"));
        MatcherAssert.assertThat(cpfResourceRef.getResourceId(), Matchers.equalTo("c17"));

        CPFNode node12 = outputCanonical.getNode("c12");
        MatcherAssert.assertThat(node12.getId(), Matchers.equalTo("c12"));
        MatcherAssert.assertThat(node12.getName(), Matchers.equalTo("T2"));
        MatcherAssert.assertThat(node12.getResourceReferences().size(), Matchers.equalTo(1));
        cpfResourceRef = node12.getResourceReferences().iterator().next();
        MatcherAssert.assertThat(cpfResourceRef.getId(), Matchers.equalTo("c19"));
        MatcherAssert.assertThat(cpfResourceRef.getResourceId(), Matchers.equalTo("c17"));

        CPFNode node13 = outputCanonical.getNode("c13");
        MatcherAssert.assertThat(node13.getId(), Matchers.equalTo("c13"));
        MatcherAssert.assertThat(node13.getName(), Matchers.equalTo("E2"));
        MatcherAssert.assertThat(node13.getResourceReferences().size(), Matchers.equalTo(1));
        cpfResourceRef = node13.getResourceReferences().iterator().next();
        MatcherAssert.assertThat(cpfResourceRef.getId(), Matchers.equalTo("c20"));
        MatcherAssert.assertThat(cpfResourceRef.getResourceId(), Matchers.equalTo("c17"));

        IEdge cpfEdge = outputCanonical.getEdge(node1, node2);
        //assertThat(cpfEdge.getId(), equalTo("c4"));
        MatcherAssert.assertThat(cpfEdge.getSource().getId(), Matchers.equalTo("c1"));
        MatcherAssert.assertThat(cpfEdge.getTarget().getId(), Matchers.equalTo("c2"));

        cpfEdge = outputCanonical.getEdge(node2, node3);
        //assertThat(cpfEdge.getId(), equalTo("c5"));
        MatcherAssert.assertThat(cpfEdge.getSource().getId(), Matchers.equalTo("c2"));
        MatcherAssert.assertThat(cpfEdge.getTarget().getId(), Matchers.equalTo("c3"));

        cpfEdge = outputCanonical.getEdge(node11, node12);
        //assertThat(cpfEdge.getId(), equalTo("c14"));
        MatcherAssert.assertThat(cpfEdge.getSource().getId(), Matchers.equalTo("c11"));
        MatcherAssert.assertThat(cpfEdge.getTarget().getId(), Matchers.equalTo("c12"));

        cpfEdge = outputCanonical.getEdge(node12, node13);
        //assertThat(cpfEdge.getId(), equalTo("c15"));
        MatcherAssert.assertThat(cpfEdge.getSource().getId(), Matchers.equalTo("c12"));
        MatcherAssert.assertThat(cpfEdge.getTarget().getId(), Matchers.equalTo("c13"));
    }

    //@Ignore("Passes under J2SE 1.7, fails under J2SE 1.8")
    @Test
    public void testTwoNetsWithResourcesObjectTreeCorrectBackToGraph() throws Exception {
        CanonicalProcessType can = newInstance(CANONICAL_MODELS_DIR + "test3.cpf");
        Canonical outputCanonical = c2g.convert(can);

        CanonicalProcessType convertedCpf = g2c.convert(outputCanonical);

        MatcherAssert.assertThat(convertedCpf.getResourceType().size(), Matchers.equalTo(2));
        MatcherAssert.assertThat(convertedCpf.getNet().size(), Matchers.equalTo(2));

        ResourceTypeType resource = convertedCpf.getResourceType().get(0);
        MatcherAssert.assertThat(resource.getId(), Matchers.equalTo("c17"));
        MatcherAssert.assertThat(resource.getName(), Matchers.equalTo("P2"));
        resource = convertedCpf.getResourceType().get(1);
        MatcherAssert.assertThat(resource.getId(), Matchers.equalTo("c7"));
        MatcherAssert.assertThat(resource.getName(), Matchers.equalTo("P1"));

        // ****** First Net  ******* //
        NetType net1 = convertedCpf.getNet().get(0);
        MatcherAssert.assertThat(net1.getId(), Matchers.equalTo("c16"));
        MatcherAssert.assertThat(net1.getNode().size(), Matchers.equalTo(3));
        MatcherAssert.assertThat(net1.getEdge().size(), Matchers.equalTo(2));

        EventType event = (EventType) net1.getNode().get(0);
        MatcherAssert.assertThat(event.getId(), Matchers.equalTo("c11"));
        MatcherAssert.assertThat(event.getName(), Matchers.equalTo("S2"));
        MatcherAssert.assertThat(event.getResourceTypeRef().size(), Matchers.equalTo(1));
        ResourceTypeRefType resourceRef = event.getResourceTypeRef().get(0);
        MatcherAssert.assertThat(resourceRef.getId(), Matchers.equalTo("c18"));
        MatcherAssert.assertThat(resourceRef.getResourceTypeId(), Matchers.equalTo("c17"));

        event = (EventType) net1.getNode().get(1);
        MatcherAssert.assertThat(event.getId(), Matchers.equalTo("c13"));
        MatcherAssert.assertThat(event.getName(), Matchers.equalTo("E2"));
        MatcherAssert.assertThat(event.getResourceTypeRef().size(), Matchers.equalTo(1));
        resourceRef = event.getResourceTypeRef().get(0);
        MatcherAssert.assertThat(resourceRef.getId(), Matchers.equalTo("c20"));
        MatcherAssert.assertThat(resourceRef.getResourceTypeId(), Matchers.equalTo("c17"));

        TaskType task = (TaskType) net1.getNode().get(2);
        MatcherAssert.assertThat(task.getId(), Matchers.equalTo("c12"));
        MatcherAssert.assertThat(task.getName(), Matchers.equalTo("T2"));
        MatcherAssert.assertThat(task.getResourceTypeRef().size(), Matchers.equalTo(1));
        resourceRef = task.getResourceTypeRef().get(0);
        MatcherAssert.assertThat(resourceRef.getId(), Matchers.equalTo("c19"));
        MatcherAssert.assertThat(resourceRef.getResourceTypeId(), Matchers.equalTo("c17"));

        EdgeType edge = net1.getEdge().get(0);
        MatcherAssert.assertThat(edge, Matchers.notNullValue());
        //assertThat(edge.getId(), equalTo("c14"));
        //assertThat(edge.getSourceId(), equalTo("c11"));
        //assertThat(edge.getTargetId(), equalTo("c2"));
        edge = net1.getEdge().get(1);
        MatcherAssert.assertThat(edge, Matchers.notNullValue());
        //assertThat(edge.getId(), equalTo("c5"));
        //assertThat(edge.getSourceId(), equalTo("c2"));
        //assertThat(edge.getTargetId(), equalTo("c3"));

        // ****** Seconds Net  ******* //
        NetType net2 = convertedCpf.getNet().get(1);
        MatcherAssert.assertThat(net2.getId(), Matchers.equalTo("c6"));
        MatcherAssert.assertThat(net2.getNode().size(), Matchers.equalTo(3));
        MatcherAssert.assertThat(net2.getEdge().size(), Matchers.equalTo(2));

        event = (EventType) net2.getNode().get(0);
        MatcherAssert.assertThat(event.getId(), Matchers.equalTo("c1"));
        MatcherAssert.assertThat(event.getName(), Matchers.equalTo("S1"));
        MatcherAssert.assertThat(event.getResourceTypeRef().size(), Matchers.equalTo(1));
        resourceRef = event.getResourceTypeRef().get(0);
        MatcherAssert.assertThat(resourceRef.getId(), Matchers.equalTo("c8"));
        MatcherAssert.assertThat(resourceRef.getResourceTypeId(), Matchers.equalTo("c7"));

        task = (TaskType) net2.getNode().get(1);
        MatcherAssert.assertThat(task.getId(), Matchers.equalTo("c2"));
        MatcherAssert.assertThat(task.getName(), Matchers.equalTo("T1"));
        MatcherAssert.assertThat(task.getResourceTypeRef().size(), Matchers.equalTo(1));
        resourceRef = task.getResourceTypeRef().get(0);
        MatcherAssert.assertThat(resourceRef.getId(), Matchers.equalTo("c9"));
        MatcherAssert.assertThat(resourceRef.getResourceTypeId(), Matchers.equalTo("c7"));

        event = (EventType) net2.getNode().get(2);
        MatcherAssert.assertThat(event.getId(), Matchers.equalTo("c3"));
        MatcherAssert.assertThat(event.getName(), Matchers.equalTo("E1"));
        MatcherAssert.assertThat(event.getResourceTypeRef().size(), Matchers.equalTo(1));
        resourceRef = event.getResourceTypeRef().get(0);
        MatcherAssert.assertThat(resourceRef.getId(), Matchers.equalTo("c10"));
        MatcherAssert.assertThat(resourceRef.getResourceTypeId(), Matchers.equalTo("c7"));

        edge = net2.getEdge().get(0);
        MatcherAssert.assertThat(edge, Matchers.notNullValue());
//        assertThat(edge.getId(), equalTo("c15"));
//        assertThat(edge.getSourceId(), equalTo("c12"));
//        assertThat(edge.getTargetId(), equalTo("c13"));
        edge = net2.getEdge().get(1);
        MatcherAssert.assertThat(edge, Matchers.notNullValue());
//        assertThat(edge.getId(), equalTo("c14"));
//        assertThat(edge.getSourceId(), equalTo("c11"));
//        assertThat(edge.getTargetId(), equalTo("c12"));
    }

    @Test
    public void testNetWithCancellationSet() throws Exception {
        CanonicalProcessType cpf = newInstance(CANONICAL_MODELS_DIR + "Case 12.cpf");

        NetType net = cpf.getNet().get(0);
        MatcherAssert.assertThat(net.getId(), Matchers.equalTo("n"));
        MatcherAssert.assertThat(net.getNode().size(), Matchers.equalTo(6));
        MatcherAssert.assertThat(net.getEdge().size(), Matchers.equalTo(6));      

        Canonical outputCanonical = c2g.convert(cpf);

        MatcherAssert.assertThat(outputCanonical.getResources().size(), Matchers.equalTo(0));
        MatcherAssert.assertThat(outputCanonical.getNodes().size(), Matchers.equalTo(6));
        MatcherAssert.assertThat(outputCanonical.getEdges().size(), Matchers.equalTo(6));

        CPFNode nodeA = outputCanonical.getNode("a");
        MatcherAssert.assertThat(nodeA.getId(), Matchers.equalTo("a"));
        MatcherAssert.assertThat(nodeA.getName(), Matchers.equalTo("A"));
        MatcherAssert.assertThat(nodeA.getResourceReferences().size(), Matchers.equalTo(0));   
        MatcherAssert.assertThat(nodeA.getCancelNodes().size(), Matchers.equalTo(1));   
        MatcherAssert.assertThat(nodeA.getCancelNodes().iterator().next(), Matchers.equalTo("b"));   

        CPFNode nodeB = outputCanonical.getNode("b");
        MatcherAssert.assertThat(nodeB.getId(), Matchers.equalTo("b"));
        MatcherAssert.assertThat(nodeB.getName(), Matchers.equalTo("B"));
        MatcherAssert.assertThat(nodeB.getResourceReferences().size(), Matchers.equalTo(0));   
        MatcherAssert.assertThat(nodeB.getCancelNodes().size(), Matchers.equalTo(1));   
        MatcherAssert.assertThat(nodeB.getCancelNodes().iterator().next(), Matchers.equalTo("a"));   
    }

    /* Loads the  */
    @SuppressWarnings("unchecked")
    private CanonicalProcessType newInstance(String fileName) throws JAXBException, SAXException, FileNotFoundException {
        InputStream stream = ClassLoader.getSystemResourceAsStream(fileName);
        return ((JAXBElement<CanonicalProcessType>) unmarshaller.unmarshal(stream)).getValue();
    }
}
