/*
 * Copyright Editions Lefebvre-Sarrut 2019
 */
package eu.lefebvre.sarrut.sie.xdprio.processes.params.lib;

import top.marchand.maven.test.utils.TestUtils;
import java.io.File;
import java.net.URI;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.runners.MethodSorters;

/**
 *
 * @author cmarchand
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class ProcessParamsUtilsTest {
    private static URI testUri;
    
    @BeforeClass
    public static void before() throws Exception {
        System.out.println("Before");
        File targetDir = TestUtils.getTargetDirectory();
        File testFile = new File(targetDir, "test.xml");
        testUri = testFile.toURI();
    }
    
    @Test
    public void aaValidateUri() throws Exception {
        System.out.println("validateUri");
        assertTrue(testUri.isAbsolute());
    }
    @Test
    public void llTestWriting() throws Exception {
        System.out.println("testWriting");
        ProcessParamsUtils ppu = new ProcessParamsUtils();
        ppu.newProcessParams();
        ppu.addParameter("toto", "pouet");
        ppu.serializeParams(testUri);
        assertTrue("test.xml doesn't exist", new File(testUri).exists());
    }
    
    @Test
    public void mmTestReading() throws Exception {
        System.out.println("testReading");
        assertTrue(testUri.isAbsolute());
        ProcessParams pp = ProcessParamsUtils.parseParameterFile(testUri);
        assertNotNull(pp);
        assertEquals("pouet", pp.getParameterValue("toto"));
    }
}
