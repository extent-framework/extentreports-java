package com.aventstack.extentreports.convert;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.model.Test;

public class TestModelReportBuilder {

	public void createDomainFromJsonArchive(ExtentReports extent, File jsonFile) throws IOException {
		if (!jsonFile.exists()) {
			return;
		}
		Boolean configChanged = extent.getReportUsesManualConfiguration() ? false : true;
		extent.setReportUsesManualConfiguration(true);
		List<Test> tests = JsonDeserializer.deserialize(jsonFile);
		for (Test test : tests) {
			try {
				if (test.getBehaviorDrivenTypeName() == null) {
					createDomain(test, extent.createTest(test.getName(), test.getDescription()));
				} else {
					createDomain(test, extent.createTest(new GherkinKeyword(test.getBehaviorDrivenTypeName()),
							test.getName(), test.getDescription()));

				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		if (configChanged) {
			extent.setReportUsesManualConfiguration(false);
		}
	}

	public void createDomain(Test test, ExtentTest extentTest) throws ClassNotFoundException {
		extentTest.getModel().setStartTime(test.getStartTime());
		extentTest.getModel().setEndTime(test.getEndTime());
		extentTest.getModel().computeEndTimeFromChildren();
		
		// create events
		for (Log log : test.getLogContext().getAll()) {
			if (log.getDetails() != null)
				extentTest.log(log.getStatus(), log.getDetails());
			if (log.getExceptionInfo() != null)
				extentTest.log(log.getStatus(), log.getExceptionInfo());
		}

		// assign attributes
		test.getAuthorContext().getAll().forEach(x -> extentTest.assignAuthor(x.getName()));
		test.getCategoryContext().getAll().forEach(x -> extentTest.assignCategory(x.getName()));
		test.getDeviceContext().getAll().forEach(x -> extentTest.assignDevice(x.getName()));

		// handle nodes
		for (Test node : test.getNodeContext().getAll()) {
			ExtentTest extentNode = null;
			if (node.getBehaviorDrivenTypeName() == null) {
				extentNode = extentTest.createNode(node.getName(), node.getDescription());
			} else {
				extentNode = extentTest.createNode(new GherkinKeyword(node.getBehaviorDrivenTypeName()), node.getName(),
						node.getDescription());
			}
			createDomain(node, extentNode);
		}
	}

}
