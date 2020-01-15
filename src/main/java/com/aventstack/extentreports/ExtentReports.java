package com.aventstack.extentreports;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;
import java.util.List;

import com.aventstack.extentreports.convert.TestModelReportBuilder;
import com.aventstack.extentreports.gherkin.GherkinDialectProvider;
import com.aventstack.extentreports.gherkin.model.IGherkinFormatterModel;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.model.ScreenCapture;
import com.aventstack.extentreports.model.SystemAttribute;
import com.aventstack.extentreports.reporter.ExtentKlovReporter;
import com.aventstack.extentreports.reporter.ExtentReporter;

/**
 * <p>
 * The ExtentReports report client for starting reporters and building reports.
 * For most applications, you should have one ExtentReports instance for the
 * entire JVM.
 * </p>
 * 
 * <p>
 * ExtentReports itself does not build any reports, but allows reporters to
 * access information, which in turn build the said reports. An example of
 * building an HTML report and adding information to ExtentX:
 * </p>
 * 
 * <pre>
 * ExtentHtmlReporter html = new ExtentHtmlReporter("Extent.html");
 * ExtentXReporter extentx = new ExtentXReporter("localhost");
 * 
 * ExtentReports extent = new ExtentReports();
 * extent.attachReporter(html, extentx);
 * 
 * extent.createTest("TestName").pass("Test Passed");
 * 
 * extent.flush();
 * </pre>
 * 
 * <p>
 * A few notes:
 * </p>
 * 
 * <ul>
 * <li>It is mandatory to call the <code>flush</code> method to ensure
 * information is written to the started reporters.</li>
 * <li>You can create standard and BDD-style tests using the
 * <code>createTest</code> method</li>
 * </ul>
 * 
 * @see ExtentTest
 * @see GherkinKeyword
 * @see IGherkinFormatterModel
 * @see Status
 */
public class ExtentReports extends ReportObservable {

	private static final String[] IMAGE_PATH_RESOLVER_DIR = new String[] { "target/", "test-output/" };
	
	/**
	 * Attach a {@link ExtentReporter} reporter, allowing it to access all started
	 * tests, nodes and logs
	 * 
	 * <p>
	 * Available reporter types are:
	 * </p>
	 * 
	 * <ul>
	 * <li>ExtentHtmlReporter provided by artifactId "extent-html-formatter"</li>
	 * <li>ExtentEmailReporter (pro-only) provided by artifactId
	 * "extent-email-formatter"</li>
	 * <li>KlovReporter provided by artifactId "extent-klov-reporter"</li>
	 * <li>ConsoleLogger</li>
	 * </ul>
	 * 
	 * @param reporter {@link ExtentReporter} reporter
	 */
	public void attachReporter(ExtentReporter... reporter) {
		Arrays.stream(reporter).forEach(this::register);
	}

	/**
	 * Returns a list of started reporters
	 * 
	 * @return A list of {@link ExtentReporter}
	 */
	public List<ExtentReporter> getStartedReporters() {
		return getReporterCollection();
	}

	/**
	 * Creates a BDD-style test with description representing one of the
	 * {@link IGherkinFormatterModel} classes such as:
	 * 
	 * <ul>
	 * <li>{@link com.aventstack.extentreports.gherkin.model.Feature}</li>
	 * <li>{@link com.aventstack.extentreports.gherkin.model.Background}</li>
	 * <li>{@link com.aventstack.extentreports.gherkin.model.Scenario}</li>
	 * <li>{@link com.aventstack.extentreports.gherkin.model.Given}</li>
	 * <li>{@link com.aventstack.extentreports.gherkin.model.When}</li>
	 * <li>{@link com.aventstack.extentreports.gherkin.model.Then}</li>
	 * <li>{@link com.aventstack.extentreports.gherkin.model.And}</li>
	 * <li>{@link com.aventstack.extentreports.gherkin.model.But}</li>
	 * </ul>
	 * 
	 * <p>
	 * Example:
	 * </p>
	 * 
	 * <pre>
	 * extent.createTest(Feature.class, "feature", "description");
	 * extent.createTest(Scenario.class, "scenario", "description");
	 * extent.createTest(Given.class, "given", "description");
	 * </pre>
	 * 
	 * @param type        A {@link IGherkinFormatterModel} type
	 * @param testName    Name of test
	 * @param description A short description of the test
	 * 
	 * @return {@link ExtentTest} object
	 */
	public ExtentTest createTest(Class<? extends IGherkinFormatterModel> type, String testName,
			String description) {
		ExtentTest t = new ExtentTest(this, type, testName, description);
		applyCommonTestSettings(t);
		saveTest(t.getModel());
		return t;
	}

