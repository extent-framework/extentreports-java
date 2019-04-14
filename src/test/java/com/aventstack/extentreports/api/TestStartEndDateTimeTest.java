package com.aventstack.extentreports.api;

import java.lang.reflect.Method;
import java.util.Calendar;
import java.util.Date;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Base;
import com.aventstack.extentreports.ExtentTest;

public class TestStartEndDateTimeTest extends Base {
    
    @Test
    public void testStartTimeBoundary(Method method) {
        Date init = Calendar.getInstance().getTime();
        ExtentTest test = extent.createTest(method.getName()).pass("pass");
        Date end = Calendar.getInstance().getTime();
        
        Assert.assertTrue(test.getModel().getStartTime().getTime() >= init.getTime());
        Assert.assertTrue(test.getModel().getStartTime().getTime() <= end.getTime());
    }
    
    @Test
    public void testEndTimeBoundary(Method method) {
        Date init = Calendar.getInstance().getTime();
        ExtentTest test = extent.createTest(method.getName()).pass("pass");
        Date end = Calendar.getInstance().getTime();
        
        Assert.assertTrue(test.getModel().getEndTime().getTime() >= init.getTime());
        Assert.assertTrue(test.getModel().getEndTime().getTime() <= end.getTime());
    }
    
    @Test
    public void testEndTimeLogTimeTaken(Method method) throws InterruptedException {
        int wait = 200;
        Date init = Calendar.getInstance().getTime();
        ExtentTest test = extent.createTest(method.getName());
        Thread.sleep(wait);
        test.pass("pass");
        
        Assert.assertTrue(test.getModel().getEndTime().getTime() >= (init.getTime() + wait));
    }
    
    @Test
    public void testEndTimeNodeTimeTaken(Method method) throws InterruptedException {
        int wait = 200;
        Date init = Calendar.getInstance().getTime();
        ExtentTest test = extent.createTest(method.getName());
        ExtentTest node = test.createNode(method.getName());
        Thread.sleep(wait);
        node.pass("pass");
        extent.flush();
        
        Assert.assertTrue(test.getModel().getEndTime().getTime() >= (init.getTime() + wait));
    }
    
    @Test
    public void testTimeWithManualSettingBoundary(Method method) {
        extent.setReportUsesManualConfiguration(true);
        
        Date init = Calendar.getInstance().getTime();
        ExtentTest test = extent.createTest(method.getName()).pass("pass");
        Date end = Calendar.getInstance().getTime();
        
        Assert.assertTrue(test.getModel().getEndTime().getTime() >= init.getTime());
        Assert.assertTrue(test.getModel().getEndTime().getTime() <= end.getTime());
    }
    
    @Test
    public void testEndTimeWithManualSettingBoundary(Method method) {
        extent.setReportUsesManualConfiguration(true);
        
        Date init = Calendar.getInstance().getTime();
        ExtentTest test = extent.createTest(method.getName()).pass("pass");
        Date end = Calendar.getInstance().getTime();
        
        Assert.assertTrue(test.getModel().getEndTime().getTime() >= init.getTime());
        Assert.assertTrue(test.getModel().getEndTime().getTime() <= end.getTime());
    }

    @Test
    public void testEndTimeWithManualSettingLog(Method method) throws InterruptedException {
        int wait = 200;
        extent.setReportUsesManualConfiguration(true);
        
        Date init = Calendar.getInstance().getTime();
        ExtentTest test = extent.createTest(method.getName());
        Thread.sleep(wait);
        test.pass("pass");
        
        Assert.assertFalse(test.getModel().getEndTime().getTime() >= (init.getTime() + wait));
    }
    
    @Test
    public void testStartTimeWithManualSettingLogSetter(Method method) throws InterruptedException {
        int wait = 200;
        extent.setReportUsesManualConfiguration(true);
        
        Date init = Calendar.getInstance().getTime();
        ExtentTest test = extent.createTest(method.getName());
        Thread.sleep(wait);
        test.pass("pass");
        test.getModel().setStartTime(test.getModel().getLogContext().getLast().getTimestamp());
        
        Assert.assertTrue(test.getModel().getStartTime().getTime() >= (init.getTime() + wait));
    }
    
    @Test
    public void testStartTimeWithManualSettingNodeLogSetter(Method method) throws InterruptedException {
        int wait = 200;
        extent.setReportUsesManualConfiguration(true);
        
        Date init = Calendar.getInstance().getTime();
        ExtentTest test = extent.createTest(method.getName());
        ExtentTest node = test.createNode(method.getName());
        Thread.sleep(wait);
        node.pass("pass");
        test.getModel().setStartTime(test.getModel().getNodeContext().getLast().getEndTime());
        
        Assert.assertTrue(test.getModel().getStartTime().getTime() >= (init.getTime() + wait));
    }
    
    @Test
    public void testEndTimeWithManualSettingLogSetter(Method method) throws InterruptedException {
        int wait = 200;
        extent.setReportUsesManualConfiguration(true);
        
        Date init = Calendar.getInstance().getTime();
        ExtentTest test = extent.createTest(method.getName());
        Thread.sleep(wait);
        test.pass("pass");
        test.getModel().setEndTime(test.getModel().getLogContext().getLast().getTimestamp());
        
        Assert.assertTrue(test.getModel().getEndTime().getTime() >= (init.getTime() + wait));
    }
    
    @Test
    public void testEndTimeWithManualSettingNodeLogSetter(Method method) throws InterruptedException {
        int wait = 200;
        extent.setReportUsesManualConfiguration(true);
        
        Date init = Calendar.getInstance().getTime();
        ExtentTest test = extent.createTest(method.getName());
        ExtentTest node = test.createNode(method.getName());
        Thread.sleep(wait);
        node.pass("pass");
        test.getModel().setEndTime(test.getModel().getNodeContext().getLast().getEndTime());
        
        Assert.assertTrue(test.getModel().getEndTime().getTime() >= (init.getTime() + wait));
    }
    
}
