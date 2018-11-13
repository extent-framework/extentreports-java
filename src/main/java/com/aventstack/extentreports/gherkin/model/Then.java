package com.aventstack.extentreports.gherkin.model;

import java.io.Serializable;

public class Then 
	implements IGherkinFormatterModel, Serializable {

	private static final long serialVersionUID = 2493591502473169772L;

	public static String getGherkinName() {
		return "Then";
	}
	
	@Override
	public String toString() {
		return getGherkinName();
	}
	
}
