package com.aventstack.extentreports.model.context;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.aventstack.extentreports.model.ExceptionInfo;
import com.aventstack.extentreports.model.Test;

/**
 * Provides and tracks the collection of tests segregated by the type of
 * {@link Exception}
 * 
 */
public class ExceptionTestContextStore {

	private List<ExceptionTestContext> exceptionTestContext = new ArrayList<>();

	public void setExceptionContext(ExceptionInfo ei, Test test) {
		Optional<ExceptionTestContext> exOptionalTestContext = exceptionTestContext.stream()
				.filter(x -> x.getExceptionInfo().getExceptionName().equals(ei.getExceptionName())).findFirst();

		if (exOptionalTestContext.isPresent()) {
			List<Test> testList = exOptionalTestContext.get().getTests();

			boolean b = testList.stream().anyMatch(t -> t.getId() == test.getId());

			if (!b) {
				exOptionalTestContext.get().setTest(test);
			}
		} else {
			ExceptionTestContext exTestContext = new ExceptionTestContext(ei);
			exTestContext.setTest(test);
			exceptionTestContext.add(exTestContext);
		}
	}

	public List<ExceptionTestContext> getExceptionTestContext() {
		return exceptionTestContext;
	}
}