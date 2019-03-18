package com.aventstack.extentreports.concurrent;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Test;
import com.aventstack.extentreports.utils.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.Consumer;

/**
 * ReadWriteList.java
 * This class demonstrates how to use ReadWriteLock to add concurrency
 * features to a non-threadsafe collection
 *
 * @author www.codejava.net
 */
public class ReadWriteList<E> {
	protected final List<E> list;
	protected final ReadWriteLock rwLock;

	public List<E> getList() {
		return list;
	}

	public ReadWriteList(List<E> list) {
		this.list = Collections.synchronizedList(list);
		this.rwLock = new ReentrantReadWriteLock();
	}

	public ReadWriteList() {
		rwLock = new ReentrantReadWriteLock();
		list = Collections.synchronizedList(new ArrayList<>());
	}

	public void add(E element) {
		rwLock.writeLock().lock();
		try {
			list.add(element);
		} finally {
			rwLock.writeLock().unlock();
		}
	}

	public E get(int index) {
		rwLock.readLock().lock();
		try {
			return list.get(index);
		} finally {
			rwLock.readLock().unlock();
		}
	}

	public void clear() {
		rwLock.readLock().lock();
		try {
			list.clear();
		} finally {
			rwLock.readLock().unlock();
		}
	}

	public E remove(int index) {
		rwLock.writeLock().lock();
		try {
			return list.remove(index);
		} finally {
			rwLock.writeLock().unlock();
		}
	}

	public boolean isEmpty() {
		rwLock.readLock().lock();
		try {
			return CollectionUtils.isEmpty(list);
		} finally {
			rwLock.readLock().unlock();
		}
	}

	public boolean isNotEmpty() {
		return !isEmpty();
	}

	public int size() {
		rwLock.readLock().lock();
		try {
			return list.size();
		} finally {
			rwLock.readLock().unlock();
		}
	}

	public void streamWithLock(final ReadWriteMap<Status, Boolean> statusMap) {}


	public void forEach(Consumer<? super E> action){
		rwLock.writeLock().lock();
		try {
			list.forEach(action);
		}finally {
			rwLock.writeLock().unlock();
		}
	}
}