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

public class BddAttributesTest extends Base {

    @Test
    public void featureIsOfBddType(Method method) {
        ExtentTest feature = extent.createTest(Feature.class, method.getName());
        Assert.assertTrue(feature.getModel().isBehaviorDrivenType());
        Assert.assertEquals(feature.getModel().getBehaviorDrivenType(), Feature.class);
    }
    
    @Test
    public void scenarioIsOfBddTypeWithBddChild(Method method) {
        ExtentTest feature = extent.createTest(Feature.class, method.getName());
        ExtentTest scenario = feature.createNode(Scenario.class, "Scenario");
        Assert.assertTrue(scenario.getModel().isBehaviorDrivenType());
        Assert.assertEquals(scenario.getModel().getBehaviorDrivenType(), Scenario.class);
    }
    
    @Test
    public void scenarioOutlineIsOfBddTypeWithBddChild(Method method) {
        ExtentTest feature = extent.createTest(Feature.class, method.getName());
        ExtentTest scenarioOutline = feature.createNode(ScenarioOutline.class, "ScenarioOutline");
        Assert.assertTrue(scenarioOutline.getModel().isBehaviorDrivenType());
        Assert.assertEquals(scenarioOutline.getModel().getBehaviorDrivenType(), ScenarioOutline.class);
    }
    
    @Test
    public void andIsOfBddTypeWithBddChild(Method method) {
        ExtentTest feature = extent.createTest(Feature.class, method.getName());
        ExtentTest scenario = feature.createNode(Scenario.class, "Scenario");
        ExtentTest and = scenario.createNode(And.class, "And");
        Assert.assertTrue(and.getModel().isBehaviorDrivenType());
        Assert.assertEquals(and.getModel().getBehaviorDrivenType(), And.class);
    }
    
    @Test
    public void asteriskIsOfBddTypeWithBddChild(Method method) {
        ExtentTest feature = extent.createTest(Feature.class, method.getName());
        ExtentTest scenario = feature.createNode(Scenario.class, "Scenario");
        ExtentTest asterisk = scenario.createNode(Asterisk.class, "Asterisk");
        Assert.assertTrue(asterisk.getModel().isBehaviorDrivenType());
        Assert.assertEquals(asterisk.getModel().getBehaviorDrivenType(), Asterisk.class);
    }
    
    @Test
    public void backgroundIsOfBddTypeWithBddChild(Method method) {
        ExtentTest feature = extent.createTest(Feature.class, method.getName());
        ExtentTest scenario = feature.createNode(Scenario.class, "Scenario");
        ExtentTest background = scenario.createNode(Background.class, "Background");
        Assert.assertTrue(background.getModel().isBehaviorDrivenType());
        Assert.assertEquals(background.getModel().getBehaviorDrivenType(), Background.class);
    }
    
    @Test
    public void butIsOfBddTypeWithBddChild(Method method) {
        ExtentTest feature = extent.createTest(Feature.class, method.getName());
        ExtentTest scenario = feature.createNode(Scenario.class, "Scenario");
        ExtentTest but = scenario.createNode(But.class, "But");
        Assert.assertTrue(but.getModel().isBehaviorDrivenType());
        Assert.assertEquals(but.getModel().getBehaviorDrivenType(), But.class);
    }
    
    @Test
    public void givenIsOfBddTypeWithBddChild(Method method) {
        ExtentTest feature = extent.createTest(Feature.class, method.getName());
        ExtentTest scenario = feature.createNode(Scenario.class, "Scenario");
        ExtentTest given = scenario.createNode(Given.class, "Given");
        Assert.assertTrue(given.getModel().isBehaviorDrivenType());
        Assert.assertEquals(given.getModel().getBehaviorDrivenType(), Given.class);
    }
    
    @Test
    public void thenIsOfBddTypeWithBddChild(Method method) {
        ExtentTest feature = extent.createTest(Feature.class, method.getName());
        ExtentTest scenario = feature.createNode(Scenario.class, "Scenario");
        ExtentTest then = scenario.createNode(Then.class, "Then");
        Assert.assertTrue(then.getModel().isBehaviorDrivenType());
        Assert.assertEquals(then.getModel().getBehaviorDrivenType(), Then.class);
    }
    
    @Test
    public void whenIsOfBddTypeWithBddChild(Method method) {
        ExtentTest feature = extent.createTest(Feature.class, method.getName());
        ExtentTest scenario = feature.createNode(Scenario.class, "Scenario");
        ExtentTest when = scenario.createNode(When.class, "When");
        Assert.assertTrue(when.getModel().isBehaviorDrivenType());
        Assert.assertEquals(when.getModel().getBehaviorDrivenType(), When.class);
    }
    
}
