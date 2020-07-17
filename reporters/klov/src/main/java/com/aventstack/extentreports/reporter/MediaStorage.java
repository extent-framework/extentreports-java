package com.aventstack.extentreports.reporter;

import java.io.IOException;

import com.aventstack.extentreports.model.Media;

public interface MediaStorage {
    void init(String v) throws IOException;
    void storeMedia(Media m) throws IOException;
}
