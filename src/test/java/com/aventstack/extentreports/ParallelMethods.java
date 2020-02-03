package com.aventstack.extentreports;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.common.ExtentManager;
import com.aventstack.extentreports.common.ExtentTestManager;

public class ParallelMethods {
    
    @BeforeClass
    public void beforeClass() {
        ExtentManager.createInstance();       
        ExtentTestManager.setReporter(ExtentManager.getInstance());
    }
    
    @AfterClass
    public void afterClass() {
        ExtentTestManager.getReporter().flush();
    }
    
    @Test
    public void method1(Method method) {
        ExtentTestManager.createTest(method.getName()).createNode(method.getName()).pass("pass");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.PASS);
    }
    
    @Test
    public void method2(Method method) {
        ExtentTestManager.createTest(method.getName()).createNode(method.getName()).fail("fail");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.FAIL);
    }
    
    @Test
    public void method3(Method method) {
        ExtentTestManager.createTest(method.getName()).createNode(method.getName()).error("error");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.ERROR);
    }
    
    @Test
    public void method4(Method method) {
        ExtentTestManager.createTest(method.getName()).createNode(method.getName()).warning("warning");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.WARNING);
    }
    
    @Test
    public void method5(Method method) {
        ExtentTestManager.createTest(method.getName()).createNode(method.getName()).info("info");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.PASS);
    }
    
    @Test
    public void method6(Method method) {
        ExtentTestManager.createTest(method.getName()).createNode(method.getName()).fatal("fatal");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.FATAL);
    }
    
    @Test
    public void method7(Method method) {
        ExtentTestManager.createTest(method.getName()).createNode(method.getName()).pass("");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.PASS);
    }
    
    @Test
    public void method17(Method method) {
        ExtentTestManager.createTest(method.getName()).pass("pass");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.PASS);
    }
    
    @Test
    public void method27(Method method) {
        ExtentTestManager.createTest(method.getName()).createNode(method.getName()).fail("fail");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.FAIL);
    }
    
    @Test
    public void method37(Method method) {
        ExtentTestManager.createTest(method.getName()).createNode(method.getName()).error("error");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.ERROR);
    }
    
    @Test
    public void method47(Method method) {
        ExtentTestManager.createTest(method.getName()).createNode(method.getName()).warning("warning");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.WARNING);
    }
    
    @Test
    public void method57(Method method) {
        ExtentTestManager.createTest(method.getName()).info("info");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.PASS);
    }
    
    @Test
    public void method67(Method method) {
        ExtentTestManager.createTest(method.getName()).fatal("fatal");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.FATAL);
    }
    
    @Test
    public void method77(Method method) {
        ExtentTestManager.createTest(method.getName());
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.PASS);
    }
    
    @Test
    public void method1x(Method method) {
        ExtentTestManager.createTest(method.getName()).createNode(method.getName()).pass("pass");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.PASS);
    }
    
    @Test
    public void method2x(Method method) {
        ExtentTestManager.createTest(method.getName()).createNode(method.getName()).fail("fail");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.FAIL);
    }
    
    @Test
    public void method3x(Method method) {
        ExtentTestManager.createTest(method.getName()).error("error");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.ERROR);
    }
    
    @Test
    public void method4x(Method method) {
        ExtentTestManager.createTest(method.getName()).warning("warning");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.WARNING);
    }
    
    @Test
    public void method5x(Method method) {
        ExtentTestManager.createTest(method.getName()).createNode(method.getName()).info("info");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.PASS);
    }
    
    @Test
    public void method6x(Method method) {
        ExtentTestManager.createTest(method.getName()).fatal("fatal");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.FATAL);
    }
    
    @Test
    public void method7x(Method method) {
        ExtentTestManager.createTest(method.getName());
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.PASS);
    }
    
    @Test
    public void method17y(Method method) {
        ExtentTestManager.createTest(method.getName()).createNode(method.getName()).pass("pass");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.PASS);
    }
    
    @Test
    public void method27y(Method method) {
        ExtentTestManager.createTest(method.getName()).fail("fail");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.FAIL);
    }
    
    @Test
    public void method37y(Method method) {
        ExtentTestManager.createTest(method.getName()).createNode(method.getName()).error("error");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.ERROR);
    }
    
    @Test
    public void method47y(Method method) {
        ExtentTestManager.createTest(method.getName()).warning("warning");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.WARNING);
    }
    
    @Test
    public void method57y(Method method) {
        ExtentTestManager.createTest(method.getName()).info("info");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.PASS);
    }
    
    @Test
    public void method67y(Method method) {
        ExtentTestManager.createTest(method.getName()).fatal("fatal");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.FATAL);
    }
    
    @Test
    public void method77y(Method method) {
        ExtentTestManager.createTest(method.getName());
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.PASS);
    }
    
    @Test
    public void method1v(Method method) {
        ExtentTestManager.createTest(method.getName()).createNode(method.getName()).pass("pass");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.PASS);
    }
    
    @Test
    public void method2v(Method method) {
        ExtentTestManager.createTest(method.getName()).createNode(method.getName()).fail("fail");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.FAIL);
    }
    
    @Test
    public void method3v(Method method) {
        ExtentTestManager.createTest(method.getName()).error("error");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.ERROR);
    }
    
    @Test
    public void method4v(Method method) {
        ExtentTestManager.createTest(method.getName()).warning("warning");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.WARNING);
    }
    
    @Test
    public void method5v(Method method) {
        ExtentTestManager.createTest(method.getName()).info("info");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.PASS);
    }
    
    @Test
    public void method6v(Method method) {
        ExtentTestManager.createTest(method.getName()).fatal("fatal");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.FATAL);
    }
    
    @Test
    public void method7v(Method method) {
        ExtentTestManager.createTest(method.getName());
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.PASS);
    }
    
    @Test
    public void method17b(Method method) {
        ExtentTestManager.createTest(method.getName()).pass("pass");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.PASS);
    }
    
    @Test
    public void method27b(Method method) {
        ExtentTestManager.createTest(method.getName()).fail("fail");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.FAIL);
    }
    
    @Test
    public void method37b(Method method) {
        ExtentTestManager.createTest(method.getName()).createNode(method.getName()).error("error");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.ERROR);
    }
    
    @Test
    public void method47b(Method method) {
        ExtentTestManager.createTest(method.getName()).createNode(method.getName()).warning("warning");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.WARNING);
    }
    
    @Test
    public void method57b(Method method) {
        ExtentTestManager.createTest(method.getName()).createNode(method.getName()).info("info");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.PASS);
    }
    
    @Test
    public void method67b(Method method) {
        ExtentTestManager.createTest(method.getName()).createNode(method.getName()).fatal("fatal");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.FATAL);
    }
    
    @Test
    public void method77g(Method method) {
        ExtentTestManager.createTest(method.getName()).pass("").createNode(method.getName()).info("");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.PASS);
    }
    
    @Test
    public void method47g(Method method) {
    	ExtentTestManager.createTest(method.getName()).pass("").createNode(method.getName()).info("");
        ExtentTestManager.getReporter().flush();
    }
    
    @Test
    public void method57g(Method method) {
    	ExtentTestManager.createTest(method.getName()).pass("").createNode(method.getName()).info("");
        ExtentTestManager.getReporter().flush();
        Assert.assertEquals(ExtentTestManager.getTest().getStatus(), Status.PASS);
    }
    
    @Test
    public void method67g(Method method) {
    	ExtentTestManager.createTest(method.getName()).pass("").createNode(method.getName()).info("");
        ExtentTestManager.getReporter().flush();
    }
    
    @Test
    public void method77u(Method method) {
    	ExtentTestManager.createTest(method.getName()).pass("").createNode(method.getName()).info("");
        ExtentTestManager.getReporter().flush();
    }
}
