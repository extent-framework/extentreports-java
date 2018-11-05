package com.aventstack.extentreports.model;

import java.io.Serializable;

import com.aventstack.extentreports.utils.ExceptionUtil;

public class ExceptionInfo implements Serializable {

    private static final long serialVersionUID = 2672123037706464734L;

    private String exceptionName;
    private String stackTrace;
    private Throwable t;
    
    public ExceptionInfo() { }
    
    public ExceptionInfo(Throwable t) {
    	setException(t);
        setExceptionName(ExceptionUtil.getExceptionHeadline(t));
        setStackTrace(ExceptionUtil.getStackTrace(t));
    }
    
    // exception-name
    public String getExceptionName() { return exceptionName; }
    
    public void setExceptionName(String exceptionName) { this.exceptionName = exceptionName; }

    // stack-trace
    public String getStackTrace() { return stackTrace; }
    
    public void setStackTrace(String stackTrace) {
        this.stackTrace = stackTrace;
    }
    
    // exception
    public void setException(Throwable t) { this.t = t; }
    
    public Throwable getException() { return t; }
    
}