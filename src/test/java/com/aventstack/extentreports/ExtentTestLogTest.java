package com.aventstack.extentreports;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class ExtentTestLogTest {
    private static final String DETAILS = "details";
    private static final String ATTACHMENT = "img.png";

    private ExtentTest test() {
        return new ExtentReports().createTest("Test");
    }

    private Exception ex() {
        return new RuntimeException();
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void logWithStatusNull() {
        test().log(null, null, null, null);
    }

    @Test
    public void logDetails() {
        ExtentTest test = test().log(Status.SKIP, DETAILS + "1");
        Assert.assertEquals(test.getModel().getStatus(), Status.SKIP);
        Assert.assertEquals(test.getModel().getLogs().get(0).getDetails(), DETAILS + "1");
        Assert.assertEquals(test.getModel().getLogs().get(0).getStatus(), Status.SKIP);
        test.log(Status.FAIL, DETAILS);
        Assert.assertEquals(test.getModel().getLogs().get(1).getDetails(), DETAILS);
        Assert.assertEquals(test.getModel().getLogs().get(1).getStatus(), Status.FAIL);
    }

    @Test
    public void logDetailsMedia() throws IOException {
        ExtentTest test = test().log(Status.SKIP, DETAILS,
                MediaEntityBuilder.createScreenCaptureFromPath(ATTACHMENT).build());
        Assert.assertEquals(test.getModel().getStatus(), Status.SKIP);
        Assert.assertEquals(test.getModel().getLogs().get(0).getDetails(), DETAILS);
        Assert.assertEquals(test.getModel().getLogs().get(0).getStatus(), Status.SKIP);
        Assert.assertEquals(test.getModel().getLogs().get(0).getMedia().getPath(), ATTACHMENT);
        test.log(Status.FAIL, DETAILS, MediaEntityBuilder.createScreenCaptureFromPath(ATTACHMENT).build());
        Assert.assertEquals(test.getModel().getLogs().get(1).getDetails(), DETAILS);
        Assert.assertEquals(test.getModel().getLogs().get(1).getStatus(), Status.FAIL);
        Assert.assertEquals(test.getModel().getLogs().get(1).getMedia().getPath(), ATTACHMENT);
    }

    @Test
    public void logMedia() throws IOException {
        ExtentTest test = test().log(Status.SKIP,
                MediaEntityBuilder.createScreenCaptureFromPath(ATTACHMENT).build());
        Assert.assertEquals(test.getModel().getStatus(), Status.SKIP);
        Assert.assertEquals(test.getModel().getLogs().get(0).getDetails(), "");
        Assert.assertEquals(test.getModel().getLogs().get(0).getStatus(), Status.SKIP);
        Assert.assertEquals(test.getModel().getLogs().get(0).getMedia().getPath(), ATTACHMENT);
        test.log(Status.FAIL, MediaEntityBuilder.createScreenCaptureFromPath(ATTACHMENT).build());
        Assert.assertEquals(test.getModel().getLogs().get(1).getDetails(), "");
        Assert.assertEquals(test.getModel().getLogs().get(1).getStatus(), Status.FAIL);
        Assert.assertEquals(test.getModel().getLogs().get(1).getMedia().getPath(), ATTACHMENT);
    }

    @Test
    public void logMarkup() throws IOException {
        Markup m = MarkupHelper.createCodeBlock("code");
        ExtentTest test = test().log(Status.SKIP, m);
        Assert.assertEquals(test.getModel().getStatus(), Status.SKIP);
        Assert.assertTrue(test.getModel().getLogs().get(0).getDetails().contains("code"));
        Assert.assertEquals(test.getModel().getLogs().get(0).getStatus(), Status.SKIP);
    }

    @Test
    public void logThrowable() {
        Exception ex = ex();
        ExtentTest test = test().log(Status.SKIP, ex);
        Assert.assertEquals(test.getModel().getStatus(), Status.SKIP);
        Assert.assertEquals(test.getModel().getLogs().get(0).getException().getException(), ex);
        Assert.assertEquals(test.getModel().getLogs().get(0).getStatus(), Status.SKIP);
        test.log(Status.FAIL, ex);
        Assert.assertEquals(test.getModel().getLogs().get(1).getException().getException(), ex);
        Assert.assertEquals(test.getModel().getLogs().get(1).getStatus(), Status.FAIL);
    }

    @Test
    public void logThrowableMedia() throws IOException {
        Exception ex = ex();
        ExtentTest test = test().log(Status.SKIP, ex,
                MediaEntityBuilder.createScreenCaptureFromPath(ATTACHMENT).build());
        Assert.assertEquals(test.getModel().getStatus(), Status.SKIP);
        Assert.assertEquals(test.getModel().getLogs().get(0).getException().getException(), ex);
        Assert.assertEquals(test.getModel().getLogs().get(0).getStatus(), Status.SKIP);
        Assert.assertEquals(test.getModel().getLogs().get(0).getMedia().getPath(), ATTACHMENT);
        test.log(Status.FAIL, ex, MediaEntityBuilder.createScreenCaptureFromPath(ATTACHMENT).build());
        Assert.assertEquals(test.getModel().getLogs().get(1).getException().getException(), ex);
        Assert.assertEquals(test.getModel().getLogs().get(1).getStatus(), Status.FAIL);
        Assert.assertEquals(test.getModel().getLogs().get(1).getMedia().getPath(), ATTACHMENT);
    }

    @Test
    public void failDetails() {
        ExtentTest test = test().fail(DETAILS);
        Assert.assertEquals(test.getModel().getLogs().get(0).getDetails(), DETAILS);
        Assert.assertEquals(test.getModel().getLogs().get(0).getStatus(), Status.FAIL);
    }

    @Test
    public void failMedia() throws IOException {
        ExtentTest test = test().fail(
                MediaEntityBuilder.createScreenCaptureFromPath(ATTACHMENT).build());
        test.log(Status.FAIL, DETAILS, MediaEntityBuilder.createScreenCaptureFromPath(ATTACHMENT).build());
        Assert.assertEquals(test.getModel().getLogs().get(0).getStatus(), Status.FAIL);
        Assert.assertEquals(test.getModel().getLogs().get(0).getMedia().getPath(), ATTACHMENT);
    }

    @Test
    public void failThrowable() {
        Exception ex = ex();
        ExtentTest test = test().fail(ex);
        Assert.assertEquals(test.getModel().getLogs().get(0).getException().getException(), ex);
        Assert.assertEquals(test.getModel().getLogs().get(0).getStatus(), Status.FAIL);
    }

    @Test
    public void failThrowableMedia() throws IOException {
        Exception ex = ex();
        ExtentTest test = test().fail(ex,
                MediaEntityBuilder.createScreenCaptureFromPath(ATTACHMENT).build());
        Assert.assertEquals(test.getModel().getLogs().get(0).getException().getException(), ex);
        Assert.assertEquals(test.getModel().getLogs().get(0).getStatus(), Status.FAIL);
        Assert.assertEquals(test.getModel().getLogs().get(0).getMedia().getPath(), ATTACHMENT);
    }

    @Test
    public void failMarkup() throws IOException {
        Markup m = MarkupHelper.createCodeBlock("code");
        ExtentTest test = test().fail(m);
        Assert.assertEquals(test.getModel().getStatus(), Status.FAIL);
        Assert.assertTrue(test.getModel().getLogs().get(0).getDetails().contains("code"));
        Assert.assertEquals(test.getModel().getLogs().get(0).getStatus(), Status.FAIL);
    }

    @Test
    public void skipDetails() {
        ExtentTest test = test().skip(DETAILS);
        Assert.assertEquals(test.getModel().getLogs().get(0).getDetails(), DETAILS);
        Assert.assertEquals(test.getModel().getLogs().get(0).getStatus(), Status.SKIP);
    }

    @Test
    public void skipMedia() throws IOException {
        ExtentTest test = test().skip(
                MediaEntityBuilder.createScreenCaptureFromPath(ATTACHMENT).build());
        test.log(Status.FAIL, DETAILS, MediaEntityBuilder.createScreenCaptureFromPath(ATTACHMENT).build());
        Assert.assertEquals(test.getModel().getLogs().get(0).getStatus(), Status.SKIP);
        Assert.assertEquals(test.getModel().getLogs().get(0).getMedia().getPath(), ATTACHMENT);
    }

    @Test
    public void skipThrowable() {
        Exception ex = ex();
        ExtentTest test = test().skip(ex);
        Assert.assertEquals(test.getModel().getLogs().get(0).getException().getException(), ex);
        Assert.assertEquals(test.getModel().getLogs().get(0).getStatus(), Status.SKIP);
    }

    @Test
    public void skipThrowableMedia() throws IOException {
        Exception ex = ex();
        ExtentTest test = test().skip(ex,
                MediaEntityBuilder.createScreenCaptureFromPath(ATTACHMENT).build());
        Assert.assertEquals(test.getModel().getLogs().get(0).getException().getException(), ex);
        Assert.assertEquals(test.getModel().getLogs().get(0).getStatus(), Status.SKIP);
        Assert.assertEquals(test.getModel().getLogs().get(0).getMedia().getPath(), ATTACHMENT);
    }

    @Test
    public void skipMarkup() throws IOException {
        Markup m = MarkupHelper.createCodeBlock("code");
        ExtentTest test = test().log(Status.SKIP, m);
        Assert.assertEquals(test.getModel().getStatus(), Status.SKIP);
        Assert.assertTrue(test.getModel().getLogs().get(0).getDetails().contains("code"));
        Assert.assertEquals(test.getModel().getLogs().get(0).getStatus(), Status.SKIP);
    }

    @Test
    public void warnDetails() {
        ExtentTest test = test().warning(DETAILS);
        Assert.assertEquals(test.getModel().getLogs().get(0).getDetails(), DETAILS);
        Assert.assertEquals(test.getModel().getLogs().get(0).getStatus(), Status.WARNING);
    }

    @Test
    public void warnMedia() throws IOException {
        ExtentTest test = test().warning(
                MediaEntityBuilder.createScreenCaptureFromPath(ATTACHMENT).build());
        test.log(Status.WARNING, DETAILS, MediaEntityBuilder.createScreenCaptureFromPath(ATTACHMENT).build());
        Assert.assertEquals(test.getModel().getLogs().get(0).getStatus(), Status.WARNING);
        Assert.assertEquals(test.getModel().getLogs().get(0).getMedia().getPath(), ATTACHMENT);
    }

    @Test
    public void warnThrowable() {
        Exception ex = ex();
        ExtentTest test = test().warning(ex);
        Assert.assertEquals(test.getModel().getLogs().get(0).getException().getException(), ex);
        Assert.assertEquals(test.getModel().getLogs().get(0).getStatus(), Status.WARNING);
    }

    @Test
    public void warnThrowableMedia() throws IOException {
        Exception ex = ex();
        ExtentTest test = test().log(Status.WARNING, ex,
                MediaEntityBuilder.createScreenCaptureFromPath(ATTACHMENT).build());
        Assert.assertEquals(test.getModel().getLogs().get(0).getException().getException(), ex);
        Assert.assertEquals(test.getModel().getLogs().get(0).getStatus(), Status.WARNING);
        Assert.assertEquals(test.getModel().getLogs().get(0).getMedia().getPath(), ATTACHMENT);
    }

    @Test
    public void warnMarkup() throws IOException {
        Markup m = MarkupHelper.createCodeBlock("code");
        ExtentTest test = test().log(Status.WARNING, m);
        Assert.assertEquals(test.getModel().getStatus(), Status.WARNING);
        Assert.assertTrue(test.getModel().getLogs().get(0).getDetails().contains("code"));
        Assert.assertEquals(test.getModel().getLogs().get(0).getStatus(), Status.WARNING);
    }

    @Test
    public void passDetails() {
        ExtentTest test = test().pass(DETAILS);
        Assert.assertEquals(test.getModel().getLogs().get(0).getDetails(), DETAILS);
        Assert.assertEquals(test.getModel().getLogs().get(0).getStatus(), Status.PASS);
    }

    @Test
    public void passMedia() throws IOException {
        ExtentTest test = test().pass(
                MediaEntityBuilder.createScreenCaptureFromPath(ATTACHMENT).build());
        test.log(Status.PASS, DETAILS, MediaEntityBuilder.createScreenCaptureFromPath(ATTACHMENT).build());
        Assert.assertEquals(test.getModel().getLogs().get(0).getStatus(), Status.PASS);
        Assert.assertEquals(test.getModel().getLogs().get(0).getMedia().getPath(), ATTACHMENT);
    }

    @Test
    public void passThrowable() {
        Exception ex = ex();
        ExtentTest test = test().pass(ex);
        Assert.assertEquals(test.getModel().getLogs().get(0).getException().getException(), ex);
        Assert.assertEquals(test.getModel().getLogs().get(0).getStatus(), Status.PASS);
    }

    @Test
    public void passThrowableMedia() throws IOException {
        Exception ex = ex();
        ExtentTest test = test().pass(ex,
                MediaEntityBuilder.createScreenCaptureFromPath(ATTACHMENT).build());
        Assert.assertEquals(test.getModel().getLogs().get(0).getException().getException(), ex);
        Assert.assertEquals(test.getModel().getLogs().get(0).getStatus(), Status.PASS);
        Assert.assertEquals(test.getModel().getLogs().get(0).getMedia().getPath(), ATTACHMENT);
    }

    @Test
    public void passMarkup() throws IOException {
        Markup m = MarkupHelper.createCodeBlock("code");
        ExtentTest test = test().log(Status.PASS, m);
        Assert.assertEquals(test.getModel().getStatus(), Status.PASS);
        Assert.assertTrue(test.getModel().getLogs().get(0).getDetails().contains("code"));
        Assert.assertEquals(test.getModel().getLogs().get(0).getStatus(), Status.PASS);
    }

    @Test
    public void infoDetails() {
        ExtentTest test = test().info(DETAILS);
        Assert.assertEquals(test.getModel().getLogs().get(0).getDetails(), DETAILS);
        Assert.assertEquals(test.getModel().getLogs().get(0).getStatus(), Status.INFO);
    }

    @Test
    public void infoMedia() throws IOException {
        ExtentTest test = test().info(
                MediaEntityBuilder.createScreenCaptureFromPath(ATTACHMENT).build());
        test.log(Status.INFO, DETAILS, MediaEntityBuilder.createScreenCaptureFromPath(ATTACHMENT).build());
        Assert.assertEquals(test.getModel().getLogs().get(0).getStatus(), Status.INFO);
        Assert.assertEquals(test.getModel().getLogs().get(0).getMedia().getPath(), ATTACHMENT);
    }

    @Test
    public void infoThrowable() {
        Exception ex = ex();
        ExtentTest test = test().info(ex);
        Assert.assertEquals(test.getModel().getLogs().get(0).getException().getException(), ex);
        Assert.assertEquals(test.getModel().getLogs().get(0).getStatus(), Status.INFO);
    }

    @Test
    public void infoThrowableMedia() throws IOException {
        Exception ex = ex();
        ExtentTest test = test().info(ex,
                MediaEntityBuilder.createScreenCaptureFromPath(ATTACHMENT).build());
        Assert.assertEquals(test.getModel().getLogs().get(0).getException().getException(), ex);
        Assert.assertEquals(test.getModel().getLogs().get(0).getStatus(), Status.INFO);
        Assert.assertEquals(test.getModel().getLogs().get(0).getMedia().getPath(), ATTACHMENT);
    }

    @Test
    public void infoMarkup() throws IOException {
        Markup m = MarkupHelper.createCodeBlock("code");
        ExtentTest test = test().log(Status.INFO, m);
        Assert.assertEquals(test.getModel().getStatus(), Status.PASS);
        Assert.assertTrue(test.getModel().getLogs().get(0).getDetails().contains("code"));
        Assert.assertEquals(test.getModel().getLogs().get(0).getStatus(), Status.INFO);
    }
}
