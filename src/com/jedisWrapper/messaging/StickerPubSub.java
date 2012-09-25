package com.jedisWrapper.messaging;

/**
 * Decorating PubSub by allowing addition of patterns that client manages (i.e. a subset of patterns Redis matches)
 * User: jasonhome
 * Date: 11/04/2012
 * Time: 17:07
 */
public interface StickerPubSub extends PubSub, PubSubPatternManager {}
