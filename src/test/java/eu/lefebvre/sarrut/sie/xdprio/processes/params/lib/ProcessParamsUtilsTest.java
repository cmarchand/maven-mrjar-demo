/*
 * Copyright Editions Lefebvre-Sarrut 2019
 */
package eu.lefebvre.sarrut.sie.xdprio.processes.params.lib;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import top.marchand.maven.test.utils.TestUtils;
import java.io.File;
import java.net.URI;
import java.nio.charset.Charset;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Elements;
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
        ProcessParamsUtils ppu = new ProcessParamsUtils();
        ppu.newProcessParams();
        ppu.addParameter("toto", "pouet");
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ppu.serializeParams(baos);
        Builder builder = new Builder();
        Document doc = builder.build(new ByteArrayInputStream(baos.toByteArray()));
        Element rootElement = doc.getRootElement();
        assertEquals("Root element isn't a properties", "properties", rootElement.getLocalName());
        Elements els = rootElement.getChildElements();
        assertEquals("First child is not a comment", "comment", els.get(0).getLocalName());
        Element entry = els.get(1);
        assertEquals("Second element is not a entry", "entry", entry.getLocalName());
        assertEquals("entry key is not toto", "toto", entry.getAttributeValue("key"));
        assertEquals("entry value is not pouet", "pouet", entry.getValue());
    }
    
    @Test
    public void mmTestReading() throws Exception {
        String data = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
"<!DOCTYPE properties SYSTEM \"http://java.sun.com/dtd/properties.dtd\">\n" +
"<properties>\n" +
"<comment>Generated by eu.lefebvre.sarrut.sie.xdprio.processes.params.lib.ProcessParamsUtils on 2019-05-23T21:48:34.209954+02:00</comment>\n" +
"<entry key=\"toto\">pouet</entry>\n" +
"</properties>";
        ByteArrayInputStream baos = new ByteArrayInputStream(data.getBytes(Charset.forName("UTF-8")));
        ProcessParams pp = ProcessParamsUtils.parseParameterStream(baos);
        assertNotNull(pp);
        assertEquals("pouet", pp.getParameterValue("toto"));
    }
}
