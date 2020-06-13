package com.aventstack.extentreports.entity.service;

import java.util.Arrays;
import java.util.List;

import org.testng.Assert;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.entity.Scenario;
import com.aventstack.extentreports.model.Author;
import com.aventstack.extentreports.model.Category;
import com.aventstack.extentreports.model.Device;
import com.aventstack.extentreports.model.ExceptionInfo;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.model.Report;
import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.model.context.NamedAttributeContextManager;
import com.aventstack.extentreports.model.service.ReportService;
import com.aventstack.extentreports.model.service.TestService;

public class ReportServiceTest {
    private ExtentReports extent() {
        return new ExtentReports();
    }

    @org.testng.annotations.Test
    public void reportTestListSize() {
        Test test = TestService.createTest("Test");
        Report report = Report.builder().build();
        Assert.assertEquals(report.getTestList().size(), 0);
        report.getTestList().add(test);
        Assert.assertEquals(report.getTestList().size(), 1);
    }

    @org.testng.annotations.Test
    public void reportIsBDD() {
        Test test = TestService.createTest("Test");
        Report report = Report.builder().build();
        Assert.assertFalse(ReportService.isBDD(report));
        report.getTestList().add(test);
        Assert.assertFalse(ReportService.isBDD(report));
        test.setBddType(Scenario.class);
        Assert.assertTrue(ReportService.isBDD(report));
    }

    @org.testng.annotations.Test
    public void reportTestHasStatus() {
        Test test = TestService.createTest("Test");
        Log skip = Log.builder().status(Status.SKIP).build();
        Log pass = Log.builder().status(Status.PASS).build();
        Report report = Report.builder().build();
        report.getTestList().add(test);
        Assert.assertTrue(ReportService.anyTestHasStatus(report, Status.PASS));
        Assert.assertFalse(ReportService.anyTestHasStatus(report, Status.SKIP));
        TestService.addLog(skip, test);
        Assert.assertFalse(ReportService.anyTestHasStatus(report, Status.PASS));
        Assert.assertTrue(ReportService.anyTestHasStatus(report, Status.SKIP));
        TestService.addLog(pass, test);
        Assert.assertFalse(ReportService.anyTestHasStatus(report, Status.PASS));
        Assert.assertTrue(ReportService.anyTestHasStatus(report, Status.SKIP));
    }

    @org.testng.annotations.Test
    public void authorCtx() {
        ExtentReports extent = extent();
        ExtentTest test = extent.createTest("Test");
        NamedAttributeContextManager<Author> context = extent.getReport().getAuthorCtx();
        Assert.assertFalse(context.hasItems());
        test.assignAuthor("x");
        Assert.assertTrue(context.hasItems());
        Assert.assertTrue(context.getSet().stream().anyMatch(x -> x.getAttr().getName().equals("x")));
        Assert.assertTrue(context.getSet().stream().anyMatch(x -> x.getTestList().size() == 1));
        Assert.assertTrue(context.getSet().stream()
                .flatMap(x -> x.getTestList().stream())
                .anyMatch(x -> x.getName().equals("Test")));
    }

    @org.testng.annotations.Test
    public void categoryCtx() {
        ExtentReports extent = extent();
        ExtentTest test = extent.createTest("Test");
        NamedAttributeContextManager<Category> context = extent.getReport().getCategoryCtx();
        Assert.assertFalse(context.hasItems());
        test.assignCategory("x");
        Assert.assertTrue(context.hasItems());
        Assert.assertTrue(context.getSet().stream().anyMatch(x -> x.getAttr().getName().equals("x")));
        Assert.assertTrue(context.getSet().stream().anyMatch(x -> x.getTestList().size() == 1));
        Assert.assertTrue(context.getSet().stream()
                .flatMap(x -> x.getTestList().stream())
                .anyMatch(x -> x.getName().equals("Test")));
    }

    @org.testng.annotations.Test
    public void deviceCtx() {
        ExtentReports extent = extent();
        ExtentTest test = extent.createTest("Test");
        NamedAttributeContextManager<Device> context = extent.getReport().getDeviceCtx();
        Assert.assertFalse(context.hasItems());
        test.assignDevice("x");
        Assert.assertTrue(context.hasItems());
        Assert.assertTrue(context.getSet().stream().anyMatch(x -> x.getAttr().getName().equals("x")));
        Assert.assertTrue(context.getSet().stream().anyMatch(x -> x.getTestList().size() == 1));
        Assert.assertTrue(context.getSet().stream()
                .flatMap(x -> x.getTestList().stream())
                .anyMatch(x -> x.getName().equals("Test")));
    }

    @org.testng.annotations.Test
    public void exceptionContext() {
        String msg = "An exception has occurred.";
        RuntimeException ex = new RuntimeException(msg);
        ExtentReports extent = extent();
        ExtentTest test = extent.createTest("Test");
        NamedAttributeContextManager<ExceptionInfo> context = extent.getReport().getExceptionInfoCtx();
        Assert.assertFalse(context.hasItems());
        test.fail(ex);
        test.assignDevice("x");
        Assert.assertTrue(context.hasItems());
        Assert.assertTrue(
                context.getSet().stream().anyMatch(x -> x.getAttr().getName().equals("java.lang.RuntimeException")));
        Assert.assertTrue(context.getSet().stream().anyMatch(x -> x.getTestList().size() == 1));
        Assert.assertTrue(context.getSet().stream()
                .flatMap(x -> x.getTestList().stream())
                .anyMatch(x -> x.getName().equals("Test")));
    }

    @org.testng.annotations.Test
    public void testRunnerLogs() {
        String[] s = new String[]{"Log 1", "Log 2", "Log 3"};
        ExtentReports extent = extent();
        List<String> logs = extent.getReport().getLogs();
        Assert.assertTrue(logs.isEmpty());
        Arrays.stream(s).forEach(x -> extent.addTestRunnerOutput(x));
        Assert.assertFalse(logs.isEmpty());
        Arrays.stream(s).forEach(x -> Assert.assertTrue(logs.contains(x)));
    }

    @org.testng.annotations.Test
    public void timeTaken() {
        Report report = Report.builder().build();
        long duration = ReportService.timeTaken(report);
        Assert.assertTrue(duration < 5);
    }
}
