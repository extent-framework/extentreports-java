package com.aventstack.extentreports.append;

import java.io.IOException;

import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.model.service.MediaService;
import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonToken;
import com.google.gson.stream.JsonWriter;

public class MediaTypeAdapter extends TypeAdapter<Media> {

	@Override
	public void write(JsonWriter out, Media value) throws IOException {
	}

	@Override
	public Media read(JsonReader reader) throws IOException {
		reader.beginObject();
		String fieldName = null;
		int cycle = 0;

		String type = null;
		String path = null;
		String resolvedPath = null;
		String title = null;
		String base64 = null;

		while (reader.hasNext()) {
			JsonToken token = reader.peek();
			if (token.equals(JsonToken.NAME)) {
				fieldName = reader.nextName();
			}
			if ("string".equalsIgnoreCase(token.name()) && fieldName.equalsIgnoreCase("type")) {
				token = reader.peek();
				type = reader.nextString();
			}
			if ("string".equalsIgnoreCase(token.name()) && fieldName.equalsIgnoreCase("path")) {
				token = reader.peek();
				path = reader.nextString();
			}
			if ("string".equalsIgnoreCase(token.name()) && fieldName.equalsIgnoreCase("resolvedPath")) {
				token = reader.peek();
				resolvedPath = reader.nextString();
			}
			if ("string".equalsIgnoreCase(token.name()) && fieldName.equalsIgnoreCase("title")) {
				token = reader.peek();
				title = reader.nextString();
			}
			if ("string".equalsIgnoreCase(token.name()) && fieldName.equalsIgnoreCase("base64")) {
				token = reader.peek();
				base64 = reader.nextString();
			}
			if (cycle++ > 10)
				return MediaService.createMedia(type, path, resolvedPath, title, base64);
		}
		reader.endObject();
		return MediaService.createMedia(type, path, resolvedPath, title, base64);
	}
}
