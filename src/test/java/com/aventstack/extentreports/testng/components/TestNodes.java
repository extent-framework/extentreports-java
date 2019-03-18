package com.aventstack.extentreports.testng.components;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.common.ExtentManager;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class TestNodes {
	private static final ExtentReports extent = ExtentManager.createInstance();
	private static final ExtentTest parent = extent.createTest("Test");


	@Test(dataProvider = "avoidConcurrentModifException")
	public void testConcurrentModifException(final int msg) {
		final ExtentTest child = parent.createNode("node");
		child.log(Status.INFO, "something");
		if (msg % 2 == 0) {
			child.pass("Tet passed");
		} else {
			child.fail("Parent failed");
		}
	}

	@DataProvider(name = "avoidConcurrentModifException", parallel = true)
	private Object[][] avoidConcurrentModifException() {
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
		};
	}
}