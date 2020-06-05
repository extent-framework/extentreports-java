package com.aventstack.extentreports.reporter.configuration;

import java.util.Arrays;
import java.util.List;

import com.aventstack.extentreports.reporter.AbstractReporter;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ViewOrder {
    private static final List<ViewName> DEFAULT_ORDER = Arrays.asList(new ViewName[]{
            ViewName.TEST,
            ViewName.EXCEPTION,
            ViewName.CATEGORY,
            ViewName.DEVICE,
            ViewName.AUTHOR,
            ViewName.LOGS,
            ViewName.DASHBOARD
    });

    private List<ViewName> viewOrder = DEFAULT_ORDER;
    private FileReporterConfigurer<?> configurer;

    public <T extends AbstractReporter> ViewOrder(FileReporterConfigurer<T> configurer) {
        this.configurer = configurer;
    }

    @SuppressWarnings("unchecked")
    public <T extends AbstractReporter> FileReporterConfigurer<T> as(List<ViewName> order) {
        this.viewOrder = order;
        return (FileReporterConfigurer<T>) configurer.configure(this);
    }

    public <T extends AbstractReporter> FileReporterConfigurer<T> as(ViewName[] viewOrder) {
        return as(Arrays.asList(viewOrder));
    }
}
