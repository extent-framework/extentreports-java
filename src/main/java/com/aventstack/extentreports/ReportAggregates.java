package com.aventstack.extentreports;

import java.util.ArrayList;
import java.util.List;

import com.aventstack.extentreports.model.Author;
import com.aventstack.extentreports.model.Category;
import com.aventstack.extentreports.model.Device;
import com.aventstack.extentreports.model.Test;

/**
 * Aggregator for report elements and collections
 *
 */
public class ReportAggregates {

	private List<Test> testList;
	private List<String> testRunnerLogs;
	private TestAttributeTestContextProvider<Category> categoryContext;
	private TestAttributeTestContextProvider<Author> authorContext;
	private TestAttributeTestContextProvider<Device> deviceContext;
	private ExceptionTestContextImpl exceptionContext;
	private SystemAttributeContext systemAttributeContext;
	private ReportStatusStats reportStatusStats;
	private List<Status> statusList;

	public ReportAggregates(List<Test> testList, List<String> testRunnerLogs, 
			TestAttributeTestContextProvider<Category> categoryContext, TestAttributeTestContextProvider<Author> authorContext,
			TestAttributeTestContextProvider<Device> deviceContext, ExceptionTestContextImpl exceptionContext, 
			SystemAttributeContext systemAttributeContext, ReportStatusStats reportStatusStats, List<Status> statusList) {
		this.testList = new ArrayList<>(testList);
		this.testRunnerLogs = testRunnerLogs;
		this.categoryContext = categoryContext;
		this.authorContext = authorContext;
		this.deviceContext = deviceContext;
		this.exceptionContext = exceptionContext;
		this.systemAttributeContext = systemAttributeContext;
		this.reportStatusStats = reportStatusStats;
		this.statusList = new ArrayList<>(statusList);
	}
	
	public List<Test> getTestList() {
		return testList;
	}
	
	public List<String> getTestRunnerLogs() {
		return testRunnerLogs;
	}
	
	public TestAttributeTestContextProvider<Category> getCategoryContext() {
		return categoryContext;
	}
	
	public TestAttributeTestContextProvider<Author> getAuthorContext() {
		return authorContext;
	}
	
	public TestAttributeTestContextProvider<Device> getDeviceContext() {
		return deviceContext;
	}
	
	public ExceptionTestContextImpl getExceptionContext() {
		return exceptionContext;
	}
	
	public SystemAttributeContext getSystemAttributeContext() {
		return systemAttributeContext;
	}
	
	public ReportStatusStats getReportStatusStats() {
		return reportStatusStats;
	}
	
	public List<Status> getStatusList() {
		return statusList;
	}
	
	public Status getStatus() {
		return Status.getHighestStatus(getStatusList());
	}
	
}
