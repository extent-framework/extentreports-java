package com.aventstack.extentreports.reporter.configuration;

import java.util.Optional;

import com.aventstack.extentreports.reporter.AbstractReporter;

/**
 * Common configuration for file based reporters
 * 
 */
public abstract class BasicFileConfiguration extends BasicConfiguration {

	protected BasicFileConfiguration(AbstractReporter reporter) {
		super(reporter);
	}

	/**
	 * Gets the timestamp format
	 *
	 * @return The time stamp format
	 */
	public String getTimeStampFormat() {
		return (String) Optional.ofNullable(getConfigurationStore().getConfig("timeStampFormat")).orElse(null);
	}

	/**
	 * Sets the timestamp format
	 *
	 * @param timeStampFormat The desired time stamp format See
	 *                        http://freemarker.org/docs/ref_builtins_date.html#ref_builtin_string_for_date
	 */
	public void setTimeStampFormat(String timeStampFormat) {
		getConfigurationStore().storeConfig("timeStampFormat", timeStampFormat);
	}

	/**
	 * Sets file encoding, eg: UTF-8
	 * 
	 * @param encoding Encoding
	 */
	public void setEncoding(String encoding) {
		getConfigurationStore().storeConfig("encoding", encoding);
	}

	public String getEncoding() {
		return (String) Optional.ofNullable(getConfigurationStore().getConfig("encoding")).orElse(null);
	}

	/**
	 * Sets the document title denoted by the <code>title</code> tag
	 * 
	 * @param documentTitle Title
	 */
	public void setDocumentTitle(String documentTitle) {
		getConfigurationStore().storeConfig("documentTitle", documentTitle);
	}

	public String getDocumentTitle() {
		return (String) Optional.ofNullable(getConfigurationStore().getConfig("documentTitle")).orElse(null);
	}

	/**
	 * Sets CSS to be used to customize the look and feel of your report
	 * 
	 * @param css CSS
	 */
	public void setCSS(String css) {
		getConfigurationStore().storeConfig("css", css);
	}

	public String getCSS() {
		return (String) Optional.ofNullable(getConfigurationStore().getConfig("css")).orElse(null);
	}

	/**
	 * Adds custom JavaScript
	 * 
	 * @param js JavaScript
	 */
	public void setJS(String js) {
		getConfigurationStore().storeConfig("js", js);
	}

	public String getJS() {
		return (String) Optional.ofNullable(getConfigurationStore().getConfig("js")).orElse(null);
	}

}