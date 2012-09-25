package com.jedisWrapper.messaging;

/**
 * Factory for creating JedisPubSub implementations
 * User: jasonhome
 * Date: 10/01/2012
 * Time: 12:42
 */
public interface PubSubFactory {
    void addNewSubPattern(String offerKey);
}
