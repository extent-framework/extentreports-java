package com.aventstack.extentreports.model;

import java.io.Serializable;
import java.util.HashMap;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScreenCapture extends Media implements Serializable {
	private static final long serialVersionUID = -3047762572007885369L;
	
    public static final String BASE64_ENCODED = "data:image/png;base64,";

	private String base64;

	@Builder
	public ScreenCapture(String path, String title, String resolvedPath, String base64) {
		super(MediaType.SCREENCAPTURE, path, title, resolvedPath, new HashMap<String, Object>());
		this.base64 = base64;
	}
}
