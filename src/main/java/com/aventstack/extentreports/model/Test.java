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
import java.util.Iterator;
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

    public final void addChild(Test child) {
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
            propogateTime();
    }

    private void propogateTime() {
        setEndTime(Calendar.getInstance().getTime());
        if (parent != null)
            parent.propogateTime();
    }

    public final void addLog(Log log) {
        addLog(log, logs);
    }

    public final void addGeneratedLog(Log log) {
        addLog(log, generatedLog);
    }

    private final void addLog(Log log, List<Log> list) {
        Assert.notNull(log, "Log must not be null");
        log.setSeq(list.size());
        list.add(log);
        end(log.getStatus());
        updateResult();
    }

    public final boolean isBDD() {
        return getBddType() != null;
    }

    public final boolean hasLog() {
        return !logs.isEmpty();
    }

    public final boolean hasAnyLog() {
        return !logs.isEmpty() || !generatedLog.isEmpty();
    }

    public final boolean hasChildren() {
        return !children.isEmpty();
    }

    public final boolean hasAttributes() {
        return hasAuthor() || hasCategory() || hasDevice();
    }

    public final boolean hasAuthor() {
        return !authorSet.isEmpty();
    }

    public final boolean hasCategory() {
        return !categorySet.isEmpty();
    }

    public final boolean hasDevice() {
        return !deviceSet.isEmpty();
    }

    public final boolean hasException() {
        return !exceptions.isEmpty();
    }

    public final String getFullName() {
        Test test = this;
        StringBuilder sb = new StringBuilder(test.getName());
        while (test.getParent() != null) {
            test = test.getParent();
            if (!test.isBDD() || test.getBddType() != ScenarioOutline.class)
                sb.insert(0, test.getName() + ".");
        }
        return sb.toString();
    }

    public final void addMedia(Media m) {
        if (m != null
                && (m.getPath() != null || m.getResolvedPath() != null || ((ScreenCapture) m).getBase64() != null))
            media.add(m);
        end(status);
    }

    public final boolean hasScreenCapture() {
        return !media.isEmpty() && media.stream().anyMatch(x -> x instanceof ScreenCapture);
    }

    public final long timeTaken() {
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
    public final String timeTakenPretty() {
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

    public final Test getAncestor() {
        Test test = this;
        while (test.getParent() != null)
            test = test.getParent();
        return test;
    }

    public final void updateResult() {
        determinator.computeTestStatus();
    }

    private class StatusDeterminator {
        public void computeTestStatus() {
            List<Test> leafList = getLeafList(Test.this);
            computeStatus(leafList);
        }

        private List<Test> getLeafList(Test test) {
            List<Test> testList = new ArrayList<>();
            if (test.isLeaf())
                testList.add(test);
            else
                for (Test t : test.getChildren())
                    if (t.isLeaf())
                        testList.add(t);
                    else
                        testList.addAll(getLeafList(t));
            return testList;
        }

        private void computeStatus(List<Test> testList) {
            testList.forEach(this::computeStatus);
        }

        private void computeStatus(Test t) {
            Set<Status> set = new HashSet<>();
            Iterator<Log> iter = new ArrayList<>(t.getLogs()).iterator();
            while (iter.hasNext())
                set.add(iter.next().getStatus());
            set.add(t.getStatus());
            t.setStatus(Status.max(set));
            if (t.getParent() != null) {
                t.getParent().setStatus(Status.max(t.getStatus(), t.getParent().getStatus()));
                computeStatus(t.getParent());
            }
        }
    }
}