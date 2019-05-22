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
        pp.setParameter(ProcessParams.WORKING_DIR_PROPERTY, uri.toString());
        assertEquals(uri, pp.getWorkingDir());
    }
    
}
