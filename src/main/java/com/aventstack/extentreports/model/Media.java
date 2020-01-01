package com.aventstack.extentreports.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.bson.types.ObjectId;

public class Media implements Serializable {

	private static final long serialVersionUID = 2620739620884939951L;
	private String name;
	private String description;
	private String path;
	private int sequence;
	private long fileSize = 0;
	private Map<String, ObjectId> bsonId;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public int getSequence() {
		return sequence;
	}

	public void setSequence(int sequence) {
		this.sequence = sequence;
	}

	public long getFileSize() {
		return fileSize;
	}

	public void setFileSize(long fileSize) {
		this.fileSize = fileSize;
	}

	public Map<String, ObjectId> getBsonId() {
		if (bsonId == null) {
			bsonId = new HashMap<>();
		}
		return bsonId;
	}

	public void setBsonId(Map<String, ObjectId> bsonId) {
		this.bsonId = bsonId;
	}

}
