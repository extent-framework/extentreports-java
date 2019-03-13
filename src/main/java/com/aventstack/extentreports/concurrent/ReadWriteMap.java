package com.aventstack.extentreports.concurrent;


import java.util.Map;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.BiConsumer;

/**
 * ReadWriteList.java
 * This class demonstrates how to use ReadWriteLock to add concurrency
 * features to a non-threadsafe collection
 *
 * @author www.codejava.net
 */
public class ReadWriteMap<K, V> {
	private final Map<K, V> map;
	private final ReadWriteLock rwLock;

	public ReadWriteMap(Map<K, V> map) {
		this.map = map;
		this.rwLock = new ReentrantReadWriteLock();
	}

	/**
	 * Put operation with write lock
	 */
	public V put(K key, V value) {
		rwLock.writeLock().lock();
		try {
			return map.put(key, value);
		} finally {
			rwLock.writeLock().unlock();
		}
	}

	/**
	 * Remove all elements from map
	 */
	public void clear(){
		rwLock.writeLock().lock();
		try {
			map.clear();
		} finally {
			rwLock.writeLock().unlock();
		}
	}

	/**
	 * Get value from ma with lock read
	 */
	public V get(K key) {
		rwLock.readLock().lock();
		try {
			return map.get(key);
		} finally {
			rwLock.readLock().unlock();
		}
	}

	public void forEach(BiConsumer<? super K, ? super V> action){
		rwLock.writeLock().lock();
		try {
			map.forEach(action);
		}finally {
			rwLock.writeLock().unlock();
		}
	}

}
