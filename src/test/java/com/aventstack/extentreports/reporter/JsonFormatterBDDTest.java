package com.aventstack.extentreports.reporter;

import java.io.IOException;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Given;
import com.aventstack.extentreports.gherkin.model.Scenario;

public class JsonFormatterBDDTest {
    private static final String JSON_PATH = "target/extent.json";

    @Test
    public void writeBdd() {
        ExtentReports extent = new ExtentReports();
        JsonFormatter json = new JsonFormatter(JSON_PATH);
        extent.attachReporter(json);
        extent.createTest(Feature.class, "FeatureName")
                .createNode(Scenario.class, "ScenarioName")
                .createNode(Given.class, "Given ..")
                .pass("Pass");
        extent.flush();
    }

    @Test(dependsOnMethods = "writeBdd")
    public void readBdd() throws IOException {
        ExtentReports extent = new ExtentReports();
        extent.createDomainFromJsonArchive(JSON_PATH);
    }
}
