package com.aventstack.extentreports.model;

public class ScreenCapture 
	extends Media { 

    private static final long serialVersionUID = -3413285738443448335L;

    public String getSource() {
        if (getBase64String() != null)
            return "<a href='" + getScreenCapturePath() + "' data-featherlight='image'><span class='label grey badge white-text text-white'>base64-img</span></a>";

        return "<img class='r-img' onerror='this.style.display=\"none\"' data-featherlight='" + getScreenCapturePath() + "' src='" + getScreenCapturePath() + "' data-src='" + getScreenCapturePath() + "'>";
    }
    
    public String getSourceWithIcon() {
        return "<a href='#' class='indigo badge' data-featherlight='" + getScreenCapturePath() + "'>img</a>";
    }
    
    public String getScreenCapturePath() {
        return getPath() != null ? getPath() : getBase64String();
    }

    public Boolean isBase64() {
        return getBase64String() != null;
    }
}