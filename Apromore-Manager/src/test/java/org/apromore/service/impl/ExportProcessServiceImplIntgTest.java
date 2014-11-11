package org.apromore.service.impl;

import org.apromore.cpf.CanonicalProcessType;
import org.apromore.dao.model.ProcessModelVersion;
import org.apromore.helper.Version;
import org.apromore.model.ExportFormatResultType;
import org.apromore.plugin.property.RequestParameterType;
import org.apromore.service.CanoniserService;
import org.apromore.service.ProcessService;
import org.apromore.service.model.CanonisedProcess;
import org.apromore.util.StreamUtil;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.activation.DataHandler;
import javax.inject.Inject;
import javax.mail.util.ByteArrayDataSource;
import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

/**
 * Unit test the UserService Implementation.
 *
 * @author <a href="mailto:cam.james@gmail.com">Cameron James</a>
 */
@Ignore
@ContextConfiguration(locations = {
        "classpath:META-INF/spring/applicationContext-jpa-TEST.xml",
        "classpath:META-INF/spring/applicationContext-services-TEST.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@TransactionConfiguration(defaultRollback = true)
@TestExecutionListeners(value = DependencyInjectionTestExecutionListener.class)
public class ExportProcessServiceImplIntgTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExportProcessServiceImplIntgTest.class);

    @Inject
    private CanoniserService cSrv;
    @Inject
    private ProcessService pSrv;

    private String epmlNativeType = "EPML 2.0";
    private String username = "james";
    private Version version = new Version(1, 0);
    private String domain = "Tests";
    private String created = "12/12/2011";
    private String lastUpdate = "12/12/2011";


    @Test
    public void testExportProcessWithSingleEdgeInEPML() throws Exception {
        String name = "Test EPML 1";

        DataHandler startStream = new DataHandler(new ByteArrayDataSource(ClassLoader.getSystemResourceAsStream("EPML_models/test1.epml"), "text/xml"));
        CanonisedProcess startCP = cSrv.canonise(epmlNativeType, startStream.getInputStream(), new HashSet<RequestParameterType<?>>());
        ProcessModelVersion pst = pSrv.importProcess(username, 0, name, version, epmlNativeType, startCP, domain, "", created, lastUpdate, true);

        ExportFormatResultType result = pSrv.exportProcess(name, pst.getId(), pst.getProcessBranch().getBranchName(), version, epmlNativeType, "Initial", false, new HashSet<RequestParameterType<?>>(0));
        DataHandler endStream = result.getNative();
        CanonisedProcess endCP = cSrv.canonise(epmlNativeType, endStream.getInputStream(), new HashSet<RequestParameterType<?>>());

        CanonicalProcessType startCPT = startCP.getCpt();
        CanonicalProcessType endCPT = endCP.getCpt();
        assertThat(startCPT.getNet().size(), equalTo(endCPT.getNet().size()));
        assertThat(startCPT.getResourceType().size(), equalTo(endCPT.getResourceType().size()));
        assertThat(startCPT.getNet().get(0).getNode().size(), equalTo(endCPT.getNet().get(0).getNode().size()));
        assertThat(startCPT.getNet().get(0).getEdge().size(), equalTo(endCPT.getNet().get(0).getEdge().size()));
        assertThat(startCPT.getNet().get(0).getObject().size(), equalTo(endCPT.getNet().get(0).getObject().size()));

        LOGGER.debug(StreamUtil.convertStreamToString(endStream));
    }

    @Test
    public void testExportProcessWithJoinAndSplitInEPML() throws Exception {
        String name = "Test EPML 2";

        DataHandler startStream = new DataHandler(new ByteArrayDataSource(ClassLoader.getSystemResourceAsStream("EPML_models/test2.epml"), "text/xml"));
        CanonisedProcess startCP = cSrv.canonise(epmlNativeType, startStream.getInputStream(), new HashSet<RequestParameterType<?>>());
        ProcessModelVersion pst = pSrv.importProcess(username, 0, name, version, epmlNativeType, startCP, domain, "", created, lastUpdate, true);

        ExportFormatResultType result = pSrv.exportProcess(name, pst.getId(), pst.getProcessBranch().getBranchName(), version, epmlNativeType, "Initial", false, new HashSet<RequestParameterType<?>>(0));
        DataHandler endStream = result.getNative();
        CanonisedProcess endCP = cSrv.canonise(epmlNativeType, endStream.getInputStream(), new HashSet<RequestParameterType<?>>());

        CanonicalProcessType startCPT = startCP.getCpt();
        CanonicalProcessType endCPT = endCP.getCpt();
        assertThat(startCPT.getNet().size(), equalTo(endCPT.getNet().size()));
        assertThat(startCPT.getResourceType().size(), equalTo(endCPT.getResourceType().size()));
        assertThat(startCPT.getNet().get(0).getNode().size(), equalTo(endCPT.getNet().get(0).getNode().size()));
        assertThat(startCPT.getNet().get(0).getEdge().size(), equalTo(endCPT.getNet().get(0).getEdge().size()));
        assertThat(startCPT.getNet().get(0).getObject().size(), equalTo(endCPT.getNet().get(0).getObject().size()));

        LOGGER.debug(StreamUtil.convertStreamToString(endStream));
    }

    @Test
    public void testExportProcessWithObjectsResourcesInXPDL() throws Exception {
        String name = "Test XPDL 3";

        DataHandler startStream = new DataHandler(new ByteArrayDataSource(ClassLoader.getSystemResourceAsStream("XPDL_models/F3 International Departure Passport Control.xpdl"), "text/xml"));
        String xpdlNativeType = "XPDL 2.2";
        CanonisedProcess startCP = cSrv.canonise(xpdlNativeType, startStream.getInputStream(), new HashSet<RequestParameterType<?>>());
        ProcessModelVersion pst = pSrv.importProcess(username, 0, name, version, xpdlNativeType, startCP, domain, "", created, lastUpdate, true);

        ExportFormatResultType result = pSrv.exportProcess(name, pst.getId(), pst.getProcessBranch().getBranchName(), version, xpdlNativeType, "Initial", false, new HashSet<RequestParameterType<?>>(0));
        DataHandler endStream = result.getNative();

        // TODO: De-canonisations doesn't produce the same as input.

//        CanonisedProcess endCP = cSrv.canonise(xpdlNativeType, endStream.getInputStream(), new HashSet<RequestParameterType<?>>());
//        CanonicalProcessType startCPT = startCP.getCpt();
//        CanonicalProcessType endCPT = endCP.getCpt();
//        assertThat(startCPT.getNet().size(), equalTo(endCPT.getNet().size()));
//        assertThat(startCPT.getResourceType().size(), equalTo(endCPT.getResourceType().size()));
//        assertThat(startCPT.getNet().get(0).getNode().size(), equalTo(endCPT.getNet().get(0).getNode().size()));
//        assertThat(startCPT.getNet().get(0).getEdge().size(), equalTo(endCPT.getNet().get(0).getEdge().size()));
//        assertThat(startCPT.getNet().get(0).getObject().size(), equalTo(endCPT.getNet().get(0).getObject().size()));

        LOGGER.debug(StreamUtil.convertStreamToString(endStream));
    }

}
