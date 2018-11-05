package com.aventstack.extentreports.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class ExceptionTestContext 
	implements Serializable {
    
    private static final long serialVersionUID = -2516200535748363722L;
    
    private ExceptionInfo exceptionInfo;
    private List<Test> testList = new ArrayList<>();;
    
    public ExceptionTestContext(ExceptionInfo exceptionInfo) { 
    	this.exceptionInfo = exceptionInfo; 
	}
    
    public void setTest(Test test) {
        testList.add(test);
    }
    
    public List<Test> getTestList() { 
    	return testList; 
	}
    
    public ExceptionInfo getExceptionInfo() { 
    	return exceptionInfo; 
	}
    
}
