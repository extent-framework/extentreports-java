package com.aventstack.extentreports.reporter;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class SparkReporterConfigTest {
    @Test
    public void configTest() {
        ExtentSparkReporter spark = new ExtentSparkReporter("target/spark.html");
        spark.config(
                ExtentSparkReporterConfig.builder()
                        .theme(Theme.DARK)
                        .documentTitle("MyReport")
                        .build());
        Assert.assertEquals(spark.getConf().getTheme(), Theme.DARK);
        Assert.assertEquals(spark.getConf().getDocumentTitle(), "MyReport");
    }
}
