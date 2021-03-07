package com.aventstack.extentreports.entity;

import java.util.stream.IntStream;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;

public class ReportStatsConcurrentTest {

    /**
     * Fix for ConcurrentModificationException caused due to iterating over testList
     * vector in ReportStats::update
     * 
     * Issue details:
     * https://github.com/extent-framework/extentreports-java/issues/250
     */
    @Test
    public void concurrentUpdateFlush() {
        ExtentReports extent = new ExtentReports();
        IntStream.range(0, 100).parallel().forEach(x -> {
            extent.createTest(String.valueOf(x)).pass("");
            extent.flush();
        });
        Assert.assertEquals(100, extent.getReport().getTestList().size());
    }
}
