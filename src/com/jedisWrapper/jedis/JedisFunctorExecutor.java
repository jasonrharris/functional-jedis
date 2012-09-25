package com.jedisWrapper.jedis;

import com.google.inject.ImplementedBy;
import com.jedisWrapper.functors.JedisCommandsFunctor;

/**
 * Executes submitted Functor
 * User: jasonhome
 * Date: 06/12/2011
 * Time: 23:53

 */
@ImplementedBy(JedisClientFunctorExecutor.class)
public interface JedisFunctorExecutor<B extends BasicJedisCommands> {
    public <O, A> O executeFunction(JedisCommandsFunctor<O, A, B> functor, A parameters);
}
