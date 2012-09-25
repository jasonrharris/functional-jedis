package com.jedisWrapper.configuration;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Indicates that the parameter is to be provided with a Slave version of the required type
 * User: jasonhome
 * Date: 28/01/2012
 * Time: 17:34
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.PARAMETER, ElementType.METHOD})
public @interface Slave {
}
