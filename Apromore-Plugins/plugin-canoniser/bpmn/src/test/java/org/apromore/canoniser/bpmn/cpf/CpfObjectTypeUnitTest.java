package org.apromore.canoniser.bpmn.cpf;

// Third party packages
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

/**
 * Test suite for {@link CpfObjectType}.
 *
 * @author <a href="mailto:simon.raboczi@uqconnect.edu.au">Simon Raboczi</a>
 */
public class CpfObjectTypeUnitTest {

    /** Test instance. */
    CpfObjectTypeImpl object;

    /** Populate {@link #object} with a freshly-constructed {@link CpfObjectType}. */
    @Before
    public void initializeObject() {
        object = (CpfObjectTypeImpl) new ObjectFactory().createObjectType();
    }

    /**
     * Test {@link CpfTaskType#isIsCollection) and {@link CpfTaskType#setIsCollection).
     */
    @Test
    public void testIsCollection() throws Exception {
        assertFalse(object.isIsCollection());

        object.setIsCollection(true);
        assertTrue(object.isIsCollection());

        object.setIsCollection(false);
        assertFalse(object.isIsCollection());
    }
     
    /**
     * Test {@link CpfTaskType#getOriginalName) and {@link CpfTaskType#setOriginalName).
     */
    @Test
    public void testOriginalName() throws Exception {
        assertNull(object.getOriginalName());

        object.setOriginalName("test");
        assertEquals("test", object.getOriginalName());

        object.setOriginalName(null);
        assertNull(object.getOriginalName());
    }
}
