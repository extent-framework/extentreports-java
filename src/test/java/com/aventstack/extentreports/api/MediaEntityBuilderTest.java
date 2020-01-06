package com.aventstack.extentreports.api;

import java.io.IOException;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Base;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.service.LogService;

public class MediaEntityBuilderTest extends Base {

    private static final String IMG_PATH = "src/test/resources/logo.png";
    
    @Test
    public void testLogMediaEntity(Method method) throws IOException {
        ExtentTest t = extent.createTest(method.getName()).pass("details", MediaEntityBuilder.createScreenCaptureFromPath(IMG_PATH).build());
        Assert.assertTrue(LogService.logHasScreenCapture(t.getModel().getLogContext().getFirst()));
    }
    
    @Test
    public void testLogMediaEntityWithTitle(Method method) throws IOException {
        ExtentTest t = extent.createTest(method.getName()).pass("details", MediaEntityBuilder.createScreenCaptureFromPath(IMG_PATH, "title").build());
        Assert.assertTrue(LogService.logHasScreenCapture(t.getModel().getLogContext().getFirst()));
    }
    
    @Test
    public void testLogMediaEntityBase64(Method method) throws IOException {
        ExtentTest t = extent.createTest(method.getName()).pass("details", MediaEntityBuilder.createScreenCaptureFromBase64String("base64").build());
        Assert.assertTrue(LogService.logHasScreenCapture(t.getModel().getLogContext().getFirst()));
    }
    
}
