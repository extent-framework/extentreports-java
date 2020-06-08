package com.aventstack.extentreports.entity.service;

import java.util.ArrayList;
import java.util.List;

import org.testng.Assert;

import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.gherkin.entity.Scenario;
import com.aventstack.extentreports.model.Author;
import com.aventstack.extentreports.model.Category;
import com.aventstack.extentreports.model.Device;
import com.aventstack.extentreports.model.Log;
import com.aventstack.extentreports.model.ScreenCapture;
import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.model.service.LogService;
import com.aventstack.extentreports.model.service.TestService;

public class TestServiceTest {
    private static final String DESCRIPTION = "test.description";

    private Test getTest() {
        return TestService.createTest("Test");
    }

    @org.testng.annotations.Test(expectedExceptions = IllegalArgumentException.class)
    public void testWithNullName() {
        TestService.createTest(null);
    }

    @org.testng.annotations.Test(expectedExceptions = IllegalArgumentException.class)
    public void testWithEmptyName() {
        TestService.createTest("");
    }

    @org.testng.annotations.Test(expectedExceptions = IllegalArgumentException.class)
    public void testWithNullName2() {
        TestService.createTest(null, DESCRIPTION);
    }

    @org.testng.annotations.Test(expectedExceptions = IllegalArgumentException.class)
    public void testWithEmptyName2() {
        TestService.createTest("", DESCRIPTION);
    }

    @org.testng.annotations.Test
    public void testWithNullDescription() {
        Test test = TestService.createTest("Test", null);
        Assert.assertNull(test.getDescription());
    }

    @org.testng.annotations.Test
    public void testWithEmptyDescription() {
        Test test = TestService.createTest("Test", "");
        Assert.assertEquals(test.getDescription(), "");
    }

    @org.testng.annotations.Test
    public void testWithDescription() {
        Test test = TestService.createTest("Test", DESCRIPTION);
        Assert.assertEquals(test.getDescription(), DESCRIPTION);
    }

    @org.testng.annotations.Test
    public void testWithNullBddType() {
        Test test = TestService.createTest(null, "Test", DESCRIPTION);
        Assert.assertEquals(test.getBddType(), null);
    }

    @org.testng.annotations.Test
    public void testWithBddType() {
        Test test = TestService.createTest(Scenario.class, "Test", DESCRIPTION);
        Assert.assertEquals(test.getBddType(), Scenario.class);
    }

    @org.testng.annotations.Test
    public void testWithBddTypeGherkinKeyword() throws ClassNotFoundException {
        GherkinKeyword keyword = new GherkinKeyword("Scenario");
        Test test = TestService.createTest(keyword.getClazz(), "Test", DESCRIPTION);
        Assert.assertEquals(test.getBddType(), keyword.getClazz());
    }

    @org.testng.annotations.Test(expectedExceptions = IllegalArgumentException.class)
    public void addNodeToNullTest() {
        TestService.addNode(TestService.createTest("Node", DESCRIPTION), null);
    }

    @org.testng.annotations.Test(expectedExceptions = IllegalArgumentException.class)
    public void addNullNodeToTest() {
        TestService.addNode(null, TestService.createTest("Test", DESCRIPTION));
    }

    @org.testng.annotations.Test(expectedExceptions = IllegalArgumentException.class)
    public void addNullNodeToNullTest() {
        TestService.addNode(null, null);
    }

    @org.testng.annotations.Test
    public void addNodeToTest() {
        Test test = getTest();
        Test node = TestService.createTest("Node", "");
        TestService.addNode(node, test);
        Assert.assertEquals(test.getChildren().size(), 1);
        TestService.addNode(node, test);
        Assert.assertEquals(test.getChildren().size(), 2);
    }

    @org.testng.annotations.Test
    public void addNodeToTestLevel() {
        Test test = getTest();
        Test node = TestService.createTest("Node", "");
        TestService.addNode(node, test);
        Assert.assertEquals(test.getLevel().intValue(), 0);
        Assert.assertEquals(node.getLevel().intValue(), 1);
    }

    @org.testng.annotations.Test
    public void addNodeToTestLeaf() {
        Test test = getTest();
        Test node = TestService.createTest("Node", "");
        TestService.addNode(node, test);
        Assert.assertFalse(test.isLeaf());
        Assert.assertTrue(node.isLeaf());
    }

    @org.testng.annotations.Test(expectedExceptions = IllegalArgumentException.class)
    public void addNullLogToTest() {
        Test test = getTest();
        TestService.addLog(null, test);
    }

    @org.testng.annotations.Test(expectedExceptions = IllegalArgumentException.class)
    public void addLogToNullTest() {
        Log log = Log.builder().build();
        TestService.addLog(log, null);
    }

