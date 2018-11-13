package com.aventstack.extentreports.gherkin.model;

import java.io.Serializable;

public class Background 
	implements IGherkinFormatterModel, Serializable {

	private static final long serialVersionUID = -955371501488725151L;
	
	public static String getGherkinName() {
		return "Background";
	}
	
	@Override
	public String toString() {
		return getGherkinName();
	}
	
}
