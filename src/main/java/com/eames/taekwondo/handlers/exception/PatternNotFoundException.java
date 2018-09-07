package com.eames.taekwondo.handlers.exception;

/**
 * This exception is thrown by the intents whenever they cannot determine to which
 * {@link com.eames.taekwondo.model.Pattern} the request is referring.
 */
public class PatternNotFoundException extends Exception {

    public PatternNotFoundException(String message) {
        super(message);
    }
}
