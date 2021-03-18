package com.aventstack.extentreports.reporter;

import java.io.File;
import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;

public class SparkReporterConfigTest {
    private static final String FILE_PATH = "target/spark.html";

    @Test
    public void configTest() {
        ExtentSparkReporter spark = new ExtentSparkReporter(FILE_PATH);
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
        ExtentSparkReporter spark = new ExtentSparkReporter(FILE_PATH);
        spark.loadJSONConfig(new File("src/test/resources/config/spark-config.json"));
        Assert.assertEquals(spark.getConf().getTheme(), Theme.STANDARD);
        Assert.assertEquals(spark.getConf().getDocumentTitle(), "Test1");
        Assert.assertEquals(spark.getConf().getEncoding(), "utf-8");
    }

    @Test
    public void loadJSONConfigStringTest() throws IOException {
        String json = "{ 'theme': 'DARK', 'documentTitle': 'Extent', 'encoding': 'utf-8' }";
        ExtentSparkReporter spark = new ExtentSparkReporter(FILE_PATH);
        spark.loadJSONConfig(json);
        Assert.assertEquals(spark.getConf().getTheme(), Theme.DARK);
        Assert.assertEquals(spark.getConf().getDocumentTitle(), "Extent");
        Assert.assertEquals(spark.getConf().getEncoding(), "utf-8");
    }

    @Test
    public void loadXMLConfigFileTest() throws IOException {
        ExtentSparkReporter spark = new ExtentSparkReporter(FILE_PATH);
        spark.loadXMLConfig(new File("src/test/resources/config/spark-config.xml"));
        Assert.assertEquals(spark.getConf().getReportName(), "Test2");
        Assert.assertEquals(spark.getConf().getDocumentTitle(), "Build 1");
        Assert.assertEquals(spark.getConf().getEncoding(), "utf-16");
    }

    @Test
    public void loadXMLConfigPathTest() throws IOException {
        ExtentSparkReporter spark = new ExtentSparkReporter(FILE_PATH);
        spark.loadXMLConfig("src/test/resources/config/spark-config.xml");
        Assert.assertEquals(spark.getConf().getReportName(), "Test2");
        Assert.assertEquals(spark.getConf().getDocumentTitle(), "Build 1");
        Assert.assertEquals(spark.getConf().getEncoding(), "utf-16");
    }
}
