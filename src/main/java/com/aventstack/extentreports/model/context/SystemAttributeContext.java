package com.aventstack.extentreports.model.context;

import java.util.ArrayList;
import java.util.List;

import com.aventstack.extentreports.model.SystemAttribute;

/**
 * A simple key-value pair collection to store System/Environment information
 *
 */
public class SystemAttributeContext {

	private List<SystemAttribute> list = new ArrayList<>();

	public void setSystemAttribute(SystemAttribute sa) {
		list.add(sa);
	}
	
	public String getSystemAttribute(String k) {
		return list.stream()
			.filter(x -> x.getName().equals(k))
			.map(SystemAttribute::getValue)
			.findFirst()
			.orElse(null);
	}

	public List<SystemAttribute> getSystemAttributeList() {
		return list;
	}

	public void clear() {
		list.clear();
	}
}