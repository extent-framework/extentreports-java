package com.aventstack.extentreports.reporter.configuration;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.reporter.AbstractReporter;

import lombok.Getter;

@Getter
public class StatusFilter {
    private Set<Status> status = new HashSet<>(Arrays.asList(Status.values()));
    private FileReporterConfigurer<?> configurer;

    public <T extends AbstractReporter> StatusFilter(FileReporterConfigurer<T> configurer) {
        this.configurer = configurer;
    }

    @SuppressWarnings("unchecked")
    public <T extends AbstractReporter> FileReporterConfigurer<T> as(Set<Status> status) {
        this.status = status;
        return (FileReporterConfigurer<T>) configurer.configure(this);
    }

    public <T extends AbstractReporter> FileReporterConfigurer<T> as(List<Status> status) {
        return as(new HashSet<>(status));
    }

    public <T extends AbstractReporter> FileReporterConfigurer<T> as(Status[] status) {
        return as(Arrays.asList(status));
    }
}