	/**
	 * Creates a BDD-style test representing one of the
	 * {@link IGherkinFormatterModel} classes such as:
	 * 
	 * <ul>
	 * <li>{@link com.aventstack.extentreports.gherkin.model.Feature}</li>
	 * <li>{@link com.aventstack.extentreports.gherkin.model.Background}</li>
	 * <li>{@link com.aventstack.extentreports.gherkin.model.Scenario}</li>
	 * <li>{@link com.aventstack.extentreports.gherkin.model.Given}</li>
	 * <li>{@link com.aventstack.extentreports.gherkin.model.When}</li>
	 * <li>{@link com.aventstack.extentreports.gherkin.model.Then}</li>
	 * <li>{@link com.aventstack.extentreports.gherkin.model.And}</li>
	 * <li>{@link com.aventstack.extentreports.gherkin.model.But}</li>
	 * </ul>
	 * 
	 * <p>
	 * Example:
	 * </p>
	 * 
	 * <pre>
	 * extent.createTest(Feature.class, "feature");
	 * extent.createTest(Scenario.class, "scenario");
	 * extent.createTest(Given.class, "given");
	 * </pre>
	 * 
	 * @param type     A {@link IGherkinFormatterModel} type
	 * @param testName Name of test
	 * 
	 * @return {@link ExtentTest} object
	 */
	public ExtentTest createTest(Class<? extends IGherkinFormatterModel> type, String testName) {
		return createTest(type, testName, null);
	}

	/**
	 * Creates a BDD-style test with description using name of the Gherkin model
	 * such as:
	 * 
	 * <ul>
	 * <li>{@link com.aventstack.extentreports.gherkin.model.Feature}</li>
	 * <li>{@link com.aventstack.extentreports.gherkin.model.Background}</li>
	 * <li>{@link com.aventstack.extentreports.gherkin.model.Scenario}</li>
	 * <li>{@link com.aventstack.extentreports.gherkin.model.Given}</li>
	 * <li>{@link com.aventstack.extentreports.gherkin.model.When}</li>
	 * <li>{@link com.aventstack.extentreports.gherkin.model.Then}</li>
	 * <li>{@link com.aventstack.extentreports.gherkin.model.And}</li>
	 * <li>{@link com.aventstack.extentreports.gherkin.model.But}</li>
	 * </ul>
	 * 
	 * <p>
	 * Example:
	 * </p>
	 * 
	 * <pre>
	 * extent.createTest(new GherkinKeyword("Feature"), "feature", "description");
	 * extent.createTest(new GherkinKeyword("Scenario"), "scenario", "description");
	 * extent.createTest(new GherkinKeyword("Given"), "given", "description");
	 * </pre>
	 * 
	 * @param gherkinKeyword Name of the {@link GherkinKeyword}
	 * @param testName       Name of test
	 * @param description    A short description of the test
	 * 
	 * @return {@link ExtentTest} object
	 */
	public ExtentTest createTest(GherkinKeyword gherkinKeyword, String testName, String description) {
		Class<? extends IGherkinFormatterModel> clazz = gherkinKeyword.getKeyword().getClass();
		return createTest(clazz, testName, description);
	}

	/**
	 * Creates a BDD-style test using name of the Gherkin model such as:
	 * 
	 * <ul>
	 * <li>{@link com.aventstack.extentreports.gherkin.model.Feature}</li>
	 * <li>{@link com.aventstack.extentreports.gherkin.model.Background}</li>
	 * <li>{@link com.aventstack.extentreports.gherkin.model.Scenario}</li>
	 * <li>{@link com.aventstack.extentreports.gherkin.model.Given}</li>
	 * <li>{@link com.aventstack.extentreports.gherkin.model.When}</li>
	 * <li>{@link com.aventstack.extentreports.gherkin.model.Then}</li>
	 * <li>{@link com.aventstack.extentreports.gherkin.model.And}</li>
	 * <li>{@link com.aventstack.extentreports.gherkin.model.But}</li>
	 * </ul>
	 * 
	 * <p>
	 * Example:
	 * </p>
	 * 
	 * <pre>
	 * extent.createTest(new GherkinKeyword("Feature"), "feature");
	 * extent.createTest(new GherkinKeyword("Scenario"), "scenario");
	 * extent.createTest(new GherkinKeyword("Given"), "given");
	 * </pre>
	 * 
	 * @param gherkinKeyword Name of the {@link GherkinKeyword}
	 * @param testName       Name of test
	 * 
	 * @return {@link ExtentTest} object
	 */
	public ExtentTest createTest(GherkinKeyword gherkinKeyword, String testName) {
		return createTest(gherkinKeyword, testName, null);
	}

