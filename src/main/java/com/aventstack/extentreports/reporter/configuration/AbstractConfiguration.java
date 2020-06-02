package com.aventstack.extentreports.reporter.configuration;

import com.aventstack.extentreports.config.ConfigStore;
import com.aventstack.extentreports.reporter.AbstractReporter;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractConfiguration {
    private ConfigStore store = new ConfigStore();
    private AbstractReporter reporter;
    private String timeStampFormat = "MMM d, yyyy hh:mm:ss a";
    private String reportName = "Extent Framework";

    protected AbstractConfiguration(AbstractReporter reporter) {
        this.reporter = reporter;
    }
}