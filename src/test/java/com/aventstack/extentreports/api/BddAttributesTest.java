package com.aventstack.extentreports.api;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Base;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.gherkin.model.And;
import com.aventstack.extentreports.gherkin.model.Asterisk;
import com.aventstack.extentreports.gherkin.model.Background;
import com.aventstack.extentreports.gherkin.model.But;
import com.aventstack.extentreports.gherkin.model.Feature;
import com.aventstack.extentreports.gherkin.model.Given;
import com.aventstack.extentreports.gherkin.model.Scenario;
import com.aventstack.extentreports.gherkin.model.ScenarioOutline;
import com.aventstack.extentreports.gherkin.model.Then;
import com.aventstack.extentreports.gherkin.model.When;
import com.aventstack.extentreports.model.service.TestService;

public class BddAttributesTest extends Base {

    @Test
    public void featureIsOfBddType(Method method) {
        ExtentTest feature = extent.createTest(Feature.class, method.getName());
        Assert.assertTrue(TestService.isTestBehaviorDriven(feature.getModel()));
        Assert.assertEquals(feature.getModel().getBddType(), Feature.class);
    }
    
    @Test
    public void scenarioIsOfBddTypeWithBddChild(Method method) {
        ExtentTest feature = extent.createTest(Feature.class, method.getName());
        ExtentTest scenario = feature.createNode(Scenario.class, "Scenario");
        Assert.assertTrue(TestService.isTestBehaviorDriven(scenario.getModel()));
        Assert.assertEquals(scenario.getModel().getBddType(), Scenario.class);
    }
    
    @Test
    public void scenarioOutlineIsOfBddTypeWithBddChild(Method method) {
        ExtentTest feature = extent.createTest(Feature.class, method.getName());
        ExtentTest scenarioOutline = feature.createNode(ScenarioOutline.class, "ScenarioOutline");
        Assert.assertTrue(TestService.isTestBehaviorDriven(scenarioOutline.getModel()));
        Assert.assertEquals(scenarioOutline.getModel().getBddType(), ScenarioOutline.class);
    }
    
    @Test
    public void andIsOfBddTypeWithBddChild(Method method) {
        ExtentTest feature = extent.createTest(Feature.class, method.getName());
        ExtentTest scenario = feature.createNode(Scenario.class, "Scenario");
        ExtentTest and = scenario.createNode(And.class, "And");
        Assert.assertTrue(TestService.isTestBehaviorDriven(and.getModel()));
        Assert.assertEquals(and.getModel().getBddType(), And.class);
    }
    
    @Test
    public void asteriskIsOfBddTypeWithBddChild(Method method) {
        ExtentTest feature = extent.createTest(Feature.class, method.getName());
        ExtentTest scenario = feature.createNode(Scenario.class, "Scenario");
        ExtentTest asterisk = scenario.createNode(Asterisk.class, "Asterisk");
        Assert.assertTrue(TestService.isTestBehaviorDriven(asterisk.getModel()));
        Assert.assertEquals(asterisk.getModel().getBddType(), Asterisk.class);
    }
    
    @Test
    public void backgroundIsOfBddTypeWithBddChild(Method method) {
        ExtentTest feature = extent.createTest(Feature.class, method.getName());
        ExtentTest scenario = feature.createNode(Scenario.class, "Scenario");
        ExtentTest background = scenario.createNode(Background.class, "Background");
        Assert.assertTrue(TestService.isTestBehaviorDriven(background.getModel()));
        Assert.assertEquals(background.getModel().getBddType(), Background.class);
    }
    
    @Test
    public void butIsOfBddTypeWithBddChild(Method method) {
        ExtentTest feature = extent.createTest(Feature.class, method.getName());
        ExtentTest scenario = feature.createNode(Scenario.class, "Scenario");
        ExtentTest but = scenario.createNode(But.class, "But");
        Assert.assertTrue(TestService.isTestBehaviorDriven(but.getModel()));
        Assert.assertEquals(but.getModel().getBddType(), But.class);
    }
    
    @Test
    public void givenIsOfBddTypeWithBddChild(Method method) {
        ExtentTest feature = extent.createTest(Feature.class, method.getName());
        ExtentTest scenario = feature.createNode(Scenario.class, "Scenario");
        ExtentTest given = scenario.createNode(Given.class, "Given");
        Assert.assertTrue(TestService.isTestBehaviorDriven(given.getModel()));
        Assert.assertEquals(given.getModel().getBddType(), Given.class);
    }
    
    @Test
    public void thenIsOfBddTypeWithBddChild(Method method) {
        ExtentTest feature = extent.createTest(Feature.class, method.getName());
        ExtentTest scenario = feature.createNode(Scenario.class, "Scenario");
        ExtentTest then = scenario.createNode(Then.class, "Then");
        Assert.assertTrue(TestService.isTestBehaviorDriven(then.getModel()));
        Assert.assertEquals(then.getModel().getBddType(), Then.class);
    }
    
    @Test
    public void whenIsOfBddTypeWithBddChild(Method method) {
        ExtentTest feature = extent.createTest(Feature.class, method.getName());
        ExtentTest scenario = feature.createNode(Scenario.class, "Scenario");
        ExtentTest when = scenario.createNode(When.class, "When");
        Assert.assertTrue(TestService.isTestBehaviorDriven(when.getModel()));
        Assert.assertEquals(when.getModel().getBddType(), When.class);
    }
    
}
