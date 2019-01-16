package com.aventstack.extentreports.reporter.configuration;

/**
 * Allows selecting a CDN/resource loader for your FileReporter
 * 
 * Note: Some hosts do not allow loading resources via HTTPS protocol:
 * 
 * <ul>
 *  <li>ExtentReports</li>
 * </ul>
 * 
 */
public enum ResourceCDN {
    GITHUB,
    EXTENTREPORTS
}
