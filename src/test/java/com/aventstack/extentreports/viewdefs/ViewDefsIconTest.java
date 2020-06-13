package com.aventstack.extentreports.viewdefs;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class ViewDefsIconTest {

    private Icon icon = new Icon();

    @Test
    public void testiconColorPassReturnsValue() {
        Assert.assertTrue(icon.getIcon(Status.PASS) != null && !icon.getIcon(Status.PASS).isEmpty());
    }
    
    @Test
    public void testiconColorFailReturnsValue() {
        Assert.assertTrue(icon.getIcon(Status.FAIL) != null && !icon.getIcon(Status.FAIL).isEmpty());
    }
    
    @Test
    public void testiconColorFatalReturnsValue() {
        Assert.assertTrue(icon.getIcon(Status.FATAL) != null && !icon.getIcon(Status.FATAL).isEmpty());
    }
    
    @Test
    public void testiconColorErrorReturnsValue() {
        Assert.assertTrue(icon.getIcon(Status.ERROR) != null && !icon.getIcon(Status.ERROR).isEmpty());
    }
    
    @Test
    public void testiconColorSkipReturnsValue() {
        Assert.assertTrue(icon.getIcon(Status.SKIP) != null && !icon.getIcon(Status.SKIP).isEmpty());
    }
    
    @Test
    public void testiconColorInfoReturnsValue() {
        Assert.assertTrue(icon.getIcon(Status.INFO) != null && !icon.getIcon(Status.INFO).isEmpty());
    }
    
    @Test
    public void testiconColorDebugReturnsValue() {
        Assert.assertTrue(icon.getIcon(Status.DEBUG) != null && !icon.getIcon(Status.DEBUG).isEmpty());
    }
    
    @Test
    public void testiconColorWarningReturnsValue() {
        Assert.assertTrue(icon.getIcon(Status.WARNING) != null && !icon.getIcon(Status.WARNING).isEmpty());
    }
    
}
