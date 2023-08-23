package com.aventstack.extentreports.model.service;

import java.util.Calendar;
import java.util.List;

import com.aventstack.extentreports.gherkin.model.IGherkinFormatterModel;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.model.ScreenCapture;
import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.model.Video;
import com.aventstack.extentreports.util.Assert;

public class TestService {
    public static Boolean testHasScreenCapture(Test test, Boolean deep) {
        if (deep) {
            Boolean hasScreenCapture = (!test.getMedia().isEmpty() && test.hasScreenCapture())
                    || test.getLogs().stream().anyMatch(Log::hasScreenCapture);
            if (!hasScreenCapture)
                hasScreenCapture = test.getChildren().stream().anyMatch(x -> testHasScreenCapture(x, deep));
            return hasScreenCapture;
        }
        return test.hasScreenCapture();
    }
    
    public static Boolean testHasVideo(Test test, Boolean deep) {
        if (deep) {
            Boolean hasVideo = (!test.getMedia().isEmpty() && test.hasVideo())
                    || test.getLogs().stream().anyMatch(Log::hasVideo);
            if (!hasVideo)
            	hasVideo = test.getChildren().stream().anyMatch(x -> testHasVideo(x, deep));
            return hasVideo;
        }
        return test.hasVideo();
    }
    
    public static Boolean testHasMedia(Test test, Boolean deep) {
        return testHasScreenCapture(test,  deep) || testHasVideo(test,  deep);
    }
    

	public static boolean isScreenCapture(Media m) {
		return m instanceof ScreenCapture;
	}

	public static boolean isVideo(Media m) {
		return m instanceof Video;
	}
	
	public static String videoSrc(Media m) {
		if (MediaService.isVideoBase64(m))
			return MediaService.getVideoBase64(m);
		else if (m.getResolvedPath() != null && !m.getResolvedPath().isEmpty())
			return m.getResolvedPath();
		else if (m.getPath() != null && !m.getPath().isEmpty())
			return m.getPath();
		return "";
	}

    public static Test createTest(Class<? extends IGherkinFormatterModel> type, String name, String description) {
    	Assert.notEmpty(name, "Test name must not be null or empty");
        return Test.builder()
                .bddType(type)
                .name(name)
                .description(description)
                .endTime(Calendar.getInstance().getTime()).build();
    }

    public static Test createTest(String name, String description) {
        return createTest(null, name, description);
    }

    public static Test createTest(String name) {
        return createTest(name, null);
    }
    
    public static boolean deleteTest(List<Test> list, Test test) {
        boolean removed = list.removeIf(x -> x.getId() == test.getId());
        if (!removed)
            list.forEach(x -> deleteTest(x.getChildren(), test));
        return removed;
    }
}
