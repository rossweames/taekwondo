package com.eames.taekwondo.handlers.pattern.utilities;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.eames.taekwondo.model.Pattern;
import com.eames.taekwondo.model.Patterns;

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
     * Unloads the pattern's movements.
     *
     * @param input the {@link HandlerInput} request object to analyze
     */
    public static void clearActivePattern(HandlerInput input) {

        // Get the active pattern.
        String activePatternKey = getActivePattern(input);
        if (activePatternKey != null) {

            // Unload the active pattern's movements.
            Pattern activePattern = Patterns.getPatternByKey(activePatternKey);
            if (activePattern != null)
                activePattern.clearMovements();

            // Clear the active pattern.
            input.getAttributesManager().getSessionAttributes().remove(ATTRIBUTE_ACTIVE_PATTERN);
        }
    }
}
