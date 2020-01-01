package com.aventstack.extentreports.model;

import java.io.Serializable;

public class ExceptionInfo implements Serializable {

    private static final long serialVersionUID = 2672123037706464734L;

    private String exceptionName;
    private String stackTrace;
    private Throwable throwable;
    
	public String getExceptionName() {
		return exceptionName;
	}
	public void setExceptionName(String exceptionName) {
		this.exceptionName = exceptionName;
	}
	public String getStackTrace() {
		return stackTrace;
	}
	public void setStackTrace(String stackTrace) {
		this.stackTrace = stackTrace;
	}
	public Throwable getThrowable() {
		return throwable;
	}
	public void setThrowable(Throwable throwable) {
		this.throwable = throwable;
	}
	
}