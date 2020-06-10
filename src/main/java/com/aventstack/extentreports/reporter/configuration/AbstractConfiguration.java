package com.aventstack.extentreports.reporter.configuration;

import com.aventstack.extentreports.config.ConfigStore;
import com.aventstack.extentreports.reporter.AbstractReporter;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
public abstract class AbstractConfiguration {
    private final ConfigStore store = new ConfigStore();
    private final AbstractReporter reporter;

    @Builder.Default
    private String timeStampFormat = "MMM d, yyyy hh:mm:ss a";
    @Builder.Default
    private String reportName = "";

    protected AbstractConfiguration(AbstractReporter reporter) {
        this.reporter = reporter;
    }
}