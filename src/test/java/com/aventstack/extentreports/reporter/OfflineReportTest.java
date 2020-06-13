package com.aventstack.extentreports.reporter;

import java.io.File;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Base;

public class OfflineReportTest extends Base {

    private static final String OUTPUT_PATH = "target/spark/";
    private static final String LOGGER_SCRIPTS_JS = OUTPUT_PATH + "spark-script.js";
    private static final String LOGGER_STYLES_CSS = OUTPUT_PATH + "spark-style.css";

    @BeforeClass
    public void createOfflineResx() {
        ExtentSparkReporter spark = new ExtentSparkReporter("target/");
        spark.config().enableOfflineMode(true);
        extent.attachReporter(spark);
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
