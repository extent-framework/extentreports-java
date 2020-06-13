package com.aventstack.extentreports.model;

import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.aventstack.extentreports.AnalysisStrategy;
import com.aventstack.extentreports.Status;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ReportStats implements Serializable {
    private static final long serialVersionUID = 5424613250832948474L;

    private AnalysisStrategy analysisStrategy = AnalysisStrategy.TEST;
    private final Map<Status, Long> parent = new ConcurrentHashMap<>();
    private final Map<Status, Long> child = new ConcurrentHashMap<>();
    private final Map<Status, Long> grandchild = new ConcurrentHashMap<>();
    private final Map<Status, Long> log = new ConcurrentHashMap<>();
    private final Map<Status, Float> parentPercentage = new ConcurrentHashMap<>();
    private final Map<Status, Float> childPercentage = new ConcurrentHashMap<>();
    private final Map<Status, Float> grandchildPercentage = new ConcurrentHashMap<>();
    private final Map<Status, Float> logPercentage = new ConcurrentHashMap<>();
}
