package com.aventstack.extentreports.mediastorage;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Base;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;

public class LocalMediaStorageTest extends Base {
    
    private static final String IMG_PATH = "src/test/resources/logo.png";
    private static final String OUTPUT_PATH = "target/";
/*
    @Test(priority=1)
    public void testLocalFolderCreationOrder(Method method) throws IOException {
        ExtentHtmlReporter html = new ExtentHtmlReporter("target/Extent.html");
        html.config().setAutoCreateRelativePathMedia(true);
        extent.attachReporter(html);
        extent.createTest(method.getName()).pass("pass").addScreenCaptureFromPath(IMG_PATH);
        extent.flush();
        Assert.assertTrue(getDirsByName(OUTPUT_PATH).contains("target.0"));
    }
    
    private List<String> getDirsByName(String path) {
        File file = new File(path);
        String[] names = file.list();
        return Arrays.asList(names);
    }
    
    @Test(priority=2)
    public void testLocalFolderCreationOrderMultipleTries(Method method) throws IOException {
        ExtentHtmlReporter html;
        for (int ix = 0; ix < 2; ix++) {
            extent = new ExtentReports();
            html = new ExtentHtmlReporter("target/Extent.html");
            html.config().setAutoCreateRelativePathMedia(true);
            extent.attachReporter(html);
            extent.createTest(method.getName()).pass("pass").addScreenCaptureFromPath(IMG_PATH);
            extent.flush();
        }
        Assert.assertTrue(getDirsByName(OUTPUT_PATH).contains("target.2"));
        Assert.assertTrue(new File(OUTPUT_PATH + "target.2/logo.png").exists());
    }
*/    
}
