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

    private ExtentReports extent() {
        return new ExtentReports();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addScreenCaptureFromEmptyPathTest() {
        extent().createTest("Test")
                .addScreenCaptureFromPath("");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addScreenCaptureFromNullPathTest() {
        extent().createTest("Test")
                .addScreenCaptureFromPath(null);
    }

    @Test
    public void addScreenCaptureFromPathTest() {
        ExtentTest test = extent().createTest("Test")
                .addScreenCaptureFromPath(PATH, TITLE)
                .pass("Pass");
        Assert.assertEquals(test.getModel().getMedia().size(), 1);
        Assert.assertEquals(test.getModel().getMedia().get(0).getPath(), PATH);
        Assert.assertEquals(test.getModel().getMedia().get(0).getTitle(), TITLE);
    }

    @Test
    public void addScreenCaptureFromPathTestOverloads() {
        ExtentTest test = extent().createTest("Test")
                .addScreenCaptureFromPath(PATH)
                .pass("Pass");
        Assert.assertEquals(test.getModel().getMedia().size(), 1);
        Assert.assertEquals(test.getModel().getMedia().get(0).getPath(), PATH);
    }

    @Test
    public void addScreenCaptureFromPathNode() {
        ExtentTest test = extent().createTest("Test");
        ExtentTest node = test
                .createNode("Node")
                .addScreenCaptureFromPath(PATH, TITLE)
                .pass("Pass");
        Assert.assertEquals(test.getModel().getMedia().size(), 0);
        Assert.assertEquals(node.getModel().getMedia().size(), 1);
        Assert.assertEquals(node.getModel().getMedia().get(0).getPath(), PATH);
        Assert.assertEquals(node.getModel().getMedia().get(0).getTitle(), TITLE);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addScreenCaptureEmptyPathTestLog() {
        extent().createTest("Test")
                .pass("Pass", MediaEntityBuilder.createScreenCaptureFromPath("").build());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addScreenCaptureNullPathTestLog() {
        extent().createTest("Test")
                .pass("Pass", MediaEntityBuilder.createScreenCaptureFromPath(null).build());
    }

    @Test
    public void addScreenCaptureFromPathTestLog() {
        ExtentTest test = extent().createTest("Test")
                .pass("Pass", MediaEntityBuilder.createScreenCaptureFromPath(PATH, TITLE).build());
        Assert.assertEquals(test.getModel().getMedia().size(), 0);
        Assert.assertNotNull(test.getModel().getLogs().get(0).getMedia());
        Assert.assertEquals(test.getModel().getLogs().get(0).getMedia().getPath(), PATH);
        Assert.assertEquals(test.getModel().getLogs().get(0).getMedia().getTitle(), TITLE);
    }

    @Test
    public void addScreenCaptureFromPathTestLogOverloads() {
        ExtentTest test = extent().createTest("Test")
                .pass("Pass", MediaEntityBuilder.createScreenCaptureFromPath(PATH).build());
        Assert.assertEquals(test.getModel().getMedia().size(), 0);
        Assert.assertNotNull(test.getModel().getLogs().get(0).getMedia());
        Assert.assertEquals(test.getModel().getLogs().get(0).getMedia().getPath(), PATH);
    }

    @Test
    public void addScreenCaptureFromPathNodeLog() {
        ExtentTest test = extent().createTest("Test");
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
        extent().createTest("Test")
                .addScreenCaptureFromBase64String("");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addScreenCaptureFromNullBase64Test() {
        extent().createTest("Test")
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
        ExtentTest test = extent().createTest("Test");
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
        ExtentTest test = extent().createTest("Test");
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
        ExtentTest test = extent().createTest("Test")
                .pass("Pass", MediaEntityBuilder.createScreenCaptureFromBase64String(BASE64, TITLE).build());
        Assert.assertEquals(test.getModel().getMedia().size(), 0);
        Assert.assertNotNull(test.getModel().getLogs().get(0).getMedia());
        Assert.assertEquals(((ScreenCapture) test.getModel().getLogs().get(0).getMedia()).getBase64(),
                BASE64_ENCODED + BASE64);
        Assert.assertEquals(test.getModel().getLogs().get(0).getMedia().getTitle(), TITLE);
    }

    @Test
    public void addScreenCaptureFromBase64NodeLog() {
        ExtentTest test = extent().createTest("Test");
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
        ExtentTest test = extent().createTest("Test");
        ExtentTest node = test
                .createNode("Node")
                .pass("Pass", MediaEntityBuilder.createScreenCaptureFromBase64String(BASE64).build());
        Assert.assertEquals(node.getModel().getMedia().size(), 0);
        Assert.assertNotNull(node.getModel().getLogs().get(0).getMedia());
        Assert.assertEquals(((ScreenCapture) node.getModel().getLogs().get(0).getMedia()).getBase64(),
                BASE64_ENCODED + BASE64);
    }
}
