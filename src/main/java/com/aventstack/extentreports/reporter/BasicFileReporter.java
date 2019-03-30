package com.aventstack.extentreports.reporter;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.aventstack.extentreports.ExceptionTestContextImpl;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ReportAggregates;
import com.aventstack.extentreports.ReportStatusStats;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.SystemAttributeContext;
import com.aventstack.extentreports.TestAttributeTestContextProvider;
import com.aventstack.extentreports.mediastorage.LocalMediaStorageHandler;
import com.aventstack.extentreports.mediastorage.MediaStorage;
import com.aventstack.extentreports.model.Author;
import com.aventstack.extentreports.model.Category;
import com.aventstack.extentreports.model.Device;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.model.ScreenCapture;
import com.aventstack.extentreports.model.Screencast;
import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.reporter.configuration.BasicFileConfiguration;
import com.aventstack.extentreports.utils.FileUtil;
import com.aventstack.extentreports.utils.Writer;
import com.aventstack.extentreports.viewdefs.Icon;
import com.aventstack.extentreports.viewdefs.MaterialIcon;
import com.aventstack.extentreports.viewdefs.TWBSColor;

import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModelException;

/**
 * A base class for all reporter types that generate an output file
 * 
 */
public abstract class BasicFileReporter extends AbstractReporter {

	private static final Logger logger = Logger.getLogger(BasicFileReporter.class.getName());

	private static final String DEFAULT_MEDIA_SAVE_PROPERTY_NAME = "autoCreateRelativePathMedia";
	private static final String TEMPLATE_LOCATION = "view/";

	private static String encoding = "UTF-8";

	private String source;
	protected String filePath;
	protected String destination;
	protected Map<String, Object> templateMap;

	private BasicFileConfiguration userConfig;
	private MediaStorage media;
	private List<Test> testList;
	private List<String> testRunnerLogs;
	private ExceptionTestContextImpl exceptionContext;
	private TestAttributeTestContextProvider<Category> categoryContext;
	private TestAttributeTestContextProvider<Author> authorContext;
	private TestAttributeTestContextProvider<Device> deviceContext;
	private SystemAttributeContext systemAttributeContext;
	private ReportStatusStats stats;
	private List<Status> statusList;

	protected BasicFileReporter(String path) {
		this.filePath = path;
	}

	protected BasicFileReporter(File path) {
		this.filePath = path.getAbsolutePath();
	}

	protected void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public String getFilePath() {
		return filePath;
	}

	public File getFileFile() {
		return new File(filePath);
	}

	protected void init(String[] configFilePath, BasicFileConfiguration userConfig) {
		this.userConfig = userConfig;

		// Required to parse the start and end times in the HTML report.
		Locale.setDefault(Locale.ENGLISH);
		loadDefaultConfig(configFilePath);

		File f = new File(getFilePath());
		File parentFile;
		if (f.isDirectory() || FileUtil.getExtension(f).isEmpty()) {
			parentFile = f;
		} else {
			parentFile = f.getParentFile();
		}
		destination = parentFile == null ? "" : parentFile.getAbsolutePath() + "/";
		File destinationFile = new File(destination);
		if (!destinationFile.exists()) {
			destinationFile.mkdirs();
		}
	}

	protected void loadUserConfig() {
		for (Map.Entry<String, String> entry : userConfig.getConfigMap().entrySet()) {
			if (entry.getValue() != null) {
				configContext.setConfig(entry.getKey().toString(), entry.getValue());
			}
		}
	}

	private void loadDefaultConfig(String[] configFilePath) {
		ClassLoader loader = getClass().getClassLoader();
		Arrays.stream(configFilePath).map(x -> loader.getResourceAsStream(x)).filter(x -> x != null).findFirst()
				.ifPresent(x -> loadConfig(x));
	}

	@Override
	public void start() {
		if (templateMap != null) {
			return;
		}

		templateMap = new HashMap<>();
		templateMap.put("report", this);
		templateMap.put("MaterialIcon", new MaterialIcon());
		templateMap.put("Icon", new Icon());
		templateMap.put("TWBSColor", new TWBSColor());

		BeansWrapperBuilder builder = new BeansWrapperBuilder(Configuration.VERSION_2_3_23);
		BeansWrapper beansWrapper = builder.build();

		try {
			TemplateHashModel fieldTypeModel = (TemplateHashModel) beansWrapper.getEnumModels()
					.get(Status.class.getName());
			templateMap.put("Status", fieldTypeModel);
		} catch (TemplateModelException e) {
			logger.log(Level.SEVERE, "", e);
		}
	}

