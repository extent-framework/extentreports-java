package com.aventstack.extentreports.append;

import java.io.File;
import java.io.IOException;
import java.util.List;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.model.ExceptionInfo;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.model.NamedAttribute;
import com.aventstack.extentreports.model.ScreenCapture;
import com.aventstack.extentreports.model.Test;

public class RawEntityConverter {

    private final ExtentReports extent;

    public RawEntityConverter(final ExtentReports extent) {
        this.extent = extent;
    }

    public void convertAndApply(final File jsonFile) throws IOException {
        if (!jsonFile.exists()) {
            return;
        }

        extent.setReportUsesManualConfiguration(true);
        final List<Test> tests = new JsonDeserializer(jsonFile).deserialize();

        for (final Test test : tests) {
            ExtentTest extentTest;
            try {
                if (test.isBDD()) {
                    final GherkinKeyword gk = new GherkinKeyword(test.getBddType().getSimpleName());
                    extentTest = extent.createTest(gk, test.getName(), test.getDescription());
                } else {
                    extentTest = extent.createTest(test.getName(), test.getDescription());
                }
                createDomain(test, extentTest);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    public void createDomain(final Test test, final ExtentTest extentTest) throws ClassNotFoundException {
        extentTest.getModel().setStartTime(test.getStartTime());
        extentTest.getModel().setEndTime(test.getEndTime());
        constructTestMedia(test, extentTest);

        // create events
        for (Log log : test.getLogs()) {
            if (log.hasException() || log.hasMedia()) {
                constructLog(log, extentTest, log.getException());
            } else {
                extentTest.log(log.getStatus(), log.getDetails());
            }
        }

        // assign attributes
        test.getAuthorSet().stream().map(NamedAttribute::getName).forEach(extentTest::assignAuthor);
        test.getCategorySet().stream().map(NamedAttribute::getName).forEach(extentTest::assignCategory);
        test.getDeviceSet().stream().map(NamedAttribute::getName).forEach(extentTest::assignDevice);

        // handle nodes
        for (Test node : test.getChildren()) {
            ExtentTest extentNode;
            if (!node.isBDD()) {
                extentNode = extentTest.createNode(node.getName(), node.getDescription());
            } else {
                GherkinKeyword gk = new GherkinKeyword(node.getBddType().getSimpleName());
                extentNode = extentTest.createNode(gk, node.getName(), node.getDescription());
            }
            constructTestMedia(node, extentNode);
            createDomain(node, extentNode);
        }
    }

    private void constructLog(final Log log, final ExtentTest extentTest, final ExceptionInfo ex) {
        final Media m = log.getMedia();

        if (m != null) {
            final Throwable t = ex != null && ex.getException() != null ? ex.getException() : null;

            if (m.getPath() != null) {
                extentTest.log(log.getStatus(), log.getDetails(), t,
                        MediaEntityBuilder.createScreenCaptureFromPath(m.getPath()).build());
            }
            if (((ScreenCapture) m).getBase64() != null) {
                extentTest.log(log.getStatus(), log.getDetails(), t,
                        MediaEntityBuilder.createScreenCaptureFromBase64String(((ScreenCapture) m).getBase64()).build());
            }
        }

        if (ex != null) {
            if (!extentTest.getModel().hasLog()) {
                extentTest.log(log.getStatus(), log.getDetails());
            }

            final List<Log> logs = extentTest.getModel().getLogs();
            final Log lastLog = logs.get(logs.size() - 1);
            lastLog.setException(ex);
            extentTest.getModel().getExceptions().add(ex);
        }
    }

    private void constructTestMedia(final Test test, final ExtentTest extentTest) {
        if (test.getMedia() != null) {
            for (Media m : test.getMedia()) {
                if (m.getPath() != null) {
                    extentTest.addScreenCaptureFromPath(m.getPath());
                } else if (m instanceof ScreenCapture) {
                    extentTest.addScreenCaptureFromBase64String(((ScreenCapture) m).getBase64());
                }
            }
        }
    }

}
