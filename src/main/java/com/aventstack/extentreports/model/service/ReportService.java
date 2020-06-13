package com.aventstack.extentreports.model.service;

import java.util.List;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Report;
import com.aventstack.extentreports.model.Test;

public class ReportService {
    public static Boolean isBDD(Report report) {
        return (report != null && report.getTestList() != null && !report.getTestList().isEmpty()
                && TestService.isBDD(report.getTestList().get(0)));
    }

    public static Boolean anyTestHasStatus(Report report, Status status) {
        return anyTestHasStatus(report.getTestList(), status);
    }

    public static Boolean anyTestHasStatus(List<Test> testList, Status status) {
        return testList.stream()
                .anyMatch(x -> x.getStatus() == status);
    }

    public static Long timeTaken(Report report) {
        return report.getEndTime().getTime() - report.getStartTime().getTime();
    }
}
