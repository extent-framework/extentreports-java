package com.aventstack.extentreports.reporter;

import java.io.File;

public class FileUtil {
    private FileUtil() {
    }

    public static String getExtension(File f) {
        String name = f.getName();
        int i = name.lastIndexOf('.');
        if (i > 0)
            return name.substring(i + 1);
        return "";
    }

    public static String getExtension(String filePath) {
        return getExtension(new File(filePath));
    }
}
