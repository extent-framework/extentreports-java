package com.aventstack.extentreports.reporter;

import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Given;
import com.aventstack.extentreports.gherkin.model.Scenario;

public class Base64Tests {

	private final static String BASE64 = "";
	
	@Test
	public void testBase64BDDAtFeature() {
		ExtentReports extent = new ExtentReports();
		ExtentHtmlReporter html = new ExtentHtmlReporter("html/index.html");
		ExtentSparkReporter spark = new ExtentSparkReporter("spark/index.html");
		extent.attachReporter(html, spark);
		extent.createTest(Feature.class, "Feature")
			.addScreenCaptureFromBase64String(BASE64);
		extent.flush();
	}
	
	@Test
	public void testBase64BDDAtScenario() {
		ExtentReports extent = new ExtentReports();
		ExtentHtmlReporter html = new ExtentHtmlReporter("html/index.html");
		ExtentSparkReporter spark = new ExtentSparkReporter("spark/index.html");
		extent.attachReporter(html, spark);
		extent.createTest(Feature.class, "Feature")
			.createNode(Scenario.class, "Scenario")
			.addScreenCaptureFromBase64String(BASE64);
		extent.flush();
	}
	
	@Test
	public void testBase64BDDAtStep() {
		ExtentReports extent = new ExtentReports();
		ExtentHtmlReporter html = new ExtentHtmlReporter("html/index.html");
		ExtentSparkReporter spark = new ExtentSparkReporter("spark/index.html");
		extent.attachReporter(html, spark);
		extent.createTest(Feature.class, "Feature")
			.createNode(Scenario.class, "Scenario")
			.createNode(Given.class, "Given")
			.addScreenCaptureFromBase64String(BASE64);
		extent.flush();
	}
	
}
