package com.aventstack.extentreports.reporter;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Base;

public class LoggerBasicFileReporterConfigurationTest2 extends Base {

    @Test
    public void htmlReporterHasInitialConfig(Method method) {
        ExtentLoggerReporter logger = new ExtentLoggerReporter(method.getName() + ".html");
        Assert.assertFalse(logger.getConfigurationStore().isEmpty());
    }
    
    @Test
    public void testHtmlReporterUserConfigInitialCSSNull(Method method) {
        ExtentLoggerReporter logger = new ExtentLoggerReporter(method.getName() + ".html");
        String v = 	(String) logger.getConfigurationStore().getConfig("css");
        Assert.assertNull(v);
    }
    
    @Test
    public void testHtmlReporterUserConfigCSSHasValue(Method method) {
        ExtentLoggerReporter logger = new ExtentLoggerReporter(method.getName() + ".html");
        String css = ".dark{background:black;}";
        logger.config().setCSS(css);
        String v = (String) logger.getConfigurationStore().getConfig("css");
        Assert.assertFalse(v.isEmpty());
    }
    
    @Test
    public void testHtmlReporterUserConfigCSSValue(Method method) {
        ExtentLoggerReporter logger = new ExtentLoggerReporter(method.getName() + ".html");
        String css = ".dark{background:black;}";
        logger.config().setCSS(css);
        String v = (String) logger.getConfigurationStore().getConfig("css");
        Assert.assertEquals(v, css);
    }
    
    @Test
    public void testHtmlReporterUserConfigInitialJSNull(Method method) {
        ExtentLoggerReporter logger = new ExtentLoggerReporter(method.getName() + ".html");
        String v = (String) logger.getConfigurationStore().getConfig("js");
        Assert.assertNull(v);
    }
    
    @Test
    public void testHtmlReporterUserConfigJSHasValue(Method method) {
        ExtentLoggerReporter logger = new ExtentLoggerReporter(method.getName() + ".html");
        logger.config().setJS("alert('');");
        String v = (String) logger.getConfigurationStore().getConfig("js");
        Assert.assertFalse(v.isEmpty());
    }
    
    @Test
    public void testHtmlReporterUserConfigJSValue(Method method) {
        ExtentLoggerReporter logger = new ExtentLoggerReporter(method.getName() + ".html");
        String js = "alert('');";
        logger.config().setJS(js);
        String v = (String) logger.getConfigurationStore().getConfig("js");
        Assert.assertEquals(v, js);
    }
    
    @Test
    public void documentTitleInitialValueTest(Method method) {
        ExtentLoggerReporter logger = new ExtentLoggerReporter(method.getName() + ".html");
        Assert.assertFalse(logger.getConfigurationStore().getConfig("documentTitle").toString().isEmpty());
    }
    
    @Test
    public void documentTitleInitialUserValueTest(Method method) {
        ExtentLoggerReporter logger = new ExtentLoggerReporter(method.getName() + ".html");
        String v = (String) logger.getConfigurationStore().getConfig("documentTitle");
        Assert.assertNotNull(v);
    }
    
    @Test
    public void documentTitleUserValueTest(Method method) {
        ExtentLoggerReporter logger = new ExtentLoggerReporter(method.getName() + ".html");
        String docTitle = "test";
        logger.config().setDocumentTitle(docTitle);
        String v = (String) logger.getConfigurationStore().getConfig("documentTitle");
        Assert.assertEquals(v, docTitle);
    }
    
    @Test
    public void encodingInitialValueTest(Method method) {
        ExtentLoggerReporter logger = new ExtentLoggerReporter(method.getName() + ".html");
        Assert.assertTrue(logger.getConfigurationStore().getConfig("encoding").toString().equalsIgnoreCase("utf-8"));
    }
    
    @Test
    public void encodingInitialUserValueTest(Method method) {
        ExtentLoggerReporter logger = new ExtentLoggerReporter(method.getName() + ".html");
        String v = (String) logger.getConfigurationStore().getConfig("encoding");
        Assert.assertNotNull(v);
    }
    
    @Test
    public void encodingUserValueTest(Method method) {
        ExtentLoggerReporter logger = new ExtentLoggerReporter(method.getName() + ".html");
        String encoding = "utf-16";
        logger.config().setEncoding(encoding);
        String v = (String) logger.getConfigurationStore().getConfig("encoding");
        Assert.assertEquals(v, encoding);
    }
}
