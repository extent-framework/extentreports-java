package com.aventstack.extentreports.reporter.configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.configuration.ConfigurationStore;
import com.aventstack.extentreports.reporter.AbstractReporter;

public abstract class BasicConfiguration {

	private ConfigurationStore store = new ConfigurationStore();
	private AbstractReporter reporter;
	private List<Status> levels;

	protected BasicConfiguration(AbstractReporter reporter) {
		this.reporter = reporter;
	}

	protected AbstractReporter getReporter() {
		return reporter;
	}

	public ConfigurationStore getConfigurationStore() {
		return store;
	}

	public void setLevel(Status... level) {
		if (levels == null) {
			levels = new ArrayList<>();
		}
		Arrays.stream(level).forEach(levels::add);
	}

	public List<Status> getLevel() {
		return levels;
	}

	public String getReportName() {
		return (String) Optional.ofNullable(getConfigurationStore().getConfig("reportName")).orElse(null);
	}

	public void setReportName(String reportName) {
		store.storeConfig("reportName", reportName);
	}

}