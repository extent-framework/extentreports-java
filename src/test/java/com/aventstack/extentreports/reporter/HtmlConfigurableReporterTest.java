package com.aventstack.extentreports.reporter;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Base;

public class HtmlConfigurableReporterTest extends Base {
    
    private static final String CONFIG_PATH = "src/test/resources/html-config.xml";
    private static final String THEME = "dark";
    private static final String ENCODING = "utf-8";
    private static final String DOCUMENT_TITLE = "Extent Framework";
    private static final String REPORT_NAME = "Build 1";
    private static final String TIMESTAMP_FORMAT = "MMM dd, yyyy HH:mm:ss";
    private static final String CSS = ".test.active { border: 1px solid #ccc; }";
    private static final String JS = "$('.test').click(function() { console.log('test'); });";    
    
    private ExtentHtmlReporter html;
    
    @BeforeClass
    public void beforeClass() {
        html = new ExtentHtmlReporter("Extent.html");
        html.loadXMLConfig(CONFIG_PATH);
    }
    
    @Test
    public void testTheme() {
        Assert.assertEquals(html.getConfigContext().getValue("theme"), THEME);
    }
    
    @Test
    public void testEncoding() {
    	html.getConfigContext().printMapForTest();
        Assert.assertTrue(String.valueOf(html.getConfigContext().getValue("encoding")).equalsIgnoreCase(ENCODING));
    }
    
    @Test
    public void testDocumentTitle() {
        Assert.assertEquals(html.getConfigContext().getValue("documentTitle"), DOCUMENT_TITLE);
    }
    
    @Test
    public void testReportName() {
        Assert.assertEquals(html.getConfigContext().getValue("reportName"), REPORT_NAME);
    }
    
    @Test
    public void testTimestampFormat() {
        Assert.assertEquals(html.getConfigContext().getValue("timeStampFormat"), TIMESTAMP_FORMAT);
    }
    
    @Test
    public void testCSS() {
        Assert.assertTrue(html.getConfigContext().getValue("styles").toString().contains(CSS));
    }
    
    @Test
    public void testJS() {
        Assert.assertTrue(html.getConfigContext().getValue("scripts").toString().contains(JS));
    }
    
}
