package com.aventstack.extentreports.reporter;

public class IntUtil {
    private IntUtil() {
    }

    public static boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}