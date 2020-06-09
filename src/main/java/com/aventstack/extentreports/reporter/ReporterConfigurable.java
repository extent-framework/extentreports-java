package com.aventstack.extentreports.reporter;

import java.io.File;
import java.io.IOException;

@FunctionalInterface
public interface ReporterConfigurable {
    void loadConfig(File jsonFile) throws IOException;
}
