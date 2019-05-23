/*
 * Copyright Editions Lefebvre-Sarrut 2019
 */
package eu.lefebvre.sarrut.sie.xdprio.processes.params.lib;

import top.marchand.maven.test.utils.TestUtils;
import java.io.File;
import java.net.URI;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author cmarchand
 */
public class ProcessParamsTest {
    
    @Test
    public void testWorkingDir() throws Exception {
        ProcessParams pp = new ProcessParams();
        File targetDir = TestUtils.getTargetDirectory();
        File testFile = new File(targetDir, "test.xml");
        URI uri = testFile.toURI();
        pp.setParameter(ProcessParams.WORKING_URI_PROPERTY, uri.toString());
        assertEquals(uri, pp.getWorkingDir());
    }
    
    @Test
    public void testNonUriParam() throws Exception {
        ProcessParams pp = new ProcessParams();
        pp.setParameter("toto", "pouet");
        assertEquals("toto has not pouet has value", "pouet", pp.getParameterValue("toto"));
        assertNull("working dir is not null", pp.getWorkingDir());
    }
}
