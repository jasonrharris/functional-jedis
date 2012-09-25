package com.jedisWrapper.configuration;

import java.util.Collections;
import java.util.Set;

/**
 * Server configuration details
 * User: jasonhome
 * Date: 11/01/2012
 * Time: 13:13
 */
public class RedisConfigurationBean {

    private String host = "";
    private int port;
    private Set<RedisConfiguration> slaves = Collections.emptySet();

    public RedisConfigurationBean(){}

    public Set<RedisConfiguration> getSlaves() {
        return slaves;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setSlaves(Set<RedisConfiguration> slaves) {
        this.slaves = slaves;
    }
}
