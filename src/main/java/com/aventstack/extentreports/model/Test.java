package com.aventstack.extentreports.model;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.model.IGherkinFormatterModel;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.gherkin.model.ScenarioOutline;
import com.aventstack.extentreports.util.Assert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public final class Test implements RunResult, Serializable, BaseEntity, MetaDataStorable {
    private static final long serialVersionUID = -4896520724677957845L;
    private static final AtomicInteger atomicInt = new AtomicInteger(0);

    private final transient int id = atomicInt.incrementAndGet();
    private final transient StatusDeterminator determinator = new StatusDeterminator();
    @Builder.Default
    private boolean useNaturalConf = true;
    @Builder.Default
    private Date startTime = Calendar.getInstance().getTime();
    @Builder.Default
    private Date endTime = Calendar.getInstance().getTime();
    @Builder.Default
    private Status status = Status.PASS;
    @Builder.Default
    private Integer level = 0;
    @Builder.Default
    private boolean isLeaf = true;
    private String name;
    private String description;
    private transient Test parent;
    private Class<? extends IGherkinFormatterModel> bddType;
    private final Map<String, Object> infoMap = new HashMap<>();
    private final List<Test> children = Collections.synchronizedList(new ArrayList<>());
    private final List<Log> logs = Collections.synchronizedList(new ArrayList<>());
    private final List<Media> media = Collections.synchronizedList(new ArrayList<>());
    private final List<ExceptionInfo> exceptions = new ArrayList<>();
    private final Set<Author> authorSet = ConcurrentHashMap.newKeySet();
    private final Set<Category> categorySet = ConcurrentHashMap.newKeySet();
    private final Set<Device> deviceSet = ConcurrentHashMap.newKeySet();
    private final List<Log> generatedLog = Collections.synchronizedList(new ArrayList<>());

    public void addChild(Test child) {
        Assert.notNull(child, "Node must not be null");
        child.setLevel(level + 1);
        child.setParent(this);
        child.setLeaf(true);
        isLeaf = false;
        if (!child.isBDD() || child.isBDD() && child.bddType == Scenario.class) {
            child.authorSet.addAll(authorSet);
            child.categorySet.addAll(categorySet);
            child.deviceSet.addAll(deviceSet);
        }
        end(child.getStatus());
        children.add(child);
    }

    private void end(Status evtStatus) {
        setStatus(Status.max(status, evtStatus));
        if (useNaturalConf)
            propagateTime();
    }

    private void propagateTime() {
        setEndTime(Calendar.getInstance().getTime());
        if (parent != null)
            parent.propagateTime();
    }

    public void addLog(Log log) {
        addLog(log, logs);
    }

    public void addGeneratedLog(Log log) {
        addLog(log, generatedLog);
    }

    private void addLog(Log log, List<Log> list) {
        Assert.notNull(log, "Log must not be null");
        log.setSeq(list.size());
        list.add(log);
        end(log.getStatus());
        updateResult();
    }

    public boolean isBDD() {
        return getBddType() != null;
    }

    public boolean hasLog() {
        return !logs.isEmpty();
    }

    public boolean hasAnyLog() {
        return !logs.isEmpty() || !generatedLog.isEmpty();
    }

    public boolean hasChildren() {
        return !children.isEmpty();
    }

    public boolean hasAttributes() {
        return hasAuthor() || hasCategory() || hasDevice();
    }

    public boolean hasAuthor() {
        return !authorSet.isEmpty();
    }

    public boolean hasCategory() {
        return !categorySet.isEmpty();
    }

    public boolean hasDevice() {
        return !deviceSet.isEmpty();
    }

    public boolean hasException() {
        return !exceptions.isEmpty();
    }

    public String getFullName() {
        Test test = this;
        StringBuilder sb = new StringBuilder(test.getName());
        while (test.getParent() != null) {
            test = test.getParent();
            if (!test.isBDD() || test.getBddType() != ScenarioOutline.class)
                sb.insert(0, test.getName() + ".");
        }
        return sb.toString();
    }

    public void addMedia(Media m) {
        if (m != null
                && (m.getPath() != null || m.getResolvedPath() != null || ((ScreenCapture) m).getBase64() != null))
            media.add(m);
        end(status);
    }

    public boolean hasScreenCapture() {
        return !media.isEmpty() && media.stream().anyMatch(x -> x instanceof ScreenCapture);
    }

    public long timeTaken() {
        return endTime.getTime() - startTime.getTime();
    }

    /**
     * Time taken formatted as HH:mm:ss:SSS
     * 
     * Solution provided by @grasshopper7
     * https://github.com/extent-framework/extentreports-java/issues/247#issuecomment-679918613
     * 
     * @return A formatted time taken string as HH:mm:ss:SSS
     */
    public String timeTakenPretty() {
        Date date = new Date(timeTaken());
        DateFormat formatter = new SimpleDateFormat("HH:mm:ss:SSS");
        formatter.setTimeZone(TimeZone.getTimeZone("UTC"));
        String formatted = formatter.format(date);
        return formatted;
    }

    public List<ExceptionInfo> aggregateExceptions() {
        return logs.stream()
                .filter(x -> x.getException() != null)
                .map(x -> x.getException())
                .collect(Collectors.toList());
    }

    public Test getAncestor() {
        Test test = this;
        while (test.getParent() != null)
            test = test.getParent();
        return test;
    }

    public void updateResult() {
        determinator.computeTestStatus();
    }

    private class StatusDeterminator {
        public void computeTestStatus() {
            List<Test> leafList = getLeafNodes(Test.this);
            computeStatus(leafList);
        }

        private List<Test> getLeafNodes(Test test) {
            List<Test> tests = new ArrayList<>();
            if (test.isLeaf()) {
                tests.add(test);
            }
            for (Test t : test.getChildren()) {
                tests.addAll(getLeafNodes(t));
            }
            return tests;
        }

        private void computeStatus(List<Test> tests) {
            tests.forEach(this::computeStatus);
        }

        private void computeStatus(Test t) {
            Set<Status> set = new HashSet<>();
            set.add(t.getStatus());
            synchronized(t.getLogs()) {
                set.addAll(t.getLogs().stream()
                        .map(Log::getStatus)
                        .collect(Collectors.toSet()));
            }
            synchronized (t.getGeneratedLog()) {
                set.addAll(t.getGeneratedLog().stream()
                        .map(Log::getStatus)
                        .collect(Collectors.toSet()));
            }
            t.setStatus(Status.max(set));
            if (t.getParent() != null) {
                t.getParent().setStatus(Status.max(t.getStatus(), t.getParent().getStatus()));
                computeStatus(t.getParent());
            }
        }
    }
}