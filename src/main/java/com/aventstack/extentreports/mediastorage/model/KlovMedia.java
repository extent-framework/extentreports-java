package com.aventstack.extentreports.mediastorage.model;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;

public class KlovMedia {

	private ObjectId reportId;
	private ObjectId projectId;
	private MongoCollection<Document> mediaCollection;
	
	public KlovMedia() { }
	
	public KlovMedia(ObjectId projectId, ObjectId reportId, MongoCollection<Document> mediaCollection) {
		this.projectId = projectId;
		this.reportId = reportId;
		this.mediaCollection = mediaCollection;
	}
	
	public ObjectId getReportId() {
		return reportId;
	}
	
	public void setReportId(ObjectId reportId) {
		this.reportId = reportId;
	}
	
	public ObjectId getProjectId() {
		return projectId;
	}
	
	public void setProjectId(ObjectId projectId) {
		this.projectId = projectId;
	}
	
	public MongoCollection<Document> getMediaCollection() {
		return mediaCollection;
	}
	
	public void setMediaCollection(MongoCollection<Document> mediaCollection) {
		this.mediaCollection = mediaCollection;
	}
	
}
