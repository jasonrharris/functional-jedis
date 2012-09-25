package com.jedisWrapper.jedis;

import javax.inject.Inject;

/**
 * This connects the DAOs to a Jedis read only instance
 * User: Jason
 * Date: 05/11/11
 * Time: 08:30
 */
public final class JedisClientSlaveImpl implements JedisClient<BasicJedisCommands>{
    private final MasterAndSlaveJedisFactory<BasicJedisCommands, AllJedisCommands> jedisFactory;

    @Inject
    public JedisClientSlaveImpl(MasterAndSlaveJedisFactory<BasicJedisCommands, AllJedisCommands> jedisFactory) {
        this.jedisFactory = jedisFactory;
    }

    public BasicJedisCommands getJedis() {
        return jedisFactory.decorateJedisSlavePool();
    }

    public void returnResource(BasicJedisCommands jedis) {
        jedisFactory.returnSlaveResource(jedis);
    }
}
