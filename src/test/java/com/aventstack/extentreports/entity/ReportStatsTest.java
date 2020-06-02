package com.aventstack.extentreports.entity;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.model.ReportStats;

public class ReportStatsTest {

    @Test
    public void analysisStrategyDefault() {
        ReportStats stats = new ReportStats();
        Assert.assertNotNull(stats.getAnalysisStrategy());
        Assert.assertEquals(stats.getAnalysisStrategy(), AnalysisStrategy.TEST);
    }

    @Test
    public void allLevelMapsNonNull() {
        ReportStats stats = new ReportStats();
        Assert.assertNotNull(stats.getChild());
        Assert.assertNotNull(stats.getChildPercentage());
        Assert.assertNotNull(stats.getGrandchild());
        Assert.assertNotNull(stats.getGrandchildPercentage());
        Assert.assertNotNull(stats.getLog());
        Assert.assertNotNull(stats.getLogPercentage());
        Assert.assertNotNull(stats.getParent());
        Assert.assertNotNull(stats.getParentPercentage());
    }

}