    @org.testng.annotations.Test
    public void addLogToTest() {
        Test test = getTest();
        Log log = Log.builder().build();
        TestService.addLog(log, test);
        Assert.assertEquals(log.getSeq().intValue(), 0);
        Assert.assertEquals(test.getLogs().size(), 1);
        Assert.assertEquals(test.getStatus(), Status.PASS);
        Assert.assertEquals(log.getStatus(), Status.PASS);
    }

    @org.testng.annotations.Test
    public void addSkipLogToTest() {
        Test test = getTest();
        Log log = Log.builder().status(Status.SKIP).build();
        TestService.addLog(log, test);
        Assert.assertEquals(test.getStatus(), Status.SKIP);
        Assert.assertEquals(log.getStatus(), Status.SKIP);
    }

    @org.testng.annotations.Test
    public void addFailLogToTest() {
        Test test = getTest();
        Log log = Log.builder().status(Status.FAIL).build();
        TestService.addLog(log, test);
        Assert.assertEquals(test.getStatus(), Status.FAIL);
        Assert.assertEquals(log.getStatus(), Status.FAIL);
    }

    @org.testng.annotations.Test
    public void testHasLog() {
        Test test = getTest();
        Assert.assertFalse(TestService.testHasLog(test));
        Log log = Log.builder().status(Status.FAIL).build();
        TestService.addLog(log, test);
        Assert.assertTrue(TestService.testHasLog(test));
    }

    @org.testng.annotations.Test
    public void isTestBDD() {
        Test test = getTest();
        Assert.assertFalse(TestService.isBDD(test));
        test.setBddType(Scenario.class);
        Assert.assertTrue(TestService.isBDD(test));
    }

    @org.testng.annotations.Test
    public void testHasChildren() {
        Test test = getTest();
        Assert.assertFalse(TestService.testHasChildren(test));
        TestService.addNode(TestService.createTest("Node", ""), test);
        Assert.assertTrue(TestService.testHasChildren(test));
    }

    @org.testng.annotations.Test
    public void testStatusWithoutLog() {
        Test test = getTest();
        Assert.assertEquals(TestService.getTestStatus(test), Status.PASS);
    }

    @org.testng.annotations.Test
    public void testStatusWithLog() {
        Test test = getTest();
        Assert.assertEquals(TestService.getTestStatus(test), Status.PASS);
        Log log = Log.builder().status(Status.FAIL).build();
        TestService.addLog(log, test);
        Assert.assertEquals(TestService.getTestStatus(test), Status.FAIL);
    }

    @org.testng.annotations.Test
    public void testStatusWithLogStatusChanged() {
        Test test = getTest();
        Assert.assertEquals(TestService.getTestStatus(test), Status.PASS);
        Log log = Log.builder().status(Status.SKIP).build();
        TestService.addLog(log, test);
        Assert.assertEquals(TestService.getTestStatus(test), Status.SKIP);
        log.setStatus(Status.FAIL);
        Assert.assertEquals(TestService.getTestStatus(test), Status.FAIL);
    }

    @org.testng.annotations.Test
    public void deleteTest() {
        Test test1 = TestService.createTest("Test1", "");
        Test test2 = TestService.createTest("Test2", "");
        List<Test> list = new ArrayList<>();
        list.add(test1);
        list.add(test2);
        Assert.assertEquals(list.size(), 2);
        TestService.deleteTest(list, test1);
        Assert.assertEquals(list.size(), 1);
        Assert.assertEquals(list.get(0).getName(), "Test2");
    }

    @org.testng.annotations.Test
    public void deleteNode() {
        Test test1 = TestService.createTest("Test1", "");
        Test test2 = TestService.createTest("Test2", "");
        Test child = TestService.createTest("Child", "");
        TestService.addNode(child, test1);
        List<Test> list = new ArrayList<>();
        list.add(test1);
        list.add(test2);
        Assert.assertEquals(list.size(), 2);
        Assert.assertEquals(list.get(0).getChildren().size(), 1);
        TestService.deleteTest(list, child);
        Assert.assertEquals(list.size(), 2);
        Assert.assertEquals(list.get(0).getChildren().size(), 0);
    }

    @org.testng.annotations.Test
    public void testHasAuthor() {
        Test test = getTest();
        Assert.assertFalse(TestService.testHasAuthor(test));
        test.getAuthorSet().add(new Author("x"));
        Assert.assertTrue(TestService.testHasAuthor(test));
    }

    @org.testng.annotations.Test
    public void testHasCategory() {
        Test test = getTest();
        Assert.assertFalse(TestService.testHasDevice(test));
        test.getCategorySet().add(new Category("x"));
        Assert.assertTrue(TestService.testHasCategory(test));
    }

