package com.aventstack.extentreports.model;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import com.aventstack.extentreports.RunResult;
import com.aventstack.extentreports.Status;

public class Log implements Serializable, RunResult {

	private static final long serialVersionUID = 8072065800800347981L;
	private Date timestamp = Calendar.getInstance().getTime();
	
	private ExceptionInfo exceptionInfo;
	private AbstractStructure<ScreenCapture> screenCapture;
	private transient Test test;
	private Status status;
	private String details;
	private int sequence;
	
	public Log(Test test) {
		this.test = test;
	}

	public Date getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Date timestamp) {
		this.timestamp = timestamp;
	}

	public ExceptionInfo getExceptionInfo() {
		return exceptionInfo;
	}

	public void setExceptionInfo(ExceptionInfo exceptionInfo) {
		this.exceptionInfo = exceptionInfo;
	}

	public AbstractStructure<ScreenCapture> getScreenCapture() {
		if (screenCapture == null) {
			screenCapture = new AbstractStructure<>();
		}
		return screenCapture;
	}

	public void setScreenCapture(AbstractStructure<ScreenCapture> screenCapture) {
		this.screenCapture = screenCapture;
	}

	public Test getTest() {
		return test;
	}

	public void setTest(Test test) {
		this.test = test;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getDetails() {
		return details;
	}

	public void setDetails(String details) {
		this.details = details;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}
	
}
