package com.aventstack.extentreports.model.service;

import com.aventstack.extentreports.model.Log;

public class LogService {

	public static Boolean logHasScreenCapture(Log log) {
		return !log.getScreenCaptureContext().isEmpty();
	}
	
}
