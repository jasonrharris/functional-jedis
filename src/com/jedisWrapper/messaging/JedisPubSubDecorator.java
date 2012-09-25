package com.jedisWrapper.messaging;

import redis.clients.jedis.Client;
import redis.clients.jedis.JedisPubSub;

/**
 * Decorates JedisPubSub as an implementer of PubSub and manages patterns
 * User: jasonhome
 * Date: 10/01/2012
 * Time: 17:43
 */
public final class JedisPubSubDecorator implements PubSub {
    private final JedisPubSub jedisPubSub;

    public JedisPubSubDecorator(JedisPubSub jedisPubSub) {
        this.jedisPubSub = jedisPubSub;
    }

    public JedisPubSub getJedisPubSub(){
        return jedisPubSub;
    }

    public void onMessage(String s, String s1) {
        jedisPubSub.onMessage(s, s1);
    }

    public void onPMessage(String s, String s1, String s2) {
        jedisPubSub.onPMessage(s, s1, s2);
    }

    public void onSubscribe(String s, int i) {
        jedisPubSub.onSubscribe(s, i);
    }

    public void onUnsubscribe(String s, int i) {
        jedisPubSub.onUnsubscribe(s, i);
    }

    public void onPUnsubscribe(String s, int i) {
        jedisPubSub.onPUnsubscribe(s, i);
    }

    public void onPSubscribe(String s, int i) {
        jedisPubSub.onPSubscribe(s, i);
    }

    public void unsubscribe() {
        jedisPubSub.unsubscribe();
    }

    public void unsubscribe(String... channels) {
        jedisPubSub.unsubscribe(channels);
    }

    public void subscribe(String... channels) {
        jedisPubSub.subscribe(channels);
    }

    public void psubscribe(String... patterns) {
        jedisPubSub.psubscribe(patterns);
    }

    public void punsubscribe() {
        jedisPubSub.punsubscribe();
    }

    public void punsubscribe(String... patterns) {
        jedisPubSub.punsubscribe(patterns);
    }

    public boolean isSubscribed() {
        return jedisPubSub.isSubscribed();
    }

    public void proceedWithPatterns(Client client, String... patterns) {
        jedisPubSub.proceedWithPatterns(client, patterns);
    }

    public void proceed(Client client, String... channels) {
        jedisPubSub.proceed(client, channels);
    }

    public int getSubscribedChannels() {
        return jedisPubSub.getSubscribedChannels();
    }
}
