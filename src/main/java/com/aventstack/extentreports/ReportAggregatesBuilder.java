package com.aventstack.extentreports;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.aventstack.extentreports.model.Author;
import com.aventstack.extentreports.model.Category;
import com.aventstack.extentreports.model.Device;
import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.model.context.ExceptionTestContextStore;
import com.aventstack.extentreports.model.context.SystemAttributeContext;
import com.aventstack.extentreports.model.context.TestAttributeTestContextStore;

/**
 * Builds {@link ReportAggregates}
 *
 */
public class ReportAggregatesBuilder {

	private List<Test> testList;
	private List<String> testRunnerLogs;
	private TestAttributeTestContextStore<Category> categoryContext;
	private TestAttributeTestContextStore<Author> authorContext;
	private TestAttributeTestContextStore<Device> deviceContext;
	private ExceptionTestContextStore exceptionContext;
	private SystemAttributeContext systemAttributeContext;
	private ReportStatusStats reportStatusStats;
	private Collection<Status> statusCollection;
	private Date startTime;
	private Date endTime;

	public ReportAggregates build() {
		ReportAggregates aggregates = new ReportAggregates();
		aggregates.setTestList(testList);
		aggregates.setTestRunnerLogs(testRunnerLogs);
		aggregates.setCategoryContext(categoryContext);
		aggregates.setAuthorContext(authorContext);
		aggregates.setDeviceContext(deviceContext);
		aggregates.setExceptionContext(exceptionContext);
		aggregates.setSystemAttributeContext(systemAttributeContext);
		aggregates.setReportStatusStats(reportStatusStats);
		aggregates.setStatusCollection(statusCollection);
		aggregates.setStartTime(startTime);
		aggregates.setEndTime(endTime);
		return aggregates;
	}

	public ReportAggregatesBuilder setTestList(List<Test> testList) {
		this.testList = testList;
		return this;
	}

	public ReportAggregatesBuilder setTestRunnerLogs(List<String> testRunnerLogs) {
		this.testRunnerLogs = testRunnerLogs;
		return this;
	}

	public ReportAggregatesBuilder setCategoryContext(TestAttributeTestContextStore<Category> categoryContext) {
		this.categoryContext = categoryContext;
		return this;
	}

	public ReportAggregatesBuilder setAuthorContext(TestAttributeTestContextStore<Author> authorContext) {
		this.authorContext = authorContext;
		return this;
	}

	public ReportAggregatesBuilder setDeviceContext(TestAttributeTestContextStore<Device> deviceContext) {
		this.deviceContext = deviceContext;
		return this;
	}

	public ReportAggregatesBuilder setExceptionContext(ExceptionTestContextStore exceptionContext) {
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

	public ReportAggregatesBuilder setStatusCollection(Collection<Status> statusCollection) {
		this.statusCollection = statusCollection;
		return this;
	}

	public ReportAggregatesBuilder setStartTime(Date startTime) {
		this.startTime = startTime;
		return this;
	}

	public ReportAggregatesBuilder setEndTime(Date endTime) {
		this.endTime = endTime;
		return this;
	}

}