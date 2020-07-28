package com.aventstack.extentreports;

import java.util.Set;
import java.util.stream.Collectors;

import org.testng.Assert;
import org.testng.annotations.Test;

public class ExtentTestAttributesHierarchyTest {

    @Test
    public void checkTagHierarchy() {
        ExtentReports extent = new ExtentReports();
        ExtentTest t = extent.createTest("Test")
                .assignCategory("Tag1")
                .createNode("Level1")
                .assignCategory("Tag2")
                .createNode("Level2")
                .assignCategory("Tag3");
        Set<String> parentTags = t.getModel().getParent().getParent().getCategorySet()
                .stream()
                .map(x -> x.getName())
                .collect(Collectors.toSet());
        Set<String> childTags = t.getModel().getParent().getCategorySet()
                .stream()
                .map(x -> x.getName())
                .collect(Collectors.toSet());
        Set<String> grandchildTags = t.getModel().getCategorySet()
                .stream()
                .map(x -> x.getName())
                .collect(Collectors.toSet());
        // grandchild, assigned to self
        Assert.assertTrue(grandchildTags.contains("Tag3"));
        // grandchild, from parent [Level2]
        Assert.assertTrue(grandchildTags.contains("Tag2"));
        // grandchild, from ancestor [Level1]
        Assert.assertTrue(grandchildTags.contains("Tag1"));
        // child, assigned to self
        Assert.assertTrue(childTags.contains("Tag2"));
        // child, propagated from parent
        Assert.assertTrue(childTags.contains("Tag1"));
        // parent
        Assert.assertTrue(parentTags.contains("Tag1"));
    }

}
