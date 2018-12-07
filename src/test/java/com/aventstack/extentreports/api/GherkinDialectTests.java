package com.aventstack.extentreports.api;

import java.io.UnsupportedEncodingException;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.gherkin.GherkinDialectProvider;

public class GherkinDialectTests {

    private static final String DEFAULT_LANGUAGE = "en";
    
    @Test
    public void defaultDialectTest() {
        Assert.assertTrue(GherkinDialectProvider.getDefaultLanguage().equals(DEFAULT_LANGUAGE));
    }
    
    @Test
    public void defaultLanguageTest() {
        Assert.assertTrue(GherkinDialectProvider.getLanguage().equals(DEFAULT_LANGUAGE));
    }
    
    @Test(expectedExceptions=UnsupportedEncodingException.class)
    public void invalidLanguageTest() throws UnsupportedEncodingException {
        GherkinDialectProvider.setLanguage("invalid");
    }
    
    @Test
    public void testLanguageSettingTest() throws UnsupportedEncodingException {
        GherkinDialectProvider.setLanguage("de");
        Assert.assertTrue(GherkinDialectProvider.getLanguage().equals("de"));
    }
}
