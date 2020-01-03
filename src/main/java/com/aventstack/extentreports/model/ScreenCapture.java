package com.aventstack.extentreports.model;

public class ScreenCapture extends Media {

	private static final long serialVersionUID = 3876935785138278521L;
	private String base64;

	public String getBase64String() {
		return base64;
	}

	public void setBase64String(String base64) {
		this.base64 = base64;
	}

	public String getScreenCapturePath() {
		return getPath() != null ? getPath() : getBase64String();
	}

	public Boolean isBase64() {
		return getBase64String() != null;
	}
	
	public String getSource() {
        if (getBase64String() != null)
            return "<a href='" + getScreenCapturePath() + "' data-featherlight='image'><span class='label grey badge white-text text-white'>base64-img</span></a>";
        return "<img class='r-img' onerror='this.style.display=\"none\"' data-featherlight='" + getScreenCapturePath() + "' src='" + getScreenCapturePath() + "' data-src='" + getScreenCapturePath() + "'>";
    }
        
}
