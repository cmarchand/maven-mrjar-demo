/*
 * Copyright Editions Lefebvre-Sarrut 2019
 */
package eu.lefebvre.sarrut.sie.xdprio.processes.params.lib;

import top.marchand.maven.test.utils.TestUtils;
import java.io.File;
import java.net.URI;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author cmarchand
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProcessParamsUtilsTest {
    
    @Test
    public void aaTestWriting() throws Exception {
        ProcessParamsUtils ppu = new ProcessParamsUtils();
        ppu.newProcessParams();
        ppu.addParameter("toto", "pouet");
        File targetDir = TestUtils.getTargetDirectory();
        File testFile = new File(targetDir, "test.xml");
        URI uri = testFile.toURI();
        ppu.serializeParams(uri);
        assertTrue("test.xml doesn't exist", testFile.exists());
    }
    
    @Test
    public void bbTestReading() throws Exception {
        File targetDir = TestUtils.getTargetDirectory();
        File testFile = new File(targetDir, "test.xml");
        URI uri = testFile.toURI();
        ProcessParams pp = ProcessParamsUtils.parseParameterFile(uri);
        assertNotNull(pp);
        assertEquals("pouet", pp.getParameterValue("toto"));
    }
}
