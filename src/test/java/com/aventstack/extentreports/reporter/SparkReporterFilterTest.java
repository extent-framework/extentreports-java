package com.aventstack.extentreports.reporter;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;

public class SparkReporterFilterTest {

    @Test
    public void singleStatusFilterTestAllFiltered() {
        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("target/spark.html");
        spark.filter()
                .statusFilter()
                .as(new Status[]{Status.FAIL})
                .apply();
        extent.attachReporter(spark);
        extent.createTest("Test").pass("Pass");
        extent.flush();
        Assert.assertEquals(spark.getReport().getTestList().size(), 0);
    }

    @Test
    public void singleStatusFilterTestOneFiltered() {
        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("target/spark.html");
        spark.filter()
                .statusFilter()
                .as(new Status[]{Status.FAIL})
                .apply();
        extent.attachReporter(spark);
        extent.createTest("Test1").pass("Pass");
        extent.createTest("Test2").fail("Fail");
        extent.flush();
        Assert.assertEquals(spark.getReport().getTestList().size(), 1);
        Assert.assertEquals(spark.getReport().getTestList().get(0).getName(), "Test2");
    }

    @Test
    public void singleStatusFilterTestSomeFiltered() {
        ExtentReports extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("target/spark.html");
        spark.filter()
                .statusFilter()
                .as(new Status[]{Status.FAIL, Status.SKIP})
                .apply();
        extent.attachReporter(spark);
        extent.createTest("Test1").pass("Pass");
        extent.createTest("Test2").fail("Fail");
        extent.createTest("Test3").skip("Skip");
        extent.flush();
        Assert.assertEquals(spark.getReport().getTestList().size(), 2);
        Assert.assertEquals(spark.getReport().getTestList().get(0).getName(), "Test2");
        Assert.assertEquals(spark.getReport().getTestList().get(1).getName(), "Test3");
    }
}
