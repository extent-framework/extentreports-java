package com.aventstack.extentreports;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import org.testng.Assert;

import com.aventstack.extentreports.gherkin.GherkinDialectManager;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.model.Test;
import org.testng.annotations.BeforeMethod;

public class ExtentReportsTest {

    private static final String TEST_NAME = "TEST";
    private ExtentReports extent;

    @BeforeMethod
    public void beforeMethod() throws UnsupportedEncodingException {
        extent = new ExtentReports();
        extent.setGherkinDialect("en");
    }

    @org.testng.annotations.Test
    public void createTestOverloadTypeNameDesc() {
        ExtentTest test = extent.createTest(Feature.class, TEST_NAME, "Description");
        Test model = test.getModel();
        Assert.assertTrue(model.isBDD());
        Assert.assertEquals(model.getName(), TEST_NAME);
        Assert.assertEquals(model.getDescription(), "Description");
        Assert.assertEquals(model.getBddType(), Feature.class);
        Assert.assertTrue(model.isLeaf());
    }

    @org.testng.annotations.Test
    public void createTestOverloadTypeName() {
        ExtentTest test = extent.createTest(Feature.class, TEST_NAME);
        Test model = test.getModel();
        Assert.assertTrue(model.isBDD());
        Assert.assertEquals(model.getName(), TEST_NAME);
        Assert.assertNull(model.getDescription());
        Assert.assertEquals(model.getBddType(), Feature.class);
        Assert.assertTrue(model.isLeaf());
    }

    @org.testng.annotations.Test
    public void createTestOverloadKeywordNameDesc() throws ClassNotFoundException {
        ExtentTest test = extent.createTest(new GherkinKeyword("Feature"), TEST_NAME, "Description");
        Test model = test.getModel();
        Assert.assertTrue(model.isBDD());
        Assert.assertEquals(model.getName(), TEST_NAME);
        Assert.assertEquals(model.getDescription(), "Description");
        Assert.assertEquals(model.getBddType(), Feature.class);
        Assert.assertTrue(model.isLeaf());
    }

    @org.testng.annotations.Test
    public void createTestOverloadKeywordName() throws ClassNotFoundException {
        ExtentTest test = extent.createTest(new GherkinKeyword("Feature"), TEST_NAME);
        Test model = test.getModel();
        Assert.assertTrue(model.isBDD());
        Assert.assertEquals(model.getName(), TEST_NAME);
        Assert.assertNull(model.getDescription());
        Assert.assertEquals(model.getBddType(), Feature.class);
        Assert.assertTrue(model.isLeaf());
    }

    @org.testng.annotations.Test
    public void createTestOverloadNameDesc() {
        ExtentTest test = extent.createTest(TEST_NAME, "Description");
        Test model = test.getModel();
        Assert.assertFalse(model.isBDD());
        Assert.assertEquals(model.getName(), TEST_NAME);
        Assert.assertEquals(model.getDescription(), "Description");
        Assert.assertNull(model.getBddType());
        Assert.assertTrue(model.isLeaf());
    }

    @org.testng.annotations.Test
    public void createTestOverloadName() {
        ExtentTest test = extent.createTest(TEST_NAME);
        Test model = test.getModel();
        Assert.assertFalse(model.isBDD());
        Assert.assertEquals(model.getName(), TEST_NAME);
        Assert.assertNull(model.getDescription());
        Assert.assertNull(model.getBddType());
        Assert.assertTrue(model.isLeaf());
    }

    @org.testng.annotations.Test
    public void gherkinDialect() throws UnsupportedEncodingException {
        extent.setGherkinDialect("de");
        Assert.assertEquals(GherkinDialectManager.getLanguage(), "de");
    }

    @org.testng.annotations.Test
    public void addTestRunnerOutputSingle() {
        String[] logs = new String[]{"Log1", "Log2"};
        Arrays.stream(logs).forEach(extent::addTestRunnerOutput);
        Assert.assertEquals(extent.getReport().getLogs().size(), 2);
        Arrays.stream(logs).forEach(x -> Assert.assertTrue(extent.getReport().getLogs().contains(x)));
    }

    @org.testng.annotations.Test
    public void addTestRunnerOutputList() {
        String[] logs = new String[]{"Log1", "Log2"};
        extent.addTestRunnerOutput(Arrays.asList(logs));
        Assert.assertEquals(extent.getReport().getLogs().size(), 2);
        Arrays.stream(logs).forEach(x -> Assert.assertTrue(extent.getReport().getLogs().contains(x)));
    }

    @org.testng.annotations.Test
    public void keepLastRetryOnlyTestEnabled() {
        final ExtentReports extent = new ExtentReports();
        extent.keepLastRetryOnly(true);
        final int numberOfTests = 5;
        for (int i = 0; i < numberOfTests; i++) {
            extent.createTest("test");
        }
        Assert.assertEquals(extent.getReport().getTestList().size(), 1);
    }

    @org.testng.annotations.Test
    public void keepLastRetryOnlyTestDisabled() {
        final ExtentReports extent = new ExtentReports();
        extent.keepLastRetryOnly(false);
        final int numberOfTests = 5;
        for (int i = 0; i < numberOfTests; i++) {
            extent.createTest("test");
        }
        Assert.assertEquals(extent.getReport().getTestList().size(), numberOfTests);
    }
}
