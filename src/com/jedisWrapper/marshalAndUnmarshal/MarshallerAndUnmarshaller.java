package com.jedisWrapper.marshalAndUnmarshal;

import java.util.Map;

/**
 * Implemented by classes responsible for converting objects into Redis key/value maps
 * and unmarshalling Redis maps into Objects..
 * User: Jason
 * Date: 23/10/11
 * Time: 22:44
 */
@SuppressWarnings("UnusedDeclaration")
public interface MarshallerAndUnmarshaller<O> {

    Map<String, String> marshal(O object);
    O unmarshal(Map<String, String> serialisedObject);
}
