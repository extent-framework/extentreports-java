package com.aventstack.extentreports.model;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Base;
import com.aventstack.extentreports.ExtentTest;

public class TestModelTest extends Base {

    @Test
    public void testTestId(Method method) {
        ExtentTest t = extent.createTest(method.getName());
        Assert.assertTrue(t.getModel().getID()>=0);
    }
    
    @Test
    public void testHasNoChildrenViaModel(Method method) {
        ExtentTest t = extent.createTest(method.getName());
        Assert.assertFalse(t.getModel().hasChildren());
    }
    
    @Test
    public void testHasChildrenViaModel(Method method) {
        ExtentTest t = extent.createTest(method.getName());
        t.createNode(method.getName());
        Assert.assertTrue(t.getModel().hasChildren());
    }
    
    @Test
    public void testNodeIsChildNode(Method method) {
        ExtentTest t = extent.createTest(method.getName()).createNode(method.getName());
        Assert.assertTrue(t.getModel().isChildNode());
    }
    
    @Test
    public void testNodeContextIsEmpty(Method method) {
        ExtentTest t = extent.createTest(method.getName());
        Assert.assertTrue(t.getModel().getChildrenNodes().isEmpty());
    }
    
    @Test
    public void testNodeContextIsNotEmpty(Method method) {
        ExtentTest t = extent.createTest(method.getName());
        t.createNode(method.getName());
        Assert.assertFalse(t.getModel().getChildrenNodes().isEmpty());
    }
    
    @Test
    public void testNodeContextFirstNotNull(Method method) {
        ExtentTest t = extent.createTest(method.getName());
        t.createNode(method.getName());
        Assert.assertTrue(t.getModel().getChildrenNodes().getFirst()!=null);
    }
    
    @Test
    public void testNodeContextFirstContent(Method method) {
        ExtentTest t = extent.createTest(method.getName());
        String name = method.getName() + "x";
        t.createNode(name);
        Assert.assertTrue(t.getModel().getChildrenNodes().getFirst().getName().equals(name));
    }
    
    @Test
    public void testNodeContextLastContent(Method method) {
        ExtentTest t = extent.createTest(method.getName());
        String name = method.getName() + "x";
        t.createNode(name);
        Assert.assertTrue(t.getModel().getChildrenNodes().getLast().getName().equals(name));
    }
    
    @Test
    public void testNodeContextFirstLastContent(Method method) {
        ExtentTest t = extent.createTest(method.getName());
        String name1 = method.getName() + "x1";
        t.createNode(name1);
        String name2 = method.getName() + "x2";
        t.createNode(name2);
        Assert.assertTrue(t.getModel().getChildrenNodes().getFirst().getName().equals(name1));
        Assert.assertTrue(t.getModel().getChildrenNodes().getLast().getName().equals(name2));
    }
    
    @Test
    public void testNodeContextIndexContent(Method method) {
        ExtentTest t = extent.createTest(method.getName());
        String name1 = method.getName() + "x1";
        t.createNode(name1);
        String name2 = method.getName() + "x2";
        t.createNode(name2);
        Assert.assertTrue(t.getModel().getChildrenNodes().get(0).getName().equals(name1));
        Assert.assertTrue(t.getModel().getChildrenNodes().get(1).getName().equals(name2));
    }
    
    @Test
    public void testHasNoEventsViaModel(Method method) {
        ExtentTest t = extent.createTest(method.getName());
        Assert.assertFalse(t.getModel().hasLog());
    }
    
    @Test
    public void testHasEventsViaModel(Method method) {
        ExtentTest t = extent.createTest(method.getName()).pass("pass");
        Assert.assertTrue(t.getModel().hasLog());
    }
    
    @Test
    public void testLogContextIsEmpty(Method method) {
        ExtentTest t = extent.createTest(method.getName());
        Assert.assertTrue(t.getModel().getLogContext().isEmpty());
    }
    
