package com.aventstack.extentreports.reporter;

import java.util.Set;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Report;
import com.aventstack.extentreports.model.service.ReportFilterService;
import com.aventstack.extentreports.reporter.filter.StatusFilterable;
import com.aventstack.extentreports.util.Assert;

import lombok.Getter;

@Getter
public class AbstractFilterableReporter extends AbstractReporter implements StatusFilterable {
    @Override
    public Report filterAndGet(Report report, Set<Status> set) {
        Assert.notNull(report, "Report must not be null");
        if (set != null)
            return ReportFilterService.filter(report, set);
        return report;
    }
}
