package com.jedisWrapper.messaging;

import com.google.inject.Inject;
import com.jedisWrapper.jedis.AllJedisCommands;
import com.jedisWrapper.jedis.JedisClient;

/**
* Manages subscriptions
* User: jasonhome
* Date: 11/04/2012
* Time: 17:39
*/
public class SubscriptionPatternManager<E extends Enum<E>> implements CallablePubSubPatternManager<Boolean> {
    private final StickerPubSub jedisPubSub;
    private final E pubSubType;
    private final JedisClient<AllJedisCommands> poolManager;

    @Inject
    public SubscriptionPatternManager(StickerPubSub jedisPubSub,
                                      E pubSubType,
                                      JedisClient<AllJedisCommands> poolManager) {
        this.jedisPubSub = jedisPubSub;
        this.pubSubType = pubSubType;
        this.poolManager = poolManager;
    }

    public Boolean call() throws Exception {
        AllJedisCommands jedis = poolManager.getJedis();
        try {
            jedisPubSub.proceedWithPatterns(jedis.getClient(), pubSubType.toString());
        }
        finally {
            jedisPubSub.unsubscribe();
            poolManager.returnResource(jedis);
        }
        return true;
    }

    public void addSubPattern(String pattern) {
        jedisPubSub.addSubPattern(pattern);
    }
}
