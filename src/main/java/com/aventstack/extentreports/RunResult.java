package com.aventstack.extentreports;

/**
 * Marker interface for the run result with a single getStatus() method
 *
 */
public interface RunResult {
	
    Status getStatus();
    
}
