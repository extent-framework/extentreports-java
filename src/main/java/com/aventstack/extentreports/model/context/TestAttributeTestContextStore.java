package com.aventstack.extentreports.model.context;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import com.aventstack.extentreports.model.Attribute;
import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.model.context.helpers.TestRemover;

/**
 * Uses an attribute context for {@link TestAttribute} (Category, Device, Author
 * etc.) and tracks the collection of tests segregated by the type
 * {@link TestAttribute}
 *
 * @param <T> A {@link TestAttribute} type
 */
public class TestAttributeTestContextStore<T extends Attribute> {

	private List<TestAttributeTestContext<T>> testAttrTestContext;

	public TestAttributeTestContextStore() {
		testAttrTestContext = new ArrayList<>();
	}

	public void setAttributeContext(T attr, Test test) {
		Optional<TestAttributeTestContext<T>> optTestContext = testAttrTestContext.stream()
				.filter(x -> x.getName().equals(attr.getName())).findFirst();

		if (optTestContext.isPresent()) {
			List<Test> tests = optTestContext.get().getTests();

			boolean b = tests.stream().anyMatch(t -> t.getId() == test.getId());

			if (!b) {
				optTestContext.get().setTest(test);
			}
			optTestContext.get().refreshTestStatusCounts();
		} else {
			TestAttributeTestContext<T> testAttrContext = new TestAttributeTestContext<>(attr);
			testAttrContext.setTest(test);
			testAttrTestContext.add(testAttrContext);
		}
	}

	public synchronized void removeTest(Test test) {
		Iterator<TestAttributeTestContext<T>> iter = testAttrTestContext.iterator();
		while (iter.hasNext()) {
			TestAttributeTestContext<T> context = iter.next();
			TestRemover.remove(context.getTests(), test);
			if (context.isEmpty()) {
				iter.remove();
			}
		}
	}

	public List<TestAttributeTestContext<T>> getTestAttributeTestContext() {
		return testAttrTestContext;
	}

}