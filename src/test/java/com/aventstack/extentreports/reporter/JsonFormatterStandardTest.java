package com.aventstack.extentreports.reporter;

import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;

public class JsonFormatterStandardTest {
    private static final String JSON_PATH = "target/extent.json";

    @Test
    public void writeStandard() {
        ExtentReports extent = new ExtentReports();
        JsonFormatter json = new JsonFormatter(JSON_PATH);
        extent.attachReporter(json);
        extent.createTest("Test");
        extent.flush();
    }

    @Test(dependsOnMethods = "writeStandard")
    public void readStandard() throws IOException {
        ExtentReports extent = new ExtentReports();
        extent.createDomainFromJsonArchive(JSON_PATH);
    }
}
