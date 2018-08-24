package com.eames.taekwondo.model;

/**
 * This enumeration represents the various TKD black belts.
 *
 * TODO: Needs unit tests.
 */
public enum Belt {

    /**
     * The enumerations
     */
    TENTH_GUP("tenth gup", "tenth gup (white)", "tenth gup white"),
    NINTH_GUP("ninth gup", "ninth gup (high white)", "ninth gup high white"),
    EIGHTH_GUP("eighth gup", "eighth gup (yellow)", "eighth gup yellow"),
    SEVENTH_GUP("seventh gup", "seventh gup (high yellow)", "seventh gup high yellow)"),
    SIXTH_GUP("sixth gup", "sixth gup (green)", "sixth gup green"),
    FIFTH_GUP("fifth gup", "fifth gup (high green)", "fifth gup high green"),
    FOURTH_GUP("fourth gup", "fourth gup (blue)", "fourth gup blue"),
    THIRD_GUP("third gup", "third gup (high blue)", "third gup high blue"),
    SECOND_GUP("second gup", "second gup (red)", "second gup red"),
    FIRST_GUP("first gup", "first gup (high red)", "first gup high red"),
    FIRST_DAN("first dan", "first dan", "first dan"),
    SECOND_DAN("second dan", "second dan", "second dan"),
    THIRD_DAN("third dan", "third dan", "third dan"),
    FOURTH_DAN("fourth dan", "fourth dan", "fourth dan"),
    FIFTH_DAN("fifth dan", "fifth dan", "fifth dan"),
    SIXTH_DAN("sixth dan", "sixth dan", "sixth dan"),
    SEVENTH_DAN("seventh dan", "seventh dan", "seventh dan"),
    EIGHTH_DAN("eighth dan", "eighth dan", "eighth dan"),
    NINTH_DAN("ninth dan", "ninth dan", "ninth dan");

    /**
     * The skill key/identifier
     *
     * This string gets passed to the backend from the Alexa skill, where it is
     * derived from the user's input (usually a voice command).
     */
    private String key;

    /**
     * The display name
     *
     * This string is passed from the backend back to the Alexa skill and is used
     * for textual displays (i.e. skill cards).
     */
    private String displayName;

    /**
     * The phonetic name
     *
     * This string is passed from the backend back to the Alexa skill and is used
     * for voice responses (i.e. text-to-speech).
     */
    private String phoneticName;

    /**
     * Constructor
     *
     * @param key the belt's key
     * @param displayName the belt's display name
     * @param phoneticName the belt's phonetic name
     */
    Belt(String key, String displayName, String phoneticName) {
        this.key = key;
        this.displayName = displayName;
        this.phoneticName = phoneticName;
    }

    /**
     * Gets the belt's key.
     *
     * @return the key
     */
    public String getKey() {
        return key;
    }

    /**
     * Gets the display name.
     *
     * @return the display name
     */
    public String getDisplayName() {
        return displayName;
    }

    /**
     * Gets the phonetic name.
     *
     * @return the phonetic name
     */
    public String getPhoneticName() {
        return phoneticName;
    }

    // TODO: Add parsing operations.

}
