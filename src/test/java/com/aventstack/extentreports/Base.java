package com.aventstack.extentreports;

import java.io.File;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.impl.ConsoleLogger;

public abstract class Base {
    
    protected final String fileName = getClass().getSimpleName();
    protected final String htmlFilePath = getOutputFolder() + fileName + ".html";
    protected final String emailFilePath = getOutputFolder() + "email-" + fileName + ".html";
    protected ExtentReports extent;
    
    @BeforeClass
    public void setup() {
        ConsoleLogger logger = new ConsoleLogger();        
        extent = new ExtentReports();
        extent.attachReporter(logger);
    }
    
    @AfterClass
    public void tearDown() {
        extent.flush();
    }
    
    protected String getOutputFolder() { 
        return "test-output/";
    }
    
    public Base() {
        File folder = new File(getOutputFolder());
        folder.mkdirs();
    }
}
