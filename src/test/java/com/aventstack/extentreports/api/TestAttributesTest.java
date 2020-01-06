package com.aventstack.extentreports.api;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Base;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.model.Attribute;

public class TestAttributesTest extends Base {

    private final String[] categories = {
            "extent",
            "git",
            "tests",
            "heroku"
    };
    private final String[] authors = {
            "anshoo",
            "viren",
            "maxi",
            "vimal"
    };
    
    @Test
    public void verifyIfTestHasAddedCategory(Method method) {
        ExtentTest test = extent.createTest(method.getName()).assignCategory(categories[0]);
        test.pass("pass");
        
        Assert.assertEquals(test.getModel().getCategoryContext().size(), 1);
        Assert.assertEquals(test.getModel().getCategoryContext().get(0).getName(), categories[0]);
    }
    
    @Test
    public void verifyIfTestHasAddedCategories(Method method) {
        ExtentTest test = extent.createTest(method.getName());        
        Arrays.stream(categories).forEach(c -> test.assignCategory(c));
        test.pass("pass");
               
        Assert.assertEquals(test.getModel().getCategoryContext().size(), categories.length);
        
        List<Attribute> categoryCollection = test.getModel().getCategoryContext().getAll();
        Arrays.stream(categories).forEach(c -> {
            Boolean result = categoryCollection.stream().anyMatch(x -> x.getName() == c); 
            Assert.assertTrue(result);
        });
    }
    
    @Test
    public void verifyIfTestHasAddedAuthor(Method method) {
        ExtentTest test = extent.createTest(method.getName()).assignAuthor(authors[0]);
        test.pass("pass");
        
        Assert.assertEquals(test.getModel().getAuthorContext().size(), 1);
        Assert.assertEquals(test.getModel().getAuthorContext().get(0).getName(), authors[0]);
    }
    
    @Test
    public void verifyIfTestHasAddedAuthors(Method method) {
        ExtentTest test = extent.createTest(method.getName());        
        Arrays.stream(authors).forEach(a -> test.assignAuthor(a));
        test.pass("pass");
               
        Assert.assertEquals(test.getModel().getAuthorContext().size(), authors.length);
        
        List<Attribute> authorCollection = test.getModel().getAuthorContext().getAll();
        Arrays.stream(authors).forEach(a -> {
            Boolean result = authorCollection.stream().anyMatch(x -> x.getName() == a); 
            Assert.assertTrue(result);
        });
    }
}
