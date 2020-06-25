package com.aventstack.extentreports.reporter;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.service.TestService;
import com.aventstack.extentreports.templating.FreemarkerTemplate;
import com.aventstack.extentreports.templating.TemplateConfig;
import com.aventstack.extentreports.view.Ico;

import freemarker.ext.beans.BeansWrapper;
import freemarker.ext.beans.BeansWrapperBuilder;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import freemarker.template.TemplateHashModel;
import freemarker.template.TemplateModelException;
import lombok.Getter;

@Getter
public abstract class AbstractFileReporter extends AbstractFilterableReporter {
    private static final Logger LOG = Logger.getLogger(AbstractFileReporter.class.getName());
    protected static final String PATH_SEP = "/";

    private File file;
    private File resolvedParentFile;
    private Map<String, Object> templateModel;
    private Configuration freemarkerConfig;

    protected AbstractFileReporter(File file) {
        this.file = file;
        File parentFile = file;
        if (!file.isDirectory()) {
            parentFile = file.getParentFile();
        }
        String resolvedPath = parentFile == null ? "" : parentFile.getAbsolutePath() + "/";
        resolvedParentFile = new File(resolvedPath);
        if (!resolvedParentFile.exists())
            resolvedParentFile.mkdirs();
    }

    protected void loadTemplateModel() {
        if (templateModel != null)
            return;

        templateModel = new HashMap<>();
        BeansWrapperBuilder builder = new BeansWrapperBuilder(Configuration.VERSION_2_3_30);
        BeansWrapper beansWrapper = builder.build();

        try {
            TemplateHashModel fieldTypeModel = (TemplateHashModel) beansWrapper.getEnumModels()
                    .get(Status.class.getName());
            templateModel.put("Status", fieldTypeModel);
            fieldTypeModel = (TemplateHashModel) beansWrapper.getStaticModels()
                    .get(Ico.class.getName());
            templateModel.put("Ico", fieldTypeModel);
            fieldTypeModel = (TemplateHashModel) beansWrapper.getStaticModels()
                    .get(TestService.class.getName());
            templateModel.put("TestService", fieldTypeModel);
        } catch (TemplateModelException e) {
            LOG.log(Level.SEVERE, "", e);
        }
    }

    protected void processTemplate(Template template, File outputFile) throws TemplateException, IOException {
        FreemarkerTemplate freemarkerTemplate = new FreemarkerTemplate(getFreemarkerConfig());
        freemarkerTemplate.writeTemplate(template, templateModel, outputFile);
    }

    protected Configuration createFreemarkerConfig(String templatePath, String encoding) {
        if (freemarkerConfig == null) {
            TemplateConfig freemarkerConfig = new TemplateConfig();
            this.freemarkerConfig = freemarkerConfig.getFreemarkerConfig(ExtentReports.class, templatePath,
                    encoding);
        }
        return freemarkerConfig;
    }

}
