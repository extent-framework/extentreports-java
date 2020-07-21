package com.aventstack.extentreports;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ExtentTestAttributesTest {

    private ExtentReports extent() {
        return new ExtentReports();
    }

    @Test
    public void extentTestWithNoAuthor() {
        Assert.assertTrue(
                extent().createTest("Test")
                        .assignAuthor()
                        .getModel()
                        .getAuthorSet().isEmpty());
    }

    @Test
    public void extentTestWithAuthor() {
        Assert.assertTrue(
                extent().createTest("Test")
                        .assignAuthor("Author")
                        .getModel()
                        .getAuthorSet().stream()
                        .anyMatch(x -> x.getName().equals("Author")));
    }

    @Test
    public void extentTestWithNoDevice() {
        Assert.assertTrue(
                extent().createTest("Test")
                        .assignDevice()
                        .getModel()
                        .getAuthorSet().isEmpty());
    }

    @Test
    public void extentTestWithDevice() {
        Assert.assertTrue(
                extent().createTest("Test")
                        .assignDevice("Device")
                        .getModel()
                        .getDeviceSet().stream()
                        .anyMatch(x -> x.getName().equals("Device")));
    }

    @Test
    public void extentTestWithNoCategory() {
        Assert.assertTrue(
                extent().createTest("Test")
                        .assignCategory()
                        .getModel()
                        .getAuthorSet().isEmpty());
    }

    @Test
    public void extentTestWithCategory() {
        Assert.assertTrue(
                extent().createTest("Test")
                        .assignCategory("Tag")
                        .getModel()
                        .getCategorySet().stream()
                        .anyMatch(x -> x.getName().equals("Tag")));
    }
}
