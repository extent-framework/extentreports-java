package com.aventstack.extentreports.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.gherkin.model.ScenarioOutline;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReportStats implements Serializable {
    private static final long serialVersionUID = 5424613250832948474L;

    private AnalysisStrategy analysisStrategy = AnalysisStrategy.TEST;
    private final Map<Status, Long> parent = new HashMap<>();
    private final Map<Status, Long> child = new HashMap<>();
    private final Map<Status, Long> leaf = new HashMap<>();
    private final Map<Status, Long> log = new HashMap<>();
    private final Map<Status, Float> parentStats = new HashMap<>();
    private final Map<Status, Float> childStats = new HashMap<>();
    private final Map<Status, Float> leafStats = new HashMap<>();
    private final Map<Status, Float> logStats = new HashMap<>();

    public final void update(final List<Test> testList) {
        reset();
        if (testList == null || testList.isEmpty())
            return;

        synchronized (testList) {
            // BDD: this level computes for features
            update(testList, parent, parentStats);
    
            // BDD: this would also include Scenario and excludes
            // ScenarioOutline
            var children = testList.stream()
                    .flatMap(x -> x.getChildren().stream())
                    .filter(x -> x.getBddType() != ScenarioOutline.class)
                    .collect(Collectors.toSet());
            var scenarios = children.stream()
                    .flatMap(x -> x.getChildren().stream())
                    .filter(x -> x.getBddType() == Scenario.class)
                    .collect(Collectors.toSet());
            children.addAll(scenarios);
            update(children, child, childStats);
    
            // BDD: this only includes Steps
            var leaf = children.stream()
                    .flatMap(x -> x.getChildren().stream())
                    .filter(x -> x.getBddType() != Scenario.class)
                    .collect(Collectors.toList());
            update(leaf, this.leaf, leafStats);

            // aggregate logs from entire hierarchy
            var logs = testList.stream()
                    .flatMap(x -> x.getLogs().stream())
                    .collect(Collectors.toSet());
            logs.addAll(children.stream().flatMap(x -> x.getLogs().stream())
                    .collect(Collectors.toSet()));
            logs.addAll(leaf.stream().flatMap(x -> x.getLogs().stream())
                    .collect(Collectors.toSet()));
            update(logs, log, logStats);
        }
    }

    private final void update(final Collection<? extends RunResult> list, final Map<Status, Long> countMap,
                              final Map<Status, Float> stats) {
        if (list == null)
            return;
        Map<Status, Long> map = list.stream().collect(
                Collectors.groupingBy(RunResult::getStatus, Collectors.counting()));
        Arrays.stream(Status.values()).forEach(x -> map.putIfAbsent(x, 0L));
        countMap.putAll(map);
        if (list.isEmpty()) {
            stats.putAll(map.entrySet().stream()
                    .collect(Collectors.toMap(e -> e.getKey(), e -> Float.valueOf(e.getValue()))));
            return;
        }
        Map<Status, Float> pctMap = map.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey(),
                        e -> Float.valueOf(e.getValue() * 100 / list.size())));
        stats.putAll(pctMap);
    }

    public final void reset() {
        List<RunResult> list = new ArrayList<>();
        update(list, parent, parentStats);
        update(list, child, childStats);
        update(list, leaf, leafStats);
        update(list, log, logStats);
    }

    public final long sumStat(final Map<Status, Long> stat) {
        return stat.values().stream().mapToLong(Long::longValue).sum();
    }
}