	/**
	 * Creates a test with description
	 * 
	 * @param testName    Name of test
	 * @param description A short test description
	 * 
	 * @return {@link ExtentTest} object
	 */
	public ExtentTest createTest(String testName, String description) {
		ExtentTest t = new ExtentTest(this, testName, description);
		applyCommonTestSettings(t);

		saveTest(t.getModel());

		return t;
	}

	/**
	 * Creates a test
	 * 
	 * @param testName Name of test
	 * 
	 * @return {@link ExtentTest} object
	 */
	public ExtentTest createTest(String testName) {
		return createTest(testName, null);
	}

	private void applyCommonTestSettings(ExtentTest extentTest) {
		extentTest.setUseManualConfiguration(getAllowManualConfig());
	}

	/**
	 * Removes a test
	 * 
	 * @param test {@link ExtentTest} object
	 */
	public void removeTest(ExtentTest test) {
		super.removeTest(test.getModel());
	}

	/**
	 * Writes test information from the started reporters to their output view
	 * 
	 * <ul>
	 * <li>extent-html-formatter: flush output to HTML file</li>
	 * <li>extent-klov-reporter: updates MongoDB collections</li>
	 * <li>extent-email-formatter (pro-only): creates or appends to an HTML
	 * file</li>
	 * <li>ConsoleLogger: no action taken</li>
	 * </ul>
	 */
	@Override
	public void flush() {
		super.flush();
	}

	/**
	 * Adds any applicable system information to all started reporters
	 * 
	 * <p>
	 * Example:
	 * </p>
	 * 
	 * <pre>
	 * extent.setSystemInfo("HostName", "AventStack-PC");
	 * </pre>
	 * 
	 * @param k Name of system variable
	 * @param v Value of system variable
	 */
	public void setSystemInfo(String k, String v) {
		SystemAttribute sa = new SystemAttribute(k, v);
		super.setSystemInfo(sa);
	}

	/**
	 * Adds logs from test framework tools to the test-runner logs view (if
	 * available in the reporter)
	 * 
	 * <p>
	 * TestNG usage example:
	 * </p>
	 * 
	 * <pre>
	 * extent.setTestRunnerOutput(Reporter.getOutput());
	 * </pre>
	 *
	 * @param log Log string from the test runner frameworks such as TestNG or JUnit
	 */
	public void setTestRunnerOutput(List<String> log) {
		log.forEach(this::setTestRunnerLogs);
	}

	/**
	 * Adds logs from test framework tools to the test-runner logs view (if
	 * available in the reporter)
	 * 
	 * <p>
	 * TestNG usage example:
	 * </p>
	 * 
	 * <pre>
	 * for (String s : Reporter.getOutput()) {
	 * 	extent.setTestRunnerOutput(s);
	 * }
	 * </pre>
	 *
	 * @param log Log string from the test runner frameworks such as TestNG or JUnit
	 */
	public void setTestRunnerOutput(String log) {
		setTestRunnerLogs(log);
	}
	
	/**
	 * Tries to resolve a {@link ScreenCapture} location if the supplied path is not found using
	 * default locations. This can resolve cases where the default path was supplied to be relative 
	 * for a FileReporter. If the absolute path is not determined, the supplied will be used. 
	 * below paths are used to locate the image:
	 * 
	 * <ul>
	 * 	<li>target/</li>
	 * 	<li>test-output//</li>
	 * </ul>
	 * 
	 *  @return {@link ExtentKlovReporter}
	 */
	public ExtentReports tryResolveMediaPath() {
		setImagePathResolveDir(IMAGE_PATH_RESOLVER_DIR);
		return this;
	}
	
