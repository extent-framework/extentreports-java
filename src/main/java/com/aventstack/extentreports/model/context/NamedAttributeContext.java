package com.aventstack.extentreports.model.context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.NamedAttribute;
import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.model.service.TestService;
import com.aventstack.extentreports.util.Assert;

import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class NamedAttributeContext<T extends NamedAttribute> implements Serializable {
    private static final long serialVersionUID = -2671203343283101908L;

    private final List<Test> testList = Collections.synchronizedList(new ArrayList<>());
    private T attr;
    private Map<Status, Integer> statusDist = new HashMap<>();
    private int size;

    public NamedAttributeContext(T attribute, Test test) {
        this.attr = attribute;
        addTest(test);
    }

    public void addTest(Test test) {
    	Assert.notNull(test, "Test must not be null");
        testList.add(test);
        refresh(test);
    }

    public void removeTest(Test test) {
        Assert.notNull(test, "Test must not be null");
        TestService.deleteTest(testList, test);
        refresh();
    }

    private synchronized void refresh(Test test) {
        statusDist.merge(test.getStatus(), 1, Integer::sum);
    }

    public void refresh() {
        statusDist.clear();
        testList.forEach(this::refresh);
        size = statusDist.values().stream().reduce(0, Integer::sum);
    }

    public Integer size() {
        return size;
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
