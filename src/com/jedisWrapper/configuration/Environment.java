package com.jedisWrapper.configuration;

/**
* Specifies the environment the node/host is running within
* User: jasonhome
* Date: 07/04/2012
* Time: 12:12
*/
public enum Environment {
    DEFAULT(""),
    DEV("dev"),
    UAT("uat"),
    PROD("prod");

    private final String name;

    Environment(String name) {
        this.name = name;
    }

    public String subName() {
        return name;
    }

}
