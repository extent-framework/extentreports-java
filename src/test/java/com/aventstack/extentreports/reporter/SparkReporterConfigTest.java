package com.aventstack.extentreports.reporter;

import java.io.File;
import java.io.IOException;

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

    @Test
    public void loadJSONConfigFileTest() throws IOException {
        ExtentSparkReporter spark = new ExtentSparkReporter("target/spark.html");
        spark.loadJSONConfig(new File("config/spark-config.json"));
        Assert.assertEquals(spark.getConf().getTheme(), Theme.STANDARD);
        Assert.assertEquals(spark.getConf().getDocumentTitle(), "ExtentReports");
        Assert.assertEquals(spark.getConf().getEncoding(), "utf-8");
    }

    @Test
    public void loadJSONConfigStringTest() throws IOException {
        String json = "{ 'theme': 'DARK', 'documentTitle': 'Extent', 'encoding': 'utf-8' }";
        ExtentSparkReporter spark = new ExtentSparkReporter("target/spark.html");
        spark.loadJSONConfig(json);
        Assert.assertEquals(spark.getConf().getTheme(), Theme.DARK);
        Assert.assertEquals(spark.getConf().getDocumentTitle(), "Extent");
        Assert.assertEquals(spark.getConf().getEncoding(), "utf-8");
    }

    @Test
    public void loadXMLConfigFileTest() throws IOException {
        ExtentSparkReporter spark = new ExtentSparkReporter("target/spark.html");
        spark.loadXMLConfig(new File("config/spark-config.xml"));
        Assert.assertEquals(spark.getConf().getReportName(), "Build 1");
        Assert.assertEquals(spark.getConf().getDocumentTitle(), "Extent Framework");
        Assert.assertEquals(spark.getConf().getEncoding(), "UTF-8");
    }

    @Test
    public void loadXMLConfigPathTest() throws IOException {
        ExtentSparkReporter spark = new ExtentSparkReporter("target/spark.html");
        spark.loadXMLConfig("config/spark-config.xml");
        Assert.assertEquals(spark.getConf().getReportName(), "Build 1");
        Assert.assertEquals(spark.getConf().getDocumentTitle(), "Extent Framework");
        Assert.assertEquals(spark.getConf().getEncoding(), "UTF-8");
    }
}
