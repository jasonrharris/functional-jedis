package com.jedisWrapper.ids;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * An exception that wraps an error message and a code for use by clients (to display the correct text for instance).
 * The default constructor also logs the error
 * User: Jason
 * Date: 24/08/11
 * Time: 15:51
 */
public class CodedRuntimeException extends RuntimeException implements Identifier<ErrorCode<?>>{

    private static final Logger LOGGER = LoggerFactory.getLogger(CodedRuntimeException.class);

    public CodedRuntimeException(String message, ErrorCode<?> exceptionCode) {
        this(message,exceptionCode,true);
        }

    public CodedRuntimeException(String message, ErrorCode<?> exceptionCode, boolean logError) {
        super(message);
        if(logError) {LOGGER.error("Error: "+exceptionCode+" -> " +message, this);}
        this.exceptionCode = exceptionCode;
    }

    private final ErrorCode<?> exceptionCode;

    /**
     * @return The exception code
     */
    public ErrorCode<?> getIdentifier() {
        return exceptionCode;
    }
}
