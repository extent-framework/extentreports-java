package com.aventstack.extentreports.entity;

import java.util.Collections;

import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Report;

public class ReportEntityTest {

    @org.testng.annotations.Test
    public void startAndEndTimesNonNullAtInit() {
        Report report = Report.builder().build();
        Assert.assertNotNull(report.getStartTime());
        Assert.assertNotNull(report.getEndTime());
    }

    @org.testng.annotations.Test
    public void startIsPassOnInit() {
        Report report = Report.builder().build();
        Assert.assertEquals(report.getStatus(), Status.PASS);
    }

    @org.testng.annotations.Test
    public void testsEmptyOnInit() {
        Report report = Report.builder().build();
        Assert.assertEquals(report.getTestList(), Collections.EMPTY_LIST);
    }

    @org.testng.annotations.Test
    public void statsNonNullAtInit() {
        Report report = Report.builder().build();
        Assert.assertNotNull(report.getStats());
    }
}
