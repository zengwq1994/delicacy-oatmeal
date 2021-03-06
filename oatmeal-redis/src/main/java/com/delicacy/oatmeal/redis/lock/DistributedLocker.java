package com.delicacy.oatmeal.redis.lock;

import java.util.concurrent.TimeUnit;

public interface DistributedLocker {

	void lock(String entityId);

	void unlock(String entityId);

	void lock(String entityId, int timeout);
	
	void lock(String entityId, TimeUnit unit ,int timeout);

	boolean tryLock(String entityId, int leaseTime);

	boolean tryLock(String entityId, int waitTime,int leaseTime);

	boolean tryLock(String entityId, TimeUnit unit ,int waitTime,int leaseTime);
}
