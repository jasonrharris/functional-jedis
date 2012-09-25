package com.jedisWrapper.messaging;

import redis.clients.jedis.JedisPubSub;

/**
 * Interface version of JedisPubSub
 * User: jasonhome
 * Date: 10/01/2012
 * Time: 17:26
 */
public interface PubSub {
    String UNMAPPED_TYPE_MESSAGE = "Unmapped Type";

    void onMessage(java.lang.String s, java.lang.String s1);

    void onPMessage(java.lang.String s, java.lang.String s1, java.lang.String s2);

    void onSubscribe(java.lang.String s, int i);

    void onUnsubscribe(java.lang.String s, int i);

    void onPUnsubscribe(java.lang.String s, int i);

    void onPSubscribe(java.lang.String s, int i);

    void unsubscribe();

    void unsubscribe(java.lang.String... channels);

    void subscribe(java.lang.String... channels);

    void psubscribe(java.lang.String... patterns);

    void punsubscribe();

    void punsubscribe(java.lang.String... patterns);

    boolean isSubscribed();

    void proceedWithPatterns(redis.clients.jedis.Client client, java.lang.String... patterns);

    void proceed(redis.clients.jedis.Client client, java.lang.String... channels);

    int getSubscribedChannels();

    JedisPubSub getJedisPubSub();
}
