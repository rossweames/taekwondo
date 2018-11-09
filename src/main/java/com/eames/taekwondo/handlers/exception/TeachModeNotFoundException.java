package com.eames.taekwondo.handlers.exception;

/**
 * This exception is thrown by the intents whenever they cannot determine to which
 * {@link com.eames.taekwondo.model.TeachMode} the request is referring.
 */
public class TeachModeNotFoundException extends TKDException {

    // The teach mode name
    private final String teachModeName;

    /**
     * Constructor
     *
     * @param teachModeName the name of the teach mode
     */
    public TeachModeNotFoundException(String teachModeName) {
        this.teachModeName = teachModeName;
    }

    public String getTeachModeName() {
        return teachModeName;
    }
}
