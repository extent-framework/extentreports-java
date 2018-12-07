package com.aventstack.extentreports.viewdefs;

import java.util.EnumMap;
import java.util.Map;

import com.aventstack.extentreports.Status;

public class TWBSColor {

private static Map<Status, String> map = new EnumMap<>(Status.class);
    
    public void override(Status status, String color) {
        map.put(status, color);
    }
    
    public String getColor(Status status) {
        if (map.containsKey(status))
            return map.get(status);

        String s = status.toString().toLowerCase();

        switch (s) {
            case "fail":
            case "fatal":
                return "b-danger";
            case "error":
            case "warning":
        		return "b-warning";
            case "skip":
                return "b-skip";
            case "pass":
                return "b-success";
            case "info":
                return "b-primary";
            case "debug":
            default:
                return "";
        }
    }
    
    public String getBgColor(Status status) {
        if (map.containsKey(status))
            return map.get(status);

        String s = status.toString().toLowerCase();

        switch (s) {
            case "fail":
            case "fatal":
                return "danger";
            case "error":
            case "warning":
        		return "warning";
            case "skip":
                return "skip";
            case "pass":
                return "success";
            case "info":
                return "primary";
            case "debug":
            default:
                return "";
        }
    }
}