	/**
	 * Tries to resolve a {@link ScreenCapture} location if the supplied path is not found using
	 * supplied locations. This can resolve cases where the default path was supplied to be relative 
	 * for a FileReporter. If the absolute path is not determined, the supplied will be used.
	 * 
	 * @param paths Dirs used to create absolute path of the {@link Media} object
	 * 
	 * @return {@link ExtentKlovReporter}
	 */
	public ExtentReports tryResolveScreenCapturePath(String[] paths) {
		setImagePathResolveDir(paths);
		return this;
	}

	/**
	 * Creates the internal models by consuming a JSON archive from a previous run
	 * session. This provides the same functionality as available in earlier versions
	 * via <code>appendExisting</code>, with the exception of being accessible by
	 * all reporters instead of just one.  
	 * 
	 * @param jsonFile The JSON archive file
	 * @throws IOException Exception thrown if the jsonFile is not found
	 */
	public void createDomainFromJsonArchive(File jsonFile) throws IOException {
		TestModelReportBuilder modelBuilder = new TestModelReportBuilder();
		modelBuilder.createDomainFromJsonArchive(this, jsonFile);
	}
	
	/**
	 * Creates the internal models by consuming a JSON archive from a previous run
	 * session. This provides the same functionality as available in earlier versions
	 * via <code>appendExisting</code>, with the exception of being accessible by
	 * all reporters instead of just one.  
	 * 
	 * @param jsonFilePath The JSON archive file
	 * @throws IOException Exception thrown if the jsonFilePath is not found
	 */
	public void createDomainFromJsonArchive(String jsonFilePath) throws IOException {
		createDomainFromJsonArchive(new File(jsonFilePath));
	}
	
	/**
	 * Use this setting when building post-execution reports, such as from TestNG
	 * IReporter. This setting allows setting test with your own time-stamps. With
	 * this enabled, Extent does not use time-stamps for tests at the time they were
	 * created.
	 * 
	 * <p>
	 * If this setting is enabled and time-stamps are not specified explicitly, the
	 * time-stamps of test creation are used.
	 * 
	 * @param useManualConfig Set to true if building reports at the end of
	 *                        execution with manual configuration
	 */
	public void setReportUsesManualConfiguration(boolean useManualConfig) {
		setAllowManualConfig(useManualConfig);
	}
	
	public Boolean getReportUsesManualConfiguration() {
		return getAllowManualConfig();
	}

	/**
	 * Type of AnalysisStrategy for the reporter. Not all reporters support this
	 * setting.
	 * 
	 * <p>
	 * There are 2 types of strategies available:
	 * 
	 * <ul>
	 * <li>TEST: Shows analysis at the test and step level</li>
	 * <li>SUITE: Shows analysis at the suite, test and step level</li>
	 * </ul>
	 * 
	 * @param strategy {@link AnalysisStrategy} determines the type of analysis
	 *                 (dashboard) created for the reporter. Not all reporters will
	 *                 support this setting.
	 */
	@Override
	public void setAnalysisStrategy(AnalysisStrategy strategy) {
		super.setAnalysisStrategy(strategy);
	}

	/**
	 * Provides common report configurations
	 * 
	 * @return an instance of {@link ReportConfigurator}
	 */
	public ReportConfigurator config() {
		return ReportConfigurator.getInstance();
	}

	/**
	 * Allows setting the target language for Gherkin keywords.
	 * 
	 * <p>
	 * Default setting is "en"
	 * 
	 * @param language A valid dialect from <a href=
	 *                 "https://github.com/cucumber/cucumber/blob/master/gherkin/gherkin-languages.json">gherkin-languages.json</a>
	 * 
	 * @throws UnsupportedEncodingException Thrown if the language is one of the
	 *                                      supported language from <a href=
	 *                                      "https://github.com/cucumber/cucumber/blob/master/gherkin/gherkin-languages.json">gherkin-languages.json</a>
	 */
	public void setGherkinDialect(String language) throws UnsupportedEncodingException {
		GherkinDialectProvider.setLanguage(language);
	}

	/**
	 * Returns an instance of {@link ReportStatusStats} with counts of tests
	 * executed by their status (pass, fail, skip etc)
	 * 
	 * @return an instance of {@link ReportStatusStats}
	 */
	@Override
	public ReportStatusStats getStats() {
		return super.getStats();
	}

}