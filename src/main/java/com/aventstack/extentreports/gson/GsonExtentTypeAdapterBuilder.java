package com.aventstack.extentreports.gson;

import com.aventstack.extentreports.append.ScreenCaptureTypeAdapter;
import com.aventstack.extentreports.model.Media;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class GsonExtentTypeAdapterBuilder {
    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        GsonBuilder builder = new GsonBuilder();

        public Builder withBddTypeAdapterFactory() {
            builder.registerTypeAdapterFactory(new BddTypeAdapterFactory());
            return this;
        }

        public Builder withScreenCaptureTypeAdapter() {
            builder.registerTypeAdapter(Media.class, new ScreenCaptureTypeAdapter());
            return this;
        }

        public Gson build() {
            return builder.create();
        }
    }
}
