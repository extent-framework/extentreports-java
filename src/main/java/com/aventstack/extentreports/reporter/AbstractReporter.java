package com.aventstack.extentreports.reporter;

import java.util.HashSet;
import java.util.Set;

import com.aventstack.extentreports.Status;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public abstract class AbstractReporter {
    private Set<Status> statusFilter = new HashSet<>();
}
