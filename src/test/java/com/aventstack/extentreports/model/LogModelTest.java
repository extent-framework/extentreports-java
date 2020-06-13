package com.aventstack.extentreports.model;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Base;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;


public class LogModelTest extends Base {

    @Test
    public void testLogStatusPass(Method method) {
        ExtentTest t = extent.createTest(method.getName()).pass("pass");
        Assert.assertEquals(t.getModel().getLogContext().getFirst().getStatus(), Status.PASS);
    }
    
    @Test
    public void testLogStatusFail(Method method) {
        ExtentTest t = extent.createTest(method.getName()).fail("fail");
        Assert.assertEquals(t.getModel().getLogContext().getFirst().getStatus(), Status.FAIL);
    }
    
    @Test
    public void testLogStatusSkip(Method method) {
        ExtentTest t = extent.createTest(method.getName()).skip("skip");
        Assert.assertEquals(t.getModel().getLogContext().getFirst().getStatus(), Status.SKIP);
    }

    @Test
    public void testLogStatusPassMultiple(Method method) {
        ExtentTest t = extent.createTest(method.getName()).fail("fail").pass("pass").skip("skip");
        Assert.assertEquals(t.getModel().getLogContext().getFirst().getStatus(), Status.FAIL);
    }
    
    @Test
    public void testLogStatusFailMultiple(Method method) {
        ExtentTest t = extent.createTest(method.getName()).fail("fail").pass("pass").skip("skip");
        Assert.assertEquals(t.getModel().getLogContext().get(1).getStatus(), Status.PASS);
    }
    
    @Test
    public void testLogStatusSkipMultiple(Method method) {
        ExtentTest t = extent.createTest(method.getName()).fail("fail").pass("pass").skip("skip");
        Assert.assertEquals(t.getModel().getLogContext().getLast().getStatus(), Status.SKIP);
    }
    
    @Test
    public void testLogDetailsContent(Method method) {
        String content = "fail";
        ExtentTest t = extent.createTest(method.getName()).fail(content);
        Assert.assertEquals(t.getModel().getLogContext().getFirst().getDetails(), content);
    }

}
