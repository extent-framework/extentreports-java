package com.aventstack.extentreports.mediastorage;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.logging.Logger;

import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.utils.FileUtil;

public class LocalMediaStorageHandler 
	implements MediaStorage {
    
    private static final Logger logger = Logger.getLogger(LocalMediaStorageHandler.class.getName());

    private String reporterGeneratedFilePath;
    private String targetPath;
    private String relativePath;
    
    public void init(String reporterGeneratedFilePath) {
        this.reporterGeneratedFilePath = reporterGeneratedFilePath;
        mkDirs();
    }
    
    public void storeMedia(Media screenCapture) throws IOException {
        storeMediaFileLocal(screenCapture);
    }
    
    private void mkDirs() {
    	String ext = FileUtil.getExtension(reporterGeneratedFilePath);
    	String archiveName = "";
    	if (ext.equalsIgnoreCase("htm") || ext.equalsIgnoreCase("html")) {
    		archiveName = FileUtil.getFileNameWithoutExtension(reporterGeneratedFilePath) + ".";
    	}
        String absolutePath = new File(reporterGeneratedFilePath).getAbsolutePath().replace("\\", "/");
        String basePath = archiveName.isEmpty() ? absolutePath : new File(absolutePath).getParent().replace("\\", "/");
        archiveName = archiveName.isEmpty() ? archiveName = FileUtil.getFileNameWithoutExtension(reporterGeneratedFilePath) + "." : archiveName;
        
        mkDirs(basePath, archiveName, 0);
    }
    
    private void mkDirs(String basePath, String fileName, int cnt) {
        relativePath = fileName + cnt + "/";
        targetPath = basePath + "/" + relativePath;
        
        File f = new File(targetPath);
        if (!f.exists()) {
            f.mkdirs();
        }
        else {
            mkDirs(basePath, fileName, ++cnt);
        }
    }
    
    private void storeMediaFileLocal(Media media) throws IOException {
        File f = new File(media.getPath());
        if (!f.exists()) {
            logger.warning("Unable to locate media file: " + media.getPath());
            return;
        }
        
        String baseFileName = new File(media.getPath()).getName();
        String mediaFileName = baseFileName;
        String copyToPath = targetPath + baseFileName;
        File copyToFile = new File(copyToPath);
        int cnt = 0;

        while(copyToFile.exists()) {
            String name = FileUtil.getFileNameWithoutExtension(baseFileName) + "." + cnt;
            String ext = FileUtil.getExtension(baseFileName);
            mediaFileName = name + "." + ext;
            
            copyToPath = targetPath + mediaFileName + "/";
            copyToFile = new File(copyToPath);
            
            cnt ++;
        }
        
        Path p = Paths.get(copyToPath);
        Files.copy(new FileInputStream(f), p);
        media.setPath(relativePath + mediaFileName);
    }
    
}
