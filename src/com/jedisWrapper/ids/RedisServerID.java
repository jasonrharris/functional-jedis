package com.jedisWrapper.ids;

import com.jedisWrapper.configuration.RedisConfiguration;

/**
 * Specifies a Redis instance
 * User: jasonhome
 * Date: 11/01/2012
 * Time: 13:42
 */
public class RedisServerID extends StringIdentifier{

    public static final String HOST_PORT_SPLIT_CHAR = "_";

    public RedisServerID(RedisConfiguration id) {
        this(id.getHost(),id.getPort());
    }

    public RedisServerID(String host, int port) {
        super(host+ HOST_PORT_SPLIT_CHAR +port);    //To change body of overridden methods use File | Settings | File Templates.
    }
}
