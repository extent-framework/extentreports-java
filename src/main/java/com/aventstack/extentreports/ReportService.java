package com.aventstack.extentreports;

import com.aventstack.extentreports.reporter.AbstractReporter;
import com.aventstack.extentreports.reporter.ExtentReporter;

/**
 * The main service for {@link ExtentReports} which allows attaching a reporter
 * of type {@link AbstractReporter}
 */
public interface ReportService {

	void attachReporter(ExtentReporter... reporter);

	void generateRecentStatus();

}