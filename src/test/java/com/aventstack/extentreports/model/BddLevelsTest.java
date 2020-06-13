package com.aventstack.extentreports.model;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Base;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.GherkinKeyword;
import com.aventstack.extentreports.gherkin.GherkinDialectProvider;
import com.aventstack.extentreports.gherkin.model.And;
import com.aventstack.extentreports.gherkin.model.But;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Given;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.gherkin.model.Then;
import com.aventstack.extentreports.gherkin.model.When;

public class BddLevelsTest extends Base {
    
    @BeforeClass
    public void beforeClass() throws UnsupportedEncodingException {
        GherkinDialectProvider.setLanguage("en");
    }
    
    @Test
    public void verifyFeatureLevelUsingGherkinKeyword(Method method) throws ClassNotFoundException {
        ExtentTest feature = extent.createTest(new GherkinKeyword("Feature"), method.getName());
        Assert.assertEquals(feature.getModel().getLevel(), 0);
    }
    
    @Test
    public void verifyScenarioLevelUsingGherkinKeyword(Method method) throws ClassNotFoundException {
        ExtentTest feature = extent.createTest(new GherkinKeyword("Feature"), method.getName());
        ExtentTest scenario = feature.createNode(new GherkinKeyword("Scenario"), "Child");
        Assert.assertEquals(scenario.getModel().getLevel(), 1);
    }
    
    @Test
    public void verifyGivenLevesUsingGherkinKeyword(Method method) throws ClassNotFoundException {
        ExtentTest feature = extent.createTest(new GherkinKeyword("Feature"), method.getName());
        ExtentTest scenario = feature.createNode(new GherkinKeyword("Scenario"), "Child");
        ExtentTest given = scenario.createNode(new GherkinKeyword("Given"), "Given").info("info");
        Assert.assertEquals(given.getModel().getLevel(), 2);
    }
    
    @Test
    public void verifyAndLevelUsingGherkinKeyword(Method method) throws ClassNotFoundException {
        ExtentTest feature = extent.createTest(new GherkinKeyword("Feature"), method.getName());
        ExtentTest scenario = feature.createNode(new GherkinKeyword("Scenario"), "Child");
        ExtentTest and = scenario.createNode(new GherkinKeyword("And"), "And").info("info");
        Assert.assertEquals(and.getModel().getLevel(), 2);
    }
    
    @Test
    public void verifyWhenLevelUsingGherkinKeyword(Method method) throws ClassNotFoundException {
        ExtentTest feature = extent.createTest(new GherkinKeyword("Feature"), method.getName());
        ExtentTest scenario = feature.createNode(new GherkinKeyword("Scenario"), "Child");
        ExtentTest when = scenario.createNode(new GherkinKeyword("When"), "When").info("info");
        Assert.assertEquals(when.getModel().getLevel(), 2);
    }
    
    @Test
    public void verifyThenLevelUsingGherkinKeyword(Method method) throws ClassNotFoundException {
        ExtentTest feature = extent.createTest(new GherkinKeyword("Feature"), method.getName());
        ExtentTest scenario = feature.createNode(new GherkinKeyword("Scenario"), "Child");
        ExtentTest then = scenario.createNode(new GherkinKeyword("Then"), "Then").pass("pass");
        Assert.assertEquals(then.getModel().getLevel(), 2);
    }
    
    @Test
    public void verifyButLevelUsingGherkinKeyword(Method method) throws ClassNotFoundException {
        ExtentTest feature = extent.createTest(new GherkinKeyword("Feature"), method.getName());
        ExtentTest scenario = feature.createNode(new GherkinKeyword("Scenario"), "Child");
        ExtentTest but = scenario.createNode(new GherkinKeyword("But"), "But").pass("pass");
        Assert.assertEquals(but.getModel().getLevel(), 2);
    }

    @Test
    public void verifyFeatureLevelUsingClass(Method method) {
        ExtentTest feature = extent.createTest(Feature.class, method.getName());
        Assert.assertEquals(feature.getModel().getLevel(), 0);
    }
    
    @Test
    public void verifyScenarioLevelUsingClass(Method method) {
        ExtentTest feature = extent.createTest(Feature.class, method.getName());
        ExtentTest scenario = feature.createNode(Scenario.class, "Child");
        Assert.assertEquals(scenario.getModel().getLevel(), 1);
    }
    
    @Test
    public void verifyGivenLevelUsingClass(Method method) {
        ExtentTest feature = extent.createTest(Feature.class, method.getName());
        ExtentTest scenario = feature.createNode(Scenario.class, "Child");
        ExtentTest given = scenario.createNode(Given.class, "Given").info("info");
        Assert.assertEquals(given.getModel().getLevel(), 2);
    }
    
    @Test
    public void verifyAndLevelUsingClass(Method method) {
        ExtentTest feature = extent.createTest(Feature.class, method.getName());
        ExtentTest scenario = feature.createNode(Scenario.class, "Child");
        ExtentTest and = scenario.createNode(And.class, "And").info("info");
        Assert.assertEquals(and.getModel().getLevel(), 2);
    }
    
    @Test
    public void verifyWhenLevelUsingClass(Method method) {
        ExtentTest feature = extent.createTest(Feature.class, method.getName());
        ExtentTest scenario = feature.createNode(Scenario.class, "Child");
        ExtentTest when = scenario.createNode(When.class, "When").info("info");
        Assert.assertEquals(when.getModel().getLevel(), 2);
    }
    
    @Test
    public void verifyThenLevelUsingClass(Method method) {
        ExtentTest feature = extent.createTest(Feature.class, method.getName());
        ExtentTest scenario = feature.createNode(Scenario.class, "Child");
        ExtentTest then = scenario.createNode(Then.class, "Then").pass("pass");
        Assert.assertEquals(then.getModel().getLevel(), 2);
    }
    
    @Test
    public void verifyButLevelUsingClass(Method method) {
        ExtentTest feature = extent.createTest(Feature.class, method.getName());
        ExtentTest scenario = feature.createNode(Scenario.class, "Child");
        ExtentTest but = scenario.createNode(But.class, "But").pass("pass");
        Assert.assertEquals(but.getModel().getLevel(), 2);
    }
    
}
