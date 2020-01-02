package com.aventstack.extentreports.convert;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.model.Test;

public class TestModelReportBuilder {

	public void recreateModelFromJson(ExtentReports extent, File jsonFile) throws IOException {
		if (!jsonFile.exists()) {
			return;
		}
		extent.setReportUsesManualConfiguration(true);
		List<Test> tests = JsonDeserializer.deserialize(jsonFile);
		for (Test test : tests) {
			recreateTest(test, extent.createTest(test.getName(), test.getDescription()));
		}
		extent.setReportUsesManualConfiguration(false);
	}

	public void recreateTest(Test test, ExtentTest extentTest) {
		// create events
		test.getLogContext().getAll().forEach(x -> extentTest.log(x.getStatus(), x.getDetails()));

		// assign attributes
		test.getAuthorContext().getAll().forEach(x -> extentTest.assignAuthor(x.getName()));
		test.getCategoryContext().getAll().forEach(x -> extentTest.assignCategory(x.getName()));
		test.getDeviceContext().getAll().forEach(x -> extentTest.assignDevice(x.getName()));

		// handle nodes
		for (Test node : test.getNodeContext().getAll()) {
			ExtentTest extentNode = extentTest.createNode(node.getName());
			recreateTest(node, extentNode);
		}
	}

}
