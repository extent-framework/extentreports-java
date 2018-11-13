package com.aventstack.extentreports.gherkin.model;

import java.io.Serializable;

public class And 
	implements IGherkinFormatterModel, Serializable {

	private static final long serialVersionUID = 8543289653944756660L;

	public static String getGherkinName() {
		return "And";
	}
	
	@Override
	public String toString() {
		return getGherkinName();
	}
	
}
