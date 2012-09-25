package com.jedisWrapper.jedis;

import com.jedisWrapper.jedis.BasicJedisCommands;

/**
 * Provide access to Jedis
 * User: Jason
 * Date: 23/10/11
 * Time: 22:05
 */
public interface JedisClient<B extends BasicJedisCommands> {

    B getJedis();

    void returnResource(B jedis);
}
