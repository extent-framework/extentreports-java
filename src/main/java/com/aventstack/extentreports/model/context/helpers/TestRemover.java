package com.aventstack.extentreports.model.context.helpers;

import java.util.List;
import java.util.stream.Collectors;

import com.aventstack.extentreports.model.Test;

public class TestRemover {

	/**
	 * Helper for removing test recursively. This flag determines when to break out
	 * of recursion
	 */
	private static boolean removed = false;

	private TestRemover() {
	}

	/**
	 * Remove a test using its unique ID from a list
	 * 
	 * @param list a list of {@link Test}
	 * @param test {@link Test} to be removed
	 */
	public static void remove(List<Test> testList, Test test) {
		removed = false;
		findAndRemoveTest(testList, test);
		testList.forEach(Test::end);
	}

	/**
	 * Recursively traverses all tests, nodes upto the last leaf to find and remove
	 * the specified test
	 * 
	 * @param list a list of {@link Test}
	 * @param test {@link Test} to be removed
	 */
	private synchronized static void findAndRemoveTest(List<Test> list, Test test) {
		List<Test> filteredTestList = list.stream().filter(x -> x.getId() == test.getId()).collect(Collectors.toList());

		if (filteredTestList.size() == 1) {
			removeTest(list, filteredTestList.get(0));
			removed = true;
			return;
		}

		for (Test t : list) {
			if (removed) {
				return;
			}
			findAndRemoveTest(t.getNodeContext().getAll(), test);
		}
	}

	/**
	 * Removes the test from a given list of tests
	 * 
	 * @param list a list of {@link Test}
	 * @param test {@link Test} to be removed
	 */
	private static void removeTest(List<Test> list, Test test) {
		list.remove(test);
	}

}