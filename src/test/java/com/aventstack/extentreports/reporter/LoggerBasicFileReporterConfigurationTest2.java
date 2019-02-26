package com.aventstack.extentreports.reporter;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Base;

public class LoggerBasicFileReporterConfigurationTest2 extends Base {

    @Test
    public void htmlReporterHasInitialConfig(Method method) {
        ExtentLoggerReporter logger = new ExtentLoggerReporter(method.getName() + ".html");
        Assert.assertFalse(logger.getConfigContext().isEmpty());
    }
    
    @Test
    public void testHtmlReporterUserConfigInitialCSSNull(Method method) {
        ExtentLoggerReporter logger = new ExtentLoggerReporter(method.getName() + ".html");
        String v = logger.config().getConfigMap().get("styles");
        Assert.assertNull(v);
    }
    
    @Test
    public void testHtmlReporterUserConfigCSSHasValue(Method method) {
        ExtentLoggerReporter logger = new ExtentLoggerReporter(method.getName() + ".html");
        String css = ".dark{background:black;}";
        logger.config().setCSS(css);
        String v = logger.config().getConfigMap().get("styles");
        Assert.assertFalse(v.isEmpty());
    }
    
    @Test
    public void testHtmlReporterUserConfigCSSValue(Method method) {
        ExtentLoggerReporter logger = new ExtentLoggerReporter(method.getName() + ".html");
        String css = ".dark{background:black;}";
        logger.config().setCSS(css);
        String v = logger.config().getConfigMap().get("styles");
        Assert.assertEquals(v, css);
    }
    
    @Test
    public void testHtmlReporterUserConfigInitialJSNull(Method method) {
        ExtentLoggerReporter logger = new ExtentLoggerReporter(method.getName() + ".html");
        String v = logger.config().getConfigMap().get("scripts");
        Assert.assertNull(v);
    }
    
    @Test
    public void testHtmlReporterUserConfigJSHasValue(Method method) {
        ExtentLoggerReporter logger = new ExtentLoggerReporter(method.getName() + ".html");
        logger.config().setJS("alert('');");
        String v = logger.config().getConfigMap().get("scripts");
        Assert.assertFalse(v.isEmpty());
    }
    
    @Test
    public void testHtmlReporterUserConfigJSValue(Method method) {
        ExtentLoggerReporter logger = new ExtentLoggerReporter(method.getName() + ".html");
        String js = "alert('');";
        logger.config().setJS(js);
        String v = logger.config().getConfigMap().get("scripts");
        Assert.assertEquals(v, js);
    }
    
    @Test
    public void documentTitleInitialValueTest(Method method) {
        ExtentLoggerReporter logger = new ExtentLoggerReporter(method.getName() + ".html");
        Assert.assertFalse(logger.getConfigContext().getValue("documentTitle").toString().isEmpty());
    }
    
    @Test
    public void documentTitleInitialUserValueTest(Method method) {
        ExtentLoggerReporter logger = new ExtentLoggerReporter(method.getName() + ".html");
        String v = logger.config().getConfigMap().get("documentTitle");
        Assert.assertNull(v);
    }
    
    @Test
    public void documentTitleUserValueTest(Method method) {
        ExtentLoggerReporter logger = new ExtentLoggerReporter(method.getName() + ".html");
        String docTitle = "test";
        logger.config().setDocumentTitle(docTitle);
        String v = logger.config().getConfigMap().get("documentTitle");
        Assert.assertEquals(v, docTitle);
    }
    
    @Test
    public void encodingInitialValueTest(Method method) {
        ExtentLoggerReporter logger = new ExtentLoggerReporter(method.getName() + ".html");
        Assert.assertTrue(logger.getConfigContext().getValue("encoding").toString().equalsIgnoreCase("utf-8"));
    }
    
    @Test
    public void encodingInitialUserValueTest(Method method) {
        ExtentLoggerReporter logger = new ExtentLoggerReporter(method.getName() + ".html");
        String v = logger.config().getConfigMap().get("encoding");
        Assert.assertNull(v);
    }
    
    @Test
    public void encodingUserValueTest(Method method) {
        ExtentLoggerReporter logger = new ExtentLoggerReporter(method.getName() + ".html");
        String encoding = "utf-16";
        logger.config().setEncoding(encoding);
        String v = logger.config().getConfigMap().get("encoding");
        Assert.assertEquals(v, encoding);
    }
}
