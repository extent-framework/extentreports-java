package com.aventstack.extentreports.reporter;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.aventstack.extentreports.ReportAggregates;
import com.aventstack.extentreports.reporter.configuration.ExtentHtmlReporterConfiguration;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * The ExtentSparkReporter creates a rich standalone spark file. It allows
 * several configuration options via the <code>config()</code> method.
 */
public class ExtentHtmlReporter extends BasicFileReporter {

	private static final Logger logger = Logger.getLogger(ExtentHtmlReporter.class.getName());
	private static final String REPORTER_NAME = "html";
    private static final String TEMPLATE_NAME = "v3html/v3-html-index.ftl";
    private static final String[] DEFAULT_CONFIG_FILE_PATH = new String[] { "html.properties",
            "src/main/resources/html.properties" };

	private ExtentHtmlReporterConfiguration externalUserConfiguration = new ExtentHtmlReporterConfiguration(this);

	public ExtentHtmlReporter(String path) {
		super(path);
		init(DEFAULT_CONFIG_FILE_PATH, config());
	}

	public ExtentHtmlReporter(File file) {
		super(file);
		init(DEFAULT_CONFIG_FILE_PATH, config());
	}

	public ExtentHtmlReporterConfiguration config() {
		return externalUserConfiguration;
	}

	@Override
	public synchronized void flush(ReportAggregates reportAggregates) {
		super.flush(reportAggregates);
		if (getTestList().isEmpty()) {
			return;
		}
		if (enforceOfflineMode()) {
			externalUserConfiguration.enableOfflineMode(true);
		}

		loadUserConfig();

		try {
			Template template = getFreemarkerConfig().getTemplate(TEMPLATE_NAME);
            processTemplate(template, new File(getFilePath()));
		} catch (IOException | TemplateException e) {
			logger.log(Level.SEVERE, "An exception occurred", e);
		}
	}

	@Override
	public String getReporterName() {
		return REPORTER_NAME;
	}

}