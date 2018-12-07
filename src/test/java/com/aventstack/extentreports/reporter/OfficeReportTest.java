package com.aventstack.extentreports.reporter;

import java.io.File;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Base;

public class OfficeReportTest extends Base {

    private static final String OUTPUT_PATH = "target/logger/";
    private static final String ATTR_JS = OUTPUT_PATH + "attr.js";
    private static final String DASHBOARD_JS = OUTPUT_PATH + "dashboard.js"; 
    private static final String LOGGER_SCRIPTS_JS = OUTPUT_PATH + "logger-scripts.js";
    private static final String LOGGER_STYLES_CSS = OUTPUT_PATH + "logger-style.css";
    
    @BeforeClass
    public void createOfflineResx() {
        ExtentLoggerReporter logger = new ExtentLoggerReporter("target/");
        logger.config().enableOfflineMode(true);
        extent.attachReporter(logger);
    }
   
    @Test
    public void verifyAttrJsExists(Method method) {
        Assert.assertTrue(new File(ATTR_JS).exists());
    }
    
    @Test
    public void verifyDashboardJsExists(Method method) {
        Assert.assertTrue(new File(DASHBOARD_JS).exists());
    }
    
    @Test
    public void verifyScriptsExists(Method method) {
        Assert.assertTrue(new File(LOGGER_SCRIPTS_JS).exists());
    }
    
    @Test
    public void verifyStylesExists(Method method) {
        Assert.assertTrue(new File(LOGGER_STYLES_CSS).exists());
    }
}