    @Test
    public void testLogContextIsNotEmpty(Method method) {
        ExtentTest t = extent.createTest(method.getName()).pass("pass");
        Assert.assertFalse(t.getModel().getLogContext().isEmpty());
    }
    
    @Test
    public void testLogContextFirstNotNull(Method method) {
        ExtentTest t = extent.createTest(method.getName()).pass("pass");
        Assert.assertTrue(t.getModel().getLogContext().getFirst()!=null);
    }
    
    @Test
    public void testLogContextFirstContent(Method method) {
        String name = method.getName() + "x";
        ExtentTest t = extent.createTest(method.getName()).pass(name);
        Assert.assertTrue(t.getModel().getLogContext().getFirst().getDetails().equals(name));
    }
    
    @Test
    public void testLogContextLastContent(Method method) {
        String name = method.getName() + "x";
        ExtentTest t = extent.createTest(method.getName()).pass(name);
        Assert.assertTrue(t.getModel().getLogContext().getLast().getDetails().equals(name));
    }
    
    @Test
    public void testLogContextFirstLastContent(Method method) {
        ExtentTest t = extent.createTest(method.getName());
        String name1 = method.getName() + "x1";
        t.pass(name1);
        String name2 = method.getName() + "x2";
        t.pass(name2);
        Assert.assertTrue(t.getModel().getLogContext().getFirst().getDetails().equals(name1));
        Assert.assertTrue(t.getModel().getLogContext().getLast().getDetails().equals(name2));
    }
    
    @Test
    public void testLogContextIndexContent(Method method) {
        ExtentTest t = extent.createTest(method.getName());
        String name1 = method.getName() + "x1";
        t.pass(name1);
        String name2 = method.getName() + "x2";
        t.pass(name2);
        Assert.assertTrue(t.getModel().getLogContext().get(0).getDetails().equals(name1));
        Assert.assertTrue(t.getModel().getLogContext().get(1).getDetails().equals(name2));
    }
    
    @Test
    public void testHierarchicalNameParent(Method method) {
        ExtentTest t = extent.createTest(method.getName());
        Assert.assertTrue(t.getModel().getHierarchicalName().equals(method.getName()));
    }
    
    @Test
    public void testHierarchicalNameChild(Method method) {
        String parentName = method.getName();
        String childName = method.getName() + "x";
        ExtentTest t = extent.createTest(parentName).createNode(childName);
        Assert.assertTrue(t.getModel().getHierarchicalName().equals(parentName + "." + childName));
    }
    
    @Test
    public void testHierarchicalNameGrandChild(Method method) {
        String parentName = method.getName();
        String childName = method.getName() + "x";
        String grandChildName = method.getName() + "xx";
        ExtentTest t = extent.createTest(parentName).createNode(childName).createNode(grandChildName);
        Assert.assertTrue(t.getModel().getHierarchicalName().equals(parentName + "." + childName + "." + grandChildName));
    }
    
    @Test
    public void testHasCategory(Method method) {
        ExtentTest t = extent.createTest(method.getName()).assignCategory("category");
        Assert.assertTrue(t.getModel().hasCategory());
    }
    
    @Test
    public void testHasNoCategoryWhenCategoryNull(Method method) {
        ExtentTest t = extent.createTest(method.getName()).assignCategory(new String[] { null });
        Assert.assertFalse(t.getModel().hasCategory());
    }
    
    @Test
    public void testHasNoCategoryWhenCategoryEmpty(Method method) {
        ExtentTest t = extent.createTest(method.getName()).assignCategory("");
        Assert.assertFalse(t.getModel().hasCategory());
    }
    
    @Test
    public void testHasNoCategoryWhenCategorySpaces(Method method) {
        ExtentTest t = extent.createTest(method.getName()).assignCategory("  ");
        Assert.assertFalse(t.getModel().hasCategory());
    }
    
