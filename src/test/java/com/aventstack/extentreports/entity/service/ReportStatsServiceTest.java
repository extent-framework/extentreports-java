package com.aventstack.extentreports.entity.service;

import java.util.Arrays;

import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Report;
import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.model.service.ReportStatsService;
import com.aventstack.extentreports.model.service.TestService;

public class ReportStatsServiceTest {
    @org.testng.annotations.Test
    public void statsSize() {
        Test test = TestService.createTest("Test");
        Report report = Report.builder().build();
        report.getTestList().add(test);
        Assert.assertEquals(report.getStats().getParent().size(), 0);
        ReportStatsService.refreshReportStats(report.getStats(), report.getTestList());
        Assert.assertEquals(report.getStats().getParent().size(), Status.values().length);
    }

    @org.testng.annotations.Test
    public void statsTestStatus() {
        Test test = TestService.createTest("Test");
        Report report = Report.builder().build();
        report.getTestList().add(test);
        ReportStatsService.refreshReportStats(report.getStats(), report.getTestList());
        // check if all Status fields are present
        Arrays.asList(Status.values()).forEach(x -> Assert.assertTrue(report.getStats().getParent().containsKey(x)));
        Arrays.asList(Status.values()).forEach(x -> Assert.assertFalse(report.getStats().getChild().containsKey(x)));
        Arrays.asList(Status.values())
                .forEach(x -> Assert.assertFalse(report.getStats().getGrandchild().containsKey(x)));
        test.setStatus(Status.FAIL);
        ReportStatsService.refreshReportStats(report.getStats(), report.getTestList());
        Assert.assertEquals(report.getStats().getParent().get(Status.PASS).longValue(), 0);
        Assert.assertEquals(report.getStats().getParent().get(Status.FAIL).longValue(), 1);
    }

    @org.testng.annotations.Test
    public void statsChildStatus() {
        Test test = TestService.createTest("Test");
        Test node = TestService.createTest("Node");
        node.setStatus(Status.SKIP);
        TestService.addNode(node, test);
        Report report = Report.builder().build();
        report.getTestList().add(test);
        ReportStatsService.refreshReportStats(report.getStats(), report.getTestList());
        // check if all Status fields are present
        Arrays.asList(Status.values()).forEach(x -> Assert.assertTrue(report.getStats().getParent().containsKey(x)));
        Arrays.asList(Status.values()).forEach(x -> Assert.assertTrue(report.getStats().getChild().containsKey(x)));
        Arrays.asList(Status.values())
                .forEach(x -> Assert.assertFalse(report.getStats().getGrandchild().containsKey(x)));
        Assert.assertEquals(report.getStats().getParent().get(Status.PASS).longValue(), 0);
        Assert.assertEquals(report.getStats().getParent().get(Status.SKIP).longValue(), 1);
        Assert.assertEquals(report.getStats().getChild().get(Status.PASS).longValue(), 0);
        Assert.assertEquals(report.getStats().getChild().get(Status.SKIP).longValue(), 1);
    }

    @org.testng.annotations.Test
    public void statsGrandchildStatus() {
        Test test = TestService.createTest("Test");
        Test node = TestService.createTest("Node");
        Test grandchild = TestService.createTest("Grandchild");
        grandchild.setStatus(Status.FAIL);
        TestService.addNode(grandchild, node);
        TestService.addNode(node, test);
        Report report = Report.builder().build();
        report.getTestList().add(test);
        ReportStatsService.refreshReportStats(report.getStats(), report.getTestList());
        // check if all Status fields are present
        Arrays.asList(Status.values()).forEach(x -> Assert.assertTrue(report.getStats().getParent().containsKey(x)));
        Arrays.asList(Status.values()).forEach(x -> Assert.assertTrue(report.getStats().getChild().containsKey(x)));
        Arrays.asList(Status.values())
                .forEach(x -> Assert.assertTrue(report.getStats().getGrandchild().containsKey(x)));
        Assert.assertEquals(report.getStats().getParent().get(Status.PASS).longValue(), 0);
        Assert.assertEquals(report.getStats().getParent().get(Status.FAIL).longValue(), 1);
        Assert.assertEquals(report.getStats().getChild().get(Status.PASS).longValue(), 0);
        Assert.assertEquals(report.getStats().getChild().get(Status.FAIL).longValue(), 1);
        Assert.assertEquals(report.getStats().getGrandchild().get(Status.PASS).longValue(), 0);
        Assert.assertEquals(report.getStats().getGrandchild().get(Status.FAIL).longValue(), 1);
    }
}
