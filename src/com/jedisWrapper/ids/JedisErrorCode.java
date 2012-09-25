package com.jedisWrapper.ids;

/**
 * Error codes (help with internationalisation)
 * User: Jason
 * Date: 24/08/11
 * Time: 16:07
 */
public enum JedisErrorCode implements ErrorCode<JedisErrorCode> {

    CONFIGURATION_ERROR;

    public JedisErrorCode errorCode() {
        return this;
    }

    public boolean isError(){
        return true;
    }
}
