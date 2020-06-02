package com.aventstack.extentreports.appender;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.aventstack.extentreports.model.Test;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

class JsonDeserializer {

    public List<Test> deserialize(File f) throws IOException {
        Gson gson = new Gson();
        String json = new String(Files.readAllBytes(f.toPath()));
        Type t = new TypeToken<ArrayList<Test>>() {}.getType();
        List<Test> tests = gson.fromJson(json, t);
        return tests;
    }

}