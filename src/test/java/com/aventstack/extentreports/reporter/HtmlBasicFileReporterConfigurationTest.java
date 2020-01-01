package com.aventstack.extentreports.reporter;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Base;

public class HtmlBasicFileReporterConfigurationTest extends Base {

    @Test
    public void htmlReporterHasInitialConfig(Method method) {
        ExtentHtmlReporter html = new ExtentHtmlReporter(method.getName() + ".html");
        Assert.assertFalse(html.getConfigurationStore().isEmpty());
    }
    
    @Test
    public void testHtmlReporterUserConfigInitialCSSNull(Method method) {
        ExtentHtmlReporter html = new ExtentHtmlReporter(method.getName() + ".html");
        String v = (String) html.getConfigurationStore().getConfig("css");
        Assert.assertNull(v);
    }
    
    @Test
    public void testHtmlReporterUserConfigCSSHasValue(Method method) {
        ExtentHtmlReporter html = new ExtentHtmlReporter(method.getName() + ".html");
        String css = ".dark{background:black;}";
        html.config().setCSS(css);
        String v = (String) html.getConfigurationStore().getConfig("css");
        Assert.assertFalse(v.isEmpty());
    }
    
    @Test
    public void testHtmlReporterUserConfigCSSValue(Method method) {
        ExtentHtmlReporter html = new ExtentHtmlReporter(method.getName() + ".html");
        String css = ".dark{background:black;}";
        html.config().setCSS(css);
        String v = (String) html.getConfigurationStore().getConfig("css");
        Assert.assertEquals(v, css);
    }
    
    @Test
    public void testHtmlReporterUserConfigInitialJSNull(Method method) {
        ExtentHtmlReporter html = new ExtentHtmlReporter(method.getName() + ".html");
        String v = (String) html.getConfigurationStore().getConfig("js");
        Assert.assertNull(v);
    }
    
    @Test
    public void testHtmlReporterUserConfigJSHasValue(Method method) {
        ExtentHtmlReporter html = new ExtentHtmlReporter(method.getName() + ".html");
        html.config().setJS("alert('');");
        String v = (String) html.getConfigurationStore().getConfig("js");
        Assert.assertFalse(v.isEmpty());
    }
    
    @Test
    public void testHtmlReporterUserConfigJSValue(Method method) {
        ExtentHtmlReporter html = new ExtentHtmlReporter(method.getName() + ".html");
        String js = "alert('');";
        html.config().setJS(js);
        String v = (String) html.getConfigurationStore().getConfig("js");
        Assert.assertEquals(v, js);
    }
    
    @Test
    public void documentTitleInitialValueTest(Method method) {
        ExtentHtmlReporter html = new ExtentHtmlReporter(method.getName() + ".html");
        Assert.assertFalse(html.getConfigurationStore().getConfig("documentTitle").toString().isEmpty());
    }
    
    @Test
    public void documentTitleInitialUserValueTest(Method method) {
        ExtentHtmlReporter html = new ExtentHtmlReporter(method.getName() + ".html");
        String v = (String) html.getConfigurationStore().getConfig("documentTitle");
        Assert.assertNotNull(v);
    }
    
    @Test
    public void documentTitleUserValueTest(Method method) {
        ExtentHtmlReporter html = new ExtentHtmlReporter(method.getName() + ".html");
        String docTitle = "test";
        html.config().setDocumentTitle(docTitle);
        String v = (String) html.getConfigurationStore().getConfig("documentTitle");
        Assert.assertEquals(v, docTitle);
    }
    
    @Test
    public void encodingInitialValueTest(Method method) {
        ExtentHtmlReporter html = new ExtentHtmlReporter(method.getName() + ".html");
        Assert.assertTrue(html.getConfigurationStore().getConfig("encoding").toString().equalsIgnoreCase("utf-8"));
    }
    
    @Test
    public void encodingInitialUserValueTest(Method method) {
        ExtentHtmlReporter html = new ExtentHtmlReporter(method.getName() + ".html");
        String v = (String) html.getConfigurationStore().getConfig("encoding");
        Assert.assertNotNull(v);
    }
    
    @Test
    public void encodingUserValueTest(Method method) {
        ExtentHtmlReporter html = new ExtentHtmlReporter(method.getName() + ".html");
        String encoding = "utf-16";
        html.config().setEncoding(encoding);
        String v = (String) html.getConfigurationStore().getConfig("encoding");
        Assert.assertEquals(v, encoding);
    }
}
