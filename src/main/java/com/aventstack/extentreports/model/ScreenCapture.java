package com.aventstack.extentreports.model;

public class ScreenCapture 
	extends Media { 

    private static final long serialVersionUID = -3413285738443448335L;

    public String getSource() {
        if (getBase64String() != null)
            return "<br/><a href='" + getScreenCapturePath() + "' data-featherlight='image'><span class='label grey white-text'>base64-img</span></a>";

        return "<img class='r-img' onerror='this.style.display=\"none\"' data-featherlight='" + getScreenCapturePath() + "' src='" + getScreenCapturePath() + "' data-src='" + getScreenCapturePath() + "'>";
    }
    
    public String getSourceWithIcon() {
        return "<a href='#' class='indigo badge' data-featherlight='" + getScreenCapturePath() + "'>img</a>";
    }
    
    private String getScreenCapturePath() {
        String path = getPath() != null ? getPath() : getBase64String();
        return path;
    }

}