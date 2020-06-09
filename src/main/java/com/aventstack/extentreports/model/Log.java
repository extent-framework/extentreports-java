package com.aventstack.extentreports.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.aventstack.extentreports.Status;

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
@ToString
public class Log implements RunResult, Serializable, BaseEntity {
    private static final long serialVersionUID = -3690764012141784427L;

    private final Date timestamp = Calendar.getInstance().getTime();
    @Builder.Default
    private Status status = Status.PASS;
    private String details;
    @Builder.Default
    private Integer seq = -1;
    private final List<ExceptionInfo> exceptions = new ArrayList<>();
    private final List<Media> media = new ArrayList<>();
    
    public Log(Integer seq) {
        this.seq = seq;
    }
}
