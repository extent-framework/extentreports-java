package com.aventstack.extentreports;

import org.testng.annotations.Test;

public class ExtentTestTest {

    private final ExtentReports extent = new ExtentReports();

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void extentTestWithEmptyName() {
        extent.createTest("");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void extentTestWithNullName() {
        extent.createTest(null);
    }
}
