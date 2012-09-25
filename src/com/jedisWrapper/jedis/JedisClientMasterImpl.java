package com.jedisWrapper.jedis;

import javax.inject.Inject;

/**
 * This connects the DAOs to a Jedis instance
 * User: Jason
 * Date: 05/11/11
 * Time: 08:30
 */
public final class JedisClientMasterImpl implements JedisClient<AllJedisCommands>{

    private final MasterAndSlaveJedisFactory<BasicJedisCommands, AllJedisCommands> jedisFactory;

    @Inject
    public JedisClientMasterImpl(MasterAndSlaveJedisFactory<BasicJedisCommands, AllJedisCommands> jedisFactory) {
        this.jedisFactory = jedisFactory;
    }


    public AllJedisCommands getJedis() {
        return jedisFactory.decorateJedisMasterPool();
    }

    public void returnResource(AllJedisCommands jedis) {
        jedisFactory.returnMasterResource(jedis);
    }
}
