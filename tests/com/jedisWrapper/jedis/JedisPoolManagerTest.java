package com.jedisWrapper.jedis;

import com.jedisWrapper.TestGroups;
import com.jedisWrapper.configuration.RedisConfiguration;
import com.jedisWrapper.configuration.RedisConfigurationBean;
import org.testng.annotations.Test;
import redis.clients.jedis.JedisPool;

import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

import static org.hamcrest.CoreMatchers.not;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

/**
 * Test for creating Jedis Pools
 * User: jasonhome
 * Date: 19/01/2012
 * Time: 06:47
 */
@Test(groups = TestGroups.UNIT)
public class JedisPoolManagerTest {
    public void shouldCreateAPoolWithAMasterAndMasterAsSlaveWhenNoSlavesSpecified() {
        RedisConfiguration redisConfig = new RedisConfiguration("201", 10);
        JedisPoolManager builder = new JedisPoolManager(redisConfig);

        JedisPool pool = builder.getMasterPool();
        assertThat(pool, notNullValue());

        JedisPool slavePool = builder.getSlavePool();
        assertThat(slavePool, sameInstance(pool));
    }

    public void shouldCreateAPoolWithOneMasterAndOneSlaveIfOneSlaveIsSpecified() {
        RedisConfigurationBean redisConfigBean = new RedisConfigurationBean();
        redisConfigBean.setHost("201");
        redisConfigBean.setPort(21);

        RedisConfiguration slaveConfig = new RedisConfiguration("101", 22);
        redisConfigBean.setSlaves(Collections.singleton(slaveConfig));

        RedisConfiguration redisConfig = new RedisConfiguration(redisConfigBean);
        JedisPoolManager builder = new JedisPoolManager(redisConfig);

        JedisPool pool = builder.getMasterPool();
        assertThat(pool, notNullValue());

        JedisPool slavePool = builder.getSlavePool();
        assertThat(slavePool, not(sameInstance(pool)));

        JedisPool slavePool2 = builder.getSlavePool();
        assertThat(slavePool2, not(sameInstance(pool)));
        assertThat(slavePool2, sameInstance(slavePool));
    }

    public void shouldCreateAPoolWithOneMasterAndSeveralSlavesAndReturnSlavesAsRoundRobinSelection() {
        JedisPoolManager builder = createBuilderWithThreeSlaves();

        JedisPool pool = builder.getMasterPool();
        assertThat(pool, notNullValue());

        JedisPool slavePool = builder.getSlavePool();
        assertThat(slavePool, not(sameInstance(pool)));

        JedisPool slavePool2 = builder.getSlavePool();
        assertThat(slavePool2, not(sameInstance(pool)));
        assertThat(slavePool2, not(sameInstance(slavePool)));

        JedisPool slavePool3 = builder.getSlavePool();
        assertThat(slavePool3, not(sameInstance(pool)));
        assertThat(slavePool3, not(sameInstance(slavePool)));
        assertThat(slavePool3, not(sameInstance(slavePool2)));

        //should now return first instance again
        JedisPool slavePool4 = builder.getSlavePool();
        assertThat(slavePool4, not(sameInstance(pool)));
        assertThat(slavePool4, sameInstance(slavePool));
        assertThat(slavePool4, not(sameInstance(slavePool2)));
        assertThat(slavePool4, not(sameInstance(slavePool3)));

    }

    public void shouldHandleRoundRobinCounterReachingMaximum() {
        JedisPoolManager builder = createBuilderWithThreeSlaves();

        JedisPool masterPool = builder.getMasterPool();
        assertThat(masterPool, notNullValue());

        Map<JedisPool, AtomicInteger> poolCount = new HashMap<JedisPool, AtomicInteger>(3);

        JedisPool lastPool = null;
        for (int i = 0; i < 260; i++) {
            JedisPool pool = builder.getSlavePool();

            if (lastPool != null){
                assertThat(pool, not(sameInstance(lastPool)));
            }

            final AtomicInteger retrievedCount = poolCount.get(pool);

            if (retrievedCount != null){
                retrievedCount.incrementAndGet();
            }
            else {
                poolCount.put(pool, new AtomicInteger(1));
            }

            lastPool = pool;                
        }
        
        Integer generalRetrievedCount = null;
        
        assertThat(poolCount.keySet().size(), equalTo(3));

        for(AtomicInteger retrievedCount : poolCount.values()){
            if (generalRetrievedCount == null){
                generalRetrievedCount = retrievedCount.get();
            }
            else {
                assertThat(retrievedCount.get(), greaterThanOrEqualTo(generalRetrievedCount - 1));
                assertThat(retrievedCount.get(), lessThanOrEqualTo(generalRetrievedCount + 1));
            }
        }
    }


    private JedisPoolManager createBuilderWithThreeSlaves() {
        RedisConfigurationBean redisConfigBean = new RedisConfigurationBean();
        redisConfigBean.setHost("201");
        redisConfigBean.setPort(21);

        RedisConfiguration slaveConfig = new RedisConfiguration("101", 22);
        RedisConfiguration slaveConfig2 = new RedisConfiguration("102", 22);
        RedisConfiguration slaveConfig3 = new RedisConfiguration("103", 22);

        redisConfigBean.setSlaves(new HashSet<RedisConfiguration>(
        Arrays.asList(slaveConfig, slaveConfig2, slaveConfig3)));

        RedisConfiguration redisConfig = new RedisConfiguration(redisConfigBean);
        return new JedisPoolManager(redisConfig);
    }


}
