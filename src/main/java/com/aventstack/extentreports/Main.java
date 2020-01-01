package com.aventstack.extentreports;

import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.convert.TestModelReportBuilder;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Given;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.gherkin.model.Then;
import com.aventstack.extentreports.gherkin.model.When;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class Main {

	public static void main(String[] args) throws IOException {
		ExtentSparkReporter spark = new ExtentSparkReporter("target/spark/");
		ExtentHtmlReporter html = new ExtentHtmlReporter("target/spark/html.html");
		ExtentReports extent = new ExtentReports();
		TestModelReportBuilder modelBuilder = new TestModelReportBuilder();
		modelBuilder.recreateModelFromJson(extent, new File("target/spark/test.json"));
		extent.attachReporter(spark, html);
		//JsonFormatter json = new JsonFormatter("target/spark/test.json");
		//extent.attachReporter(json);
		
		ExtentTest feature = extent.createTest(Feature.class, "My feature");
		ExtentTest scenario = feature.createNode(Scenario.class, "My scenario");
		scenario.createNode(Given.class, "Given something").pass("");
		scenario.createNode(When.class, "When I").pass("");
		scenario.createNode(Then.class, "Then something").pass("");
		
		extent.flush();
		
		/*
		extent.createTest("FirstTest").pass("Passed");
		extent.createTest("SecondTest").createNode("SecondTestNode").warning("warn");
		extent.createTest("ThirdTest").createNode("ThirdTestNode").createNode("ThirdTestNodeNode").fail("fail");
		extent.createTest("CategoryTest").assignCategory("Cat").pass("Pass");
		extent.createTest("CategoryNodeTest").createNode("CategoryNode").assignCategory("ChildCat").skip("skip");
		*/
		extent.flush();
	}

}
