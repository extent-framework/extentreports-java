package com.aventstack.extentreports.reporter.configuration;

import java.io.File;
import java.util.stream.Stream;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.util.ResourceHelper;

import lombok.Getter;
import lombok.Setter;

/**
 * Defines configuration settings for the Spark reporter
 */
@Getter
@Setter
public class ExtentSparkReporterConfig extends RichViewReporterConfig {
    private static final String REPORTER_NAME = "spark";
    private static final String SEP = "/";
    private static final String COMMONS = "commons" + SEP;
    private static final String CSS = "css" + SEP;
    private static final String JS = "js" + SEP;
    private static final String ICONS = "icons" + SEP;
    private static final String IMG = "img" + SEP;

    private Boolean offlineMode = false;
    private String resourceCDN = "github";

    public ExtentSparkReporterConfig(ExtentSparkReporter reporter) {
        super(reporter);
    }

    /**
     * Creates the HTML report, saving all resources (css, js, fonts) in the
     * same location, so the report can be viewed without an internet connection
     * 
     * @param offlineMode
     *            Setting to enable an offline accessible report
     */
    public void enableOfflineMode(Boolean offlineMode) {
        this.offlineMode = offlineMode;
        if (offlineMode) {
            File f = getTargetDirectory(((ExtentSparkReporter) getReporter()).getResolvedParentFile());
            String resPackage = ExtentReports.class.getPackage().getName().replace(".", SEP);
            resPackage += SEP + "offline" + SEP;
            String[] resx = combine(getJSFiles(), getCSSFiles(), getIconFiles(), getImgFiles());
            ResourceHelper.saveOfflineResources(resPackage, resx, f.getAbsolutePath());
        }
    }

    private File getTargetDirectory(File f) {
        String dir = f.getAbsolutePath().replace("\\", SEP);
        if (!f.isDirectory())
            dir = new File(dir).getParent();
        dir += "/" + REPORTER_NAME;
        return new File(dir);
    }

    private String[] combine(String[]... array) {
        String[] result = new String[]{};
        for (String[] arr : array)
            result = Stream.of(result, arr).flatMap(Stream::of).toArray(String[]::new);
        return result;
    }

    private String[] getJSFiles() {
        final String commonsPath = COMMONS + JS;
        final String reporterPath = REPORTER_NAME + SEP + JS;
        final String[] files = {reporterPath + "spark-script.js", commonsPath + "jsontree.js"};
        return files;
    }

    private String[] getCSSFiles() {
        final String reporterPath = REPORTER_NAME + SEP + CSS;
        final String[] files = {reporterPath + "spark-style.css"};
        return files;
    }

    private String[] getIconFiles() {
        final String path = COMMONS + CSS + ICONS;
        final String iconDirPath = "fontawesome" + SEP;
        final String[] files = {path + "font-awesome.min.css", path + iconDirPath + "fontawesome-webfont.eot",
                path + iconDirPath + "fontawesome-webfont.svg", path + iconDirPath + "fontawesome-webfont.ttf",
                path + iconDirPath + "fontawesome-webfont.woff", path + iconDirPath + "fontawesome-webfont.woff2",
                path + iconDirPath + "FontAwesome.otf"};
        return files;
    }

    private String[] getImgFiles() {
        final String path = COMMONS + IMG;
        final String[] files = {path + "logo.png"};
        return files;
    }
}