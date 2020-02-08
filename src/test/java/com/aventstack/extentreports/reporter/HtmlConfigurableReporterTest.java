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
    
    private ExtentSparkReporter spark;
    
    @BeforeClass
    public void beforeClass() {
        spark = new ExtentSparkReporter("Extent.html");
        spark.loadXMLConfig(CONFIG_PATH);
    }
    
    @Test
    public void testTheme(Method method) {
        Assert.assertEquals(spark.getConfigurationStore().getConfig("theme"), THEME);
    }
    
    @Test
    public void testEncoding(Method method) {
        Assert.assertTrue(String.valueOf(spark.getConfigurationStore().getConfig("encoding")).equalsIgnoreCase(ENCODING));
    }
    
    @Test
    public void testDocumentTitle(Method method) {
        Assert.assertEquals(spark.getConfigurationStore().getConfig("documentTitle"), DOCUMENT_TITLE);
    }
    
    @Test
    public void testReportName(Method method) {
        Assert.assertEquals(spark.getConfigurationStore().getConfig("reportName"), REPORT_NAME);
    }
    
    @Test
    public void testTimestampFormat(Method method) {
        Assert.assertEquals(spark.getConfigurationStore().getConfig("timeStampFormat"), TIMESTAMP_FORMAT);
    }
    
    @Test
    public void testCSS(Method method) {
        Assert.assertTrue(spark.getConfigurationStore().getConfig("styles").toString().contains(CSS));
    }
    
    @Test
    public void testJS(Method method) {
        Assert.assertTrue(spark.getConfigurationStore().getConfig("scripts").toString().contains(JS));
    }
    
}
