package com.aventstack.extentreports.reporter;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.aventstack.extentreports.configuration.ConfigurationBuilder;
import com.aventstack.extentreports.configuration.ConfigurationStore;
import com.aventstack.extentreports.reporter.configuration.BasicConfiguration;

public abstract class ConfigurableReporter extends AbstractReporter {

	private static final Logger logger = Logger.getLogger(ConfigurableReporter.class.getName());
	
	private BasicConfiguration basicConfiguration;
	
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
		ConfigurationBuilder builder = new ConfigurationBuilder(file, silent);
		ConfigurationStore store = builder.getConfigurationStore();
		basicConfiguration.getConfigurationStore().extendConfig(store);
	}

	/**
	 * Loads configuration from a Properties file
	 * 
	 * @param properties a {@link Properties} object
	 */
	public void loadConfig(Properties properties) {
		properties.entrySet().forEach(o -> {
			if (o.getKey() != null) {
				basicConfiguration.getConfigurationStore().storeConfig(o.getKey().toString(), o.getValue());
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
	
	protected void loadUserConfig() {
		for (Map.Entry<String, Object> entry : getConfigurationStore().getStore().entrySet()) {
			getConfigurationStore().storeConfig(entry.getKey(), entry.getValue());
		}
	}
	
	protected void init(String[] configFilePath, BasicConfiguration userConfig) {
		basicConfiguration = userConfig;
		loadInternalReporterConfiguration(configFilePath);
	}

	protected void loadInternalReporterConfiguration(String[] configFilePath) {
		ClassLoader loader = getClass().getClassLoader();
		Arrays.stream(configFilePath).map(x -> loader.getResourceAsStream(x)).filter(x -> x != null).findFirst()
				.ifPresent(x -> loadConfig(x));
	}

	/**
	 * Returns the current configuration (default and user-defined)
	 * 
	 * @return a {@link ConfigurationStore} containing key-value pairs of config
	 *         entries
	 */
	public ConfigurationStore getConfigurationStore() {
		return basicConfiguration == null ? null : basicConfiguration.getConfigurationStore();
	}

}