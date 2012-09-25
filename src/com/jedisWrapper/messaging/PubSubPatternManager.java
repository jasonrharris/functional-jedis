package com.jedisWrapper.messaging;

/**
 * Adds patterns of messages from Redis that client matches
 * User: jasonhome
 * Date: 11/04/2012
 * Time: 17:32
 */
public interface PubSubPatternManager {
    void addSubPattern(String pattern);
}
