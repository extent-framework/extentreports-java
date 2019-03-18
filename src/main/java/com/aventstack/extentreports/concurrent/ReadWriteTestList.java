package com.aventstack.extentreports.concurrent;

import com.aventstack.extentreports.Status;
import com.aventstack.extentreports.model.Test;

public class ReadWriteTestList extends ReadWriteList<Test> {

	@Override
	public void streamWithLock(final ReadWriteMap<Status, Boolean> statusMap) {
		rwLock.writeLock().lock();
		try {
			list.stream()
					.map(Test::getStatus)
					.distinct()
					.forEach(x -> statusMap.put(x, false));
		} finally {
			rwLock.writeLock().unlock();
		}
	}

}
