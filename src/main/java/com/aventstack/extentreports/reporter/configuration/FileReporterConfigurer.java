package com.aventstack.extentreports.reporter.configuration;

import com.aventstack.extentreports.reporter.AbstractReporter;

import lombok.Getter;

@Getter
public class FileReporterConfigurer<T extends AbstractReporter> {
    private T reporter;
    private ViewOrder viewOrder = new ViewOrder(this);
    private StatusFilter statusFilter = new StatusFilter(this);

    public FileReporterConfigurer(T reporter) {
        this.reporter = reporter;
    }

    @SuppressWarnings({"unchecked", "hiding"})
    public <T> T build() {
        return (T) reporter;
    }

    public ViewOrder viewOrder() {
        return viewOrder;
    }

    public FileReporterConfigurer<T> configure(ViewOrder viewOrder) {
        this.viewOrder = viewOrder;
        return this;
    }

    public StatusFilter statusFilter() {
        return statusFilter;
    }

    public FileReporterConfigurer<T> configure(StatusFilter statusFilter) {
        return this;
    }
}
