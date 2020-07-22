package com.aventstack.extentreports.reporter;

import java.io.File;
import java.util.Calendar;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;

public class FileReporterPathsTest {

    private ExtentReports getExtent(String r, String f) {
        ExtentReports extent = new ExtentReports();
        if (r == "spark")
            extent.attachReporter(new ExtentSparkReporter(f));
        else
            extent.attachReporter(new JsonFormatter(f));
        extent.createTest("Test").pass("Pass");
        extent.flush();
        return extent;
    }

    private String getFileName(String ext) {
        final String basePath = "target/";
        long t = Calendar.getInstance().getTimeInMillis();
        String fileName = basePath + t + ext;
        return fileName;
    }

    @Test
    public void sparkPathAsFile() {
        String f = getFileName(".html");
        getExtent("spark", f);
        Assert.assertTrue(new File(f).exists());
    }

    @Test
    public void sparkPathAsDirectory() {
        String f = getFileName("");
        getExtent("spark", f);
        Assert.assertTrue(new File(f).exists());
        Assert.assertTrue(new File(f + "/Index.html").exists());
    }

    @Test
    public void jsonPathAsFile() {
        String f = getFileName(".json");
        getExtent("json", f);
        Assert.assertTrue(new File(f).exists());
    }

    @Test
    public void jsonPathAsDirectory() {
        String f = getFileName("");
        getExtent("json", f);
        Assert.assertTrue(new File(f).exists());
        Assert.assertTrue(new File(f + "/extent.json").exists());
    }
}
