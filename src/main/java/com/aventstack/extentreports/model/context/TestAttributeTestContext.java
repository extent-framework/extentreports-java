package com.aventstack.extentreports.model.context;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Attribute;
import com.aventstack.extentreports.model.Test;

public class TestAttributeTestContext<T extends Attribute> implements Serializable {

	private static final long serialVersionUID = 2595632998970711190L;

	private List<Test> tests = new ArrayList<>();
	private T attr;
	private int passed = 0;
	private int failed = 0;
	private int skipped = 0;
	private int others = 0;

	public TestAttributeTestContext(T attr) {
		this.attr = attr;
	}

	public void setTest(Test test) {
		updateTestStatusCounts(test);
		tests.add(test);
	}

	private void updateTestStatusCounts(Test test) {
		if (test.getStatus() == Status.PASS) {
			passed++;
		} else if (test.getStatus() == Status.FAIL || test.getStatus() == Status.FATAL) {
			failed++;
		} else if (test.getStatus() == Status.SKIP) {
			skipped++;
		} else {
			others++;
		}
	}

	public void refreshTestStatusCounts() {
		passed = failed = skipped = others = 0;
		tests.forEach(this::updateTestStatusCounts);
	}

	public List<Test> getTests() {
		return tests;
	}

	public String getName() {
		return attr.getName();
	}

	public int getPassed() {
		return passed;
	}

	public int getFailed() {
		return failed;
	}

	public int getSkipped() {
		return skipped;
	}

	public int getOthers() {
		return others;
	}

	public int size() {
		return tests == null ? 0 : tests.size();
	}

	public boolean isEmpty() {
		return size() == 0;
	}

	public T getAttribute() {
		return attr;
	}
	
}