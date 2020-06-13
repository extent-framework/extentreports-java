package com.aventstack.extentreports.entity;

import java.lang.reflect.Method;

import org.testng.Assert;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.model.service.TestService;

public class TestEntityTest {

	@org.testng.annotations.Test
	public void logSeqIncrements(Method method) {
		Test test = TestService.createTest(method.getName());
		Log log = Log.builder().build();
		TestService.addLog(log, test);
		Assert.assertEquals(log.getSeq().intValue(), 0);
		TestService.addLog(log, test);
		Assert.assertEquals(log.getSeq().intValue(), 1);
		TestService.addLog(log, test);
		Assert.assertEquals(log.getSeq().intValue(), 2);
	}

	@org.testng.annotations.Test
	public void testEntities(Method method) {
		Test test = TestService.createTest(method.getName());
		Assert.assertEquals(test.getAuthorSet().size(), 0);
		Assert.assertEquals(test.getDeviceSet().size(), 0);
		Assert.assertEquals(test.getCategorySet().size(), 0);
		Assert.assertEquals(test.getChildren().size(), 0);
		Assert.assertTrue(test.isLeaf());
		Assert.assertEquals(test.getLevel().intValue(), 0);
		Assert.assertEquals(test.getStatus(), Status.PASS);
		Assert.assertNull(test.getDescription());
	}

}
