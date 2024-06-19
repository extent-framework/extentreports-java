package com.aventstack.extentreports.reporter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;

public class JsonFormatterStandardTest {

    private static final String BASE_PATH = "target/";
    private static final String JSON_PATH = BASE_PATH + "extent.json";

    @BeforeMethod
    public void beforeHook() throws IOException {
        try {
            Files.createDirectories(Paths.get(BASE_PATH));
            Files.delete(Path.of(JSON_PATH));
        } catch (Exception ignored) { }
    }

    @Test
    public void writeRead() throws IOException {
        // write
        ExtentReports extent = new ExtentReports();
        JsonFormatter json = new JsonFormatter(JSON_PATH);
        extent.attachReporter(json);
        extent.createTest("Test").pass("message");
        extent.flush();

        // read, recreate domain from JSON_PATH
        extent.createDomainFromJsonArchive(JSON_PATH);

        executeAsserts(extent);
    }

    private void executeAsserts(final ExtentReports extent) {
        Assert.assertEquals(extent.getReport().getTestList().size(), 2);
        Assert.assertEquals(extent.getReport().getTestList().get(0).getName(),
                extent.getReport().getTestList().get(1).getName());
    }

    @Test
    public void supportsThrowable() throws IOException {
        // write a failed test with an exception
        ExtentReports extent = new ExtentReports();
        JsonFormatter json = new JsonFormatter(JSON_PATH);
        extent.attachReporter(json);
        extent.createTest("Test")
                .fail(new IllegalArgumentException(""));
        extent.flush();

        // read back the test with exception
        extent.createDomainFromJsonArchive(JSON_PATH);

        executeAsserts(extent);

        // check for exceptions
        for (var test : extent.getReport().getTestList()) {
            Assert.assertEquals(test.getExceptions().size(), 1);
            Assert.assertEquals(test.getExceptions().get(0).getName(), IllegalArgumentException.class.getName());
        }
    }

}
