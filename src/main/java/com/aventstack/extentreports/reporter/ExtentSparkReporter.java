package com.aventstack.extentreports.reporter;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.aventstack.extentreports.ReportAggregates;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfiguration;

import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * The ExtentSparkReporter creates a rich standalone spark file. It allows several
 * configuration options via the <code>config()</code> method.
 */
public class ExtentSparkReporter extends BasicFileReporter {

    private static final Logger logger = Logger.getLogger(ExtentSparkReporter.class.getName());
    private static final String REPORTER_NAME = "spark";
    private static final String TEST_TEMPLATE_NAME = REPORTER_NAME + "/test.ftl";
    private static final String TAG_TEMPLATE_NAME = REPORTER_NAME + "/tag.ftl";
    private static final String EXCEPTION_TEMPLATE_NAME = REPORTER_NAME + "/exception.ftl";
    private static final String DASHBOARD_TEMPLATE_NAME = REPORTER_NAME + "/dashboard.ftl";
    private static final String[] DEFAULT_CONFIG_FILE_PATH = new String[] { "spark.properties",
            "src/main/resources/spark.properties" };

    private ExtentSparkReporterConfiguration userConfig = new ExtentSparkReporterConfiguration(this);

    public ExtentSparkReporter(String path) {
        super(path);
        init(DEFAULT_CONFIG_FILE_PATH, config());
    }

    public ExtentSparkReporter(File file) {
        super(file);
        init(DEFAULT_CONFIG_FILE_PATH, config());
    }

    public ExtentSparkReporterConfiguration config() {
        return userConfig;
    }

    @Override
    public synchronized void flush(ReportAggregates reportAggregates) {
        super.flush(reportAggregates);

        if (getTestList().isEmpty())
            return;

        loadUserConfig();

        try {
            Template template = getFreemarkerConfig().getTemplate(TEST_TEMPLATE_NAME);
            processTemplate(template, new File(destination + "index.html"));
            if (!getCategoryContextInfo().getTestAttributeTestContextList().isEmpty()) {
                template = getFreemarkerConfig().getTemplate(TAG_TEMPLATE_NAME);
                processTemplate(template, new File(destination + "tag.html"));
            }
            if (!getExceptionContextInfo().getExceptionTestContextList().isEmpty()) {
                template = getFreemarkerConfig().getTemplate(EXCEPTION_TEMPLATE_NAME);
                processTemplate(template, new File(destination + "exception.html"));
            }
            template = getFreemarkerConfig().getTemplate(DASHBOARD_TEMPLATE_NAME);
            processTemplate(template, new File(destination + "dashboard.html"));
        } catch (IOException | TemplateException e) {
            logger.log(Level.SEVERE, "An exception occurred", e);
        }
    }

    @Override
    public String getReporterName() {
        return REPORTER_NAME;
    }

}
