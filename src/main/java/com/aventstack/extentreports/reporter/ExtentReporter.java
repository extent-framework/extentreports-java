package com.aventstack.extentreports.reporter;

import com.aventstack.extentreports.IAnalysisStrategyMethod;
import com.aventstack.extentreports.ReportAggregates;
import com.aventstack.extentreports.ReportAggregatesListener;
import com.aventstack.extentreports.TestListener;

/**
 * Primary interface implemented by each reporter. This interface implements
 * {@link TestListener} and {@link ReportAggregatesListener} interface to
 * provide additional functionality to each reporter.
 */
public interface ExtentReporter extends TestListener, IAnalysisStrategyMethod {

	/**
	 * Starts passing run information to the reporter
	 */
	void start();

	/**
	 * Stops the reporter. Ensures no information is passed to the reporter.
	 */
	void stop();

	/**
	 * Write to or update the target source (file, database)
	 * 
	 * @param reportAggregates a {@link ReportAggregates} object
	 */
	void flush(ReportAggregates reportAggregates);

	/**
	 * Get the reporter name
	 * 
	 * @return reporter name
	 */
	String getReporterName();
}