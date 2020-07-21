package com.aventstack.extentreports.reporter.configuration;

@FunctionalInterface
public interface ViewsConfigurable<T> {
    ViewConfigurer<?> viewConfigurer();
}