    @org.testng.annotations.Test
    public void testHasDevice() {
        Test test = getTest();
        Assert.assertFalse(TestService.testHasDevice(test));
        test.getDeviceSet().add(new Device("x"));
        Assert.assertTrue(TestService.testHasDevice(test));
    }

    @org.testng.annotations.Test
    public void testHasAttributes() {
        Test test = getTest();
        Assert.assertFalse(TestService.testHasAttributes(test));
        test.getAuthorSet().add(new Author("x"));
        Assert.assertTrue(TestService.testHasAttributes(test));
        test = TestService.createTest("Test", "");
        test.getDeviceSet().add(new Device("x"));
        Assert.assertTrue(TestService.testHasAttributes(test));
        test = TestService.createTest("Test", "");
        test.getCategorySet().add(new Category("x"));
        Assert.assertTrue(TestService.testHasAttributes(test));
    }

    @org.testng.annotations.Test
    public void testFullName() {
        String[] name = new String[]{"Test", "Child", "Grandchild"};
        Test test = TestService.createTest(name[0], "");
        Test child = TestService.createTest(name[1], "");
        TestService.addNode(child, test);
        Test grandchild = TestService.createTest(name[2], "");
        TestService.addNode(grandchild, child);
        Assert.assertEquals(TestService.fullName(test), name[0]);
        Assert.assertEquals(TestService.fullName(child), name[0] + "." + name[1]);
        Assert.assertEquals(TestService.fullName(grandchild),
                name[0] + "." + name[1] + "." + name[2]);
    }

    @org.testng.annotations.Test
    public void testHasScreenCapture() {
        Test test = getTest();
        Assert.assertFalse(TestService.testHasScreenCapture(test));
        TestService.addMedia(test, ScreenCapture.builder().build());
        Assert.assertFalse(TestService.testHasScreenCapture(test));
        TestService.addMedia(test, ScreenCapture.builder().path("/img.png").build());
        Assert.assertTrue(TestService.testHasScreenCapture(test));
    }

    @org.testng.annotations.Test
    public void testHasScreenCaptureDeep() {
        Test test = getTest();
        Log log = Log.builder().status(Status.PASS).details("").build();
        LogService.addMedia(log, ScreenCapture.builder().path("/img.png").build());
        TestService.addLog(log, test);
        Assert.assertFalse(TestService.testHasScreenCapture(test));
        Assert.assertTrue(TestService.testHasScreenCapture(test, true));
    }

    @org.testng.annotations.Test
    public void computeTestStatusNoLog() {
        Test test = getTest();
        TestService.computeTestStatus(test);
        Assert.assertEquals(test.getStatus(), Status.PASS);
    }

    @org.testng.annotations.Test
    public void computeTestStatusSkipLog() {
        Test test = getTest();
        test.getLogs().add(Log.builder().status(Status.SKIP).build());
        TestService.computeTestStatus(test);
        Assert.assertEquals(test.getStatus(), Status.SKIP);
    }

    @org.testng.annotations.Test
    public void computeTestStatusSkipAndFailLog() {
        Test test = getTest();
        test.getLogs().add(Log.builder().status(Status.SKIP).build());
        test.getLogs().add(Log.builder().status(Status.FAIL).build());
        TestService.computeTestStatus(test);
        Assert.assertEquals(test.getStatus(), Status.FAIL);
    }

    @org.testng.annotations.Test
    public void computeTestStatusNode() {
        Test parent = getTest();
        Test node = getTest();
        TestService.addNode(node, parent);
        node.getLogs().add(Log.builder().status(Status.SKIP).build());
        TestService.computeTestStatus(parent);
        Assert.assertEquals(parent.getStatus(), Status.SKIP);
        Assert.assertEquals(node.getStatus(), Status.SKIP);
        node.getLogs().add(Log.builder().status(Status.FAIL).build());
        TestService.computeTestStatus(parent);
        Assert.assertEquals(parent.getStatus(), Status.FAIL);
        Assert.assertEquals(node.getStatus(), Status.FAIL);
    }

    @org.testng.annotations.Test
    public void ancestor() {
        Test parent = TestService.createTest("Ancestor");
        Test node = TestService.createTest("Node");
        Test child = TestService.createTest("Child");
        TestService.addNode(node, parent);
        TestService.addNode(child, node);
        Assert.assertEquals(TestService.getAncestor(parent), parent);
        Assert.assertEquals(TestService.getAncestor(child), parent);
        Assert.assertEquals(TestService.getAncestor(node), parent);
    }
}
