package com.aventstack.extentreports.reporter.configuration;

import com.aventstack.extentreports.reporter.AbstractReporter;

/**
 * Common configuration for file reporters:
 * 
 * <ul>
 * 	<li>{@link com.aventstack.extentreports.reporter.ExtentHtmlReporter}</li>
 * </ul>
 */
public class BasicFileConfiguration 
	extends BasicConfiguration {

    private String encoding;
    private String docTitle;
    private String css;
    private String js;
    
    protected BasicFileConfiguration() { }
    
    protected BasicFileConfiguration(AbstractReporter reporter) {
    	super(reporter);
    }
    
    /**
     * Sets file encoding, eg: UTF-8
     * 
     * @param encoding Encoding
     */
    public void setEncoding(String encoding) {
        usedConfigs.put("encoding", encoding);
        this.encoding = encoding; 
    }   
    public String getEncoding() { return encoding; }

    /**
     * Sets the document title denoted by the <code>title</code> tag
     * 
     * @param docTitle Title
     */
    public void setDocumentTitle(String docTitle) {
        usedConfigs.put("documentTitle", docTitle);
        this.docTitle = docTitle; 
    }
    public String getDocumentTitle() { return docTitle; }
    
    /**
     * Sets CSS to be used to customize the look and feel of your report
     * 
     * @param css CSS
     */
    public void setCSS(String css) { 
        usedConfigs.put("styles", css);
        this.css = css; 
    }    
    public String getCSS() { return css; }
    
    /**
     * Adds custom JavaScript
     * 
     * @param js JavaScript
     */
    public void setJS(String js) { 
        usedConfigs.put("scripts", js);
        this.js = js; 
    }
    public String getJS() { return js; }

}
