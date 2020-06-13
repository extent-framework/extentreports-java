package com.aventstack.extentreports.gherkin;

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

public class GherkinNameTest {
    private static final String AND = "And";
    private static final String ASTERISK = "*";
    private static final String BACKGROUND = "Background";
    private static final String BUT = "But";
    private static final String FEATURE = "Feature";
    private static final String GIVEN = "Given";
    private static final String SCENARIO = "Scenario";
    private static final String SCENARIO_OUTLINE = "Scenario Outline";
    private static final String THEN = "Then";
    private static final String WHEN = "When";

    @Test
    public void testAndGherkinName() {
        Assert.assertTrue(And.getGherkinName().equals(AND));
    }

    @Test
    public void testAndToString() {
        Assert.assertTrue(new And().toString().equals(AND));
    }

    @Test
    public void testAsteriskGherkinName() {
        Assert.assertTrue(Asterisk.getGherkinName().equals(ASTERISK));
    }

    @Test
    public void testAsteriskToString() {
        Assert.assertTrue(new Asterisk().toString().equals(ASTERISK));
    }

    @Test
    public void testBackgroundGherkinName() {
        Assert.assertTrue(Background.getGherkinName().equals(BACKGROUND));
    }

    @Test
    public void testBackgroundToString() {
        Assert.assertTrue(new Background().toString().equals(BACKGROUND));
    }

    @Test
    public void testButGherkinName() {
        Assert.assertTrue(But.getGherkinName().equals(BUT));
    }

    @Test
    public void testButToString() {
        Assert.assertTrue(new But().toString().equals(BUT));
    }

    @Test
    public void testFeatureGherkinName() {
        Assert.assertTrue(Feature.getGherkinName().equals(FEATURE));
    }

    @Test
    public void testFeatureToString() {
        Assert.assertTrue(new Feature().toString().equals(FEATURE));
    }

    @Test
    public void testGivenGherkinName() {
        Assert.assertTrue(Given.getGherkinName().equals(GIVEN));
    }

    @Test
    public void testGivenToString() {
        Assert.assertTrue(new Given().toString().equals(GIVEN));
    }

    @Test
    public void testScenarioGherkinName() {
        Assert.assertTrue(Scenario.getGherkinName().equals(SCENARIO));
    }

    @Test
    public void testScenarioToString() {
        Assert.assertTrue(new Scenario().toString().equals(SCENARIO));
    }

    @Test
    public void testScenarioOutlineGherkinName() {
        Assert.assertTrue(ScenarioOutline.getGherkinName().equals(SCENARIO_OUTLINE));
    }

    @Test
    public void testScenarioOutlineToString() {
        Assert.assertTrue(new ScenarioOutline().toString().equals(SCENARIO_OUTLINE));
    }

    @Test
    public void testThenGherkinName() {
        Assert.assertTrue(Then.getGherkinName().equals(THEN));
    }

    @Test
    public void testThenToString() {
        Assert.assertTrue(new Then().toString().equals(THEN));
    }

    @Test
    public void testWhenGherkinName() {
        Assert.assertTrue(When.getGherkinName().equals(WHEN));
    }

    @Test
    public void testWhenToString() {
        Assert.assertTrue(new When().toString().equals(WHEN));
    }
}