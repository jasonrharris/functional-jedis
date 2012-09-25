package com.jedisWrapper.messaging;

import redis.clients.jedis.JedisPubSub;

/**
 * Listens for new bids to match to existing Offers
 * User: jasonhome
 * Date: 22/01/2012
 * Time: 12:33
 */
public class BidPubSub extends JedisPubSub implements StickerPubSub {

    @Override
    public void onMessage(String s, String s1) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onPMessage(String s, String s1, String s2) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onSubscribe(String s, int i) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onUnsubscribe(String s, int i) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onPUnsubscribe(String s, int i) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void onPSubscribe(String s, int i) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    public JedisPubSub getJedisPubSub() {
        return this;  //To change body of implemented methods use File | Settings | File Templates.
    }

    public void addSubPattern(String pattern) {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
