package org.apromore.manager.service;

import static org.easymock.EasyMock.expect;
import static org.powermock.api.easymock.PowerMock.createMock;
import static org.powermock.api.easymock.PowerMock.replay;
import static org.powermock.api.easymock.PowerMock.verify;

import javax.xml.bind.JAXBElement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apromore.canoniser.Canoniser;
import org.apromore.canoniser.DefaultAbstractCanoniser;
import org.apromore.manager.ManagerPortalEndpoint;
import org.apromore.model.ObjectFactory;
import org.apromore.model.PluginInfo;
import org.apromore.model.ReadCanoniserInfoInputMsgType;
import org.apromore.model.ReadCanoniserInfoOutputMsgType;
import org.apromore.plugin.exception.PluginNotFoundException;
import org.apromore.service.*;
import org.apromore.service.helper.UIHelper;
import org.apromore.service.helper.UserInterfaceHelper;
import org.apromore.service.impl.*;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ReadCanoniserInfoEndpointUnitTest {

    private ManagerPortalEndpoint endpoint;

    private DeploymentService deploymentService;
    private CanoniserService canoniserService;
    private PluginService pluginService;
    private FragmentService fragmentSrv;
    private ProcessService procSrv;
    private ClusterService clusterService;
    private FormatService frmSrv;
    private DomainService domSrv;
    private UserService userSrv;
    private SimilarityService simSrv;
    private MergeService merSrv;
    private SecurityService secSrv;
    private WorkspaceService wrkSrv;
    private UserInterfaceHelper uiHelper;

    private PQLService pqlService;
    private DatabaseService dbService;

    @Before
    public void setUp() throws Exception {
        deploymentService = createMock(DeploymentServiceImpl.class);
        pluginService = createMock(PluginServiceImpl.class);
        fragmentSrv = createMock(FragmentServiceImpl.class);
        canoniserService = createMock(CanoniserServiceImpl.class);
        procSrv = createMock(ProcessServiceImpl.class);
        clusterService = createMock(ClusterServiceImpl.class);
        frmSrv = createMock(FormatServiceImpl.class);
        domSrv = createMock(DomainServiceImpl.class);
        userSrv = createMock(UserServiceImpl.class);
        simSrv = createMock(SimilarityServiceImpl.class);
        merSrv = createMock(MergeServiceImpl.class);
        secSrv = createMock(SecurityServiceImpl.class);
        wrkSrv = createMock(WorkspaceServiceImpl.class);
        uiHelper = createMock(UIHelper.class);

        pqlService = createMock(PQLServiceImpl.class);
        dbService= createMock(DatabaseServiceImpl.class);

        endpoint = new ManagerPortalEndpoint(deploymentService, pluginService, fragmentSrv, canoniserService, procSrv,
                clusterService, frmSrv, domSrv, userSrv, simSrv, merSrv, secSrv, wrkSrv, uiHelper, pqlService, dbService);
    }



    @Test
    public void testReadCanoniserInfo() throws PluginNotFoundException {
        ReadCanoniserInfoInputMsgType msg = new ReadCanoniserInfoInputMsgType();
        msg.setNativeType("Test 9.2");
        JAXBElement<ReadCanoniserInfoInputMsgType> request = new ObjectFactory().createReadCanoniserInfoRequest(msg);

        DefaultAbstractCanoniser mockCanoniser = createMock(DefaultAbstractCanoniser.class);
        expect(mockCanoniser.getName()).andReturn("Test Plugin");
        expect(mockCanoniser.getVersion()).andReturn("1.0");
        expect(mockCanoniser.getAuthor()).andReturn("Scott");
        expect(mockCanoniser.getDescription()).andReturn("Beam me up");
        expect(mockCanoniser.getType()).andReturn("Starship");
        expect(mockCanoniser.getEMail()).andReturn("scott@mail.com");
        replay(mockCanoniser);

        Set<Canoniser> canoniserSet = new HashSet<Canoniser>();
        canoniserSet.add(mockCanoniser);

        expect(canoniserService.listByNativeType(msg.getNativeType())).andReturn(canoniserSet);
        replay(canoniserService);

        JAXBElement<ReadCanoniserInfoOutputMsgType> response = endpoint.readCanoniserInfo(request);
        verify(canoniserService);

        List<PluginInfo> infoResult = response.getValue().getPluginInfo();
        Assert.assertNotNull(infoResult);
        Assert.assertTrue(!infoResult.isEmpty());
        PluginInfo info = infoResult.iterator().next();
        Assert.assertNotNull(info);
        Assert.assertEquals("Plugin name does not match", info.getName(), "Test Plugin");
        Assert.assertEquals("Plugin version does not match", info.getVersion(), "1.0");
        Assert.assertEquals("Plugin author does not match", info.getAuthor(), "Scott");
        Assert.assertEquals("Plugin descr does not match", info.getDescription(), "Beam me up");
        Assert.assertEquals("Plugin type does not match", info.getType(), "Starship");
        Assert.assertEquals("Plugin type does not match", info.getEmail(), "scott@mail.com");

        verify(mockCanoniser);
    }

}
