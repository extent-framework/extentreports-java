package com.aventstack.extentreports.model.context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.NamedAttribute;
import com.aventstack.extentreports.model.Test;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class NamedAttributeContext<T extends NamedAttribute> implements Serializable {
    private static final long serialVersionUID = -2671203343283101908L;

    private final List<Test> testList = Collections.synchronizedList(new ArrayList<>());
    private T attr;
    private Map<Status, Integer> statusDist = new HashMap<>();

    public NamedAttributeContext(T attribute, Test test) {
        this.attr = attribute;
        addTest(test);
    }

    public void addTest(Test test) {
        if (test == null)
            throw new IllegalArgumentException("Test cannot be null");
        testList.add(test);
        refresh(test);
    }

    private synchronized void refresh(Test test) {
        statusDist.merge(test.getStatus(), 1, Integer::sum);
    }

    public void refresh() {
        statusDist.clear();
        testList.forEach(this::refresh);
    }

    public Integer size() {
        return statusDist.values().stream().reduce(0, Integer::sum);
    }

    public Integer getPassed() {
        return get(Status.PASS);
    }

    private Integer get(Status s) {
        return statusDist.get(s) == null ? 0 : statusDist.get(s);
    }

    public Integer getFailed() {
        return get(Status.FAIL);
    }

    public Integer getSkipped() {
        return get(Status.SKIP);
    }

    public Integer getOthers() {
        return get(Status.WARNING);
    }
}
