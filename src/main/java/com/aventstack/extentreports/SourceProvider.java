package com.aventstack.extentreports;

/**
 * Some formatters can provide the source which is also written to file,
 * for example, Email formatters or reporters.  Along with writing the contents
 * to a file, you can retrieve the source using which an email can be sent.
 */
public interface SourceProvider {

	String getSource();
	
}
