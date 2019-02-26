package com.aventstack.extentreports.externalconfig.model;

import java.util.HashMap;
import java.util.Map;

public class ConfigMap {

	private Map<String, Object> configMap;

	public ConfigMap() {
		configMap = new HashMap<String, Object>();
	}

	public void setConfig(String key, Object value) {
		configMap.put(key, value);
	}

	public Map<String, Object> getConfigMap() {
		return configMap;
	}

	public boolean containsKey(String k) {
		return configMap.containsKey(k);
	}

	void removeKey(String k) {
		configMap.remove(k);
	}

	public Object getValue(String k) {
		return configMap.containsKey(k) ? configMap.get(k) : null;
	}
	
	public void appendConfig(Map<String, Object> configurations)
	{
		// This might create a hard dependency on Java 8. As Lambda function were introduced in Java *.
		// We can instead have an in-line implementation of BiConsumer interface
		configurations.forEach((key, value) -> this.setConfig(key,  value));
	}
	
	public void appendConfig(ConfigMap configurations)
	{
		Map<String, Object> map = configurations.configMap;
		this.appendConfig(map);
	}
	
	
	public boolean isEmpty() {
		return configMap.isEmpty();
	}
	
	public void printMapForTest()
	{
		configMap.forEach((key, value) -> System.out.println("Key: " + key + " value: " + String.valueOf(value)));
	}
}
