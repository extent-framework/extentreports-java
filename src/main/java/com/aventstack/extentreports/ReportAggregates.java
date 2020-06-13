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
 * Aggregator for report elements and collections
 *
 */
public class ReportAggregates {

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

	public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Status getStatus() {
		return Status.getHighestStatus(getStatusCollection());
	}
	
	public List<Test> getTestList() {
        return testList;
    }

    public void setTestList(List<Test> testList) {
        this.testList = testList;
    }

    public List<String> getTestRunnerLogs() {
        return testRunnerLogs;
    }

    public void setTestRunnerLogs(List<String> testRunnerLogs) {
        this.testRunnerLogs = testRunnerLogs;
    }

    public TestAttributeTestContextStore<Category> getCategoryContext() {
        return categoryContext;
    }

    public void setCategoryContext(TestAttributeTestContextStore<Category> categoryContext) {
        this.categoryContext = categoryContext;
    }

    public TestAttributeTestContextStore<Author> getAuthorContext() {
        return authorContext;
    }

    public void setAuthorContext(TestAttributeTestContextStore<Author> authorContext) {
        this.authorContext = authorContext;
    }

    public TestAttributeTestContextStore<Device> getDeviceContext() {
        return deviceContext;
    }

    public void setDeviceContext(TestAttributeTestContextStore<Device> deviceContext) {
        this.deviceContext = deviceContext;
    }

    public ExceptionTestContextStore getExceptionContext() {
        return exceptionContext;
    }

    public void setExceptionContext(ExceptionTestContextStore exceptionContext) {
        this.exceptionContext = exceptionContext;
    }

    public SystemAttributeContext getSystemAttributeContext() {
        return systemAttributeContext;
    }

    public void setSystemAttributeContext(SystemAttributeContext systemAttributeContext) {
        this.systemAttributeContext = systemAttributeContext;
    }

    public ReportStatusStats getReportStatusStats() {
        return reportStatusStats;
    }

    public void setReportStatusStats(ReportStatusStats reportStatusStats) {
        this.reportStatusStats = reportStatusStats;
    }

    public Collection<Status> getStatusCollection() {
        return statusCollection;
    }

    public void setStatusCollection(Collection<Status> statusCollection) {
        this.statusCollection = statusCollection;
    }
	
}