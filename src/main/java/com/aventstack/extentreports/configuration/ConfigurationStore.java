package com.aventstack.extentreports.configuration;

import java.util.HashMap;
import java.util.Map;

public class ConfigurationStore {

	private Map<String, Object> store = new HashMap<String, Object>();

	public Map<String, Object> getStore() {
		return store;
	}

	public void storeConfig(String key, Object value) {
		store.put(key, value);
	}

	public boolean containsConfig(String k) {
		return store.containsKey(k);
	}

	public void removeConfig(String k) {
		store.remove(k);
	}

	public Object getConfig(String k) {
		return store.containsKey(k) ? store.get(k) : null;
	}

	public void extendConfig(Map<String, Object> map) {
		map.forEach((key, value) -> this.storeConfig(key, value));
	}

	public void extendConfig(ConfigurationStore config) {
		Map<String, Object> map = config.store;
		this.extendConfig(map);
	}

	public boolean isEmpty() {
		return store.isEmpty();
	}

}
