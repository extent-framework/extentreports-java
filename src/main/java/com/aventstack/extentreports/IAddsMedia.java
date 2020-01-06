package com.aventstack.extentreports;

import java.io.IOException;

public interface IAddsMedia<T> {

	/**
	 * Adds a snapshot to the test or log with title
	 * 
	 * @param mediaPath Image path
	 * @param title     Image title
	 * 
	 * @return Object this method is called from, generally
	 *         {@link com.aventstack.extentreports.ExtentTest} or {@link com.aventstack.extentreports.model.Log}
	 * 
	 * @throws IOException thrown if the <code>imagePath</code> of image is not
	 *                     found
	 */
	T addScreenCaptureFromPath(String mediaPath, String title) throws IOException;

	/**
	 * Adds a snapshot to test or log
	 * 
	 * @param mediaPath Image path
	 * 
	 * @return Object this method is called from, generally
	 *         {@link com.aventstack.extentreports.ExtentTest} or {@link com.aventstack.extentreports.model.Log}
	 * 
	 * @throws IOException thrown if the <code>imagePath</code> of image is not
	 *                     found
	 */
	T addScreenCaptureFromPath(String mediaPath) throws IOException;

	/**
	 * Adds a base64 screenshot
	 * 
	 * @param base64     base64 string
	 * @param title Image title
	 * 
	 * @return Object this method is called from, generally
	 *         {@link com.aventstack.extentreports.ExtentTest} or {@link com.aventstack.extentreports.model.Log}
	 */
	T addScreenCaptureFromBase64String(String base64, String title);

	/**
	 * Adds a base64 screenshot
	 * 
	 * @param base64 base64 string
	 * 
	 * @return Object this method is called from, generally
	 *         {@link com.aventstack.extentreports.ExtentTest} or {@link com.aventstack.extentreports.model.Log}
	 */
	T addScreenCaptureFromBase64String(String base64);

}