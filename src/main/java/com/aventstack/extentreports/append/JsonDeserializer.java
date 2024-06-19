package com.aventstack.extentreports.append;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import com.aventstack.extentreports.gson.GsonExtentTypeAdapterBuilder;
import com.aventstack.extentreports.model.Test;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class JsonDeserializer {
    private final File f;

    public JsonDeserializer(final File f) {
        this.f = f;
    }

    public List<Test> deserialize() throws IOException {
        Gson gson = GsonExtentTypeAdapterBuilder.builder()
                .withMediaTypeAdapter()
                .withGsonTypeAdapterFactory()
                .build();
        String json = new String(Files.readAllBytes(f.toPath()));
        Type t = new TypeToken<ArrayList<Test>>(){}.getType();
        return gson.fromJson(json, t);
    }
}