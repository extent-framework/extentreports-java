package com.aventstack.extentreports.reporter;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.reporter.configuration.Protocol;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class HtmlRichViewReporterConfigurationTest {

    @Test
    public void testHtmlReporterUserConfigEnableTimelineEnabled(Method method) {
        ExtentSparkReporter spark = new ExtentSparkReporter(method.getName() + ".html");
        spark.config().enableTimeline(true);
        String v = (String) spark.getConfigurationStore().getConfig("enableTimeline");
        Assert.assertEquals(v, String.valueOf(true));
    }
    
    @Test
    public void testHtmlReporterUserConfigEnableTimelineDisabled(Method method) {
        ExtentSparkReporter spark = new ExtentSparkReporter(method.getName() + ".html");
        spark.config().enableTimeline(false);
        String v = (String) spark.getConfigurationStore().getConfig("enableTimeline");
        Assert.assertEquals(v, String.valueOf(false));
    }
    
    @Test
    public void testHtmlReporterUserConfigDetaultProtocol(Method method) {
        ExtentSparkReporter spark = new ExtentSparkReporter(method.getName() + ".html");
        String v = (String) spark.getConfigurationStore().getConfig("protocol");
        Assert.assertEquals(Enum.valueOf(Protocol.class, v.toUpperCase()), Protocol.HTTPS);
    }
    
    @Test
    public void testHtmlReporterUserConfigProtocolSetting(Method method) {
        ExtentSparkReporter spark = new ExtentSparkReporter(method.getName() + ".html");
        spark.config().setProtocol(Protocol.HTTP);
        Object p = spark.getConfigurationStore().getConfig("protocol");
        Protocol v = Protocol.valueOf(String.valueOf(p).toUpperCase());
        Assert.assertEquals(v, Protocol.HTTP);
    }
    
    @Test
    public void testHtmlReporterUserConfigDetaultTheme(Method method) {
        ExtentSparkReporter spark = new ExtentSparkReporter(method.getName() + ".html");
        Object t = spark.getConfigurationStore().getConfig("theme");
        Theme theme = Theme.valueOf(String.valueOf(t).toUpperCase());
        Assert.assertEquals(theme, Theme.STANDARD);
    }
    
    @Test
    public void testHtmlReporterUserConfigThemeSetting(Method method) {
        ExtentSparkReporter spark = new ExtentSparkReporter(method.getName() + ".html");
        spark.config().setTheme(Theme.DARK);
        Object t = spark.config().getConfigurationStore().getConfig("theme");
        Theme theme = Theme.valueOf(String.valueOf(t).toUpperCase());
        Assert.assertEquals(theme, Theme.DARK);
    }
    
}
