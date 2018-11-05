package com.aventstack.extentreports.reporter;

import java.io.File;

import com.aventstack.extentreports.ReportAggregates;
import com.aventstack.extentreports.SourceProvider;

/**
 * The ExtentHtmlReporter creates a rich standalone HTML file. It allows several configuration options
 * via the <code>config()</code> method.
 */
public class ExtentEmailReporter 
	extends BasicFileReporter
	implements SourceProvider {
    
    private static final String REPORTER_NAME = "email";
    
    public ExtentEmailReporter(String path) {
        super(path);
    }
    
    public ExtentEmailReporter(File file) {
    	super(file);
    }
    
    @Override
    public synchronized void flush(ReportAggregates reportAggregates) {
    }

	@Override
	public String getReporterName() {
		return REPORTER_NAME;
	}

	@Override
	public String getSource() {
		return super.getSource();
	}

}
