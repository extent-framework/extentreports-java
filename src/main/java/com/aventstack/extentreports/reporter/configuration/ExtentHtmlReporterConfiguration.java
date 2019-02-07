package com.aventstack.extentreports.reporter.configuration;

import java.io.File;
import java.util.stream.Stream;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.resource.OfflineResxDelegate;
import com.aventstack.extentreports.utils.FileUtil;

/**
 * Defines configuration settings for the HTML reporter
 */
public class ExtentHtmlReporterConfiguration 
	extends RichViewReporterConfiguration {

    public ExtentHtmlReporterConfiguration(ExtentHtmlReporter reporter) {
		super(reporter);
	}
    
    /**
     * Creates the HTML report, saving all resources (css, js, fonts) in the same
     * location, so the report can be viewed without an internet connection
     * 
     * @param offline Setting to enable an offline accessible report
     */
    public void enableOfflineMode(Boolean offline) {
        usedConfigs.put("enableOfflineMode", String.valueOf(offline));
        usedConfigs.put("offlineDirectory", getReporter().getReporterName() + "/");
        if (offline) {
            File f = getTargetDirectory(((ExtentHtmlReporter) getReporter()).getFileFile());
            String s = "/";
            String resourcePackagePath = ExtentReports.class.getPackage().getName().replace(".", s);
            resourcePackagePath += s + "offline" + s;
            String[] resx = combine(getJSFiles(), getCSSFiles(), getIconFiles(), getImgFiles());
            OfflineResxDelegate.saveOfflineResources(resourcePackagePath, resx, f.getAbsolutePath());
        }
    }

    private File getTargetDirectory(File f) {
        String dir;
        if (FileUtil.isDirectory(f)) {
            dir = f.getAbsolutePath().replace("\\", "/");
        } else {
            dir = f.getAbsolutePath().replace("\\", "/");
            dir = new File(dir).getParent();
        }
        dir += "/" + getReporter().getReporterName();
        return new File(dir);
    }

    private String[] combine(String[]... array) {
        String[] result = new String[] {};
        for (String[] arr : array) {
            result = Stream.of(result, arr).flatMap(Stream::of).toArray(String[]::new);
        }
        return result;
    }

    private String[] getJSFiles() {
        String commonsPath = "commons/js/";
        String reporterPath = getReporter().getReporterName() + "/js/";
        String[] files = { reporterPath + "v3html-script.js", commonsPath + "jsontree.js" };
        return files;
    }

    private String[] getCSSFiles() {
        String stylesPath = "css/";
        String reporterPath = getReporter().getReporterName() + "/" + stylesPath;
        String[] files = { reporterPath + "v3html-style.css" };
        return files;
    }

    private String[] getIconFiles() {
        String path = "commons/css/icons/";
        String iconDirPath = "material/";
        String[] files = { path + "material-icons.css", path + iconDirPath + "MaterialIcons-Regular.eot",
                path + iconDirPath + "MaterialIcons-Regular.ijmap", path + iconDirPath + "MaterialIcons-Regular.svg",
                path + iconDirPath + "MaterialIcons-Regular.ttf", path + iconDirPath + "MaterialIcons-Regular.woff",
                path + iconDirPath + "MaterialIcons-Regular.woff2" };
        return files;
    }

    private String[] getImgFiles() {
        String path = "commons/img/";
        String[] files = { path + "logo.png" };
        return files;
    }

}
