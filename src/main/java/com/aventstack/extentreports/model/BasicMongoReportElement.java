package com.aventstack.extentreports.model;

import org.bson.types.ObjectId;

/**
 * Marker interface for MongoDB elements
 *
 */
public interface BasicMongoReportElement {
 
	public ObjectId getObjectId();

	public void setObjectId(ObjectId id);
 
}