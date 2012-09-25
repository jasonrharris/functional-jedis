package com.jedisWrapper.jedis;

import com.google.inject.Inject;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Decorates the PoolManaged resource (a Jedis instance) with methods specified by the
 * Master and Slave versions of BasicJedisCommands as required.
 * User: jasonhome
 * Date: 07/04/2012
 * Time: 18:33
 */
public class BasicAndAllJedisCommandJedisFactory implements MasterAndSlaveJedisFactory<BasicJedisCommands, AllJedisCommands>{
    private final PoolManager<Jedis, JedisPool> poolManager;

    @Inject
    public BasicAndAllJedisCommandJedisFactory(PoolManager<Jedis, JedisPool> poolManager) {
        this.poolManager = poolManager;
    }

    public AllJedisCommands decorateJedisMasterPool() {
        return new JedisDecorator(poolManager.getMasterPool().getResource());
    }

    public BasicJedisCommands decorateJedisSlavePool() {
        return new JedisBasicDecorator(poolManager.getSlavePool().getResource());
    }

    public void returnMasterResource(AllJedisCommands masterResource) {
        poolManager.getMasterPool().returnResource(masterResource.getDecoratedResource());
    }

    public void returnSlaveResource(BasicJedisCommands slaveResource) {
        poolManager.getMasterPool().returnResource(slaveResource.getDecoratedResource());
    }
}
