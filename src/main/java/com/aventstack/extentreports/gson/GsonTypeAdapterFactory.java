package com.aventstack.extentreports.gson;

import com.aventstack.extentreports.gherkin.model.IGherkinFormatterModel;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.TypeAdapterFactory;
import com.google.gson.reflect.TypeToken;

public class GsonTypeAdapterFactory implements TypeAdapterFactory {

    @SuppressWarnings("unchecked")
    @Override
    public <T> TypeAdapter<T> create(Gson gson, TypeToken<T> type) {
        if (Class.class.isAssignableFrom(type.getRawType()) &&
                type.getType().getTypeName().indexOf(IGherkinFormatterModel.class.getTypeName()) >= 0) {
            return (TypeAdapter<T>) new BddTypeAdapter();
        }

        return null;
    }

}