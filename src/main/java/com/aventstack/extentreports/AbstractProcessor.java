package com.aventstack.extentreports;

import java.io.File;
import java.io.IOException;

import com.aventstack.extentreports.append.RawEntityConverter;
import com.aventstack.extentreports.model.Author;
import com.aventstack.extentreports.model.Category;
import com.aventstack.extentreports.model.Device;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.model.SystemEnvInfo;
import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.model.service.MediaService;
import com.aventstack.extentreports.model.service.TestService;

import lombok.Data;

@Data
public abstract class AbstractProcessor extends ReactiveSubject {
    private String[] mediaResolverPath;
    private boolean usingNaturalConf = true;
    protected boolean keepLastRetryOnly;

    @Override
    protected void onTestCreated(Test test) {
        if (keepLastRetryOnly) {
            getReport().findTest(test.getName())
                    .ifPresent(x -> getReport().removeTest(x));
        }
        getReport().getTestList().add(test);
        super.onTestCreated(test);
    }

    @Override
    protected void onTestRemoved(Test test) {
        TestService.deleteTest(getReport().getTestList(), test);
        getReport().getAuthorCtx().removeTest(test);
        getReport().getCategoryCtx().removeTest(test);
        getReport().getDeviceCtx().removeTest(test);
        super.onTestRemoved(test);
    }

    protected void onNodeCreated(Test node) {
        if (keepLastRetryOnly) {
            getReport().findTest(node.getName())
                    .ifPresent(x -> getReport().removeTest(x));
        }
        super.onTestCreated(node);
    }

    @Override
    protected void onLogCreated(Log log, Test test) {
        super.onLogCreated(log, test);
        if (log.hasException())
            getReport().getExceptionInfoCtx().addContext(log.getException(), test);
    }

    @Override
    protected void onMediaAdded(Media m, Test test) {
        tryResolvePath(m);
        super.onMediaAdded(m, test);
    }

    @Override
    protected void onMediaAdded(Media m, Log log, Test test) {
        tryResolvePath(m);
        super.onMediaAdded(m, log, test);
    }

    private void tryResolvePath(Media m) {
        MediaService.tryResolveMediaPath(m, mediaResolverPath);
    }

    protected void onAuthorAdded(Author x, Test test) {
        getReport().getAuthorCtx().addContext(x, test);
        super.onAuthorAssigned(x, test);
    }

    protected void onCategoryAdded(Category x, Test test) {
        getReport().getCategoryCtx().addContext(x, test);
        super.onCategoryAssigned(x, test);
    }

    protected void onDeviceAdded(Device x, Test test) {
        getReport().getDeviceCtx().addContext(x, test);
        super.onDeviceAssigned(x, test);
    }

    @Override
    protected void onFlush() {
        getReport().refresh();
        if (!usingNaturalConf) {
            getReport().applyOverrideConf();
        }
        super.onFlush();
    }

    protected void onReportLogAdded(String log) {
        getReport().getLogs().add(log);
    }

    protected void onSystemInfoAdded(SystemEnvInfo env) {
        getReport().getSystemEnvInfo().add(env);
    }

    protected void convertRawEntities(ExtentReports extent, File f) throws IOException {
        RawEntityConverter converter = new RawEntityConverter(extent);
        converter.convertAndApply(f);
    }
}
