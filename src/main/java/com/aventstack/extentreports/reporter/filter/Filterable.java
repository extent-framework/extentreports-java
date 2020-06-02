package com.aventstack.extentreports.reporter.filter;

@FunctionalInterface
public interface Filterable {
    void withFilter(ContextFilter cf);
}
