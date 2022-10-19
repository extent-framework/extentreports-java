package com.aventstack.extentreports;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ExtentReportsRemoveTest {
    private static final String TEST_NAME = "TEST";
    private static final String TAG = "tag";

    private ExtentReports extent;

    @BeforeMethod
    private void beforeMethod() {
        extent = new ExtentReports();
    }

    @Test
    public void removeTest() {
        ExtentTest test = extent.createTest(TEST_NAME);
        Assert.assertEquals(extent.getReport().getTestList().size(), 1);
        extent.removeTest(test);
        Assert.assertEquals(extent.getReport().getTestList().size(), 0);
    }

    @Test
    public void removeTestByName() {
        extent.createTest(TEST_NAME);
        Assert.assertEquals(extent.getReport().getTestList().size(), 1);
        extent.removeTest(TEST_NAME);
        Assert.assertEquals(extent.getReport().getTestList().size(), 0);
    }

    @Test
    public void removeTestWithTag() {
        extent.createTest(TEST_NAME).assignCategory(TAG);
        Assert.assertEquals(extent.getReport().getTestList().size(), 1);
        Assert.assertEquals(extent.getReport().getCategoryCtx().getSet().size(), 1);
        extent.removeTest(TEST_NAME);
        Assert.assertEquals(extent.getReport().getTestList().size(), 0);
        Assert.assertEquals(extent.getReport().getCategoryCtx().hasItems(), false);
    }

    @Test
    public void removeNode() {
        ExtentTest test = extent.createTest(TEST_NAME);
        ExtentTest node = test.createNode(TEST_NAME);
        Assert.assertEquals(extent.getReport().getTestList().size(), 1);
        Assert.assertEquals(extent.getReport().getTestList().get(0).getChildren().size(), 1);
        extent.removeTest(node);
        Assert.assertEquals(extent.getReport().getTestList().size(), 1);
        Assert.assertEquals(extent.getReport().getTestList().get(0).getChildren().size(), 0);
    }

    @Test
    public void removeNodeByName() {
        ExtentTest test = extent.createTest(TEST_NAME + "Parent");
        test.createNode(TEST_NAME);
        Assert.assertEquals(extent.getReport().getTestList().size(), 1);
        Assert.assertEquals(extent.getReport().getTestList().get(0).getChildren().size(), 1);
        extent.removeTest(TEST_NAME);
        Assert.assertEquals(extent.getReport().getTestList().size(), 1);
        Assert.assertEquals(extent.getReport().getTestList().get(0).getChildren().size(), 0);
    }
}