    @Test
    public void testCategorySingleNameMatch(Method method) {
        ExtentTest t = extent.createTest(method.getName()).assignCategory(method.getName());
        Assert.assertTrue(t.getModel().getCategoryContext().getFirst().getName().equals(method.getName()));
    }
    
    @Test
    public void testCategoryMultipleOrderedNameMatch(Method method) {
        ExtentTest t = extent.createTest(method.getName()).assignCategory(method.getName(), "tag");
        Assert.assertTrue(t.getModel().getCategoryContext().getFirst().getName().equals(method.getName()));
        Assert.assertTrue(t.getModel().getCategoryContext().getLast().getName().equals("tag"));
    }
    
    @Test
    public void testHasAuthor(Method method) {
        ExtentTest t = extent.createTest(method.getName()).assignAuthor("Author");
        Assert.assertTrue(t.getModel().hasAuthor());
    }
    
    @Test
    public void testHasNoAuthorWhenAuthorNull(Method method) {
        ExtentTest t = extent.createTest(method.getName()).assignAuthor(new String[] { null });
        Assert.assertFalse(t.getModel().hasAuthor());
    }
    
    @Test
    public void testHasNoAuthorWhenAuthorEmpty(Method method) {
        ExtentTest t = extent.createTest(method.getName()).assignAuthor("");
        Assert.assertFalse(t.getModel().hasAuthor());
    }
    
    @Test
    public void testHasNoAuthorWhenAuthorSpaces(Method method) {
        ExtentTest t = extent.createTest(method.getName()).assignAuthor("  ");
        Assert.assertFalse(t.getModel().hasAuthor());
    }
    
    @Test
    public void testAuthorSingleNameMatch(Method method) {
        ExtentTest t = extent.createTest(method.getName()).assignAuthor(method.getName());
        Assert.assertTrue(t.getModel().getAuthorContext().getFirst().getName().equals(method.getName()));
    }
    
    @Test
    public void testAuthorMultipleOrderedNameMatch(Method method) {
        ExtentTest t = extent.createTest(method.getName()).assignAuthor(method.getName(), "author");
        Assert.assertTrue(t.getModel().getAuthorContext().getFirst().getName().equals(method.getName()));
        Assert.assertTrue(t.getModel().getAuthorContext().getLast().getName().equals("author"));
    }
    
    @Test
    public void testHasDevice(Method method) {
        ExtentTest t = extent.createTest(method.getName()).assignDevice("Device");
        Assert.assertTrue(t.getModel().hasDevice());
    }
    
    @Test
    public void testHasNoDeviceWhenDeviceNull(Method method) {
        ExtentTest t = extent.createTest(method.getName()).assignDevice(new String[] { null });
        Assert.assertFalse(t.getModel().hasDevice());
    }
    
    @Test
    public void testHasNoDeviceWhenDeviceEmpty(Method method) {
        ExtentTest t = extent.createTest(method.getName()).assignDevice("");
        Assert.assertFalse(t.getModel().hasDevice());
    }
    
    @Test
    public void testHasNoDeviceWhenDeviceSpaces(Method method) {
        ExtentTest t = extent.createTest(method.getName()).assignDevice("  ");
        Assert.assertFalse(t.getModel().hasDevice());
    }
    
    @Test
    public void testDeviceSingleNameMatch(Method method) {
        ExtentTest t = extent.createTest(method.getName()).assignDevice(method.getName());
        Assert.assertTrue(t.getModel().getDeviceContext().getFirst().getName().equals(method.getName()));
    }
    
    @Test
    public void testDeviceMultipleOrderedNameMatch(Method method) {
        ExtentTest t = extent.createTest(method.getName()).assignDevice(method.getName(), "Device");
        Assert.assertTrue(t.getModel().getDeviceContext().getFirst().getName().equals(method.getName()));
        Assert.assertTrue(t.getModel().getDeviceContext().getLast().getName().equals("Device"));
    }
    
}
