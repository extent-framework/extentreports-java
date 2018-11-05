package com.aventstack.extentreports.api;

import java.lang.reflect.Method;
import java.util.Optional;

import org.testng.Assert;
import org.testng.SkipException;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Base;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.AbstractReporter;

public class RemoveStartedTestsFromExtentTest extends Base {

	private AbstractReporter reporter;
	
	@BeforeMethod
	public void beforeMethod() {
		setup();
		
		Optional<AbstractReporter> reporter = extent
			.getStartedReporters()
			.stream()
			.filter(x -> x instanceof AbstractReporter)
			.map(x -> (AbstractReporter)x)
			.findFirst();
		if (reporter.get() != null)
			this.reporter = reporter.get();
	}
	
	@Test
	public void removeAnErroredTest(Method method) {
		if (reporter == null)
			throw new SkipException("No Reporters were started.");		
		
		extent.createTest("Pass").pass("Hello");
		ExtentTest test = extent.createTest("Error").error("Hello");
		extent.removeTest(test);
		extent.flush();
		
		boolean b = reporter.getStatusList().contains(Status.ERROR);
		Assert.assertFalse(b, "Error status was removed, collection still contains it");
		
		b = reporter.getStatusList().contains(Status.PASS);
		Assert.assertTrue(b, "Pass status was not removed, collection does not contain it");
	}
	
	@Test
	public void removeAPassedTest(Method method) {
		if (reporter == null)
			throw new SkipException("No Reporters were started.");		
		
		ExtentTest test = extent.createTest("Pass").pass("Hello");
		extent.createTest("Error").error("Hello");
		extent.removeTest(test);
		extent.flush();
		
		boolean b = reporter.getStatusList().contains(Status.PASS);
		Assert.assertFalse(b, "Pass status was removed, collection still contains it");
		
		b = reporter.getStatusList().contains(Status.ERROR);
		Assert.assertTrue(b, "Error status was not removed, collection does not contain it");
	}
	
}
