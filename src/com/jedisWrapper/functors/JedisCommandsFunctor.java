package com.jedisWrapper.functors;

import com.jedisWrapper.jedis.BasicJedisCommands;

/**
 * The implemented method is run according to the JedisClient/JedisCommands usage pattern without
 * client code needing to worry about administrative code
 *
 * User: Jason
 * Date: 29/11/11
 * Time: 23:05
 */
public interface JedisCommandsFunctor<O, A, B extends BasicJedisCommands> {
    O execute(B jedis, A parameters);

}
