package com.jedisWrapper.messaging;

import java.util.concurrent.Callable;

/**
 * PubSubPatternManager implementation can be run as Callables
 * User: jasonhome
 * Date: 11/04/2012
 * Time: 17:36
 */
public interface CallablePubSubPatternManager<B> extends Callable<B>, PubSubPatternManager{
}
