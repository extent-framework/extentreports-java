package com.aventstack.extentreports.reporter.configuration.util;

import java.io.File;
import java.util.Arrays;

public class IOUtil {
    private IOUtil() {
    }

    public static void createDirectory(String path) {
        new File(path).mkdirs();
    }

    public static void createDirectory(String[] path) {
        Arrays.asList(path).forEach(IOUtil::createDirectory);
    }
}
