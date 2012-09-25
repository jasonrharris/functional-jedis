package com.jedisWrapper.configuration;

import java.util.Set;

/**
 * System Configuration methods are specified here.
 * User: jasonhome
 * Date: 28/01/2012
 * Time: 17:43
 */
public interface Configuration<A extends ApplicationConfiguration> {
    A getApplicationConfiguration();

    Environment getEnvironment();

    Set<RedisConfiguration> getRedisServerConfigurations();

    String getHostname();
}
