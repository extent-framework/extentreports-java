package com.aventstack.extentreports.model.service;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.ExceptionInfo;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.model.Test;

public class LogService {
    public static Log createLog(Test test, Status status, ExceptionInfo exceptionInfo, String details) {
        if (status == null)
            throw new IllegalArgumentException("Status must not be null");
        Log log = Log.builder()
                .status(status)
                .details(details)
                .build();
        if (exceptionInfo != null)
            log.getExceptions().add(exceptionInfo);
        TestService.addLog(log, test);
        return log;
    }

    public static Log createLog(Test test, Status status, ExceptionInfo exceptionInfo) {
        return createLog(test, status, exceptionInfo, null);
    }

    public static Log createLog(Test test, Status status, String details) {
        return createLog(test, status, null, details);
    }

    public static void addMedia(Log log, Media media) {
        if (media != null && (media.getPath() != null || media.getResolvedPath() != null))
            log.getMedia().add(media);
    }

    public static Boolean logHasMedia(Log log) {
        return log != null && !log.getMedia().isEmpty();
    }

}
