package com.jedisWrapper.jedis;

import com.google.inject.ImplementedBy;
import com.jedisWrapper.messaging.PubSub;
import redis.clients.jedis.BinaryJedisCommands;
import redis.clients.jedis.JedisCommands;
import redis.clients.jedis.Pipeline;

/**
 * An interface to extend BinaryJedisCommands by including the pub/sub, pipeline and transaction commands
 * User: jasonhome
 * Date: 10/01/2012
 * Time: 16:12
 */
@ImplementedBy(JedisDecorator.class)
public interface AllJedisCommands extends JedisCommands, BinaryJedisCommands, BasicJedisCommands {
    String auth(String password);
    void subscribe(PubSub jedisPubSub, String... channels);
    void psubscribe(PubSub jedisPubSub, String... patterns);
    Pipeline pipelined();
}
