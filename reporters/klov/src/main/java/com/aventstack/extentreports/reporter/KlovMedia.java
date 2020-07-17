package com.aventstack.extentreports.reporter;

import org.bson.Document;
import org.bson.types.ObjectId;

import com.mongodb.client.MongoCollection;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class KlovMedia {
    private ObjectId reportId;
    private ObjectId projectId;
    private MongoCollection<Document> mediaCollection;
}
