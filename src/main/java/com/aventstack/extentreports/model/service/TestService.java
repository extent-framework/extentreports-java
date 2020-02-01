package com.aventstack.extentreports.model.service;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import com.aventstack.extentreports.gherkin.model.IGherkinFormatterModel;
import com.aventstack.extentreports.model.Test;

public class TestService {

	public static Boolean testHasAttributes(Test test) {
		return testHasAuthor(test) || testHasCategory(test) || testHasDevice(test);
	}

	public static Boolean testHasAuthor(Test test) {
		return !test.getAuthorContext().isEmpty();
	}

	public static Boolean testHasCategory(Test test) {
		return !test.getCategoryContext().isEmpty();
	}

	public static Boolean testHasDevice(Test test) {
		return !test.getDeviceContext().isEmpty();
	}

	public static Boolean testHasException(Test test) {
		return !test.getExceptionInfoContext().isEmpty();
	}

	public static Boolean testHasChildren(Test test) {
		return !test.getNodeContext().isEmpty();
	}

	public static Boolean testHasLog(Test test) {
		return !test.getLogContext().isEmpty();
	}

	public static Boolean testHasScreenCapture(Test test) {
		return !test.getScreenCaptureContext().isEmpty();
	}

	public static Boolean isTestBehaviorDriven(Test test) {
		return test.getBddType() != null;
	}

	public static String getBehaviorDrivenTypeName(Class<? extends IGherkinFormatterModel> bddType)
			throws NoSuchMethodException, SecurityException, IllegalAccessException, IllegalArgumentException,
			InvocationTargetException {
		Method method = bddType.getMethod("getGherkinName");
		Object o = method.invoke(null, (Object[]) null);
		return o.toString();
	}

	public static String getBehaviorDrivenTypeName(Test test) throws NoSuchMethodException, SecurityException,
			IllegalAccessException, IllegalArgumentException, InvocationTargetException {
		return getBehaviorDrivenTypeName(test.getBddType());
	}

	public static String getRunDuration(Test test) {
		long diff = test.getEndTime().getTime() - test.getStartTime().getTime();
		long secs = diff / 1000;
		long millis = diff % 1000;
		long mins = secs / 60;
		secs = secs % 60;
		long hours = mins / 60;
		mins = mins % 60;
		return hours + "h " + mins + "m " + secs + "s+" + millis + "ms";
	}

	public static Long getRunDurationMillis(Test test) {
		return test.getEndTime().getTime() - test.getStartTime().getTime();
	}
	
	public static String getHierarchicalName(Test test) {
		StringBuilder sb = new StringBuilder(test.getName());
		while (test.getParent() != null) {
			test = test.getParent();
			sb.insert(0, test.getName() + ".");
		}
		return sb.toString();
	}

}
