package com.aventstack.extentreports.reporter;

import java.io.File;

import com.aventstack.extentreports.ReportAggregates;

/**
 * The ExtentHtmlReporter creates a rich standalone HTML file. It allows several configuration options
 * via the <code>config()</code> method.
 */
public class ExtentCardsReporter 
	extends BasicFileReporter {
    
    private static final String REPORTER_NAME = "cards";

    public ExtentCardsReporter(String path) {
        super(path);
    }
    
    public ExtentCardsReporter(File file) {
    	super(file);
    }

    @Override
    public synchronized void flush(ReportAggregates reportAggregates) {
    }
    
	@Override
	public String getReporterName() {
		return REPORTER_NAME;
	}

}
