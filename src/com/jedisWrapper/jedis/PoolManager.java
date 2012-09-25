package com.jedisWrapper.jedis;

import com.google.inject.ImplementedBy;
import redis.clients.util.Pool;

/**
 * Manages master and slave pools of the specified type
 * User: jasonhome
 * Date: 19/01/2012
 * Time: 06:57
 */
@ImplementedBy(JedisPoolManager.class)
public interface PoolManager<T, P extends Pool<T>> {
    P getMasterPool();

    P getSlavePool();
}
