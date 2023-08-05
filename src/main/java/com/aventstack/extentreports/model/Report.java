package com.aventstack.extentreports.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.context.NamedAttributeContextManager;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString(includeFieldNames = true)
public class Report implements Serializable, BaseEntity {
    private static final long serialVersionUID = -8667496631392333349L;

    @Builder.Default
    private Date startTime = Calendar.getInstance().getTime();
    @Builder.Default
    private Date endTime = Calendar.getInstance().getTime();
    @Builder.Default
    private Status status = Status.PASS;
    private final transient ReportStats stats = new ReportStats();
    private final List<Test> testList = Collections.synchronizedList(new ArrayList<>());
    private final transient NamedAttributeContextManager<Author> authorCtx = new NamedAttributeContextManager<>();
    private final transient NamedAttributeContextManager<Category> categoryCtx = new NamedAttributeContextManager<>();
    private final transient NamedAttributeContextManager<Device> deviceCtx = new NamedAttributeContextManager<>();
    private final transient NamedAttributeContextManager<ExceptionInfo> exceptionInfoCtx = new NamedAttributeContextManager<>();
    private final List<String> logs = Collections.synchronizedList(new ArrayList<>());
    private final List<SystemEnvInfo> systemEnvInfo = new ArrayList<>();

    public final void refresh() {
        authorCtx.getSet().forEach(x -> x.refresh());
        categoryCtx.getSet().forEach(x -> x.refresh());
        deviceCtx.getSet().forEach(x -> x.refresh());
        exceptionInfoCtx.getSet().forEach(x -> x.refresh());
        synchronized (testList) {
            stats.update(testList);
            setEndTime(Calendar.getInstance().getTime());
        }
    }
    
    public void addTest(Test test) {
        testList.add(test);
    }
    
    public boolean removeTest(Test test) {
        synchronized (testList) {
            return removeTest(testList, test);
        }
    }

    private boolean removeTest(List<Test> testList, Test test) {
        boolean removed = testList.removeIf(x -> x.getId() == test.getId());
        if (!removed)
            testList.forEach(x -> removeTest(x.getChildren(), test));
        return removed;
    }
    
    public final void applyOverrideConf() {
        synchronized (testList) {
            Date min = testList.stream()
                    .map(t -> t.getStartTime())
                    .min(Date::compareTo)
                    .get();
            Date max = testList.stream()
                    .map(t -> t.getEndTime())
                    .max(Date::compareTo)
                    .get();
            setStartTime(min);
            setEndTime(max);
        }
    }

    public final boolean isBDD() {
        synchronized (testList) {
            return !testList.isEmpty() && testList.stream().allMatch(Test::isBDD);
        }
    }

    public final boolean anyTestHasStatus(Status status) {
        synchronized (testList) {
            return testList.stream()
                .anyMatch(x -> x.getStatus() == status);
        }
    }
   
    public Optional<Test> findTest(List<Test> list, String name) {
        synchronized (testList) {
            Optional<Test> test = list.stream().filter(x -> x.getName().equals(name)).findFirst();
            if (!test.isPresent())
                for (Test t : list)
                    return findTest(t.getChildren(), name);
            return test;
        }
    }

    public Optional<Test> findTest(final String name) {
        return findTest(testList, name);
    }
    
    public List<ExceptionInfo> aggregateExceptions(List<Test> testList) {
        synchronized (testList) {
            List<ExceptionInfo> list = new ArrayList<>();
            for (Test test : testList) {
                list.addAll(test.aggregateExceptions());
                if (!test.getChildren().isEmpty())
                    aggregateExceptions(test.getChildren());
            }
            return list;
        }
    }
	
    public final long timeTaken() {
        return endTime.getTime() - startTime.getTime();
    }

    public final Status getStatus() {
        synchronized (testList) {
            List<Status> list = testList
                .stream()
                .map(x -> x.getStatus())
                .collect(Collectors.toList());
            Status s = Status.max(list);
            if (s == Status.SKIP && anyTestHasStatus(Status.PASS))
                s = Status.PASS;
            return s;
        }
    }
}
