package com.aventstack.extentreports.reporter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;

public class SparkTagTest {
    private static final String PATH = "target/spark.html";
    private static final String TAG = "TAG#1234";

    private ExtentReports extent;

    @BeforeMethod
    public void before() {
        extent = new ExtentReports();
        extent.attachReporter(new ExtentSparkReporter(PATH));
    }

    @Test
    public void testTag() throws IOException {
        extent.createTest("Test").assignCategory(TAG);
        extent.flush();
        Assert.assertTrue(Files.readAllLines(new File(PATH).toPath()).stream()
                .anyMatch(x -> x.contains(TAG)));
    }

    @Test
    public void nodeTag() throws IOException {
        extent.createTest("Test").createNode("Node").assignCategory(TAG);
        extent.flush();
        Assert.assertTrue(Files.readAllLines(new File(PATH).toPath()).stream()
                .anyMatch(x -> x.contains(TAG)));
    }
}
