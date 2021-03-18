package com.aventstack.extentreports.entity;

import java.util.stream.IntStream;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

public class ReportStatsConcurrentTest {

    /**
     * Fix for ConcurrentModificationException caused due to iterating over testList
     * vector in ReportStats::update
     * 
     * Issue details:
     * https://github.com/extent-framework/extentreports-java/issues/250
     */
    @Test
    public void concurrentAddUpdateFlush() {
        ExtentReports extent = new ExtentReports();
        IntStream.range(0, 100).parallel().forEach(x -> {
            extent.createTest(String.valueOf(x)).pass("");
            extent.flush();
        });
        Assert.assertEquals(100, extent.getReport().getTestList().size());
    }
    
    @Test
    public void concurrentAddRemoveUpdateFlush() {
        ExtentReports extent = new ExtentReports();
        IntStream.range(0, 100).parallel().forEach(x -> {
            extent.createTest(String.valueOf(x)).pass("");
            extent.removeTest(String.valueOf(x));
            extent.flush();
        });
        Assert.assertEquals(0, extent.getReport().getTestList().size());
    }
    
    @Test
    public void concurrentAddModifyUpdateFlush() {
        ExtentReports extent = new ExtentReports();
        IntStream.range(0, 100).parallel().forEach(x -> {
            ExtentTest test = extent.createTest(String.valueOf(x)).pass("");
            test.createNode("Node").pass("");
            extent.flush();
        });
        Assert.assertEquals(100, extent.getReport().getTestList().size());
    }
}
