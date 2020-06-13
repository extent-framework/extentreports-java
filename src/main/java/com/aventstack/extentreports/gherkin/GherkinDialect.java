package com.aventstack.extentreports.gherkin;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * Modified version of GherkinKeyword.java from cucumber/gherkin. Source url:
 * https://raw.githubusercontent.com/cucumber/cucumber/master/gherkin/java/src/main/java/gherkin/GherkinDialect.java
 * 
 * <p>
 * Gherkin source is licensed under the MIT License
 *
 */
public class GherkinDialect {
	
	private final Map<String, List<String>> keywords;
	private String language;

	public GherkinDialect(String language, Map<String, List<String>> keywords) {
		keywords.remove("name");
		keywords.remove("native");
		this.language = language;
		this.keywords = keywords;
	}

	public Map<String, List<String>> getKeywords() {
		return keywords;
	}

	public String getLanguage() {
		return language;
	}
	
}