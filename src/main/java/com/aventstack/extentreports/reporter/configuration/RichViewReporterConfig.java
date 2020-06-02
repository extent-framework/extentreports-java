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

    /**
     * Enable or disable the Timeline section in the Dashboard view
     *
     * @param v
     *            Setting to enable or disable the Timeline section in the
     *            Dashboard view
     */
    public void enableTimeline(boolean v) {
        timelineEnabled = v;
    }
}