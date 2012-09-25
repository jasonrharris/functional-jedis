package com.jedisWrapper.ids;

/**
 * Used to encode an exception
 * User: jasonhome
 * Date: 21/07/2012
 * Time: 06:22
 */
public interface ErrorCode<E extends Enum<E>> {
    E errorCode();
    boolean isError();
}
