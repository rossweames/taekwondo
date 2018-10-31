package com.eames.taekwondo.handlers.pattern.utilities;

import com.amazon.ask.dispatcher.request.handler.HandlerInput;
import com.eames.taekwondo.model.Pattern;
import com.eames.taekwondo.model.Patterns;

/**
 * Provides a set of utilities for managing the active pattern.
 */
public abstract class ActivePatternUtilities {

    /**
     * The active pattern attribute keys.
     */
    private static final String ATTRIBUTE_ACTIVE_PATTERN = "activePattern";
    private static final String ATTRIBUTE_CURRENT_STEP = "currentStep";

    /*
     * The active pattern
     */

    /**
     * Gets the active pattern from the session.
     *
     * @param input the {@link HandlerInput} request object to analyze
     * @return the active pattern key (an be {@code null})
     */
    public static String getActivePattern(HandlerInput input) {

        // Get and return the active pattern.
        return (String) input.getAttributesManager().getSessionAttributes().get(ATTRIBUTE_ACTIVE_PATTERN);
    }

    /**
     * Sets the given active pattern into the session.
     * Clears the current step.
     *
     * @param input the {@link HandlerInput} request object to analyze
     * @param activePattern the pattern key to set
     */
    public static void setActivePattern(HandlerInput input, String activePattern) {

        // Set the active pattern.
        input.getAttributesManager().getSessionAttributes().put(ATTRIBUTE_ACTIVE_PATTERN, activePattern);

        // Clear the current step.
        clearCurrentStep(input);
    }

    /**
     * Clears the active pattern in the session.
     * Unloads the pattern's movements.
     * Clears the current step.
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

        // Clear the current step.
        clearCurrentStep(input);
    }

    /*
     * The current step
     *
     */

    /**
     * Gets the current step from the session.
     *
     * @param input the {@link HandlerInput} request object to analyze
     * @return the current step (an be {@code null})
     */
    public static Integer getCurrentStep(HandlerInput input) {

        // If there is a current pattern, get the current step.
        String activePatternKey = getActivePattern(input);
        if (activePatternKey != null)
            return (Integer) input.getAttributesManager().getSessionAttributes().get(ATTRIBUTE_CURRENT_STEP);
        else
            return null;
    }

    /**
     * Sets the given current step into the session.
     *
     * @param input the {@link HandlerInput} request object to analyze
     * @param currentStep the current step to set
     */
    public static void setCurrentStep(HandlerInput input, Integer currentStep) {

        // If there is a current pattern, set the current step.
        String activePatternKey = getActivePattern(input);
        if (activePatternKey != null)
            input.getAttributesManager().getSessionAttributes().put(ATTRIBUTE_CURRENT_STEP, currentStep);
        else
            clearCurrentStep(input);
    }

    /**
     * Clears the current step in the session.
     *
     * @param input the {@link HandlerInput} request object to analyze
     */
    public static void clearCurrentStep(HandlerInput input) {

        // Clear the current step.
        input.getAttributesManager().getSessionAttributes().remove(ATTRIBUTE_CURRENT_STEP);
    }
}
