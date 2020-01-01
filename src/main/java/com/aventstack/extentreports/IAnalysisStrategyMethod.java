package com.aventstack.extentreports;

/**
 * Marker interface for a class that uses an {@link AnalysisStrategy}
 *
 */
public interface IAnalysisStrategyMethod {
    
    void setAnalysisStrategy(AnalysisStrategy strategy);
    
    AnalysisStrategy getAnalysisStrategy();
    
}