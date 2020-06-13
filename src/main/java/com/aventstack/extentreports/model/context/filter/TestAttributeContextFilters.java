package com.aventstack.extentreports.model.context.filter;

import com.aventstack.extentreports.model.Test;

public class TestAttributeContextFilters {

	public static boolean testHasStatusIncrEligibility(Test test) {
		return test.isBehaviorDrivenType() && test.getBehaviorDrivenTypeName().equalsIgnoreCase("feature") ? false
				: true;
	}

}
