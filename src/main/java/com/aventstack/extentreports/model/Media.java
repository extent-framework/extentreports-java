package com.aventstack.extentreports.model;

import java.io.Serializable;
import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Media implements Serializable, BaseEntity, MetaDataStorable {
    private static final long serialVersionUID = 5428859443090457608L;

    private MediaType type;
    private String path;
    private String title;
    private String resolvedPath;
    private transient Map<String, Object> infoMap;
    
    public enum MediaType {
    	SCREENCAPTURE, VIDEO
    }
}
