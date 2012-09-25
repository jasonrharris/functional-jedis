package com.jedisWrapper.configuration;

/**
* Specifies yaml tags used in Redis configuration
* User: jasonhome
* Date: 07/04/2012
* Time: 12:11
*/
public enum Tag implements ConfigurationBeanDetails {
    REDIS_SERVER_BEAN(RedisConfigurationBean.class, "!RedisServerSet"),
    REDIS_SERVER_CONFIG(RedisConfiguration.class, "!RedisServer");
    private final Class<?> beanClass;
    private final String tagName;

    Tag(Class<?> beanClass, String tagName) {
        this.beanClass = beanClass;
        this.tagName = tagName;
    }

    public String getTagName() {
        return tagName;
    }

    public Class<?> getBeanClass() {
        return beanClass;
    }
}
