package com.aventstack.extentreports.model.service;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.model.Media.MediaType;
import com.aventstack.extentreports.model.ScreenCapture;
import com.aventstack.extentreports.model.Video;

public class MediaService {
    public static void tryResolveMediaPath(Media media, String[] knownPath) {
        if (knownPath == null || (media instanceof ScreenCapture && ((ScreenCapture) media).getBase64() != null)
        		|| (media instanceof Video && ((Video) media).getBase64() != null))
            return;
        String loc = media.getPath();
        File f = new File(loc);
        if (!new File(loc).exists()) {
            for (String p : knownPath) {
                Path path = Paths.get(p, loc);
                if (path.toFile().exists()) {
                    media.setResolvedPath(path.toFile().getAbsolutePath());
                    break;
                }
                path = Paths.get(p, f.getName());
                if (path.toFile().exists()) {
                    media.setResolvedPath(path.toFile().getAbsolutePath());
                    break;
                }
            }
        }
    }

    public static boolean isBase64(Media m) {
        return m instanceof ScreenCapture && ((ScreenCapture) m).getBase64() != null;
    }

    public static String getBase64(Media m) {
        if (isBase64(m))
            return ((ScreenCapture) m).getBase64();
        return null;
    }
    
    public static boolean isVideoBase64(Media m) {
		return m instanceof Video && ((Video) m).getBase64() != null;
	}

	public static String getVideoBase64(Media m) {
		if (isVideoBase64(m))
			return ((Video) m).getBase64();
		return null;
	}
	
	public static Media createMedia(String type, String path, String resolvedPath, String title, String base64) {
		if (MediaType.valueOf(type) == MediaType.SCREENCAPTURE) {
			ScreenCapture sc = ScreenCapture.builder().build();
			if (base64 != null)
				sc.setBase64(base64);
			updatePaths(sc,  path,  resolvedPath, title);
			return sc;
		}
		if (MediaType.valueOf(type) == MediaType.VIDEO) {
			Video vid = Video.builder().build();
			if (base64 != null)
				vid.setBase64(base64);
			updatePaths(vid,  path,  resolvedPath, title);
			return vid;
		}
		return null;
	}
	
	private static void updatePaths(Media media, String path, String resolvedPath, String title) {
		if (path != null)
			media.setPath(path);
		if (resolvedPath != null)
			media.setResolvedPath(resolvedPath);
		if (title != null)
			media.setTitle(title);
	}
}
