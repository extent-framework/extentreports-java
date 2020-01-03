package com.aventstack.extentreports.utils;

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