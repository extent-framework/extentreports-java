package com.aventstack.extentreports.reporter;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.Status;

public class SparkMediaTest {
	private static final String PATH = "target/spark.html";

	private ExtentReports extent;

	@BeforeMethod
	public void before() {
		extent = new ExtentReports();
		extent.attachReporter(new ExtentSparkReporter(PATH));
	}

	@Test
	public void testMediaInTest() throws IOException {
		extent.createTest("Test").addScreenCaptureFromPath("../src/test/resources/img.png")
				.addVideoFromPath("../src/test/resources/vid.mp4");
		extent.flush();
		Assert.assertEquals(Files.readAllLines(new File(PATH).toPath()).stream()
				.filter(x -> x.contains("src=\"../src/test/resources/img.png\"")
						|| x.contains("src='../src/test/resources/vid.mp4'"))
				.count(), 2);
		Assert.assertTrue(Files.readAllLines(new File(PATH).toPath()).stream()
				.anyMatch(x -> x.contains("src=\"../src/test/resources/img.png\"")));
		Assert.assertTrue(Files.readAllLines(new File(PATH).toPath()).stream()
				.anyMatch(x -> x.contains("src='../src/test/resources/vid.mp4'")));
	}

	@Test
	public void testMediaInTestNode() throws IOException {
		extent.createTest("Test").createNode("node")
				.log(Status.PASS, "Screen Log",
						MediaEntityBuilder.createScreenCaptureFromPath("../src/test/resources/img.png").build())
				.log(Status.PASS, "Screen Log",
						MediaEntityBuilder.createVideoFromPath("../src/test/resources/vid.mp4").build());
		extent.flush();
		Assert.assertEquals(Files.readAllLines(new File(PATH).toPath()).stream()
				.filter(x -> x.contains("src=\"../src/test/resources/img.png\"")
						|| x.contains("src='../src/test/resources/vid.mp4'"))
				.count(), 2);
		Assert.assertTrue(Files.readAllLines(new File(PATH).toPath()).stream()
				.anyMatch(x -> x.contains("src=\"../src/test/resources/img.png\"")));
		Assert.assertTrue(Files.readAllLines(new File(PATH).toPath()).stream()
				.anyMatch(x -> x.contains("src='../src/test/resources/vid.mp4'")));
	}
}