	@Override
	public synchronized void flush(ReportAggregates reportAggregates) {
		super.flush(reportAggregates);
		this.authorContext = reportAggregates.getAuthorContext();
		this.categoryContext = reportAggregates.getCategoryContext();
		this.deviceContext = reportAggregates.getDeviceContext();
		this.exceptionContext = reportAggregates.getExceptionContext();
		this.stats = reportAggregates.getReportStatusStats();
		this.systemAttributeContext = reportAggregates.getSystemAttributeContext();
		this.testList = reportAggregates.getTestList();
		this.testRunnerLogs = reportAggregates.getTestRunnerLogs();
		this.statusList = reportAggregates.getStatusList();
	}

	public List<Test> getTestList() {
		return testList;
	}

	public List<String> getTestRunnerLogs() {
		return testRunnerLogs;
	}

	public TestAttributeTestContextProvider<Category> getCategoryContextInfo() {
		return categoryContext;
	}

	public TestAttributeTestContextProvider<Author> getAuthorContextInfo() {
		return authorContext;
	}

	public TestAttributeTestContextProvider<Device> getDeviceContextInfo() {
		return deviceContext;
	}

	public ExceptionTestContextImpl getExceptionContextInfo() {
		return exceptionContext;
	}

	public SystemAttributeContext getSystemAttributeContext() {
		return systemAttributeContext;
	}

	public ReportStatusStats getReportStatusStats() {
		return stats;
	}

	public List<Status> getStatusList() {
		return statusList;
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
		mediaExists(screenCapture);
		autoCreateRelativePathMedia(screenCapture);
	}

	@Override
	public void onScreenCaptureAdded(Log log, ScreenCapture screenCapture) throws IOException {
		mediaExists(screenCapture);
		autoCreateRelativePathMedia(screenCapture);
	}

	private void mediaExists(Media m) {
		if (m.getPath() != null && !new File(m.getPath()).exists()) {
		}
	}

	private void autoCreateRelativePathMedia(ScreenCapture screenCapture) throws IOException {
		// if user has not specific a configuration, exit
		if (userConfig == null || screenCapture.isBase64())
			return;

		String autoCreateRelativePathMedia = userConfig.getConfigMap().get(DEFAULT_MEDIA_SAVE_PROPERTY_NAME);
		// check always so user has the option to disable this setting at anytime
		if (autoCreateRelativePathMedia != null && Boolean.valueOf(autoCreateRelativePathMedia)) {
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

	@Override
	public Date getStartTime() {
		return super.getStartTime();
	}

	@Override
	public Date getEndTime() {
		return super.getEndTime();
	}

	@Override
	public void onScreencastAdded(Test test, Screencast screencast) throws IOException {
	}

	public boolean containsStatus(String status) {
		Status s = Status.valueOf(status.toUpperCase());
		return containsStatus(s);
	}

	public boolean containsStatus(Status status) {
		return getStatusList() != null && getStatusList().contains(status);
	}

	protected void processTemplate(Template template, File outputFile) throws TemplateException, IOException {
		StringWriter out = new StringWriter();
		template.process(templateMap, out);
		source = out.toString();
		Writer.getInstance().write(outputFile, source);
		out.close();
	}

	protected String getSource() {
		return source;
	}

	protected Configuration getFreemarkerConfig() {
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
		cfg.setClassForTemplateLoading(ExtentReports.class, TEMPLATE_LOCATION);
		cfg.setDefaultEncoding(encoding);
		return cfg;
	}

	protected Boolean enforceOfflineMode() {
		if (configContext.containsKey("enableOfflineMode")) {
			String offlineMode = String.valueOf(configContext.getValue("enableOfflineMode"));
			if (!configContext.containsKey("offlineDirectory") && offlineMode.equals("true")) {
				return true;
			}
		}
		return false;
	}

}
