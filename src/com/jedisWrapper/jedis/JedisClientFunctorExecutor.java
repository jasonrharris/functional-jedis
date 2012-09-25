package com.jedisWrapper.jedis;

import com.jedisWrapper.functors.JedisCommandsFunctor;

import javax.inject.Inject;

/**
 * Process JedisCommandFunctor.
 * User: jasonhome
 * Date: 06/12/2011
 * Time: 23:42
 */
public final class JedisClientFunctorExecutor<B extends BasicJedisCommands> implements JedisFunctorExecutor<B> {
    private final JedisClient<B> jedisClient;

    @Inject
    public JedisClientFunctorExecutor(JedisClient<B> jedisClient) {
        this.jedisClient = jedisClient;
    }

    public <O, A> O executeFunction(JedisCommandsFunctor<O, A, B> functor, A parameters){
        O output;

        B jedis = jedisClient.getJedis();

        try {
            output = functor.execute(jedis, parameters);
        }
        finally {
            jedisClient.returnResource(jedis);
        }
        return output;

    }
}
