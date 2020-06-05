package com.aventstack.extentreports.reporter.configuration;

import com.aventstack.extentreports.reporter.AbstractFileReporter;

@FunctionalInterface
public interface ViewsOrderable<T extends AbstractFileReporter> {
    T withViewsOrder(ViewOrder viewOrder);
}
