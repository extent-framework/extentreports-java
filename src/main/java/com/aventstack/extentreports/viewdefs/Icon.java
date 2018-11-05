package com.aventstack.extentreports.viewdefs;

import java.util.HashMap;

import com.aventstack.extentreports.Status;

public class Icon {

    private static HashMap<Status, String> map = new HashMap<>();
    
    public void override(Status status, String icon) {
        map.put(status, icon);
    }
    
    public String getIcon(Status status) {
        if (map.containsKey(status))
            return map.get(status);

        String s = status.toString().toLowerCase();

        switch (s) {
            case "fail":
            case "fatal":
                return "times";
            case "error":
                return "exclamation";
            case "warning":
                return "warning";
            case "skip":
                return "long-arrow-right";
            case "pass":
                return "check";
            case "debug":
                return "low_priority";
            case "info":
                return "info";
            default:
                return "help";
        }
    }

}
