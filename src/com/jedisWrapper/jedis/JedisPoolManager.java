package com.jedisWrapper.jedis;

import com.jedisWrapper.configuration.RedisConfiguration;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import javax.inject.Singleton;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

/**
 * Creates JedisPool instances based on the supplied RedisConfiguration data
 * User: jasonhome
 * Date: 16/01/2012
 * Time: 00:02
 */
@Singleton
public class JedisPoolManager implements PoolManager<Jedis, JedisPool> {
    private final int slaveCount;
    private final JedisPool masterJedisPool;
    private final List<JedisPool> slaveJedisPools;
    private volatile byte slaveIndexRequestCount = 0;
    private final String masterHost;

    public JedisPoolManager(RedisConfiguration redisConfiguration) {
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        this.masterHost = redisConfiguration.getHost();

        this.masterJedisPool = new JedisPool(
                jedisPoolConfig, 
                masterHost,
                redisConfiguration.getPort());

        this.slaveJedisPools = initialiseSlaveJedisPool(redisConfiguration.getSlaves());
        this.slaveCount = slaveJedisPools.size();
        
    }

    private List<JedisPool> initialiseSlaveJedisPool(Set<RedisConfiguration> slaves) {
        int requiredSlavesCount = slaves.size();
        final List<JedisPool> slavePools;

        if (requiredSlavesCount > 0){
                    slavePools = new ArrayList<JedisPool>(requiredSlavesCount);
                    JedisPoolConfig jedisSlavePoolConfig = new JedisPoolConfig();
                    for (RedisConfiguration slave : slaves) {
                        slavePools.add(
                                new JedisPool(
                                        jedisSlavePoolConfig,
                                        masterHost,
                                        slave.getPort()));
                    }
                }
                else {
                    slavePools = Collections.singletonList(masterJedisPool);
                }
        return slavePools;
    }

    public JedisPool getMasterPool(){
        return masterJedisPool;
    }

    /**
     * Returns a slave, selected using a Round Robin algorithm
     * @return
     */
    public JedisPool getSlavePool(){
        final int remainder = slaveIndexRequestCount++ % slaveCount;
        return slaveJedisPools.get(remainder * Integer.signum(remainder));
    }
}
