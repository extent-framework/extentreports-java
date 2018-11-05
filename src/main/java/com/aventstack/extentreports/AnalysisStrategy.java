package com.aventstack.extentreports;

/**
 * Strategy used to generate statistics for the current run
 * <p>
 * Available strategies are:
 * <ul>
 * 	<li>BDD: Strategy for BDD-style (Gherkin) tests</li>
 * 	<li>CLASS: Used for 2 levels: Class, Test</li>
 * 	<li>SUITE: Used for 3 levels: Suite, Class, Test</li>
 * 	<li>TEST: Used for 1 level only: Test</li>
 * </ul>
 */
public enum AnalysisStrategy {
	BDD,
	CLASS,
	SUITE,
	TEST
}
