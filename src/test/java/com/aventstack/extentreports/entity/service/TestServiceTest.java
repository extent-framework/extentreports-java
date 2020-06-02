package com.aventstack.extentreports.entity.service;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.entity.Scenario;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.model.service.TestService;

public class TestServiceTest {
    private static final String DESCRIPTION = "test.description";

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testWithNullName() {
        TestService.createTest(null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testWithEmptyName() {
        TestService.createTest("");
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testWithNullName2() {
        TestService.createTest(null, DESCRIPTION);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void testWithEmptyName2() {
        TestService.createTest("", DESCRIPTION);
    }

    @Test
    public void testWithNullDescription() {
        com.aventstack.extentreports.model.Test test = TestService.createTest("Test", null);
        Assert.assertNull(test.getDescription());
    }

    @Test
    public void testWithEmptyDescription() {
        com.aventstack.extentreports.model.Test test = TestService.createTest("Test", "");
        Assert.assertEquals(test.getDescription(), "");
    }

    @Test
    public void testWithDescription() {
        com.aventstack.extentreports.model.Test test = TestService.createTest("Test", DESCRIPTION);
        Assert.assertEquals(test.getDescription(), DESCRIPTION);
    }

    @Test
    public void testWithNullBddType() {
        com.aventstack.extentreports.model.Test test = TestService.createTest(null, "Test", DESCRIPTION);
        Assert.assertEquals(test.getBddType(), null);
    }

    @Test
    public void testWithBddType() {
        com.aventstack.extentreports.model.Test test = TestService.createTest(Scenario.class, "Test", DESCRIPTION);
        Assert.assertEquals(test.getBddType(), Scenario.class);
    }

    @Test
    public void testWithBddTypeGherkinKeyword() throws ClassNotFoundException {
        GherkinKeyword keyword = new GherkinKeyword("Scenario");
        com.aventstack.extentreports.model.Test test = TestService.createTest(keyword.getClazz(), "Test", DESCRIPTION);
        Assert.assertEquals(test.getBddType(), keyword.getClazz());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addNodeToNullTest() {
        TestService.addNode(TestService.createTest("Node", DESCRIPTION), null);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addNullNodeToTest() {
        TestService.addNode(null, TestService.createTest("Test", DESCRIPTION));
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addNullNodeToNullTest() {
        TestService.addNode(null, null);
    }

    @Test
    public void addNodeToTest() {
        com.aventstack.extentreports.model.Test test = TestService.createTest("Test", "");
        com.aventstack.extentreports.model.Test node = TestService.createTest("Node", "");
        TestService.addNode(node, test);
        Assert.assertEquals(test.getChildren().size(), 1);
        TestService.addNode(node, test);
        Assert.assertEquals(test.getChildren().size(), 2);
    }

    @Test
    public void addNodeToTestLevel() {
        com.aventstack.extentreports.model.Test test = TestService.createTest("Test", "");
        com.aventstack.extentreports.model.Test node = TestService.createTest("Node", "");
        TestService.addNode(node, test);
        Assert.assertEquals(test.getLevel().intValue(), 0);
        Assert.assertEquals(node.getLevel().intValue(), 1);
    }

    @Test
    public void addNodeToTestLeaf() {
        com.aventstack.extentreports.model.Test test = TestService.createTest("Test", "");
        com.aventstack.extentreports.model.Test node = TestService.createTest("Node", "");
        TestService.addNode(node, test);
        Assert.assertFalse(test.isLeaf());
        Assert.assertTrue(node.isLeaf());
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addNullLogToTest() {
        com.aventstack.extentreports.model.Test test = TestService.createTest("Test", "");
        TestService.addLog(null, test);
    }

    @Test(expectedExceptions = IllegalArgumentException.class)
    public void addLogToNullTest() {
        Log log = Log.builder().build();
        TestService.addLog(log, null);
    }

    @Test
    public void addLogToTest() {
        com.aventstack.extentreports.model.Test test = TestService.createTest("Test", "");
        Log log = Log.builder().build();
        TestService.addLog(log, test);
        Assert.assertEquals(log.getSeq().intValue(), 0);
        Assert.assertEquals(test.getLogs().size(), 1);
        Assert.assertEquals(test.getStatus(), Status.PASS);
        Assert.assertEquals(log.getStatus(), Status.PASS);
    }

    @Test
    public void addSkipLogToTest() {
        com.aventstack.extentreports.model.Test test = TestService.createTest("Test", "");
        Log log = Log.builder().status(Status.SKIP).build();
        TestService.addLog(log, test);
        Assert.assertEquals(test.getStatus(), Status.SKIP);
        Assert.assertEquals(log.getStatus(), Status.SKIP);
    }

    @Test
    public void addFailLogToTest() {
        com.aventstack.extentreports.model.Test test = TestService.createTest("Test", "");
        Log log = Log.builder().status(Status.FAIL).build();
        TestService.addLog(log, test);
        Assert.assertEquals(test.getStatus(), Status.FAIL);
        Assert.assertEquals(log.getStatus(), Status.FAIL);
    }

    @Test
    public void testHasLog() {
        com.aventstack.extentreports.model.Test test = TestService.createTest("Test", "");
        Assert.assertFalse(TestService.testHasLog(test));
        Log log = Log.builder().status(Status.FAIL).build();
        TestService.addLog(log, test);
        Assert.assertTrue(TestService.testHasLog(test));
    }

    @Test
    public void isTestBDD() {
        com.aventstack.extentreports.model.Test test = TestService.createTest("Test", "");
        Assert.assertFalse(TestService.isBDD(test));
        test.setBddType(Scenario.class);
        Assert.assertTrue(TestService.isBDD(test));
    }

    @Test
    public void testHasChildren() {
        com.aventstack.extentreports.model.Test test = TestService.createTest("Test", "");
        Assert.assertFalse(TestService.testHasChildren(test));
        TestService.addNode(TestService.createTest("Node", ""), test);
        Assert.assertTrue(TestService.testHasChildren(test));
    }

    @Test
    public void testStatusWithoutLog() {
        com.aventstack.extentreports.model.Test test = TestService.createTest("Test", "");
        Assert.assertEquals(TestService.getTestStatus(test), Status.PASS);
    }

    @Test
    public void testStatusWithLog() {
        com.aventstack.extentreports.model.Test test = TestService.createTest("Test", "");
        Assert.assertEquals(TestService.getTestStatus(test), Status.PASS);
        Log log = Log.builder().status(Status.FAIL).build();
        TestService.addLog(log, test);
        Assert.assertEquals(TestService.getTestStatus(test), Status.FAIL);
    }

    @Test
    public void testStatusWithLogStatusChanged() {
        com.aventstack.extentreports.model.Test test = TestService.createTest("Test", "");
        Assert.assertEquals(TestService.getTestStatus(test), Status.PASS);
        Log log = Log.builder().status(Status.SKIP).build();
        TestService.addLog(log, test);
        Assert.assertEquals(TestService.getTestStatus(test), Status.SKIP);
        log.setStatus(Status.FAIL);
        Assert.assertEquals(TestService.getTestStatus(test), Status.FAIL);
    }
}
