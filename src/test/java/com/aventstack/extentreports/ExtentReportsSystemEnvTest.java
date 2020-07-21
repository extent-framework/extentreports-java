package com.aventstack.extentreports;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ExtentReportsSystemEnvTest {

    private ExtentReports extent() {
        return new ExtentReports();
    }

    @Test
    public void systemInfo() {
        ExtentReports extent = extent();
        extent.setSystemInfo("a", "b");
        Assert.assertEquals(extent.getReport().getSystemEnvInfo().size(), 1);
        Assert.assertEquals(extent.getReport().getSystemEnvInfo().get(0).getName(), "a");
        Assert.assertEquals(extent.getReport().getSystemEnvInfo().get(0).getValue(), "b");
    }

    @Test
    public void nullSystemInfo() {
        ExtentReports extent = extent();
        extent.setSystemInfo(null, null);
        Assert.assertEquals(extent.getReport().getSystemEnvInfo().size(), 1);
        Assert.assertEquals(extent.getReport().getSystemEnvInfo().get(0).getName(), null);
        Assert.assertEquals(extent.getReport().getSystemEnvInfo().get(0).getValue(), null);
    }

    @Test
    public void emptySystemInfo() {
        ExtentReports extent = extent();
        extent.setSystemInfo("", "");
        Assert.assertEquals(extent.getReport().getSystemEnvInfo().size(), 1);
        Assert.assertEquals(extent.getReport().getSystemEnvInfo().get(0).getName(), "");
        Assert.assertEquals(extent.getReport().getSystemEnvInfo().get(0).getValue(), "");
    }
}
