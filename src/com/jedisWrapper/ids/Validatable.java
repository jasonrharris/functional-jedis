package com.jedisWrapper.ids;

/**
 * Identifiers can expose a validity check by implementing this interface.
 * User: Jason
 * Date: 23/08/11
 * Time: 14:37
 */
public interface Validatable {
    boolean hasValidID();
    JedisErrorCode getErrorCode();
    String getRedisIdentifier();
}
