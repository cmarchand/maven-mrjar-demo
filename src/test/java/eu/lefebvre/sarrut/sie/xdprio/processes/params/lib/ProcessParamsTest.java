/*
 * Copyright Editions Lefebvre-Sarrut 2019
 */
package eu.lefebvre.sarrut.sie.xdprio.processes.params.lib;

import top.marchand.maven.test.utils.TestUtils;
import java.io.File;
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
        File targetDir = new File(new File(TestUtils.class.getClassLoader().getResource("").getFile()).getParentFile().getParentFile(), "target");
        File testFile = new File(targetDir, "test.xml");
        pp.setParameter(ProcessParams.WORKING_URI_PROPERTY, testFile.getAbsolutePath());
        assertEquals(testFile.toURI(), pp.getWorkingDir());
    }
    
    @Test
    public void testNonUriParam() throws Exception {
        ProcessParams pp = new ProcessParams();
        pp.setParameter("toto", "pouet");
        assertEquals("toto has not pouet has value", "pouet", pp.getParameterValue("toto"));
        assertNull("working dir is not null", pp.getWorkingDir());
    }
}
