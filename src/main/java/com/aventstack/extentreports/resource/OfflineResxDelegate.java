package com.aventstack.extentreports.resource;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.aventstack.extentreports.utils.FileUtil;
import com.aventstack.extentreports.utils.ResourceUtil;

public class OfflineResxDelegate {
	
	public static void saveOfflineResources(String baseResourceDirectory, String[] resx, String toPath) {
		FileUtil.createDirectory(toPath);
		for (String f : resx) {
			Path path = Paths.get(baseResourceDirectory, f);
			String fromPath = path.toString();
			String toPathComplete = Paths.get(toPath, new File(f).getName()).toString();
            ResourceUtil.moveResource(fromPath, toPathComplete);
        }
	}
	
	public static void saveOfflineResources(String[] resx, String toPath) {
		for (String f : resx) {
            ResourceUtil.moveResource(f, toPath);
        }
	}

}
