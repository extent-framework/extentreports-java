package com.aventstack.extentreports;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.aventstack.extentreports.model.Author;
import com.aventstack.extentreports.model.Category;
import com.aventstack.extentreports.model.Device;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.model.ScreenCapture;
import com.aventstack.extentreports.model.Screencast;
import com.aventstack.extentreports.model.SystemAttribute;
import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.reporter.BasicFileReporter;

abstract class ExtentObservable 
	implements ReportService {

	/**
	 * The current AnalysisStrategy for the run session. This decides the technique used 
	 * to count the test status at differing levels. For example, for a BDD style report, 
	 * the levels to be calculated are Feature, Scenario and Step (3 levels). For a generic, 
	 * non-BDD style report, levels can be dynamic. For a non-BDD style report, levels typically 
	 * consist of:
	 * 
	 * <p>
	 * 1 level: Test<br>
	 * Test<br>
	 * - Event
	 * 
	 * <p>
	 * 2 levels: Test & node<br>
	 * Test<br>- Node<br>-- Event
	 * 
	 * <p>
	 * 2 levels: Test, the leaf-node<br>
	 * Test<br>- Node<br>-- Leaf Node<br>--- Event
	 * 
	 */
	private AnalysisStrategy strategy = AnalysisStrategy.TEST;
    
	/**
	 * Use this setting when building post-execution reports, such as from TestNG IReporter.
     * This setting allows setting test with your own variables and prevent update by Extent. 
     * As of today, with this enabled, Extent does not use time-stamps for tests at the time 
     * they were created
	 */
	private boolean usesManualConfiguration = false;
    
	/**
	 * The status of the entire report or build
	 */
	private Status reportStatus = Status.PASS;
	
	/**
	 * Time the report or build was started 
	 */
	private Date reportStartDate = Calendar.getInstance().getTime();
    
	/**
	 * Time the report or build ended. This value is updated everytime <code>flush</code>
	 * is called
	 */
	private Date reportEndDate;
    
	/**
	 * A collection of tests arranged by category
	 */
	private TestAttributeTestContextProvider<Category> categoryContext = new TestAttributeTestContextProvider<>();
    
	/**
	 * A collection of tests arranged by author
	 */
	private TestAttributeTestContextProvider<Author> authorContext = new TestAttributeTestContextProvider<>();
    
	/**
	 * A collection of tests arranged by author
	 */
	private TestAttributeTestContextProvider<Device> deviceContext = new TestAttributeTestContextProvider<>();
    
	
	/**
	 * A collection of tests arranged by exception
	 */
	private ExceptionTestContextImpl exceptionContextBuilder = new ExceptionTestContextImpl();
    
	/**
	 * A context of all system or environment variables
	 */
	private SystemAttributeContext systemAttributeContext = new SystemAttributeContext();
    
	/**
	 * A list of all {@link ExtentReporter} reporters started by the <code>attachReporter</code>
	 * method
	 */
	private List<ExtentReporter> reporterList = new ArrayList<>();
    
	/**
	 * Any logs added by to the test runner can be added to Extent
	 * 
	 * <p>
	 * TestNG Example:
	 * 
	 * <p>
	 * Setting logs with TestNG:
	 * 
	 * <pre>Reporter.log("hello world")</pre>
	 * 
	 * <p>
	 * Informing Extent of any logs added:
	 * <pre>
	 * for (String s : Reporter.getOutput()) {
     *       extent.setTestRunnerOutput(s);
     * }
     * </pre>
	 */
	private List<String> testRunnerLogs = new ArrayList<>();
    
	/**
	 * A list of all tests created
	 */
	private List<Test> testList = new ArrayList<>();    
    
	/**
	 * Instance of {@link ReportStatusStats}
	 */
	private ReportStatusStats stats = new ReportStatusStats(strategy);
	
	/**
	 * A unique collection of statuses tests are marked with
	 * 
	 * <p>
	 * Consider a report having 10 tests:
	 * 
	 * <ol>
	 * 	<li>Test1: PASS</li>
	 * 	<li>Test2: PASS</li>
	 * 	<li>Test3: PASS</li>
	 * 	<li>Test4: SKIP</li>
	 * 	<li>Test5: SKIP</li>
	 * 	<li>Test6: FAIL</li>
	 * 	<li>Test7: PASS</li>
	 * 	<li>Test8: PASS</li>
	 * 	<li>Test9: FAIL</li>
	 * 	<li>Test10: PASS</li>
	 * </ol>
	 * 
	 * <p>
	 * Distinct list of contained status:
	 * 
	 * <ol>
	 * <li>PASS</li>
	 * <li>SKIP</li>
	 * <li>FAIL</li>
	 * </ol>
	 */
	private Set<Status> statusSet = new HashSet<Status>();
	
    protected ExtentObservable() { }
    
    /**
     * Subscribe the reporter to receive updates when making calls to the API
     * 
     * @param reporter {@link ExtentReporter} reporter
     */
    protected void register(ExtentReporter reporter) {        
        reporterList.add(reporter);
        reporter.start();
    }
    
    /**
     * Unsubscribe the reporter. Calling unsubscribe will call the <code>stop</code> method
     * and also remove the reporter from the list of started reporters
     * 
     * @param reporter {@link ExtentReporter} reporter to unsubscribe
     */
    protected void deregister(ExtentReporter reporter) {
    	
    	if(reporterList.contains((Object) reporter)){
    		reporter.stop();
    		reporterList.remove(reporter);
    	}
    }
    
    /**
     * Retrieve a list of all started reporters
     * 
     * @return a list of {@link ExtentReporter} objects
     */
    protected List<ExtentReporter> getReporterCollection() {
    	return reporterList;
    }
    
    /**
     * Saves the started test and notifies all started reporters
     * 
     * @param test a {@link Test} object
     */
    protected synchronized void saveTest(Test test) {        
        testList.add(test);
        reporterList.forEach(x -> x.onTestStarted(test));
    }

    /**
     * Removes the test and notifies all started reporters
     * 
     * @param test a {@link Test} object
     */
    protected synchronized void removeTest(Test test) {
        removeTestTestList(test);
        removeTestTestAttributeContext(test);
        reporterList.forEach(x -> x.onTestRemoved(test));
    }
    
    /**
     * Removes a test from test list 
     * 
     * @param test a {@link Test} object
     */
    private void removeTestTestList(Test test) {
    	TestRemover.remove(testList, test);
        refreshReportEntities();
    }
    
    /**
     * Removes test from test list of each context
     * 
     * @param test  a {@link Test} object
     */
    private void removeTestTestAttributeContext(Test test) {
        if (test.hasCategory()) {
        	categoryContext.removeTest(test);
        }
        if (test.hasAuthor()) { 
            authorContext.removeTest(test);
        }
        if (test.hasDevice()) {
        	deviceContext.removeTest(test);
        }
    }
    
    /**
     * Refreshes report entities such as {@link ReportStatusStats} and list of distinct {@link Status}
     */
    private void refreshReportEntities() {
    	refreshReportStats();
    	refreshStatusList();
    }
    
    /**
     * Refresh and notify all reports of {@link ReportStatusStats}
     */
    private void refreshReportStats() {
        stats.refresh(testList);
    }
    
    /**
     * Refresh and notify all reports of distinct status assigned to tests
     */
    private synchronized void refreshStatusList() {
    	statusSet.clear();
    	refreshStatusList(testList);
    }
    
    /**
     * Refreshes distinct status list
     * 
     * @param list a list of started {@link Test}
     */
    private synchronized void refreshStatusList(List<Test> list) {
    	if (list == null || list.isEmpty())
    		return;

    	list.forEach(x -> statusSet.add(x.getStatus()));   	
    	list.forEach(x -> refreshStatusList(x.getChildrenNodes().getAll()));
    }
    
    /**
     * Notify reporters of the added node 
     * 
     * @param node a {@link Test} node
     */
    synchronized void addNode(Test node) {
        reporterList.forEach(x -> x.onNodeStarted(node));
    }
    
    /**
     * Notifies reporters with information of added {@link Log}
     * 
     * @param test {@link Test} to which the event is added
     * @param log {@link Log}
     */
    synchronized void addLog(Test test, Log log) {
        reporterList.forEach(x -> x.onLogAdded(test, log));
    }
    
    /**
     * Notifies reporters with information of added {@link Category}
     * 
     * @param test {@link Test} to which the Category is added
     * @param category {@link Category}
     */
    synchronized void assignCategory(Test test, Category category) {
        reporterList.forEach(x -> x.onCategoryAssigned(test, category));
    }
    
    /**
     * Notifies reporters with information of added {@link Author}
     * 
     * @param test {@link Test} to which the Author is added
     * @param author {@link Author}
     */
    synchronized void assignAuthor(Test test, Author author) {
        reporterList.forEach(x -> x.onAuthorAssigned(test, author));
    }
    
    /**
     * Notifies reporters with information of added {@link Author}
     * 
     * @param test {@link Test} to which the Device is added
     * @param device {@link Device}
     */
    synchronized void assignDevice(Test test, Device device) {
        reporterList.forEach(x -> x.onDeviceAssigned(test, device));
    }
    
    /**
     * Notifies reporters with information of added {@link ScreenCapture}
     * 
     * @param test {@link Test} to which the ScreenCapture is added
     * @param screenCapture {@link ScreenCapture}
     * 
     * @throws IOException thrown if the {@link ScreenCapture} is not found
     */
    synchronized void addScreenCapture(Test test, ScreenCapture screenCapture) throws IOException {
        for (ExtentReporter r : reporterList) {
            r.onScreenCaptureAdded(test, screenCapture);
        }
    }
    
    /**
     * Notifies reporters with information of added {@link ScreenCapture}
     * 
     * @param test {@link Log} to which the ScreenCapture is added
     * @param screenCapture {@link ScreenCapture}
     * 
     * @throws IOException thrown if the {@link ScreenCapture} is not found
     */
    synchronized void addScreenCapture(Log log, ScreenCapture screenCapture) throws IOException {
    	for (ExtentReporter r : reporterList) {
            r.onScreenCaptureAdded(log, screenCapture);
        }
    }
    
    /**
     * Notifies reporters with information of added {@link Screencast}
     * 
     * @param test {@link Test} to which the ScreenCast is added
     * @param sc {@link Screencast}
     * 
     * @throws IOException thrown if the {@link Screencast} is not found
     */
    synchronized void addScreencast(Test test, Screencast screencast) throws IOException {
    	for (ExtentReporter r : reporterList) {
            r.onScreencastAdded(test, screencast);
    	}
    }

    /**
     * Returns the context of author with the list of tests for each
     * 
     * @return a {@link TestAttributeTestContextProvider} object
     */
    protected TestAttributeTestContextProvider<Author> getAuthorContextInfo() {
    	return authorContext; 
	}
    
    /**
     * Updates the status of the report or build
     * 
     * @param status a {@link Status}
     */
    private void updateReportStatus(Status status) {
        int statusIndex = Status.getStatusHierarchy().indexOf(status);        
        int reportStatusIndex = Status.getStatusHierarchy().indexOf(reportStatus);
        
        reportStatus = statusIndex < reportStatusIndex 
                ? status 
                : reportStatus;
    }

    /**
     * Ends the test
     * 
     * @param test a {@link Test} object
     */
    private void endTest(Test test) {
        test.end();
        updateReportStatus(test.getStatus());
    }

    /**
     * Collects and updates all run information and notifies all reporters. Depending upon the 
     * type of reporter, additional events can occur such as:
     * 
     * <ul>
     * 	<li>A file written to disk (eg. case of {@link BasicFileReporter}</li>
     * 	<li>A database being updated (eg. case of KlovReporter)</li>
     * </ul>
     */
    protected synchronized void flush() {    	
    	generateRecentStatus();
        notifyReporters();
    }
    
    /**
     * Collects run information from all tests for assigned {@link Category}, {@link Author}, 
     * Exception, Nodes. This also ends and updates all internal test information and 
     * refreshes {@link ReportStatusStats} and the distinct list of {@link Status}
     */
    public synchronized void generateRecentStatus() {
        if (testList == null || testList.isEmpty())
            return;
        
        reportEndDate = Calendar.getInstance().getTime();
        
        refreshReportEntities();
        
        for (Test test : testList) {
            endTest(test);
            test.setUseManualConfiguration(getAllowManualConfig());
            if (test.hasCategory()) {
                test.getCategoryContext().getAll()
                	.forEach(x -> categoryContext.setAttributeContext((Category)x, test));
            }
            if (test.hasAuthor()) { 
                test.getAuthorContext().getAll()
                	.forEach(x -> authorContext.setAttributeContext((Author)x, test));
            }
            if (test.hasDevice()) {
            	test.getDeviceContext().getAll()
            		.forEach(x -> deviceContext.setAttributeContext((Device)x, test));
            }
            if (test.hasException()) {
                test.getExceptionInfoList()
                	.forEach(x -> exceptionContextBuilder.setExceptionContext(x, test));
            }
            if (test.hasChildren()) {
                for (Test node : test.getChildrenNodes().getAll()) {
                    copyNodeAttributeAndRunTimeInfoToAttributeContexts(node);
                    node.setUseManualConfiguration(getAllowManualConfig());
                }
            }
        }
        
        updateReportStartTimeForManualConfigurationSetting();
    }
    
    /**
     * In case where manual configuration is used, calculate the correct timestamps based upon
     * the timestamps assigned to tests
     */
    private void updateReportStartTimeForManualConfigurationSetting() {
        if (getAllowManualConfig() && !testList.isEmpty()) {
        	Date minDate = testList.stream()
        			.map(t -> t.getStartTime())
        			.min(Date::compareTo)
        			.get();
        	Date maxDate = testList.stream()
        			.map(t -> t.getEndTime())
        			.max(Date::compareTo)
        			.get();
        	reportStartDate = reportStartDate.getTime() > minDate.getTime() ? minDate : reportStartDate;
        	reportEndDate = reportEndDate.getTime() < maxDate.getTime() ? maxDate : reportEndDate;
        }
    }
    
    /**
     * Traverse all nodes and refresh {@link Category}, {@link Author}, Exception and Node context
     * information
     * 
     * @param node a {@link Test} node
     */
    private void copyNodeAttributeAndRunTimeInfoToAttributeContexts(Test node) {
        if (node.hasCategory()) {
            node.getCategoryContext().getAll()
            	.forEach(x -> categoryContext.setAttributeContext((Category)x, node));
        }
        if (node.hasAuthor()) {
            node.getAuthorContext().getAll()
            	.forEach(x -> authorContext.setAttributeContext((Author)x, node));
        }
        if (node.hasDevice()) {
            node.getDeviceContext().getAll()
            	.forEach(x -> deviceContext.setAttributeContext((Device)x, node));
        }
        if (node.hasException()) {
            node.getExceptionInfoList()
            	.forEach(x -> exceptionContextBuilder.setExceptionContext(x, node));
        }
        if (node.hasChildren()) {
            node.getChildrenNodes().getAll()
            	.forEach(this::copyNodeAttributeAndRunTimeInfoToAttributeContexts);
        }
    }
    
    /**
     * Notify all reporters with complete run information
     */
    private synchronized void notifyReporters() {
    	if (!testList.isEmpty() && testList.get(0).isBehaviorDrivenType()) {
    		strategy = AnalysisStrategy.BDD;
    	}
    	ReportAggregates reportAggregates = new ReportAggregatesBuilder()
    			.setAuthorContext(authorContext)
    			.setCategoryContext(categoryContext)
    			.setDeviceContext(deviceContext)
    			.setExceptionContext(exceptionContextBuilder)
    			.setReportStatusStats(stats)
    			.setStatusCollection(statusSet)
    			.setSystemAttributeContext(systemAttributeContext)
    			.setTestList(testList)
    			.setTestRunnerLogs(testRunnerLogs)
    			.setStartTime(reportStartDate)
    			.setEndTime(reportEndDate)
    			.build();
        reporterList.forEach(x -> x.setAnalysisStrategy(strategy));
    	reporterList.forEach(x -> x.flush(reportAggregates));
    }
    
    /**
     * Ends logging, stops and clears the list of reporters
     */
    protected void end() {
        flush();
        reporterList.forEach(ExtentReporter::stop);
        reporterList.clear();
    }
    
    /**
     * Add a system attribute
     * 
     * @param sa a {@link SystemAttribute} object
     */
    protected void setSystemInfo(SystemAttribute sa) {
        systemAttributeContext.setSystemAttribute(sa);
    }

    /**
     * Add a test runner log
     * 
     * @param log a log event
     */
    protected void setTestRunnerLogs(String log) {
        testRunnerLogs.add(log);
    }
    
    /**
     * Updates the {@link AnalysisStrategy}
     * 
     * @param strategy a {@link AnalysisStrategy} object
     */
    protected void setAnalysisStrategy(AnalysisStrategy strategy) {
        this.strategy = strategy;
        stats = new ReportStatusStats(strategy);
    }
    
    /**
     * Setting to allow user driven configuration for test time-stamps
     * 
     * @param useManualConfig setting for manual configuration
     */
    protected void setAllowManualConfig(boolean useManualConfig) {
    	this.usesManualConfiguration = useManualConfig;
	}
    
    /**
     * Returns the current value of using manual configuration for test time-stamps
     * 
     * @return setting for manual configuration
     */
    protected boolean getAllowManualConfig() {
    	return usesManualConfiguration;
    }
    
    /**
     * Return the {@link ReportStatusStats} object
     * 
     * @return a {@link ReportStatusStats} object
     */
    protected ReportStatusStats getStats() {
    	generateRecentStatus();
    	return stats;
    }
}
