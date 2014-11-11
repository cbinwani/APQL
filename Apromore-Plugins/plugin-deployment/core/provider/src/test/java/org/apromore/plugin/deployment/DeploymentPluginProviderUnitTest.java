package org.apromore.plugin.deployment;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.HashSet;
import java.util.Set;

import org.apromore.plugin.deployment.provider.impl.DeploymentPluginProviderImpl;
import org.apromore.plugin.exception.PluginNotFoundException;
import org.junit.Before;
import org.junit.Test;

public class DeploymentPluginProviderUnitTest {

    private DeploymentPluginProviderImpl provider;
    private MockDeploymentPlugin mockPlugin;

    @Before
    public void setUp() {
        final DeploymentPluginProviderImpl dp = new DeploymentPluginProviderImpl();
        final Set<DeploymentPlugin> deploymentPluginSet = new HashSet<DeploymentPlugin>();
        mockPlugin = new MockDeploymentPlugin();
        deploymentPluginSet.add(mockPlugin);
        dp.setDeploymentPluginSet(deploymentPluginSet);
        this.provider = dp;
    }

    @Test
    public void testListAll() {
        assertNotNull(provider.listAll());
        assertFalse(provider.listAll().isEmpty());
    }

    @Test
    public void testFindByNativeTypeAndNameAndVersion() throws PluginNotFoundException {
        assertNotNull(provider.findByNativeTypeAndNameAndVersion("YAWL 2.2","test","1.0"));
        try {
            provider.findByNativeTypeAndNameAndVersion("YAWL 2.2","invalid","1.0");
            fail();
          } catch (PluginNotFoundException e) {
          }
    }

    @Test
    public void testFindByNativeType() throws PluginNotFoundException {
        assertNotNull(provider.findByNativeType("YAWL 2.2"));
        try {
          provider.findByNativeType("invalid");
          fail();
        } catch (PluginNotFoundException e) {
        }
    }

    @Test
    public void testListByNativeType() {
        assertNotNull(provider.listByNativeType("YAWL 2.2"));
        assertNotNull(provider.listAll());
        assertTrue(provider.listByNativeType("YAWL 2.2").size() == 1);
        assertTrue(provider.listAll().size() == 1);
    }



}
