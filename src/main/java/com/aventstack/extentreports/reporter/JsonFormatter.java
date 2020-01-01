package com.aventstack.extentreports.reporter;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import com.aventstack.extentreports.ReportAggregates;
import com.aventstack.extentreports.model.Test;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JsonFormatter extends BasicFileReporter {

	public JsonFormatter(String path) {
		super(path);
	}

	public JsonFormatter(File file) {
		super(file);
	}
	
	@Override
	public void start() {

	}

	@Override
	public synchronized void flush(ReportAggregates reportAggregates) {
		super.flush(reportAggregates);
		GsonBuilder builder = new GsonBuilder(); 
		//builder.registerTypeAdapter(Given.class, new GherkinModelSerializer());
		Gson gson = builder.create();
		try (FileWriter writer = new FileWriter(getFilePath())) {
			List<Test> l = reportAggregates.getTestList();
			gson.toJson(l, writer);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String getReporterName() {
		return "JSON";
	}

}
