package com.aventstack.extentreports;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.model.ScreenCapture;

public class ExtentTestMediaTest {
    private static final String BASE64_ENCODED = "data:image/png;base64,";
    private static final String BASE64 = "iVBORw0KGgoAAAANSUhEUgAAAY4AAABbCAYAAABkgGJUAAAAAXNSR0IArs4c6QAAAARnQU1BAACxjwv8YQUAAAAJcEhZcwAADsMAAA7DAcdvqGQAABIMSURBVHhe7Z3"
            +
            "daxRXH8effyZ3gVwIXgiFemXwookXkUIINFQQGrx4clV9LpILadCLEOzjWm0U00ZMQ7SQUEuKbSw1BoKkEdnqI1tss3lZ1rh1a9JsTMLvOWfmnJmzu/NyzuyLO/H7gUObcWfmzMzu7zPn/V8EAAAA"
            +
            "GABxAAAAMALiAAAAYATEAQAAwAiIAwAAgBEQBwAAACMgDgAAAEZAHAAAAIyAOAAAABgBcQAAADAC4gAAAGAExAEAAMAIiAMAAIAREAcAAAAjIA4AAABGQBwAAACMgDgAAAAYAXEAAAAwAuIAAABgB";
    private static final String PATH = "src/test/resources/img.png";
    private static final String TITLE = "MediaTitle";

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addScreenCaptureFromEmptyPathTest() {
        ExtentReports extent = new ExtentReports();
        extent.createTest("Test")
                .addScreenCaptureFromPath("");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addScreenCaptureFromNullPathTest() {
        ExtentReports extent = new ExtentReports();
        extent.createTest("Test")
                .addScreenCaptureFromPath(null);
    }

    @Test
    public void addScreenCaptureFromPathTest() {
        ExtentReports extent = new ExtentReports();
        ExtentTest test = extent.createTest("Test")
                .addScreenCaptureFromPath(PATH, TITLE)
                .pass("Pass");
        Assert.assertEquals(test.getModel().getMedia().size(), 1);
        Assert.assertEquals(test.getModel().getMedia().get(0).getPath(), PATH);
        Assert.assertEquals(test.getModel().getMedia().get(0).getTitle(), TITLE);
    }

    @Test
    public void addScreenCaptureFromPathTestOverloads() {
        ExtentReports extent = new ExtentReports();
        ExtentTest test = extent.createTest("Test")
                .addScreenCaptureFromPath(PATH)
                .pass("Pass");
        Assert.assertEquals(test.getModel().getMedia().size(), 1);
        Assert.assertEquals(test.getModel().getMedia().get(0).getPath(), PATH);
    }

    @Test
    public void addScreenCaptureFromPathNode() {
        ExtentReports extent = new ExtentReports();
        ExtentTest test = extent.createTest("Test");
        ExtentTest node = test
                .createNode("Node")
                .addScreenCaptureFromPath(PATH, TITLE)
                .pass("Pass");
        Assert.assertEquals(test.getModel().getMedia().size(), 0);
        Assert.assertEquals(node.getModel().getMedia().size(), 1);
        Assert.assertEquals(node.getModel().getMedia().get(0).getPath(), PATH);
        Assert.assertEquals(node.getModel().getMedia().get(0).getTitle(), TITLE);
    }

    @Test
    public void addScreenCaptureFromPathTestLog() {
        ExtentReports extent = new ExtentReports();
        ExtentTest test = extent.createTest("Test")
                .pass("Pass", MediaEntityBuilder.createScreenCaptureFromPath(PATH, TITLE).build());
        Assert.assertEquals(test.getModel().getMedia().size(), 0);
        Assert.assertNotNull(test.getModel().getLogs().get(0).getMedia());
        Assert.assertEquals(test.getModel().getLogs().get(0).getMedia().getPath(), PATH);
        Assert.assertEquals(test.getModel().getLogs().get(0).getMedia().getTitle(), TITLE);
    }

    @Test
    public void addScreenCaptureFromPathTestLogOverloads() {
        ExtentReports extent = new ExtentReports();
        ExtentTest test = extent.createTest("Test")
                .pass("Pass", MediaEntityBuilder.createScreenCaptureFromPath(PATH).build());
        Assert.assertEquals(test.getModel().getMedia().size(), 0);
        Assert.assertNotNull(test.getModel().getLogs().get(0).getMedia());
        Assert.assertEquals(test.getModel().getLogs().get(0).getMedia().getPath(), PATH);
    }

    @Test
    public void addScreenCaptureFromPathNodeLog() {
        ExtentReports extent = new ExtentReports();
        ExtentTest test = extent.createTest("Test");
        ExtentTest node = test
                .createNode("Node")
                .pass("Pass", MediaEntityBuilder.createScreenCaptureFromPath(PATH, TITLE).build());
        Assert.assertEquals(node.getModel().getMedia().size(), 0);
        Assert.assertNotNull(node.getModel().getLogs().get(0).getMedia());
        Assert.assertEquals(node.getModel().getLogs().get(0).getMedia().getPath(), PATH);
        Assert.assertEquals(node.getModel().getLogs().get(0).getMedia().getTitle(), TITLE);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addScreenCaptureFromEmptyBase64Test() {
        ExtentReports extent = new ExtentReports();
        extent.createTest("Test")
                .addScreenCaptureFromBase64String("");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addScreenCaptureFromNullBase64Test() {
        ExtentReports extent = new ExtentReports();
        extent.createTest("Test")
                .addScreenCaptureFromBase64String(null);
    }

    @Test
    public void addScreenCaptureFromBase64Test() {
        ExtentReports extent = new ExtentReports();
        ExtentTest test = extent.createTest("Test")
                .addScreenCaptureFromBase64String(BASE64, TITLE)
                .pass("Pass");
        Assert.assertEquals(test.getModel().getMedia().size(), 1);
        Assert.assertEquals(((ScreenCapture) test.getModel().getMedia().get(0)).getBase64(), BASE64_ENCODED + BASE64);
        Assert.assertEquals(test.getModel().getMedia().get(0).getTitle(), TITLE);
    }

    @Test
    public void addScreenCaptureFromBase64Node() {
        ExtentReports extent = new ExtentReports();
        ExtentTest test = extent.createTest("Test");
        ExtentTest node = test
                .createNode("Node")
                .addScreenCaptureFromBase64String(BASE64, TITLE)
                .pass("Pass");
        Assert.assertEquals(test.getModel().getMedia().size(), 0);
        Assert.assertEquals(node.getModel().getMedia().size(), 1);
        Assert.assertEquals(((ScreenCapture) node.getModel().getMedia().get(0)).getBase64(), BASE64_ENCODED + BASE64);
        Assert.assertEquals(node.getModel().getMedia().get(0).getTitle(), TITLE);
    }

    @Test
    public void addScreenCaptureFromBase64NodeOverloads() {
        ExtentReports extent = new ExtentReports();
        ExtentTest test = extent.createTest("Test");
        ExtentTest node = test
                .createNode("Node")
                .addScreenCaptureFromBase64String(BASE64)
                .pass("Pass");
        Assert.assertEquals(test.getModel().getMedia().size(), 0);
        Assert.assertEquals(node.getModel().getMedia().size(), 1);
        Assert.assertEquals(((ScreenCapture) node.getModel().getMedia().get(0)).getBase64(), BASE64_ENCODED + BASE64);
    }

    @Test
    public void addScreenCaptureFromBase64TestLog() {
        ExtentReports extent = new ExtentReports();
        ExtentTest test = extent.createTest("Test")
                .pass("Pass", MediaEntityBuilder.createScreenCaptureFromBase64String(BASE64, TITLE).build());
        Assert.assertEquals(test.getModel().getMedia().size(), 0);
        Assert.assertNotNull(test.getModel().getLogs().get(0).getMedia());
        Assert.assertEquals(((ScreenCapture) test.getModel().getLogs().get(0).getMedia()).getBase64(),
                BASE64_ENCODED + BASE64);
        Assert.assertEquals(test.getModel().getLogs().get(0).getMedia().getTitle(), TITLE);
    }

    @Test
    public void addScreenCaptureFromBase64NodeLog() {
        ExtentReports extent = new ExtentReports();
        ExtentTest test = extent.createTest("Test");
        ExtentTest node = test
                .createNode("Node")
                .pass("Pass", MediaEntityBuilder.createScreenCaptureFromBase64String(BASE64, TITLE).build());
        Assert.assertEquals(node.getModel().getMedia().size(), 0);
        Assert.assertNotNull(node.getModel().getLogs().get(0).getMedia());
        Assert.assertEquals(((ScreenCapture) node.getModel().getLogs().get(0).getMedia()).getBase64(),
                BASE64_ENCODED + BASE64);
        Assert.assertEquals(node.getModel().getLogs().get(0).getMedia().getTitle(), TITLE);
    }

    @Test
    public void addScreenCaptureFromBase64NodeLogOverloads() {
        ExtentReports extent = new ExtentReports();
        ExtentTest test = extent.createTest("Test");
        ExtentTest node = test
                .createNode("Node")
                .pass("Pass", MediaEntityBuilder.createScreenCaptureFromBase64String(BASE64).build());
        Assert.assertEquals(node.getModel().getMedia().size(), 0);
        Assert.assertNotNull(node.getModel().getLogs().get(0).getMedia());
        Assert.assertEquals(((ScreenCapture) node.getModel().getLogs().get(0).getMedia()).getBase64(),
                BASE64_ENCODED + BASE64);
    }
}
