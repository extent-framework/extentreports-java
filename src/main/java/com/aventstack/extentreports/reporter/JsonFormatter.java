package com.aventstack.extentreports.reporter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.aventstack.extentreports.gson.GsonExtentTypeAdapterBuilder;
import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.observer.ReportObserver;
import com.aventstack.extentreports.observer.entity.ReportEntity;
import com.google.gson.Gson;

import io.reactivex.rxjava3.core.Observer;
import io.reactivex.rxjava3.disposables.Disposable;

public class JsonFormatter extends AbstractFileReporter implements ReporterConfigurable, ReportObserver<ReportEntity> {
    private static final String FILE_NAME = "extent.json";

    public JsonFormatter(File file) {
        super(file);
    }

    public JsonFormatter(String filePath) {
        super(new File(filePath));
    }

    @Override
    public Observer<ReportEntity> getReportObserver() {
        return new Observer<ReportEntity>() {
            @Override
            public void onSubscribe(Disposable d) {
            }

            @Override
            public void onNext(ReportEntity value) {
                flush(value);
            }

            @Override
            public void onError(Throwable e) {
            }

            @Override
            public void onComplete() {
            }
        };
    }

    private void flush(ReportEntity value) {
        Gson gson = GsonExtentTypeAdapterBuilder.builder()
                .withGsonTypeAdapterFactory()
                .build();
        final String filePath = getFileNameAsExt(FILE_NAME, new String[]{".json"});
        try (FileWriter writer = new FileWriter(filePath)) {
            List<Test> list = value.getReport().getTestList();
            gson.toJson(list, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void loadJSONConfig(File jsonFile) {
    }

    @Override
    public void loadJSONConfig(String jsonString) {

    }

    @Override
    public void loadXMLConfig(File xmlFile) {
    }

    @Override
    public void loadXMLConfig(String xmlFile) {
    }
}
