package com.aventstack.extentreports.entity.service;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.ExceptionInfo;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.model.ScreenCapture;
import com.aventstack.extentreports.model.service.ExceptionInfoService;
import com.aventstack.extentreports.model.service.LogService;
import com.aventstack.extentreports.model.service.TestService;

public class LogServiceTest {
    private static final String DETAILS = "log.details";
    private static final Status STATUS = Status.INFO;

    private com.aventstack.extentreports.model.Test createTest() {
        return TestService.createTest("Test");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createLogNulls() {
        LogService.createLog(null, null, null, null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createLogWithNullTest() {
        LogService.createLog(null, STATUS, DETAILS);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createLogWithNullStatus() {
        LogService.createLog(createTest(), null, DETAILS);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void createLogWithNullStatusAndTest() {
        LogService.createLog(null, null, DETAILS);
    }

    @Test
    public void createLogWithTestAndStatus() {
        Log log = LogService.createLog(createTest(), STATUS);
        Assert.assertEquals(log.getSeq().intValue(), 0);
        Assert.assertEquals(log.getStatus(), STATUS);
        Assert.assertEquals(log.getDetails(), "");
    }

    @Test
    public void createLogWithNullDetails() {
        Log log = LogService.createLog(createTest(), STATUS, null, null);
        Assert.assertEquals(log.getStatus(), STATUS);
        Assert.assertNotNull(log.getDetails());
        Assert.assertEquals(log.getDetails(), "");
    }

    @Test
    public void createLogWithEmptyDetails() {
        Log log = LogService.createLog(createTest(), STATUS, "");
        Assert.assertEquals(log.getStatus(), STATUS);
        Assert.assertEquals(log.getDetails(), "");
    }

    @Test
    public void createLogWithDetails() {
        Log log = LogService.createLog(createTest(), STATUS, DETAILS);
        Assert.assertEquals(log.getSeq().intValue(), 0);
        Assert.assertEquals(log.getStatus(), STATUS);
        Assert.assertEquals(log.getDetails(), DETAILS);
    }

    @Test
    public void createLogWithException() {
        Throwable t = new RuntimeException();
        ExceptionInfo exInfo = ExceptionInfoService.createExceptionInfo(t);
        Log log = LogService.createLog(createTest(), Status.FAIL, exInfo);
        Assert.assertEquals(log.getSeq().intValue(), 0);
        Assert.assertEquals(log.getStatus(), Status.FAIL);
        Assert.assertEquals(log.getDetails(), "");
        Assert.assertEquals(log.getExceptions().get(0), exInfo);
        Assert.assertEquals(log.getExceptions().get(0).getException(), t);
    }

    @Test
    public void addNullMediaToLog() {
        Log log = LogService.createLog(createTest(), STATUS, DETAILS);
        LogService.addMedia(log, null);
        Assert.assertFalse(LogService.logHasMedia(log));
    }

    @Test
    public void addEmptyMediaToLog() {
        Log log = LogService.createLog(createTest(), STATUS, DETAILS);
        LogService.addMedia(log, ScreenCapture.builder().build());
        Assert.assertFalse(LogService.logHasMedia(log));
    }

    @Test
    public void addMediaWithPathToLog() {
        Log log = LogService.createLog(createTest(), STATUS, DETAILS);
        LogService.addMedia(log, ScreenCapture.builder().path("./img.png").build());
        Assert.assertTrue(LogService.logHasMedia(log));
    }

    @Test
    public void addMediaWithResolvedPathToLog() {
        Log log = LogService.createLog(createTest(), STATUS, DETAILS);
        LogService.addMedia(log, ScreenCapture.builder().resolvedPath("./img.png").build());
        Assert.assertTrue(LogService.logHasMedia(log));
    }
}
