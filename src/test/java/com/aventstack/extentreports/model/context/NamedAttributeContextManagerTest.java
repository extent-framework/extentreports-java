package com.aventstack.extentreports.model.context;

import java.util.Set;
import java.util.stream.IntStream;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.model.Author;
import com.aventstack.extentreports.model.Category;
import com.aventstack.extentreports.model.Device;
import com.aventstack.extentreports.model.NamedAttribute;

public class NamedAttributeContextManagerTest {

    private final String[] tags = new String[] { "tag1", "tag2", "tag3", "tag4" };

    private ExtentReports _extent;

    @BeforeMethod
    public void beforeMethod() {
        _extent = new ExtentReports();
    }

    /**
     * This test checks if the context only tracks tag uniqueness across N number of
     * tests
     * 
     * Issue details:
     * https://github.com/extent-framework/extentreports-java/issues/282
     */
    @Test
    public void concurrentlyAssignedTags() {
        IntStream.range(1, 100).parallel().forEach(x -> {
            _extent.createTest("Test" + x).assignCategory(tags).pass("");
        });
        final Set<NamedAttributeContext<Category>> set = _extent.getReport().getCategoryCtx().getSet();
        verifyContentAssert(set);
    }

    private <T extends NamedAttribute> void verifyContentAssert(final Set<NamedAttributeContext<T>> set) {
        final String[] runtimeTags = set.stream().map(x -> x.getAttr().getName()).toArray(String[]::new);
        Assert.assertEqualsNoOrder(tags, runtimeTags);
    }

    @Test
    public void concurrentlyAssignedUsers() {
        IntStream.range(1, 100).parallel().forEach(x -> {
            _extent.createTest("Test" + x).assignAuthor(tags).pass("");
        });
        final Set<NamedAttributeContext<Author>> set = _extent.getReport().getAuthorCtx().getSet();
        verifyContentAssert(set);
    }

    @Test
    public void concurrentlyAssignedDevices() {
        IntStream.range(1, 100).parallel().forEach(x -> {
            _extent.createTest("Test" + x).assignDevice(tags).pass("");
        });
        final Set<NamedAttributeContext<Device>> set = _extent.getReport().getDeviceCtx().getSet();
        verifyContentAssert(set);
    }
}
