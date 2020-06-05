package com.aventstack.extentreports.model.service;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.entity.Scenario;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.model.ReportStats;
import com.aventstack.extentreports.model.RunResult;
import com.aventstack.extentreports.model.Test;

public class ReportStatsService {

    public static void refreshReportStats(final ReportStats stats,
            final List<Test> testList) {
        if (testList == null || testList.isEmpty())
            return;

        resetReportStats(stats);
        refreshReportStats(stats, testList, stats.getParent(), stats.getParentPercentage());

        // level 1, for BDD< this would also include ScenarioOutline, Scenario
        List<Test> children = testList.stream()
                .flatMap(x -> x.getChildren().stream())
                .collect(Collectors.toList());
        List<Test> scenarios = children.stream()
                .flatMap(x -> x.getChildren().stream())
                .filter(x -> x.getBddType() == Scenario.class)
                .collect(Collectors.toList());
        children.addAll(scenarios);
        refreshReportStats(stats, children, stats.getChild(), stats.getChildPercentage());

        // level 2, for BDD, this only includes Steps
        List<Test> grandChildren = children.stream()
                .flatMap(x -> x.getChildren().stream())
                .filter(x -> x.getBddType() != Scenario.class)
                .collect(Collectors.toList());

        // additional step for BDD
        // BDD tests can have the following 2 hierarchies:
        // Feature -> Scenario -> Step
        // Feature -> ScenarioOutline -> Scenario -> Step
        if (!grandChildren.isEmpty()) {
            List<Test> list = grandChildren.stream()
                    .filter(x -> x.getBddType() != Scenario.class)
                    .flatMap(x -> x.getChildren().stream())
                    .collect(Collectors.toList());
            grandChildren.addAll(list);
            refreshReportStats(stats, grandChildren, stats.getGrandchild(), stats.getGrandchildPercentage());
        }

        List<Log> logs = testList.stream().flatMap(x -> x.getLogs().stream()).collect(Collectors.toList());
        logs.addAll(children.stream().flatMap(x -> x.getLogs().stream()).collect(Collectors.toList()));
        logs.addAll(grandChildren.stream().flatMap(x -> x.getLogs().stream()).collect(Collectors.toList()));
        refreshReportStats(stats, logs, stats.getLog(), stats.getLogPercentage());
    }

    private static void refreshReportStats(final ReportStats stats,
            final List<? extends RunResult> list, final Map<Status, Long> countMap,
            final Map<Status, Float> percentageMap) {
        if (list == null || list.isEmpty())
            return;
        Map<Status, Long> map = list.stream().collect(
                Collectors.groupingBy(RunResult::getStatus, Collectors.counting()));
        Arrays.asList(Status.values()).forEach(x -> map.putIfAbsent(x, 0L));
        Map<Status, Float> pctMap = map.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey(),
                        e -> Float.valueOf(e.getValue() * 100 / list.size())));
        countMap.putAll(map);
        percentageMap.putAll(pctMap);
    }

    public static void resetReportStats(final ReportStats stats) {
        stats.getChild().clear();
        stats.getChildPercentage().clear();
        stats.getGrandchild().clear();
        stats.getGrandchildPercentage().clear();
        stats.getLog().clear();
        stats.getLogPercentage().clear();
        stats.getParent().clear();
        stats.getParentPercentage().clear();
    }
}
