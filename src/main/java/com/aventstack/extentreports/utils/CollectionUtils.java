package com.aventstack.extentreports.utils;

import java.util.Collection;

public class CollectionUtils {

	private CollectionUtils(){}

	public static boolean isNull(Collection<?> col) {
		return col == null;
	}

	public static boolean isEmpty(Collection<?> col) {
		return isNull(col) || col.isEmpty();
	}

	public static boolean isNotEmpty(Collection<?> col) {
		return !isEmpty(col);
	}
}
