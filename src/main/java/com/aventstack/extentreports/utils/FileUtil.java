package com.aventstack.extentreports.utils;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;

public class FileUtil {

	private FileUtil() {
	}

	public static String getFileName(File f) {
		if (f == null || !f.exists())
			return "";
		return f.getName();
	}

	public static String getFileName(String path) {
		return getFileName(new File(path));
	}

	public static String getFileNameWithoutExtension(File f) {
		String name = f.getName();
		int i = name.lastIndexOf('.');
		if (i > 0)
			return name.substring(0, i);

		return name;
	}

	public static String getFileNameWithoutExtension(String filePath) {
		return getFileNameWithoutExtension(new File(filePath));
	}

	public static String getExtension(File f) {
		String name = f.getName();
		int i = name.lastIndexOf('.');
		if (i > 0)
			return name.substring(i + 1);
		return "";
	}

	public static String getExtension(String filePath) {
		return getExtension(new File(filePath));
	}

	public static Boolean isDirectory(String filePath) {
		return isDirectory(new File(filePath));
	}

	public static Boolean isDirectory(File file) {
		Path path = file.toPath();
		return Files.isDirectory(path);
	}

	public static void createDirectory(String path) {
		new File(path).mkdirs();
	}

	public static void createDirectory(String[] path) {
		Arrays.asList(path).forEach(FileUtil::createDirectory);
	}

	public static Boolean fileExists(String path) {
		File f = new File(path);
		return f.exists() && !f.isDirectory();
	}

	public static long getFileSize(String path) {
		if (path == null)
			return 0;
		File f = new File(path);
		return f.exists() ? f.length() : 0;
	}

}