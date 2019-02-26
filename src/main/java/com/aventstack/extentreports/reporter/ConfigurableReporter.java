package com.aventstack.extentreports.reporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.aventstack.extentreports.ExtentReporter;
import com.aventstack.extentreports.externalconfig.ConfigLoader;
import com.aventstack.extentreports.externalconfig.model.Config;
import com.aventstack.extentreports.externalconfig.model.ConfigMap;

/**
 * A base class to provide bootstrapping for loading custom configuration for
 * the Reporter using XML or Properties file
 * 
 */
public abstract class ConfigurableReporter implements ExtentReporter {

	private static final Logger logger = Logger.getLogger(ConfigurableReporter.class.getName());

	/**
	 * A key-value pair holding information from default and the user-provided
	 * configuration
	 */
	protected ConfigMap configContext = new ConfigMap();

	/**
	 * Loads configuration from an XML file
	 * 
	 * @param filePath Configuration file path
	 * @param silent   Load configuration silently, no errors are thrown if the
	 *                 configuration file is not present
	 */
	public void loadXMLConfig(String filePath, Boolean silent) {
		loadXMLConfig(new File(filePath), silent);
	}

	/**
	 * Loads configuration from an XML file
	 * 
	 * @param filePath configuration file path
	 */
	public void loadXMLConfig(String filePath) {
		loadXMLConfig(filePath, false);
	}

	/**
	 * Loads configuration from an XML file
	 * 
	 * @param file   configuration {@link File}
	 * @param silent If silent, no errors will be thrown
	 */
	public void loadXMLConfig(File file, Boolean silent) {
		ConfigLoader configLoader = new ConfigLoader(file, silent);
		ConfigMap config = configLoader.getConfigurationHash();
		configContext.appendConfig(config);
	}

	/**
	 * Loads configuration from a Properties file
	 * 
	 * @param properties a {@link Properties} object
	 */
	public void loadConfig(Properties properties) {
		properties.entrySet().forEach(o -> {
			if (o.getKey() != null) {
				configContext.setConfig(o.getKey().toString(), o.getValue());
			}
		});
	}

	/**
	 * Loads configuration from an {@link InputStream} which is a {@link Properties}
	 * file
	 * 
	 * @param stream an {@link InputStream}
	 */
	public void loadConfig(InputStream stream) {
		Properties properties = new Properties();

		try {
			properties.load(stream);
			loadConfig(properties);
		} catch (FileNotFoundException e) {
			logger.log(Level.SEVERE, "Default Properties file not found", e);
		} catch (IOException e) {
			logger.log(Level.SEVERE, "Unable to load properties file", e);
		}
	}

	/**
	 * Loads configuration from an path which is a {@link Properties} file
	 * 
	 * @param filePath Properties file path
	 */
	public void loadConfig(String filePath) {
		try {
			InputStream is = new FileInputStream(filePath);
			loadConfig(is);
		} catch (FileNotFoundException e) {
			logger.log(Level.SEVERE, "Default Properties file not found", e);
		}
	}

	/**
	 * Returns the current configuration (default and user-defined)
	 * 
	 * @return a {@link ConfigMap} containing key-value pairs of config entries
	 */
	public ConfigMap getConfigContext() {
		return configContext;
	}

}
