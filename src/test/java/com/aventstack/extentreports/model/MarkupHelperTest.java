package com.aventstack.extentreports.model;

import java.lang.reflect.Method;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Base;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.markuputils.CodeLanguage;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;

public class MarkupHelperTest extends Base {

    private static final String TEXTAREA_START = "<textarea";
    private static final String TEXTAREA_END = "</textarea>";
    private static final String DIV_START = "<div";
    private static final String DIV_END = "</div>";
    private static final String TABLE_START = "<table";
    private static final String TABLE_END = "</table>";
    private static final String XML = "<xml></xml>";
    private static final String JSON = "{ foo: { bar : '1' } }";
    private static final String LABEL_CLASS = "label";
    private static final String LABEL_CLASS_ALT = "badge";
    
    @Test
    public void testCodeBlockXML(Method method) {
        Markup m = MarkupHelper.createCodeBlock(XML);       
        ExtentTest t = extent.createTest(method.getName()).pass(m);
        String details = t.getModel().getLogContext().getFirst().getDetails();
        Assert.assertTrue(details.contains(TEXTAREA_START));
        Assert.assertTrue(details.contains(TEXTAREA_END));
        Assert.assertTrue(details.contains(XML));
    }
    
    @Test
    public void testCodeBlockJSON(Method method) {
        Markup m = MarkupHelper.createCodeBlock(JSON);       
        ExtentTest t = extent.createTest(method.getName()).pass(m);
        String details = t.getModel().getLogContext().getFirst().getDetails();
        Assert.assertTrue(details.contains(TEXTAREA_START));
        Assert.assertTrue(details.contains(TEXTAREA_END));
        Assert.assertTrue(details.contains(JSON));
    }
    
    @Test
    public void testCodeBlockXMLCodeLanguage(Method method) {
        Markup m = MarkupHelper.createCodeBlock(XML, CodeLanguage.XML);       
        ExtentTest t = extent.createTest(method.getName()).pass(m);
        String details = t.getModel().getLogContext().getFirst().getDetails();
        Assert.assertTrue(details.contains(TEXTAREA_START));
        Assert.assertTrue(details.contains(TEXTAREA_END));
        Assert.assertTrue(details.contains(XML));
    }
    
    @Test
    public void testCodeBlockJSONCodeLanguage(Method method) {
        Markup m = MarkupHelper.createCodeBlock(JSON, CodeLanguage.JSON);       
        ExtentTest t = extent.createTest(method.getName()).pass(m);
        String details = t.getModel().getLogContext().getFirst().getDetails();
        Assert.assertTrue(details.contains(DIV_START));
        Assert.assertTrue(details.contains(DIV_END));
        Assert.assertTrue(details.contains(JSON));
    }
    
    @Test
    public void testLabel(Method method) {
        Markup m = MarkupHelper.createLabel("label", ExtentColor.BLACK);
        ExtentTest t = extent.createTest(method.getName()).pass(m);
        String details = t.getModel().getLogContext().getFirst().getDetails();
        Assert.assertTrue(details.contains(LABEL_CLASS) || details.contains(LABEL_CLASS_ALT));
    }
    
    @Test
    public void createTable(Method method) {
        String[][] data = {
                { "Header1", "Header2", "Header3" },
                { "Content.1.1", "Content.2.1", "Content.3.1" },
                { "Content.1.2", "Content.2.2", "Content.3.2" },
                { "Content.1.3", "Content.2.3", "Content.3.3" },
                { "Content.1.4", "Content.2.4", "Content.3.4" }
            };
        Markup m = MarkupHelper.createTable(data);
        ExtentTest t = extent.createTest(method.getName()).pass(m);
        String details = t.getModel().getLogContext().getFirst().getDetails();
        Assert.assertTrue(details.contains(TABLE_START) || details.contains(TABLE_END));
    }
    
}
