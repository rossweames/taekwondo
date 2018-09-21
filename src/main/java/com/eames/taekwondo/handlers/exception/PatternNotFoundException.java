package com.eames.taekwondo.handlers.exception;

/**
 * This exception is thrown by the intents whenever they cannot determine to which
 * {@link com.eames.taekwondo.model.Pattern} the request is referring.
 */
public class PatternNotFoundException extends TKDException {

    // The pattern name
    private final String patternName;

    /**
     * Constructor
     *
     * @param patternName the name of the pattern
     */
    public PatternNotFoundException(String patternName) {
        this.patternName = patternName;
    }

    public String getPatternName() {
        return patternName;
    }
}
