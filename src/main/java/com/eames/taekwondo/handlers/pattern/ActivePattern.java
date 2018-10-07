package com.eames.taekwondo.handlers.pattern;

import com.eames.taekwondo.model.Pattern;

/**
 * Holds the active pattern and the current step in the pattern.
 */
public class ActivePattern {

    /**
     * The pattern
     */
    private Pattern pattern;

    /**
     * The current step in the pattern.
     */
    private Integer currentStep;

    /**
     * Constructor
     *
     * @param pattern the {@link Pattern} to set
     */
    public ActivePattern(Pattern pattern) {

        // Set the pattern.
        this.pattern = pattern;
    }

    /**
     * Gets the pattern.
     *
     * @return the pattern
     */
    public Pattern getPattern() {
        return pattern;
    }

    // TODO: Need to add operations for the movement count.
}
