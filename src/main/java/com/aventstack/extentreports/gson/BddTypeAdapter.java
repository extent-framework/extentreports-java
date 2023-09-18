package com.aventstack.extentreports.gson;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.aventstack.extentreports.gherkin.model.IGherkinFormatterModel;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class BddTypeAdapter extends TypeAdapter<Class<? extends IGherkinFormatterModel>> {

    private static final Logger LOG = Logger.getLogger(BddTypeAdapter.class.getName());

    @SuppressWarnings("unchecked")
    @Override
    public Class<? extends IGherkinFormatterModel> read(final JsonReader reader) throws IOException {
        while (reader.hasNext()) {
            final JsonToken token = reader.peek();
            if ("string".equalsIgnoreCase(token.name())) {
                final String s = reader.nextString();
                if (s != null && !s.isEmpty()) {
                    try {
                        return (Class<? extends IGherkinFormatterModel>) Class.forName(s);
                    } catch (ClassNotFoundException e) {
                        LOG.log(Level.SEVERE, "Failed to convert Gherkin type", e);
                    }
                }
            }
        }
        return null;
    }

    @Override
    public void write(final JsonWriter out, final Class<? extends IGherkinFormatterModel> value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }
        out.value(value.getName());
    }

}
