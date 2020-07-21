package com.aventstack.extentreports;

import org.testng.Assert;

public class ExtentReportsRemoveTest {
    private static final String TEST_NAME = "TEST";

    private ExtentReports extent() {
        return new ExtentReports();
    }

    @org.testng.annotations.Test
    public void removeTest() {
        ExtentReports extent = extent();
        ExtentTest test = extent.createTest(TEST_NAME);
        Assert.assertEquals(extent.getReport().getTestList().size(), 1);
        extent.removeTest(test);
        Assert.assertEquals(extent.getReport().getTestList().size(), 0);
    }

    @org.testng.annotations.Test
    public void removeTestByName() {
        ExtentReports extent = extent();
        extent.createTest(TEST_NAME);
        Assert.assertEquals(extent.getReport().getTestList().size(), 1);
        extent.removeTest(TEST_NAME);
        Assert.assertEquals(extent.getReport().getTestList().size(), 0);
    }

    @org.testng.annotations.Test
    public void removeNode() {
        ExtentReports extent = extent();
        ExtentTest test = extent.createTest(TEST_NAME);
        ExtentTest node = test.createNode(TEST_NAME);
        Assert.assertEquals(extent.getReport().getTestList().size(), 1);
        Assert.assertEquals(extent.getReport().getTestList().get(0).getChildren().size(), 1);
        extent.removeTest(node);
        Assert.assertEquals(extent.getReport().getTestList().size(), 1);
        Assert.assertEquals(extent.getReport().getTestList().get(0).getChildren().size(), 0);
    }

    @org.testng.annotations.Test
    public void removeNodeByName() {
        ExtentReports extent = extent();
        ExtentTest test = extent.createTest(TEST_NAME + "Parent");
        test.createNode(TEST_NAME);
        Assert.assertEquals(extent.getReport().getTestList().size(), 1);
        Assert.assertEquals(extent.getReport().getTestList().get(0).getChildren().size(), 1);
        extent.removeTest(TEST_NAME);
        Assert.assertEquals(extent.getReport().getTestList().size(), 1);
        Assert.assertEquals(extent.getReport().getTestList().get(0).getChildren().size(), 0);
    }
}
