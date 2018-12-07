package com.aventstack.extentreports.reporter.configuration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.AbstractReporter;

public abstract class BasicConfiguration {

	private AbstractReporter reporter;
	
    protected List<Status> levels;
    protected String reportName;
    protected String timeStampFormat;
    protected Map<String, String> usedConfigs = new HashMap<>();

    protected BasicConfiguration() { }
    
    protected BasicConfiguration(AbstractReporter reporter) {
    	this.reporter = reporter;
    }
    
    protected AbstractReporter getReporter() {
    	return reporter;
    }
    
    public Map<String, String> getConfigMap() {
        return usedConfigs;
    }

    public void setLevel(Status... level) {
        if (levels == null)
            levels = new ArrayList<>();

        Arrays.stream(level).forEach(levels::add);
    }

    public List<Status> getLevel() {
        return levels;
    }

    /**
     * Gets the timestamp format
     *
     * @return The time stamp format
     */
    public String getTimeStampFormat() {
        return usedConfigs.get("timeStampFormat");
    }

    /**
     * Sets the timestamp format
     *
     * @param timeStampFormat The desired time stamp format
     *                        See http://freemarker.org/docs/ref_builtins_date.html#ref_builtin_string_for_date
     */
    public void setTimeStampFormat(String timeStampFormat) {
        usedConfigs.put("timeStampFormat", timeStampFormat);
        this.timeStampFormat = timeStampFormat;
    }

    public void setReportName(String reportName) {
        usedConfigs.put("reportName", reportName);
        this.reportName = reportName;
    }

    public String getReportName() {
        return reportName;
    }

}
