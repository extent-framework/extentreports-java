package com.aventstack.extentreports.reporter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ReportAggregates;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.model.ScreenCapture;
import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.model.service.LogService;
import com.aventstack.extentreports.model.service.TestService;
import com.aventstack.extentreports.templating.FreemarkerTemplate;
import com.aventstack.extentreports.templating.TemplateConfig;
import com.aventstack.extentreports.utils.FileUtil;
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
public abstract class BasicFileReporter extends ConfigurableReporter {

	private static final Logger logger = Logger.getLogger(BasicFileReporter.class.getName());
	private static final String DEFAULT_MEDIA_SAVE_PROPERTY_NAME = "autoCreateRelativePathMedia";
	private static final String TEMPLATE_LOCATION = "view/";

	private static String encoding = "UTF-8";

	private String filePath;
	private String destination;
	private Map<String, Object> templateModel;
	private String source;
	private Configuration freemarkerConfig;

	protected BasicFileReporter(File f) {
		this.filePath = f.getAbsolutePath();
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
	
	protected BasicFileReporter(String path) {
		this(new File(path));
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
	
	public String getDestinationPath() {
		return destination;
	}

	@Override
	public void start() {
		if (templateModel != null) {
			return;
		}

		templateModel = new HashMap<>();
		templateModel.put("report", this);
		templateModel.put("MaterialIcon", new MaterialIcon());
		templateModel.put("Icon", new Icon());
		templateModel.put("TWBSColor", new TWBSColor());

		BeansWrapperBuilder builder = new BeansWrapperBuilder(Configuration.VERSION_2_3_29);
		BeansWrapper beansWrapper = builder.build();

		try {
			TemplateHashModel fieldTypeModel = (TemplateHashModel) beansWrapper.getEnumModels()
					.get(Status.class.getName());
			templateModel.put("Status", fieldTypeModel);
			fieldTypeModel = (TemplateHashModel) beansWrapper.getStaticModels()
					.get(TestService.class.getName());
			templateModel.put("TestService", fieldTypeModel);
			fieldTypeModel = (TemplateHashModel) beansWrapper.getStaticModels()
					.get(LogService.class.getName());
			templateModel.put("LogService", fieldTypeModel);
		} catch (TemplateModelException e) {
			logger.log(Level.SEVERE, "", e);
		}
	}

	@Override
	public synchronized void flush(ReportAggregates reportAggregates) {
		super.flush(reportAggregates);
	}

	@Override
	public void onScreenCaptureAdded(Test test, ScreenCapture screenCapture) throws IOException {
		onScreenCaptureAdded(screenCapture);
	}

	@Override
	public void onScreenCaptureAdded(Log log, ScreenCapture screenCapture) throws IOException {
		onScreenCaptureAdded(screenCapture);
	}

	private void onScreenCaptureAdded(ScreenCapture screenCapture) throws IOException {
		String autoCreateRelativePathMedia = (String) getConfigurationStore()
				.getConfig(DEFAULT_MEDIA_SAVE_PROPERTY_NAME);
		if (autoCreateRelativePathMedia != null && Boolean.valueOf(autoCreateRelativePathMedia)) {
			autoCreateRelativePathMedia(screenCapture, Boolean.valueOf(autoCreateRelativePathMedia), destination);
		}
	}

	public boolean containsStatus(String status) {
		Status s = Status.valueOf(status.toUpperCase());
		return containsStatus(s);
	}

	public boolean containsStatus(Status status) {
		return getStatusCollection() != null && getStatusCollection().contains(status);
	}

	protected void processTemplate(Template template, File outputFile) throws TemplateException, IOException {
		FreemarkerTemplate freemarkerTemplate = new FreemarkerTemplate(getFreemarkerConfig());
		freemarkerTemplate.writeTemplate(template, templateModel, outputFile);
	}

	protected String getSource() {
		return source;
	}

	protected Configuration getFreemarkerConfig() {
		if (freemarkerConfig == null) {
			TemplateConfig freemarkerConfig = new TemplateConfig();
			this.freemarkerConfig = freemarkerConfig.getFreemarkerConfig(ExtentReports.class, TEMPLATE_LOCATION,
					encoding);
		}
		return freemarkerConfig;
	}

	protected Boolean enforceOfflineMode() {
		if (getConfigurationStore().containsConfig("enableOfflineMode")) {
			String offlineMode = String.valueOf(getConfigurationStore().containsConfig("enableOfflineMode"));
			if (!getConfigurationStore().containsConfig("offlineDirectory") && offlineMode.equals("true")) {
				return true;
			}
		}
		return false;
	}

}