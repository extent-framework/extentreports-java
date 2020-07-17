package com.aventstack.extentreports.model;

import java.util.Map;

@FunctionalInterface
public interface MetaDataStorable {
    Map<String, Object> getInfoMap();
}
