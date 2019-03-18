package com.aventstack.extentreports.testng.components;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.common.ExtentManager;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestWithMultipleDataset {
	private ExtentReports extent;
	private ExtentTest parent;

	@BeforeClass
	public void beforeClass() {
		extent = ExtentManager.createInstance();
		parent = extent.createTest("Test");
	}

	@AfterClass
	public void afterClass() {
		ExtentManager.getInstance().flush();
	}

	@Test(dataProvider = "avoidConcurrentModificationException", enabled = false)
	public void testConcurrentModificationException(final int msg) {
		final ExtentTest child = parent.createNode("node");
		child.log(Status.INFO, "something");
		if (msg % 2 == 0) {
			child.pass("Tet passed");
		} else {
			child.fail("Parent failed");
		}
	}

	@DataProvider(name = "avoidConcurrentModificationException", parallel = true)
	private Object[][] avoidConcurrentModificationException() {
		return new Object[][]{
				{1},
				{2},
				{3},
				{4},
				{5},
				{6},
				{7},
				{8},
				{9},
				{10},
				{11},
				{12},
				{13},
				{14},
				{15},
				{16},
				{17},
				{18},
				{19},
				{20},
				{21},
				{22},
				{23},
				{24},
				{25},
				{26},
				{27},
				{28},
				{29},
				{30},
				{31},
				{32},
				{33},
				{34},
				{35},
				{36},
				{37},
				{38},
				{39},
				{40},
				{41},
				{42},
				{43},
				{44},
				{45},
				{46},
				{47},
				{48},
				{49},
				{50}
		};
	}
}