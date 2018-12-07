package com.aventstack.extentreports.viewdefs;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;

public class ViewDefsTWBSColorTest {

    private TWBSColor twbs = new TWBSColor();

    @Test
    public void testTWBSColorPassReturnsValue() {
        Assert.assertTrue(twbs.getColor(Status.PASS) != null && !twbs.getColor(Status.PASS).isEmpty());
    }
    
    @Test
    public void testTWBSColorFailReturnsValue() {
        Assert.assertTrue(twbs.getColor(Status.FAIL) != null && !twbs.getColor(Status.FAIL).isEmpty());
    }
    
    @Test
    public void testTWBSColorFatalReturnsValue() {
        Assert.assertTrue(twbs.getColor(Status.FATAL) != null && !twbs.getColor(Status.FATAL).isEmpty());
    }
    
    @Test
    public void testTWBSColorErrorReturnsValue() {
        Assert.assertTrue(twbs.getColor(Status.ERROR) != null && !twbs.getColor(Status.ERROR).isEmpty());
    }
    
    @Test
    public void testTWBSColorSkipReturnsValue() {
        Assert.assertTrue(twbs.getColor(Status.SKIP) != null && !twbs.getColor(Status.SKIP).isEmpty());
    }
    
    @Test
    public void testTWBSColorInfoReturnsValue() {
        Assert.assertTrue(twbs.getColor(Status.INFO) != null && !twbs.getColor(Status.INFO).isEmpty());
    }
    
    @Test
    public void testTWBSColorDebugReturnsEmpty() {
        Assert.assertTrue(twbs.getColor(Status.DEBUG) != null && twbs.getColor(Status.DEBUG).isEmpty());
    }
    
    @Test
    public void testTWBSColorWarningReturnsValue() {
        Assert.assertTrue(twbs.getColor(Status.WARNING) != null && !twbs.getColor(Status.WARNING).isEmpty());
    }
    
    @Test
    public void testTWBSBgColorPassReturnsValue() {
        Assert.assertTrue(twbs.getBgColor(Status.PASS) != null && !twbs.getBgColor(Status.PASS).isEmpty());
    }
    
    @Test
    public void testTWBSBgColorFailReturnsValue() {
        Assert.assertTrue(twbs.getBgColor(Status.FAIL) != null && !twbs.getBgColor(Status.FAIL).isEmpty());
    }
    
    @Test
    public void testTWBSBgColorFatalReturnsValue() {
        Assert.assertTrue(twbs.getBgColor(Status.FATAL) != null && !twbs.getBgColor(Status.FATAL).isEmpty());
    }
    
    @Test
    public void testTWBSBgColorErrorReturnsValue() {
        Assert.assertTrue(twbs.getBgColor(Status.ERROR) != null && !twbs.getBgColor(Status.ERROR).isEmpty());
    }
    
    @Test
    public void testTWBSBgColorSkipReturnsValue() {
        Assert.assertTrue(twbs.getBgColor(Status.SKIP) != null && !twbs.getBgColor(Status.SKIP).isEmpty());
    }
    
    @Test
    public void testTWBSBgColorInfoReturnsValue() {
        Assert.assertTrue(twbs.getBgColor(Status.INFO) != null && !twbs.getBgColor(Status.INFO).isEmpty());
    }
    
    @Test
    public void testTWBSBgColorDebugReturnsEmpty() {
        Assert.assertTrue(twbs.getBgColor(Status.DEBUG) != null && twbs.getBgColor(Status.DEBUG).isEmpty());
    }
    
    @Test
    public void testTWBSBgColorWarningReturnsValue() {
        Assert.assertTrue(twbs.getBgColor(Status.WARNING) != null && !twbs.getBgColor(Status.WARNING).isEmpty());
    }
    
}
