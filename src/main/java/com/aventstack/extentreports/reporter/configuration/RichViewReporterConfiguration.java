package com.aventstack.extentreports.reporter.configuration;

import com.aventstack.extentreports.reporter.AbstractReporter;

/**
 * Contains configuration for rich reporters such as Avent, Tabular, Cards etc.
 *
 */
public abstract class RichViewReporterConfiguration extends BasicFileConfiguration implements IReporterConfiguration {

	protected RichViewReporterConfiguration(AbstractReporter reporter) {
		super(reporter);
	}

	/**
	 * Sets the protocol of accessing CSS/JS resources from CDN
	 * 
	 * <p>
	 * Default protocol value: HTTPS
	 * </p>
	 * 
	 * @param protocol Protocol, HTTPS or HTTP
	 */
	public void setProtocol(Protocol protocol) {
		getConfigurationStore().storeConfig("protocol", String.valueOf(protocol).toLowerCase());
	}

	public Protocol getProtocol() {
		if (getConfigurationStore().containsConfig("protocol")) {
			return (Protocol) getConfigurationStore().getConfig("protocol");
		}
		return null;
	}

	/**
	 * Sets the {@link Theme} of the report
	 * 
	 * @param theme {@link Theme}
	 */
	public void setTheme(Theme theme) {
		getConfigurationStore().storeConfig("theme", String.valueOf(theme).toLowerCase());
	}

	public Theme getTheme() {
		if (getConfigurationStore().containsConfig("theme")) {
			return Theme.valueOf(getConfigurationStore().getConfig("theme").toString());
		}
		return null;
	}

	/**
	 * Enable or disable the Timeline section in the Dashboard view
	 *
	 * @param v Setting to enable or disable the Timeline section in the Dashboard
	 *          view
	 */
	public void enableTimeline(boolean v) {
		getConfigurationStore().storeConfig("enableTimeline", String.valueOf(v).toLowerCase());
	}

}