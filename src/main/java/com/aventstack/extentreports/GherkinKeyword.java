package com.aventstack.extentreports;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.aventstack.extentreports.gherkin.GherkinDialect;
import com.aventstack.extentreports.gherkin.GherkinDialectProvider;
import com.aventstack.extentreports.gherkin.entity.Asterisk;
import com.aventstack.extentreports.gherkin.entity.IGherkinFormatterModel;

import freemarker.template.utility.StringUtil;
import lombok.Getter;

/**
 * Allows {@link IGherkinFormatterModel} to be returned by using a name, from
 * the below gherkin model classes:
 * 
 * <ul>
 * <li>{@link com.aventstack.extentreports.gherkin.model.Feature}</li>
 * <li>{@link com.aventstack.extentreports.gherkin.model.Background}</li>
 * <li>{@link com.aventstack.extentreports.gherkin.model.Scenario}</li>
 * <li>{@link com.aventstack.extentreports.gherkin.model.Given}</li>
 * <li>{@link com.aventstack.extentreports.gherkin.model.When}</li>
 * <li>{@link com.aventstack.extentreports.gherkin.model.Then}</li>
 * <li>{@link com.aventstack.extentreports.gherkin.model.And}</li>
 * <li>{@link com.aventstack.extentreports.gherkin.model.But}</li>
 * </ul>
 * 
 * <p>
 * Example:
 * </p>
 * 
 * <pre>
 * extent.createTest(new GherkinKeyword("Feature"), "bddTest");
 * test.createNode(new GherkinKeyword("Scenario"), bddNode");
 * </pre>
 * 
 * @see IGherkinFormatterModel
 */
@Getter
public class GherkinKeyword {
	
	private static final Logger logger = Logger.getLogger(GherkinKeyword.class.getName());

	private Class<IGherkinFormatterModel> clazz = IGherkinFormatterModel.class;
	private IGherkinFormatterModel keyword;

	public GherkinKeyword(String gk) throws ClassNotFoundException {
		GherkinDialect dialect = null;
		String apiKeyword = StringUtil.capitalize(gk.trim());
		String refPath = clazz.getPackage().getName();

		try {
			apiKeyword = apiKeyword.equals("*") ? Asterisk.class.getSimpleName() : apiKeyword;
			dialect = GherkinDialectProvider.getDialect();
			if (dialect != null
					&& !dialect.getLanguage().equalsIgnoreCase(GherkinDialectProvider.getDefaultLanguage())) {
				apiKeyword = null;
				Map<String, List<String>> keywords = dialect.getKeywords();

				for (Entry<String, List<String>> key : keywords.entrySet()) {
					apiKeyword = key.getValue().stream()
						.filter(x -> x.trim().equalsIgnoreCase(gk.trim()))
						.findAny()
						.map(x -> StringUtil.capitalize(x))
						.orElse(null);
					if (apiKeyword != null) 
						break;
				}
			}

			if (apiKeyword == null) {
				throw new GherkinKeywordNotFoundException("Keyword " + apiKeyword + " cannot be null");
			}

			String clazzName = refPath + "." + apiKeyword.replace(" ", "");
			Class<?> c = Class.forName(clazzName);
			keyword = (IGherkinFormatterModel) c.newInstance();
		} catch (InstantiationException | IllegalAccessException e) {
			logger.log(Level.SEVERE, "", e);
		}
	}
}