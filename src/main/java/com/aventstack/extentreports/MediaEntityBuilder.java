package com.aventstack.extentreports;

import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.model.ScreenCapture;
import com.aventstack.extentreports.model.Video;
import com.aventstack.extentreports.util.Assert;

/**
 * Media builder for base64 and binary screenshots
 *
 */
public class MediaEntityBuilder {
    
    private static ThreadLocal<Media> media = new ThreadLocal<>();

    private static class MediaBuilderInstance {
        static final MediaEntityBuilder INSTANCE = new MediaEntityBuilder();

        private MediaBuilderInstance() {
        }
    }

    private MediaEntityBuilder() {
    }

    private static synchronized MediaEntityBuilder getInstance() {
        return MediaBuilderInstance.INSTANCE;
    }

    public Media build() {
        return media.get();
    }

    public static MediaEntityBuilder createScreenCaptureFromPath(String path, String title) {
    	Assert.notEmpty(path, "ScreenCapture path must not be null or empty");
        media.set(ScreenCapture.builder().path(path).title(title).build());
        return getInstance();
    }

    public static MediaEntityBuilder createScreenCaptureFromPath(String path) {
        return createScreenCaptureFromPath(path, null);
    }

    public static MediaEntityBuilder createScreenCaptureFromBase64String(String base64, String title) {
    	Assert.notEmpty(base64, "ScreenCapture's base64 string must not be null or empty");
        if (!base64.startsWith("data:"))
            base64 = ScreenCapture.BASE64_ENCODED + base64;
        media.set(ScreenCapture.builder().base64(base64).title(title).build());
        return getInstance();
    }

    public static MediaEntityBuilder createScreenCaptureFromBase64String(String base64) {
        return createScreenCaptureFromBase64String(base64, null);
    }
    
    public static MediaEntityBuilder createVideoFromPath(String path, String title) {
    	Assert.notEmpty(path, "Video path must not be null or empty");
        media.set(Video.builder().path(path).title(title).build());
        return getInstance();
    }
    
    public static MediaEntityBuilder createVideoFromPath(String path) {
        return createVideoFromPath(path, null);
    }

    public static MediaEntityBuilder createVideoFromBase64String(String base64, String title) {
    	Assert.notEmpty(base64, "Video's base64 string must not be null or empty");
        if (!base64.startsWith("data:"))
            base64 = Video.BASE64_ENCODED + base64;
        media.set(Video.builder().base64(base64).title(title).build());
        return getInstance();
    }

    public static MediaEntityBuilder createVideoFromBase64String(String base64) {
        return createVideoFromBase64String(base64, null);
    }
}