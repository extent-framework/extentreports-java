package com.aventstack.extentreports.reporter;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class HtmlRichViewReporterConfigurationTest {

    @Test
    public void testHtmlReporterUserConfigEnableTimelineEnabled(Method method) {
        ExtentHtmlReporter html = new ExtentHtmlReporter(method.getName() + ".html");
        html.config().enableTimeline(true);
        String v = html.config().getConfigMap().get("enableTimeline");
        Assert.assertEquals(v, String.valueOf(true));
    }
    
    @Test
    public void testHtmlReporterUserConfigEnableTimelineDisabled(Method method) {
        ExtentHtmlReporter html = new ExtentHtmlReporter(method.getName() + ".html");
        html.config().enableTimeline(false);
        String v = html.config().getConfigMap().get("enableTimeline");
        Assert.assertEquals(v, String.valueOf(false));
    }
    
    @Test
    public void testHtmlReporterUserConfigAutoConfigMediaEnabled(Method method) {
        ExtentHtmlReporter html = new ExtentHtmlReporter(method.getName() + ".html");
        html.config().setAutoCreateRelativePathMedia(true);
        String v = html.config().getConfigMap().get("autoCreateRelativePathMedia");
        Assert.assertEquals(v, String.valueOf(true));
    }
    
    @Test
    public void testHtmlReporterUserConfigAutoConfigMediaDisabled(Method method) {
        ExtentHtmlReporter html = new ExtentHtmlReporter(method.getName() + ".html");
        html.config().setAutoCreateRelativePathMedia(false);
        String v = html.config().getConfigMap().get("autoCreateRelativePathMedia");
        Assert.assertEquals(v, String.valueOf(false));
    }
    
    @Test
    public void testHtmlReporterUserConfigDetaultProtocol(Method method) {
        ExtentHtmlReporter html = new ExtentHtmlReporter(method.getName() + ".html");
        String v = (String) html.getConfigContext().getValue("protocol");
        Assert.assertEquals(Enum.valueOf(Protocol.class, v.toUpperCase()), Protocol.HTTPS);
    }
    
    @Test
    public void testHtmlReporterUserConfigProtocolSetting(Method method) {
        ExtentHtmlReporter html = new ExtentHtmlReporter(method.getName() + ".html");
        html.config().setProtocol(Protocol.HTTP);
        String v = html.config().getConfigMap().get("protocol");
        Assert.assertEquals(Enum.valueOf(Protocol.class, v.toUpperCase()), Protocol.HTTP);
    }
    
    @Test
    public void testHtmlReporterUserConfigDetaultTheme(Method method) {
        ExtentHtmlReporter html = new ExtentHtmlReporter(method.getName() + ".html");
        String v = (String) html.getConfigContext().getValue("theme");
        Assert.assertEquals(Enum.valueOf(Theme.class, v.toUpperCase()), Theme.STANDARD);
    }
    
    @Test
    public void testHtmlReporterUserConfigThemeSetting(Method method) {
        ExtentHtmlReporter html = new ExtentHtmlReporter(method.getName() + ".html");
        html.config().setTheme(Theme.DARK);
        String v = html.config().getConfigMap().get("theme");
        Assert.assertEquals(Enum.valueOf(Theme.class, v.toUpperCase()), Theme.DARK);
    }
    
}
