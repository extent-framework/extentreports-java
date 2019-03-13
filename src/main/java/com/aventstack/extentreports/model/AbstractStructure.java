package com.aventstack.extentreports.model;

import com.aventstack.extentreports.concurrent.ReadWriteList;

import java.io.Serializable;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

public class AbstractStructure<T> implements Serializable {

	private static final long serialVersionUID = -2630417398255980331L;
	private final ReadWriteList<T> readWriteList;


	AbstractStructure() {
		readWriteList = new ReadWriteList<>();
	}

	public void add(T t) {
		readWriteList.add(t);
	}

	public T get(int x) {
		return readWriteList.get(x);
	}

	public T getFirst() {
		return readWriteList.get(0);
	}

	public T getLast() {
		return readWriteList.get(readWriteList.size() - 1);
	}

	public List<T> getAll() {
		return readWriteList.getList();
	}

	public boolean isEmpty() {
		return readWriteList.isEmpty();
	}

	public int size(){
		return readWriteList.size();
	}
}
