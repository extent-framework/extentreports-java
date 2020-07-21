package com.aventstack.extentreports.reporter;

import java.io.IOException;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.aventstack.extentreports.model.MetaDataStorable;
import com.aventstack.extentreports.model.ScreenCapture;
import com.aventstack.extentreports.model.Test;

public class KlovMediaStorageHandler {
    private MediaStorage mediaStorage;
    private KlovMedia klovMedia;

    public KlovMediaStorageHandler(String url, KlovMedia klovMedia) throws IOException {
        if (url == null || url.isEmpty())
            throw new IllegalArgumentException("Invalid URL or resource not found");
        this.klovMedia = klovMedia;
        this.mediaStorage = new HttpMediaManagerImplKlov();
        mediaStorage.init(url);
    }

    public void saveScreenCapture(MetaDataStorable el, ScreenCapture media) throws IOException {
        Document doc = new Document("project", klovMedia.getProjectId())
                .append("report", klovMedia.getReportId())
                .append("test", media.getInfoMap().get(ExtentKlovReporter.TEST_ID_KEY));

        if (el.getClass() != Test.class) {
            doc.append("log", media.getInfoMap().get(ExtentKlovReporter.LOG_ID_KEY));
        } else {
            doc.append("testName", ((Test) el).getName());
        }

        if (media.getBase64() != null)
            doc.append("base64String", media.getBase64());

        klovMedia.getMediaCollection().insertOne(doc);
        ObjectId mediaId = MongoUtil.getId(doc);
        media.getInfoMap().put(ExtentKlovReporter.ID_KEY, mediaId);
        media.getInfoMap().put(ExtentKlovReporter.REPORT_ID_KEY, klovMedia.getReportId());

        if (media.getBase64() != null)
            return;

        mediaStorage.storeMedia((ScreenCapture) media);
    }

}
