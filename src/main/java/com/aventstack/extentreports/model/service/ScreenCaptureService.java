package com.aventstack.extentreports.model.service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.aventstack.extentreports.model.ScreenCapture;
import com.aventstack.extentreports.utils.FileUtil;

public class ScreenCaptureService {

	public static void resolvePath(ScreenCapture capture, String[] paths) {
		if (paths == null || capture.isBase64()) {
			return;
		}
		String capturePath = capture.getPath();
		String captureFileName = new File(capturePath).getName();
		if (!FileUtil.fileExists(capturePath)) {
			for (String p : paths) {
				Path path = Paths.get(p, capturePath);
				if (path.toFile().exists()) {
					capturePath = path.toFile().getAbsolutePath();
					break;
				}
				path = Paths.get(p, captureFileName);
				if (path.toFile().exists()) {
					capturePath = path.toFile().getAbsolutePath();
					break;
				}
			}
		}
		capture.setResolvedPath(capturePath);
	}
	
}
