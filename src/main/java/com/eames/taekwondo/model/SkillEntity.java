package com.eames.taekwondo.model;

import java.util.Objects;

/**
 * This abstract class is the base class for all skill entities.
 * It provides a partial implementation for concrete skill entities.
 */
abstract class SkillEntity {

    /**
     * The skill key/identifier
     *
     * This string gets passed to the backend from the Alexa skill, where it is
     * derived from the user's input (usually a voice command).
     */
    private final String key;

    /**
     * The display name
     *
     * This string is passed from the backend back to the Alexa skill and is used
     * for textual displays (i.e. skill cards).
     */
    private final String displayName;

    /**
     * The phonetic name
     *
     * This string is passed from the backend back to the Alexa skill and is used
     * for voice responses (i.e. text-to-speech).
     */
    private final String phoneticName;

    /**
     * Constructor
     *
     * @param key the pattern key name
     * @param displayName the display name
     * @param phoneticName the phonetic name
     */
    protected SkillEntity(String key, String displayName, String phoneticName) {

        this.key = key;
        this.displayName = displayName;
        this.phoneticName = phoneticName;
    }

    /**
     * Gets the skill entity's key.
     *
     * @return the skill entity's key
     */
    public String getKey() {
        return key;
    }

    /**
     * Gets the skill entity's display name.
     *
     * @return the skill entity's  display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets the skill entity's phonetic name.
     *
     * @return the skill entity's phonetic name
     */
    public String getPhoneticName() {
        return phoneticName;
    }

    /*
     * Overridden {@link Object} operations
     */

    @Override
    public int hashCode() {
        return Objects.hash(key);
    }
}
