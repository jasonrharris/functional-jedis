package com.jedisWrapper.jedis;

import com.jedisWrapper.TestGroups;
import com.jedisWrapper.messaging.CallablePubSubPatternManager;
import com.jedisWrapper.messaging.JedisPubSubFactory;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.jmock.lib.concurrent.DeterministicScheduler;
import org.testng.annotations.Test;
import redis.clients.jedis.JedisPubSub;

/**
 * Test for JedisPubSubFactory
 * User: jasonhome
 * Date: 11/01/2012
 * Time: 10:31
 */
@Test(groups = TestGroups.UNIT)
public class JedisPubSubFactoryTest {

    public void shouldGetCorrectPubSub() {
        Mockery context = new Mockery();

        final CallablePubSubPatternManager<Boolean> pubSub = context.mock(CallablePubSubPatternManager.class);
        DeterministicScheduler service = new DeterministicScheduler();

        JedisPubSubFactory pubSubFactory = new JedisPubSubFactory(pubSub, service);

        context.checking(new Expectations(){{
            one(pubSub).addSubPattern("bid");
        }});

        pubSubFactory.addNewSubPattern("bid");

        context.assertIsSatisfied();
    }


    private class TestPubSub extends JedisPubSub {
        private final StringBuilder message;

        private TestPubSub(StringBuilder message) {
            this.message = message;
        }

        @Override
        public void onMessage(String s, String s1) {
            message.append(s);
            message.append(" received :");
            message.append(s1);
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
    }
}
