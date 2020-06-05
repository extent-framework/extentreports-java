package com.aventstack.extentreports.reporter;

import com.aventstack.extentreports.model.Report;
import com.aventstack.extentreports.model.service.ReportFilterService;
import com.aventstack.extentreports.observer.entity.ReportEntity;
import com.aventstack.extentreports.reporter.filter.ContextFilter;
import com.aventstack.extentreports.reporter.filter.Filterable;

import lombok.Getter;

@Getter
public class AbstractFilterableReporter extends AbstractReporter implements Filterable {
    private ContextFilter filter;

    @Override
    public void withFilter(ContextFilter filter) {
        this.filter = filter;
    }

    protected Report filterAndGet(ReportEntity entity) {
        if (entity.getReport() == null || entity.getReport().getTestList().isEmpty())
            return Report.builder().build();
        if (filter != null)
            return ReportFilterService.filter(entity.getReport(), filter.getStatus());
        return entity.getReport();
    }
}
