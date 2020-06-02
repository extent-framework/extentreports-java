package com.aventstack.extentreports;

import java.util.List;

import com.aventstack.extentreports.model.Author;
import com.aventstack.extentreports.model.Category;
import com.aventstack.extentreports.model.Device;
import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.model.context.AttributeTestContextManager;
import com.aventstack.extentreports.model.service.ReportStatsService;
import com.aventstack.extentreports.model.service.TestService;

public abstract class ReportObservable extends ReactiveSubject {
    private final List<Test> testList = getReport().getTestList();
    private final AttributeTestContextManager<Author> authorCtx = getReport().getAuthorCtx();
    private final AttributeTestContextManager<Category> categoryCtx = getReport().getCategoryCtx();
    private final AttributeTestContextManager<Device> deviceCtx = getReport().getDeviceCtx();

    @Override
    protected void onTestCreated(Test test) {
        testList.add(test);
        super.onTestCreated(test);
    }

    @Override
    protected void onTestRemoved(Test test) {
        TestService.deleteTest(testList, test);
        super.onTestRemoved(test);
    }

    @Override
    protected void onNodeRemoved(Test node) {
        TestService.deleteTest(testList, node);
        super.onNodeRemoved(node);
    }

    protected void onAuthorAdded(Author x, Test test) {
        authorCtx.addContext(x, test);
    }

    protected void onCategoryAdded(Category x, Test test) {
        categoryCtx.addContext(x, test);
    }

    protected void onDeviceAdded(Device x, Test test) {
        deviceCtx.addContext(x, test);
    }

    @Override
    protected void onFlush() {
        authorCtx.getSet().forEach(x -> x.refresh());
        categoryCtx.getSet().forEach(x -> x.refresh());
        deviceCtx.getSet().forEach(x -> x.refresh());
        ReportStatsService.refreshReportStats(getReport().getStats(), testList);
        super.onFlush();
    }
}
