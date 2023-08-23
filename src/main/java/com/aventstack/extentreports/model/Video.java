package com.aventstack.extentreports.model;

import java.io.Serializable;
import java.util.HashMap;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Video extends Media implements Serializable {
	private static final long serialVersionUID = -6874043323709622353L;

    public static final String BASE64_ENCODED = "data:video/mp4;base64,";

	private String base64;

	@Builder
	public Video(String path, String title, String resolvedPath, String base64) {
		super(MediaType.VIDEO, path, title, resolvedPath, new HashMap<String, Object>());
		this.base64 = base64;
	}
}
