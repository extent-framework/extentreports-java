package com.aventstack.extentreports.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.context.NamedAttributeContextManager;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
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
    private final ReportStats stats = new ReportStats();
    private final List<Test> testList = Collections.synchronizedList(new ArrayList<>());
    private final NamedAttributeContextManager<Author> authorCtx = new NamedAttributeContextManager<>();
    private final NamedAttributeContextManager<Category> categoryCtx = new NamedAttributeContextManager<>();
    private final NamedAttributeContextManager<Device> deviceCtx = new NamedAttributeContextManager<>();
    private final NamedAttributeContextManager<ExceptionInfo> exceptionInfoCtx = new NamedAttributeContextManager<>();
    private final List<String> logs = Collections.synchronizedList(new ArrayList<>());
    private final List<SystemEnvInfo> systemEnvInfo = new ArrayList<>();
}
