package com.aventstack.extentreports.reporter;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Base;

public class HtmlBasicFileReporterConfigurationTest extends Base {

    @Test
    public void htmlReporterHasInitialConfig(Method method) {
        ExtentHtmlReporter html = new ExtentHtmlReporter(method.getName() + ".html");
        Assert.assertFalse(html.getConfigContext().getConfigList().isEmpty());
    }
    
    @Test
    public void testHtmlReporterUserConfigInitialCSSNull(Method method) {
        ExtentHtmlReporter html = new ExtentHtmlReporter(method.getName() + ".html");
        String v = html.config().getConfigMap().get("styles");
        Assert.assertNull(v);
    }
    
    @Test
    public void testHtmlReporterUserConfigCSSHasValue(Method method) {
        ExtentHtmlReporter html = new ExtentHtmlReporter(method.getName() + ".html");
        String css = ".dark{background:black;}";
        html.config().setCSS(css);
        String v = html.config().getConfigMap().get("styles");
        Assert.assertFalse(v.isEmpty());
    }
    
    @Test
    public void testHtmlReporterUserConfigCSSValue(Method method) {
        ExtentHtmlReporter html = new ExtentHtmlReporter(method.getName() + ".html");
        String css = ".dark{background:black;}";
        html.config().setCSS(css);
        String v = html.config().getConfigMap().get("styles");
        Assert.assertEquals(v, css);
    }
    
    @Test
    public void testHtmlReporterUserConfigInitialJSNull(Method method) {
        ExtentHtmlReporter html = new ExtentHtmlReporter(method.getName() + ".html");
        String v = html.config().getConfigMap().get("scripts");
        Assert.assertNull(v);
    }
    
    @Test
    public void testHtmlReporterUserConfigJSHasValue(Method method) {
        ExtentHtmlReporter html = new ExtentHtmlReporter(method.getName() + ".html");
        html.config().setJS("alert('');");
        String v = html.config().getConfigMap().get("scripts");
        Assert.assertFalse(v.isEmpty());
    }
    
    @Test
    public void testHtmlReporterUserConfigJSValue(Method method) {
        ExtentHtmlReporter html = new ExtentHtmlReporter(method.getName() + ".html");
        String js = "alert('');";
        html.config().setJS(js);
        String v = html.config().getConfigMap().get("scripts");
        Assert.assertEquals(v, js);
    }
    
    @Test
    public void documentTitleInitialValueTest(Method method) {
        ExtentHtmlReporter html = new ExtentHtmlReporter(method.getName() + ".html");
        Assert.assertFalse(html.getConfigContext().getValue("documentTitle").toString().isEmpty());
    }
    
    @Test
    public void documentTitleInitialUserValueTest(Method method) {
        ExtentHtmlReporter html = new ExtentHtmlReporter(method.getName() + ".html");
        String v = html.config().getConfigMap().get("documentTitle");
        Assert.assertNull(v);
    }
    
    @Test
    public void documentTitleUserValueTest(Method method) {
        ExtentHtmlReporter html = new ExtentHtmlReporter(method.getName() + ".html");
        String docTitle = "test";
        html.config().setDocumentTitle(docTitle);
        String v = html.config().getConfigMap().get("documentTitle");
        Assert.assertEquals(v, docTitle);
    }
    
    @Test
    public void encodingInitialValueTest(Method method) {
        ExtentHtmlReporter html = new ExtentHtmlReporter(method.getName() + ".html");
        Assert.assertTrue(html.getConfigContext().getValue("encoding").toString().equalsIgnoreCase("utf-8"));
    }
    
    @Test
    public void encodingInitialUserValueTest(Method method) {
        ExtentHtmlReporter html = new ExtentHtmlReporter(method.getName() + ".html");
        String v = html.config().getConfigMap().get("encoding");
        Assert.assertNull(v);
    }
    
    @Test
    public void encodingUserValueTest(Method method) {
        ExtentHtmlReporter html = new ExtentHtmlReporter(method.getName() + ".html");
        String encoding = "utf-16";
        html.config().setEncoding(encoding);
        String v = html.config().getConfigMap().get("encoding");
        Assert.assertEquals(v, encoding);
    }
}
