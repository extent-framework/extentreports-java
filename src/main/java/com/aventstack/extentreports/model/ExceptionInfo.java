package com.aventstack.extentreports.model;

import java.io.Serializable;

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
public class ExceptionInfo implements Serializable, BaseEntity {
    private static final long serialVersionUID = -8152865623044194249L;

    private String name;
    private Throwable exception;
    private String stackTrace;
}
