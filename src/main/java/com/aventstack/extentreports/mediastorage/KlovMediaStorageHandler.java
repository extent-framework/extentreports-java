package com.aventstack.extentreports.mediastorage;

import java.io.IOException;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.aventstack.extentreports.mediastorage.impl.HttpMediaManagerImplKlov;
import com.aventstack.extentreports.mediastorage.model.KlovMedia;
import com.aventstack.extentreports.model.BasicMongoReportElement;
import com.aventstack.extentreports.model.ScreenCapture;
import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.utils.MongoUtil;

public class KlovMediaStorageHandler {

	private MediaStorage mediaStorage;
	private KlovMedia klovMedia;
	
	public KlovMediaStorageHandler(String url, KlovMedia klovMedia) throws IOException {
		if (url == null || url.isEmpty()) {
			throw new IllegalArgumentException("Invalid URL or resource not found");
		}
		this.klovMedia = klovMedia;
		this.mediaStorage = new HttpMediaManagerImplKlov();
		mediaStorage.init(url);
	}

	public void saveScreenCapture(BasicMongoReportElement el, ScreenCapture media) throws IOException {
    	Document doc = new Document("project", klovMedia.getProjectId())
                .append("report", klovMedia.getReportId())
                .append("sequence", media.getSequence())
                .append("test", media.getBsonId().get("testId"));

        if (el.getClass() != Test.class) {
            doc.append("log", el.getObjectId());
        } else {
            doc.append("testName", ((Test)el).getName());
        }
        
        klovMedia.getMediaCollection().insertOne(doc);
        ObjectId mediaId = MongoUtil.getId(doc);
        media.getBsonId().put("id", mediaId);
        media.getBsonId().put("reportId", klovMedia.getReportId());
        mediaStorage.storeMedia((ScreenCapture)media);
    }
	
}
