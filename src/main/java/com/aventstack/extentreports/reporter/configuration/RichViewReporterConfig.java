package com.aventstack.extentreports.reporter.configuration;

import com.aventstack.extentreports.reporter.AbstractReporter;

import lombok.Getter;
import lombok.Setter;

/**
 * Contains configuration for rich reporters such as Avent, Tabular, Spark.
 *
 */
@Getter
@Setter
public abstract class RichViewReporterConfig extends AbstractFileReporterConfig {
    private Protocol protocol = Protocol.HTTP;
    private Theme theme = Theme.STANDARD;
    private boolean timelineEnabled = true;
    private String css = "";
    private String js = "";

    protected RichViewReporterConfig(AbstractReporter reporter) {
        super(reporter);
    }
}