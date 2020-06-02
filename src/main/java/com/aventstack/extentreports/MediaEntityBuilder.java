package com.aventstack.extentreports;

import java.io.IOException;

import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.model.ScreenCapture;

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

    public static MediaEntityBuilder createScreenCaptureFromPath(String path, String title) throws IOException {
        if (path == null || path.isEmpty()) {
            throw new IllegalArgumentException("ScreenCapture path cannot be null or empty");
        }
        media.set(ScreenCapture.builder().path(path).title(title).build());
        return getInstance();
    }

    public static MediaEntityBuilder createScreenCaptureFromPath(String path) throws IOException {
        return createScreenCaptureFromPath(path, null);
    }

    public static MediaEntityBuilder createScreenCaptureFromBase64String(String base64, String title)
            throws IOException {
        if (base64 == null || base64.trim().equals("")) {
            throw new IllegalArgumentException("Base64 string cannot be null or empty");
        }
        media.set(ScreenCapture.builder().base64(base64).title(title).build());
        return getInstance();
    }

    public static MediaEntityBuilder createScreenCaptureFromBase64String(String base64) throws IOException {
        if (base64 == null || base64.trim().equals("")) {
            throw new IllegalArgumentException("Base64 string cannot be null or empty");
        }
        return createScreenCaptureFromBase64String(base64, null);
    }

}