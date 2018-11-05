package com.aventstack.extentreports;

import java.util.List;

import com.aventstack.extentreports.model.Author;
import com.aventstack.extentreports.model.Category;
import com.aventstack.extentreports.model.Device;
import com.aventstack.extentreports.model.Test;

/**
 * Builds {@link ReportAggregates}
 *
 */
public class ReportAggregatesBuilder {

	private List<Test> testList;
	private List<String> testRunnerLogs;
	private TestAttributeTestContextProvider<Category> categoryContext;
	private TestAttributeTestContextProvider<Author> authorContext;
	private TestAttributeTestContextProvider<Device> deviceContext;
	private ExceptionTestContextImpl exceptionContext;
	private SystemAttributeContext systemAttributeContext;
	private ReportStatusStats reportStatusStats;
	private List<Status> statusList;
	
	public ReportAggregates build() {
		return new ReportAggregates(testList, testRunnerLogs, categoryContext, 
    			authorContext, deviceContext, exceptionContext, systemAttributeContext, 
    			reportStatusStats, statusList);
	}

	public ReportAggregatesBuilder setTestList(List<Test> testList) {
		this.testList = testList;
		return this;
	}

	public ReportAggregatesBuilder setTestRunnerLogs(List<String> testRunnerLogs) {
		this.testRunnerLogs = testRunnerLogs;
		return this;
	}

	public ReportAggregatesBuilder setCategoryContext(TestAttributeTestContextProvider<Category> categoryContext) {
		this.categoryContext = categoryContext;
		return this;
	}

	public ReportAggregatesBuilder setAuthorContext(TestAttributeTestContextProvider<Author> authorContext) {
		this.authorContext = authorContext;
		return this;
	}

	public ReportAggregatesBuilder setDeviceContext(TestAttributeTestContextProvider<Device> deviceContext) {
		this.deviceContext = deviceContext;
		return this;
	}

	public ReportAggregatesBuilder setExceptionContext(ExceptionTestContextImpl exceptionContext) {
		this.exceptionContext = exceptionContext;
		return this;
	}

	public ReportAggregatesBuilder setSystemAttributeContext(SystemAttributeContext systemAttributeContext) {
		this.systemAttributeContext = systemAttributeContext;
		return this;
	}

	public ReportAggregatesBuilder setReportStatusStats(ReportStatusStats reportStatusStats) {
		this.reportStatusStats = reportStatusStats;
		return this;
	}

	public ReportAggregatesBuilder setStatusList(List<Status> statusList) {
		this.statusList = statusList;
		return this;
	}
	
}
