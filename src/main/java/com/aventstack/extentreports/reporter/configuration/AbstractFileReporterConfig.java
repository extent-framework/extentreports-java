package com.aventstack.extentreports.reporter.configuration;

import com.aventstack.extentreports.reporter.AbstractReporter;

import lombok.Getter;
import lombok.Setter;

/**
 * Common configuration for file based reporters
 * 
 */
@Getter
@Setter
public abstract class AbstractFileReporterConfig extends AbstractConfiguration {
    private String encoding = "UTF-8";
    private String documentTitle = "";

    protected AbstractFileReporterConfig(AbstractReporter reporter) {
        super(reporter);
    }
}