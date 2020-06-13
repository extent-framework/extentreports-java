package com.aventstack.extentreports.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileWriterBuffered {

	private static class WriterInstance {
		static final FileWriterBuffered INSTANCE = new FileWriterBuffered();

		private WriterInstance() {
		}
	}

	static final Logger logger = Logger.getLogger(FileWriterBuffered.class.getName());

	private FileWriterBuffered() {
	}

	public synchronized void write(final File f, String text) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(f))) {
			writer.write(text);
		} catch (Exception e) {
			logger.log(Level.SEVERE, f.getPath(), e);
		}
	}

	public static FileWriterBuffered getInstance() {
		return WriterInstance.INSTANCE;
	}

}