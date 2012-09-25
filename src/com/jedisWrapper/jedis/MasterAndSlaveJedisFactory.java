package com.jedisWrapper.jedis;

import com.google.inject.ImplementedBy;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * Transform Jedis to required types of BasicJedisCommands (one slave, one master)
 * User: jasonhome
 * Date: 07/04/2012
 * Time: 18:18
 */
@ImplementedBy(BasicAndAllJedisCommandJedisFactory.class)
public interface MasterAndSlaveJedisFactory<B extends BasicJedisCommands, A extends BasicJedisCommands> {
    A decorateJedisMasterPool();

    B decorateJedisSlavePool();

    void returnMasterResource(AllJedisCommands masterResource);

    void returnSlaveResource(BasicJedisCommands slaveResource);
}
