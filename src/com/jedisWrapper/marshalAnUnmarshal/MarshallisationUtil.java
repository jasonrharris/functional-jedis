package com.jedisWrapper.marshalAnUnmarshal;

import com.jedisWrapper.ids.LongIdentifier;
import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormatter;

import java.util.Map;

/**
 * Useful methods for converting Redis stored Strings to other data types
 * User: Jason
 * Date: 26/11/11
 * Time: 22:28
 */
public final class MarshallisationUtil {

    private MarshallisationUtil() {}

    static DateTime getDateFromString(
            Map<String, String> serialisedObject,
            String timeKey,
            DateTimeFormatter dateTimeFormatter) {
        final String dateTime = serialisedObject.get(timeKey);
        return StringUtils.isNotEmpty(dateTime)?dateTimeFormatter.parseDateTime(dateTime):new DateTime(0);
    }

    static long getIDFromString(Map<String, String> serialisedObject, String key) {
        final String value = serialisedObject.get(key);
        return StringUtils.isNotEmpty(value)?Long.parseLong(value):0L;
    }

    static String marshalLongIdentifier(LongIdentifier identifier) {
        return identifier.getIdentifier().toString();
    }
}
