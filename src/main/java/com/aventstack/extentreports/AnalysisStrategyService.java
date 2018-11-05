package com.aventstack.extentreports;

/**
 * Marker interface for a class that uses an {@link AnalysisStrategy}
 *
 */
public interface AnalysisStrategyService {
    
    void setAnalysisStrategy(AnalysisStrategy strategy);
    
    AnalysisStrategy getAnalysisStrategy();
    
}
