package com.aventstack.extentreports.reporter.configuration;

public enum ViewName {
    AUTHOR, CATEGORY, DASHBOARD, DEVICE, EXCEPTION, LOGS, TEST;
    
    @Override
    public String toString() {
        return this.name().toLowerCase();
    }
}
