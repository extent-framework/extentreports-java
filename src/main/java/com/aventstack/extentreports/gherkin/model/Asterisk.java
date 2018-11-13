package com.aventstack.extentreports.gherkin.model;

import java.io.Serializable;

public class Asterisk 
	implements IGherkinFormatterModel, Serializable {

	private static final long serialVersionUID = 7251419811428200133L;
	
	public static String getGherkinName() {
		return "*";
	}
	
	@Override
	public String toString() {
		return getGherkinName();
	}
	
}
