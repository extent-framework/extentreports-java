package com.aventstack.extentreports.viewdefs;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class ViewDefsIconOverrideTest {

    @Test
    public void testTWBSColorPassReturnsValue() {
        Icon icon = new Icon();
        String ico = "close";
        icon.override(Status.PASS, ico);
        Assert.assertTrue(icon.getIcon(Status.PASS).equals(ico));
    }
    
}
