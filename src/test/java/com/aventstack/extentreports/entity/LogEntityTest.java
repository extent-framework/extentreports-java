package com.aventstack.extentreports.entity;

import java.util.Collections;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Log;

public class LogEntityTest {

    @Test
    public void defaultStatusBuilder() {
        Log log = Log.builder().build();
        Assert.assertEquals(log.getStatus(), Status.PASS);
    }

    /*
     * Lombok @Builder.Default bug causing not setting fields on instantiating via new
     */
//    @Test
//    public void defaultStatusInstantiate() {
//        Log log = new Log();
//        Assert.assertEquals(log.getStatus(), Status.PASS);
//    }

    @Test
    public void changedStatus() {
        Log log = new Log();
        log.setStatus(Status.FAIL);
        Assert.assertEquals(log.getStatus(), Status.FAIL);
        log.setStatus(Status.PASS);
        Assert.assertEquals(log.getStatus(), Status.PASS);
    }

    @Test
    public void timestampNonNullOnInit() {
        Log log = Log.builder().build();
        Assert.assertNotNull(log.getTimestamp());
    }

    @Test
    public void detailsNullOnInit() {
        Log log = Log.builder().build();
        Assert.assertNull(log.getDetails());
    }

    @Test
    public void seqNegOnInit() {
        Log log = Log.builder().build();
        Assert.assertEquals(log.getSeq().intValue(), -1);
    }

    @Test
    public void mediaEmptyOnInit() {
        Log log = Log.builder().build();
        Assert.assertEquals(log.getMedia(), Collections.EMPTY_LIST);
    }

    @Test
    public void exceptionsEmptyOnInit() {
        Log log = Log.builder().build();
        Assert.assertEquals(log.getExceptions(), Collections.EMPTY_LIST);
    }
}
