package com.aventstack.extentreports.entity.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.model.Media;
import com.aventstack.extentreports.model.Media.MediaType;
import com.aventstack.extentreports.model.ScreenCapture;
import com.aventstack.extentreports.model.Video;
import com.aventstack.extentreports.model.service.MediaService;

public class MediaServiceTest {
	
	@Test
	public void testMediaWithExactFilePath() throws IOException {
		Path tmpDir = Files.createTempDirectory(Paths.get("target"),"known");
		Path tmpFile = Files.copy(Paths.get("src/test/resources/img.png"),Paths.get(tmpDir.toString()+"/img.png"));
		Media m = ScreenCapture.builder().path("img.png").build();		
		String[] known = {tmpDir.toString()};		
		MediaService.tryResolveMediaPath(m, known);				
		Assert.assertEquals(m.getResolvedPath(), tmpFile.toAbsolutePath().toString());		
		tmpFile.toFile().delete();
		tmpDir.toFile().delete();		
	}
	
	@Test
	public void testMediaWithSameFileName() throws IOException {
		Path tmpDir = Files.createTempDirectory(Paths.get("target"),"known");
		Path tmpFile = Files.copy(Paths.get("src/test/resources/img.png"),Paths.get(tmpDir.toString()+"/img.png"));
		Media m = ScreenCapture.builder().path("folder/img.png").build();		
		String[] known = {tmpDir.toString()};		
		MediaService.tryResolveMediaPath(m, known);				
		Assert.assertEquals(m.getResolvedPath(), tmpFile.toAbsolutePath().toString());		
		tmpFile.toFile().delete();
		tmpDir.toFile().delete();		
	}
	
	@Test
	public void testMediaWithUnavailableFile() throws IOException {
		Path tmpDir = Files.createTempDirectory(Paths.get("target"),"known");
		Media m = ScreenCapture.builder().path("img.png").build();		
		String[] known = {tmpDir.toString()};		
		MediaService.tryResolveMediaPath(m, known);				
		Assert.assertEquals(m.getResolvedPath(), null);
		Assert.assertEquals(m.getPath(), "img.png");
		tmpDir.toFile().delete();		
	}	
	
	@Test
	public void testScreenCaptureCreation() {
		Media m = MediaService.createMedia(MediaType.SCREENCAPTURE.toString(), "img.png", "known/img.png", "title", null);
		Assert.assertTrue(m instanceof ScreenCapture);
		Assert.assertEquals(m.getTitle(), "title");
		Assert.assertEquals(m.getPath(), "img.png");
		Assert.assertEquals(m.getResolvedPath(), "known/img.png");
		Assert.assertNull(((ScreenCapture)m).getBase64());
	}
	
	@Test
	public void testVideoCreation() {
		Media m = MediaService.createMedia(MediaType.VIDEO.toString(), "vid.mp4", "known/vid.mp4", "title", null);
		Assert.assertTrue(m instanceof Video);
		Assert.assertEquals(m.getTitle(), "title");
		Assert.assertEquals(m.getPath(), "vid.mp4");
		Assert.assertEquals(m.getResolvedPath(), "known/vid.mp4");
		Assert.assertNull(((Video)m).getBase64());
	}
	
	@Test
	public void testBase64VideoCreation() {
		Media m = MediaService.createMedia(MediaType.VIDEO.toString(), null, null, null, "base64 video");
		Assert.assertTrue(m instanceof Video);
		Assert.assertNull(m.getTitle());
		Assert.assertNull(m.getPath());
		Assert.assertNull(m.getResolvedPath());
		Assert.assertEquals(((Video)m).getBase64(), "base64 video");
	}
}
