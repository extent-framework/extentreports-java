package com.aventstack.extentreports.config.external;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.lang.reflect.Type;

import org.testng.reporters.Files;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.InstanceCreator;

public class JsonConfigLoader<T> {
    private File f;
    private T instance;
    private InstanceCreator<T> creator;

    public JsonConfigLoader(T instance, File f) throws FileNotFoundException {
        if (f == null)
            throw new IllegalArgumentException("File cannot be null");
        if (!f.exists())
            throw new FileNotFoundException("File " + f.getAbsolutePath() + " could not be found");
        creator = new InstanceCreator<T>() {
            @Override
            public T createInstance(Type type) {
                return instance;
            }
        };
        this.instance = instance;
        this.f = f;
    }

    @SuppressWarnings("unchecked")
    public void apply() {
        Gson gson = new GsonBuilder().registerTypeAdapter(instance.getClass(), creator).create();
        try {
            String json = Files.readFile(f);
            instance = (T) gson.fromJson(json, instance.getClass());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
