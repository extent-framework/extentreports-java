package com.aventstack.extentreports;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.entity.And;
import com.aventstack.extentreports.gherkin.entity.Asterisk;
import com.aventstack.extentreports.gherkin.entity.Background;
import com.aventstack.extentreports.gherkin.entity.But;
import com.aventstack.extentreports.gherkin.entity.Feature;
import com.aventstack.extentreports.gherkin.entity.Given;
import com.aventstack.extentreports.gherkin.entity.Scenario;
import com.aventstack.extentreports.gherkin.entity.ScenarioOutline;
import com.aventstack.extentreports.gherkin.entity.Then;
import com.aventstack.extentreports.gherkin.entity.When;
import com.aventstack.extentreports.model.service.TestService;

public class BddTypeTest {
    private ExtentReports extent() {
        return new ExtentReports();
    }

    @Test
    public void featureIsOfBddType(Method method) {
        ExtentTest feature = extent().createTest(Feature.class, method.getName());
        Assert.assertTrue(TestService.isBDD(feature.getModel()));
        Assert.assertEquals(feature.getModel().getBddType(), Feature.class);
    }

    @Test
    public void scenarioIsOfBddTypeWithBddChild(Method method) {
        ExtentTest feature = extent().createTest(Feature.class, method.getName());
        ExtentTest scenario = feature.createNode(Scenario.class, "Scenario");
        Assert.assertTrue(TestService.isBDD(scenario.getModel()));
        Assert.assertEquals(scenario.getModel().getBddType(), Scenario.class);
    }

    @Test
    public void scenarioOutlineIsOfBddTypeWithBddChild(Method method) {
        ExtentTest feature = extent().createTest(Feature.class, method.getName());
        ExtentTest scenarioOutline = feature.createNode(ScenarioOutline.class, "ScenarioOutline");
        Assert.assertTrue(TestService.isBDD(scenarioOutline.getModel()));
        Assert.assertEquals(scenarioOutline.getModel().getBddType(), ScenarioOutline.class);
    }

    @Test
    public void andIsOfBddTypeWithBddChild(Method method) {
        ExtentTest feature = extent().createTest(Feature.class, method.getName());
        ExtentTest scenario = feature.createNode(Scenario.class, "Scenario");
        ExtentTest and = scenario.createNode(And.class, "And");
        Assert.assertTrue(TestService.isBDD(and.getModel()));
        Assert.assertEquals(and.getModel().getBddType(), And.class);
    }

    @Test
    public void asteriskIsOfBddTypeWithBddChild(Method method) {
        ExtentTest feature = extent().createTest(Feature.class, method.getName());
        ExtentTest scenario = feature.createNode(Scenario.class, "Scenario");
        ExtentTest asterisk = scenario.createNode(Asterisk.class, "Asterisk");
        Assert.assertTrue(TestService.isBDD(asterisk.getModel()));
        Assert.assertEquals(asterisk.getModel().getBddType(), Asterisk.class);
    }

    @Test
    public void backgroundIsOfBddTypeWithBddChild(Method method) {
        ExtentTest feature = extent().createTest(Feature.class, method.getName());
        ExtentTest scenario = feature.createNode(Scenario.class, "Scenario");
        ExtentTest background = scenario.createNode(Background.class, "Background");
        Assert.assertTrue(TestService.isBDD(background.getModel()));
        Assert.assertEquals(background.getModel().getBddType(), Background.class);
    }

    @Test
    public void butIsOfBddTypeWithBddChild(Method method) {
        ExtentTest feature = extent().createTest(Feature.class, method.getName());
        ExtentTest scenario = feature.createNode(Scenario.class, "Scenario");
        ExtentTest but = scenario.createNode(But.class, "But");
        Assert.assertTrue(TestService.isBDD(but.getModel()));
        Assert.assertEquals(but.getModel().getBddType(), But.class);
    }

    @Test
    public void givenIsOfBddTypeWithBddChild(Method method) {
        ExtentTest feature = extent().createTest(Feature.class, method.getName());
        ExtentTest scenario = feature.createNode(Scenario.class, "Scenario");
        ExtentTest given = scenario.createNode(Given.class, "Given");
        Assert.assertTrue(TestService.isBDD(given.getModel()));
        Assert.assertEquals(given.getModel().getBddType(), Given.class);
    }

    @Test
    public void thenIsOfBddTypeWithBddChild(Method method) {
        ExtentTest feature = extent().createTest(Feature.class, method.getName());
        ExtentTest scenario = feature.createNode(Scenario.class, "Scenario");
        ExtentTest then = scenario.createNode(Then.class, "Then");
        Assert.assertTrue(TestService.isBDD(then.getModel()));
        Assert.assertEquals(then.getModel().getBddType(), Then.class);
    }

    @Test
    public void whenIsOfBddTypeWithBddChild(Method method) {
        ExtentTest feature = extent().createTest(Feature.class, method.getName());
        ExtentTest scenario = feature.createNode(Scenario.class, "Scenario");
        ExtentTest when = scenario.createNode(When.class, "When");
        Assert.assertTrue(TestService.isBDD(when.getModel()));
        Assert.assertEquals(when.getModel().getBddType(), When.class);
    }

}
