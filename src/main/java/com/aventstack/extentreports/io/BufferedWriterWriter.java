package com.aventstack.extentreports.io;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BufferedWriterWriter {

    private static class WriterInstance {
        static final BufferedWriterWriter INSTANCE = new BufferedWriterWriter();

        private WriterInstance() {
        }
    }

    static final Logger logger = Logger.getLogger(BufferedWriterWriter.class.getName());

    private BufferedWriterWriter() {
    }

    public synchronized void write(final File f, String text, String encoding) {
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                        new FileOutputStream(f), encoding))) {
            writer.write(text);
        } catch (IOException e) {
            logger.log(Level.SEVERE, f.getPath(), e);
        }
    }

    public static BufferedWriterWriter getInstance() {
        return WriterInstance.INSTANCE;
    }

}