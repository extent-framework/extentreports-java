package com.aventstack.extentreports.reporter;

import java.io.IOException;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.ReportAggregates;
import com.aventstack.extentreports.ReportStatusStats;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.mediastorage.LocalMediaStorageHandler;
import com.aventstack.extentreports.mediastorage.MediaStorage;
import com.aventstack.extentreports.model.Author;
import com.aventstack.extentreports.model.Category;
import com.aventstack.extentreports.model.Device;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.model.ScreenCapture;
import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.model.context.ExceptionTestContextStore;
import com.aventstack.extentreports.model.context.SystemAttributeContext;
import com.aventstack.extentreports.model.context.TestAttributeTestContextStore;

public abstract class AbstractReporter implements ExtentReporter {

	private Date startTime = Calendar.getInstance().getTime();
	private Date endTime = startTime;
	private AnalysisStrategy strategy = AnalysisStrategy.TEST;

	private MediaStorage media;
	private List<Test> testList;
	private List<String> testRunnerLogs;
	private ExceptionTestContextStore exceptionContext;
	private TestAttributeTestContextStore<Category> categoryContext;
	private TestAttributeTestContextStore<Author> authorContext;
	private TestAttributeTestContextStore<Device> deviceContext;
	private SystemAttributeContext systemAttributeContext;
	private ReportStatusStats stats;
	private Collection<Status> statusCollection;

	public void flush(ReportAggregates reportAggregates) {
		startTime = reportAggregates.getStartTime();
		endTime = reportAggregates.getEndTime();
		this.authorContext = reportAggregates.getAuthorContext();
		this.categoryContext = reportAggregates.getCategoryContext();
		this.deviceContext = reportAggregates.getDeviceContext();
		this.exceptionContext = reportAggregates.getExceptionContext();
		this.stats = reportAggregates.getReportStatusStats();
		this.systemAttributeContext = reportAggregates.getSystemAttributeContext();
		this.testList = reportAggregates.getTestList();
		this.testRunnerLogs = reportAggregates.getTestRunnerLogs();
		this.statusCollection = reportAggregates.getStatusCollection();
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setAnalysisStrategy(AnalysisStrategy strategy) {
		this.strategy = strategy;
	}

	public AnalysisStrategy getAnalysisStrategy() {
		return strategy;
	}

	public long getRunDuration() {
		return endTime.getTime() - startTime.getTime();
	}

	public String getLongRunDuration() {
		long millis = getRunDuration();

		long secs = millis / 1000;
		long ms = millis % 1000;
		long mins = secs / 60;
		secs = (secs % 60);
		long hours = mins / 60;
		mins = mins % 60;

		return hours + "h " + mins + "m " + secs + "s+" + ms + "ms";
	}

	public List<Test> getTestList() {
		return testList;
	}

	public List<String> getTestRunnerLogs() {
		return testRunnerLogs;
	}

	public TestAttributeTestContextStore<Category> getCategoryContextInfo() {
		return categoryContext;
	}

	public TestAttributeTestContextStore<Author> getAuthorContextInfo() {
		return authorContext;
	}

	public TestAttributeTestContextStore<Device> getDeviceContextInfo() {
		return deviceContext;
	}

	public ExceptionTestContextStore getExceptionContextInfo() {
		return exceptionContext;
	}

	public SystemAttributeContext getSystemAttributeContext() {
		return systemAttributeContext;
	}

	public ReportStatusStats getReportStatusStats() {
		return stats;
	}

	public Collection<Status> getStatusCollection() {
		return statusCollection;
	}

	@Override
	public void onTestStarted(Test test) {
	}

	@Override
	public void onTestRemoved(Test test) {
	}

	@Override
	public void onNodeStarted(Test node) {
	}

	@Override
	public void onLogAdded(Test test, Log log) {
	}

	@Override
	public void onCategoryAssigned(Test test, Category category) {
	}

	@Override
	public void onAuthorAssigned(Test test, Author author) {
	}

	@Override
	public void onDeviceAssigned(Test test, Device device) {
	}

	@Override
	public void onScreenCaptureAdded(Test test, ScreenCapture screenCapture) throws IOException {
	}

	@Override
	public void onScreenCaptureAdded(Log log, ScreenCapture screenCapture) throws IOException {
	}

	protected void autoCreateRelativePathMedia(ScreenCapture screenCapture, Boolean autoCreateRelativePath,
			String destination) throws IOException {
		// if user has not specific a configuration, exit
		if (screenCapture.isBase64()) {
			return;
		}
		// check always so user has the option to disable this setting at anytime
		if (autoCreateRelativePath) {
			if (media == null) {
				media = new LocalMediaStorageHandler();
				media.init(destination);
			}
			media.storeMedia(screenCapture);
		}
	}

	@Override
	public void stop() {
	}

}
