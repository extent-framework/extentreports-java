package com.aventstack.extentreports.reporter;

import java.io.File;
import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;

public class SparkReporterTest {
    private static final String FILE_PATH = "target/spark/";
    private static final String FILE_NAME = "spark.html";
    private static final String PARENT = "Parent";
    private static final String CHILD = "Child";
    private static final String GRANDCHILD = "Grandchild";

    private final String path() {
        return FILE_PATH + Calendar.getInstance().getTimeInMillis() + FILE_NAME;
    }

    @Test
    public void createsReportWithNoTestsInitPath() {
        ExtentReports extent = new ExtentReports();
        String path = path();
        ExtentSparkReporter spark = new ExtentSparkReporter(path);
        extent.attachReporter(spark);
        extent.flush();
        Assert.assertTrue(new File(path).exists());
    }

    @Test
    public void createsReportWithNoTestsInitFile() {
        ExtentReports extent = new ExtentReports();
        String path = path();
        ExtentSparkReporter spark = new ExtentSparkReporter(new File(path));
        extent.attachReporter(spark);
        extent.flush();
        Assert.assertTrue(new File(path).exists());
    }

    @Test
    public void reportContainsTestsAndNodes() {
        ExtentReports extent = new ExtentReports();
        String path = path();
        ExtentSparkReporter spark = new ExtentSparkReporter(path);
        extent.attachReporter(spark);
        extent.createTest(PARENT)
                .createNode(CHILD)
                .createNode(GRANDCHILD)
                .pass("Pass");
        extent.flush();
        Assert.assertTrue(new File(path).exists());
        Assert.assertEquals(spark.getReport().getTestList().size(), 1);
        Assert.assertEquals(spark.getReport().getTestList().get(0).getName(), PARENT);
        Assert.assertEquals(spark.getReport().getTestList().get(0).getChildren().size(), 1);
        Assert.assertEquals(spark.getReport().getTestList().get(0).getChildren().get(0).getName(), CHILD);
        Assert.assertEquals(spark.getReport().getTestList().get(0).getChildren().get(0).getChildren().size(), 1);
        Assert.assertEquals(spark.getReport().getTestList().get(0).getChildren().get(0).getChildren().get(0).getName(),
                GRANDCHILD);
    }

    @Test
    public void statusFilterable() {
        ExtentReports extent = new ExtentReports();
        String path = path();
        ExtentSparkReporter spark = new ExtentSparkReporter(path)
                .filter()
                .statusFilter()
                .as(new Status[]{Status.FAIL})
                .apply();
        extent.attachReporter(spark);
        extent.createTest(PARENT).pass("Pass");
        extent.createTest(CHILD).fail("Fail");
        extent.flush();
        Assert.assertEquals(spark.getReport().getTestList().size(), 1);
        Assert.assertEquals(spark.getReport().getTestList().get(0).getName(), CHILD);
    }

    @Test
    public void statusFilterableNode() {
        ExtentReports extent = new ExtentReports();
        String path = path();
        ExtentSparkReporter spark = new ExtentSparkReporter(path)
                .filter()
                .statusFilter()
                .as(new Status[]{Status.FAIL})
                .apply();
        extent.attachReporter(spark);
        extent.createTest(PARENT).pass("Pass");
        extent.createTest(CHILD).pass("Pass")
                .createNode(GRANDCHILD).fail("Fail");
        extent.flush();
        Assert.assertEquals(spark.getReport().getTestList().size(), 1);
        Assert.assertEquals(spark.getReport().getTestList().get(0).getName(), CHILD);
        Assert.assertEquals(spark.getReport().getTestList().get(0).getChildren().size(), 1);
        Assert.assertEquals(spark.getReport().getTestList().get(0).getChildren().get(0).getName(), GRANDCHILD);
    }

}
