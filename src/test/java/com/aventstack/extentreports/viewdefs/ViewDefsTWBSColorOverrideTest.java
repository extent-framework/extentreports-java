package com.aventstack.extentreports.viewdefs;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class ViewDefsTWBSColorOverrideTest {

    @Test
    public void testTWBSColorPassReturnsValue() {
        TWBSColor twbs = new TWBSColor();
        String color = "green";
        twbs.override(Status.PASS, color);
        Assert.assertTrue(twbs.getColor(Status.PASS).equals(color));
    }
    
}
