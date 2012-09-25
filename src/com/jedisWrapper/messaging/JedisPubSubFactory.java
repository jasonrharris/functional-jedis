package com.jedisWrapper.messaging;

import com.google.inject.Singleton;

import javax.inject.Inject;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * PubSub factory that subscribes for messages of the required type and matches them to one of the submitted patterns
 * User: jasonhome
 * Date: 11/01/2012
 * Time: 10:19
 */

@Singleton
public class JedisPubSubFactory implements PubSubFactory {

    private final CallablePubSubPatternManager<Boolean> pubSub;
    private final ExecutorService executorService;
    private final AtomicBoolean subscriptionStarted = new AtomicBoolean(false);
    private Future<Boolean> subscription;

    @Inject
    public JedisPubSubFactory(
            CallablePubSubPatternManager<Boolean> pubSub,
            ExecutorService executorService) {
        this.pubSub = pubSub;
        this.executorService = executorService;
    }

    public void addNewSubPattern(String keyPattern) {
        pubSub.addSubPattern(keyPattern);
        if (!subscriptionStarted.getAndSet(true)) {
            subscription = executorService.submit(pubSub);
        }
    }

    public void cancelSubscription(){
        subscription.cancel(true);
    }

}
