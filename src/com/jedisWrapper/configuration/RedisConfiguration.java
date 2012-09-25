package com.jedisWrapper.configuration;

import com.jedisWrapper.ids.RedisServerID;

import java.util.Collections;
import java.util.Set;

/**
 * Thread safe Redis Server configuration details
 * User: jasonhome
 * Date: 11/01/2012
 * Time: 13:13
 */
public class RedisConfiguration {
    public RedisConfiguration(RedisConfigurationBean redisConfigBean) {
        this(redisConfigBean.getHost(), redisConfigBean.getPort(), redisConfigBean.getSlaves());
    }

    public RedisConfiguration(String host, int port){
        this(host, port, Collections.<RedisConfiguration>emptySet());
    }

    private RedisConfiguration(String host, int port, Set<RedisConfiguration> slaves) {
        this.port = port;
        this.host = host;
        this.type = slaves.isEmpty()? Type.SLAVE: Type.MASTER;
        this.slaves = slaves;
        this.redisServerID = new RedisServerID(this);
    }

    public enum Type {
        MASTER,
        SLAVE
    }

    private final Type type;
    private final String host;
    private final int port;
    private final RedisServerID redisServerID;
    private final Set<RedisConfiguration> slaves;


    public Type getType() {
        return type;
    }

    public Set<RedisConfiguration> getSlaves() {
        return slaves;
    }

    public RedisServerID getRedisServerID() {
        return redisServerID;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }
}
