package com.aventstack.extentreports.model.context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.aventstack.extentreports.model.ExceptionInfo;
import com.aventstack.extentreports.model.Test;

public class ExceptionTestContext implements Serializable {

	private static final long serialVersionUID = -2516200535748363722L;

	private ExceptionInfo exceptionInfo;
	private List<Test> tests = new ArrayList<>();

	public ExceptionTestContext(ExceptionInfo exceptionInfo) {
		this.exceptionInfo = exceptionInfo;
	}

	public void setTest(Test test) {
		tests.add(test);
	}

	public List<Test> getTests() {
		return tests;
	}

	public ExceptionInfo getExceptionInfo() {
		return exceptionInfo;
	}

}