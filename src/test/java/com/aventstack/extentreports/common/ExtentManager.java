package com.aventstack.extentreports.common;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.impl.ConsoleLogger;

public class ExtentManager {
    
    static ExtentReports extent;
    
    public static ExtentReports getInstance() {
        return extent;
    }
    
    public static ExtentReports createInstance() {
        ConsoleLogger logger = new ConsoleLogger();
        extent = new ExtentReports();
        extent.attachReporter(logger);
        
        return extent;
    }
    
}
