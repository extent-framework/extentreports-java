package com.aventstack.extentreports.config.external;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.file.Files;

import com.aventstack.extentreports.util.Assert;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;

public class JsonConfigLoader<T> implements ConfigLoadable<T> {
    private File f;
    private String json;
    private T instance;
    private InstanceCreator<T> creator;

    public JsonConfigLoader(T instance, File f) throws FileNotFoundException {
    	Assert.notNull(f, "File must not be null");
        if (!f.exists())
            throw new FileNotFoundException("File " + f.getAbsolutePath() + " could not be found");
        init(instance);
        this.f = f;
    }

    public JsonConfigLoader(T instance, String json) {
    	Assert.notEmpty(json, "Json input must not be null or empty");
        init(instance);
        this.json = json;
    }

    private void init(T instance) {
        this.instance = instance;
        creator = type -> instance;
    }

    @SuppressWarnings("unchecked")
    public void apply() {
        final Gson gson = new GsonBuilder()
                .registerTypeAdapter(instance.getClass(), creator)
                .create();
        try {
            String json = f != null ? String.join("\n", Files.readAllLines(f.toPath())) : this.json;
            instance = (T) gson.fromJson(json, instance.getClass());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
