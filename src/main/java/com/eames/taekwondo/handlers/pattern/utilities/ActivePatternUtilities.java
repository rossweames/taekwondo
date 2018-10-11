package com.eames.taekwondo.handlers.pattern.utilities;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;

/**
 * Provides a set of utilities for managing the active pattern.
 */
public abstract class ActivePatternUtilities {

    /**
     * The active pattern attribute key.
     */
    private static final String ATTRIBUTE_ACTIVE_PATTERN = "activePattern";

    /**
     * Gets the active pattern from the session.
     *
     * @param input the {@link HandlerInput} request object to analyze
     * @return the {@link ActivePatternUtilities} name (an be {@code null})
     */
    public static String getActivePattern(HandlerInput input) {

        // Get and return the session attribute collection.
        return (String) input.getAttributesManager().getSessionAttributes().get(ATTRIBUTE_ACTIVE_PATTERN);
    }

    /**
     * Sets the given active pattern into the session.
     *
     * @param input the {@link HandlerInput} request object to analyze
     * @param activePattern the {@link ActivePatternUtilities} name to set
     */
    public static void setActivePattern(HandlerInput input, String activePattern) {

        // Set the active pattern.
        input.getAttributesManager().getSessionAttributes().put(ATTRIBUTE_ACTIVE_PATTERN, activePattern);
    }

    /**
     * Clears the active pattern in the session.
     *
     * @param input the {@link HandlerInput} request object to analyze
     */
    public static void clearActivePattern(HandlerInput input) {

        // Clear the active pattern.
        input.getAttributesManager().getSessionAttributes().remove(ATTRIBUTE_ACTIVE_PATTERN);
    }
}
