package com.aventstack.extentreports.reporter;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.aventstack.extentreports.config.ConfigStore;
import com.aventstack.extentreports.config.external.JsonConfigLoader;
import com.aventstack.extentreports.config.external.XmlConfigLoader;
import com.aventstack.extentreports.model.Report;
import com.aventstack.extentreports.observer.ReportObserver;
import com.aventstack.extentreports.observer.entity.ReportEntity;
import com.aventstack.extentreports.reporter.configuration.EntityFilters;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.ViewConfigurer;
import com.aventstack.extentreports.reporter.configuration.ViewName;
import com.aventstack.extentreports.reporter.configuration.ViewsConfigurable;
import com.google.gson.Gson;

import freemarker.template.Template;
import freemarker.template.TemplateException;
import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;
import lombok.AccessLevel;
import lombok.Getter;

@Getter
@SuppressWarnings("rawtypes")
public class ExtentSparkReporter extends AbstractFileReporter
        implements
            ReportObserver,
            ReporterConfigurable,
            ViewsConfigurable<ExtentSparkReporter>,
            ReporterFilterable {
    private static final Logger logger = Logger.getLogger(ExtentSparkReporter.class.getName());
    private static final String TEMPLATE_LOCATION = "templates/";
    private static final String ENCODING = "UTF-8";
    private static final String REPORTER_NAME = "spark";
    private static final String SPA_TEMPLATE_NAME = REPORTER_NAME + "/spark.spa.ftl";
    private static final String FILE_NAME = "Index.html";
    private static final List<ViewName> SUPPORTED_VIEWS = Arrays.asList(new ViewName[]{
            ViewName.CATEGORY, ViewName.DASHBOARD, ViewName.EXCEPTION, ViewName.TEST, ViewName.LOG
    });

    @Getter(value = AccessLevel.NONE)
    private final ViewConfigurer<ExtentSparkReporter> viewConfigurer = new ViewConfigurer<>(this);
    @Getter(value = AccessLevel.NONE)
    private final EntityFilters<ExtentSparkReporter> filter = new EntityFilters<>(this);

    private ExtentSparkReporterConfig conf = ExtentSparkReporterConfig.builder().build();
    private Disposable disposable;
    private List<ViewName> viewNames = SUPPORTED_VIEWS;

    public ExtentSparkReporter(String path) {
        super(new File(path));
    }

    public ExtentSparkReporter(File f) {
        super(f);
    }

    @Override
    public EntityFilters<ExtentSparkReporter> filter() {
        return filter;
    }

    @Override
    public ViewConfigurer<ExtentSparkReporter> viewConfigurer() {
        return viewConfigurer;
    }

    public ExtentSparkReporterConfig config() {
        return conf;
    }

    public ExtentSparkReporter withConfig(ExtentSparkReporterConfig conf) {
        this.conf = conf;
        return this;
    }

    @Override
    public void loadJSONConfig(File jsonFile) throws IOException {
        @SuppressWarnings("unchecked")
        final JsonConfigLoader loader = new JsonConfigLoader(conf, jsonFile);
        loader.apply();
        executeActions();
    }

    @Override
    public void loadJSONConfig(String jsonString) throws IOException {
        @SuppressWarnings("unchecked")
        final JsonConfigLoader loader = new JsonConfigLoader(conf, jsonString);
        loader.apply();
        executeActions();
    }

    @Override
    public void loadXMLConfig(File xmlFile) throws IOException {
        final XmlConfigLoader loader = new XmlConfigLoader(xmlFile);
        ConfigStore store = loader.getConfigStore();
        Gson gson = new Gson();
        String json = gson.toJson(store.getStore());
        loadJSONConfig(json);
    }

    private void executeActions() {
        conf.enableOfflineMode(conf.getOfflineMode());
    }

    @Override
    public Observer<ReportEntity> getReportObserver() {
        return new Observer<ReportEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
                start(d);
            }

            @Override
            public void onNext(ReportEntity value) {
                flush(value);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        };
    }

    private void start(Disposable d) {
        disposable = d;
        loadTemplateModel();
    }

    private void flush(ReportEntity value) {
        final Report report = filterAndGet(value.getReport(), filter.statusFilter().getStatus());
        try {
            getTemplateModel().put("this", this);
            getTemplateModel().put("viewOrder", viewConfigurer.viewOrder().getViewOrder());
            getTemplateModel().put("report", report);
            createFreemarkerConfig(TEMPLATE_LOCATION, ENCODING);
            final String filePath = getFile().isDirectory()
                    ? getResolvedParentFile().getAbsolutePath()
                            + PATH_SEP + FILE_NAME
                    : getFile().getAbsolutePath();
            final Template template = getFreemarkerConfig().getTemplate(SPA_TEMPLATE_NAME);
            processTemplate(template, new File(filePath));
            return;
        } catch (IOException | TemplateException e) {
            disposable.dispose();
            logger.log(Level.SEVERE, "An exception occurred", e);
        }
    }
}
