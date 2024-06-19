package com.aventstack.extentreports.gson;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;

public class ThrowableTypeAdapter extends TypeAdapter<Throwable> {

    @Override
    public Throwable read(final JsonReader reader) {
        return new UnsupportedOperationException();
    }

    @Override
    public void write(final JsonWriter out, final Throwable value) throws IOException {
        if (value == null) {
            out.nullValue();
            return;
        }

        out.beginObject();
        out.name("type");
        out.value(value.getClass().getSimpleName());

        out.name("message");
        out.value(value.getMessage());

        final Throwable cause = value.getCause();
        if (cause != null) {
            out.name("cause");
            write(out, cause);
        }

        out.endObject();
    }
}
