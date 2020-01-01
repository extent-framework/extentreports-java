package com.aventstack.extentreports.gherkin.model;

import java.io.IOException;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

public class GherkinModelSerializer extends TypeAdapter<IGherkinFormatterModel> {

	@Override
	public void write(JsonWriter out, IGherkinFormatterModel value) throws IOException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public IGherkinFormatterModel read(JsonReader in) throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

}
