package com.aventstack.extentreports.reporter;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.aventstack.extentreports.ReportAggregates;
import com.aventstack.extentreports.reporter.configuration.ExtentLoggerReporterConfiguration;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * The ExtentHtmlReporter creates a rich standalone HTML file. It allows several
 * configuration options via the <code>config()</code> method.
 */
public class ExtentLoggerReporter extends BasicFileReporter {

    private static final Logger logger = Logger.getLogger(ExtentLoggerReporter.class.getName());
    private static final String REPORTER_NAME = "logger";
    private static final String TEMPLATE_NAME = "logger/logger-test.ftl";
    private static final String DASHBOARD_TEMPLATE_NAME = "logger/logger-dashboard.ftl";
    private static final String CATEGORY_TEMPLATE_NAME = "logger/logger-tag.ftl";
    private static final String EXCEPTION_TEMPLATE_NAME = "logger/logger-exception.ftl";
    private static final String[] DEFAULT_CONFIG_FILE_PATH = new String[] { "logger.properties",
            "src/main/resources/logger.properties" };

    private ExtentLoggerReporterConfiguration userConfig = new ExtentLoggerReporterConfiguration(this);

    public ExtentLoggerReporter(String path) {
        super(path);
        init(DEFAULT_CONFIG_FILE_PATH, config());
    }

    public ExtentLoggerReporter(File file) {
        super(file);
        init(DEFAULT_CONFIG_FILE_PATH, config());
    }

    public ExtentLoggerReporterConfiguration config() {
        return userConfig;
    }

    @Override
    public synchronized void flush(ReportAggregates reportAggregates) {
        super.flush(reportAggregates);

        if (getTestList().isEmpty())
            return;

        if (enforceOfflineMode())
            userConfig.enableOfflineMode(true);

        loadUserConfig();

        try {
            Template template = getFreemarkerConfig().getTemplate(TEMPLATE_NAME);
            processTemplate(template, new File(getDestinationPath() + "index.html"));
            if (String.valueOf(getConfigurationStore().getConfig("enableDashboard")).equalsIgnoreCase("true")) {
                template = getFreemarkerConfig().getTemplate(DASHBOARD_TEMPLATE_NAME);
                processTemplate(template, new File(getDestinationPath() + "dashboard.html"));
            }
            if (!getCategoryContextInfo().getTestAttributeTestContext().isEmpty()) {
                template = getFreemarkerConfig().getTemplate(CATEGORY_TEMPLATE_NAME);
                processTemplate(template, new File(getDestinationPath() + "tag.html"));
            }
            if (!getExceptionContextInfo().getExceptionTestContext().isEmpty()) {
                template = getFreemarkerConfig().getTemplate(EXCEPTION_TEMPLATE_NAME);
                processTemplate(template, new File(getDestinationPath() + "exception.html"));
            }
        } catch (IOException | TemplateException e) {
            logger.log(Level.SEVERE, "An exception occurred", e);
        }
    }

    @Override
    public String getReporterName() {
        return REPORTER_NAME;
    }

}
