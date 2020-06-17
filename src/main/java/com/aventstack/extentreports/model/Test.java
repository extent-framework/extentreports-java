package com.aventstack.extentreports.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.entity.IGherkinFormatterModel;

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
public final class Test implements RunResult, Serializable, BaseEntity {
    private static final long serialVersionUID = -4896520724677957845L;
    private static final AtomicInteger atomicInt = new AtomicInteger(0);

    private final transient Integer id = atomicInt.incrementAndGet();
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
    private final List<Test> children = Collections.synchronizedList(new ArrayList<>());
    private final List<Log> logs = Collections.synchronizedList(new ArrayList<>());
    private final List<Media> media = Collections.synchronizedList(new ArrayList<>());
    private final List<ExceptionInfo> exceptions = new ArrayList<>();
    private final Set<Author> authorSet = ConcurrentHashMap.newKeySet();
    private final Set<Category> categorySet = ConcurrentHashMap.newKeySet();
    private final Set<Device> deviceSet = ConcurrentHashMap.newKeySet();
    private final List<Log> generatedLog = Collections.synchronizedList(new ArrayList<>());
}
