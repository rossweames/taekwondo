package com.eames.taekwondo.model;

/**
 * This class represents the various TKD starting stances.
 *
 * TODO: Need to add instructions to the stance and lazily load them.
 *
 */
public class Stance extends SkillEntity {

    /**
     * Constructor
     *
     * This constructor is declared package-private to prohibit stances
     * from being instantiated outside this package.
     *
     * @param key the stance's key
     * @param displayName the stance's display name
     * @param phoneticName the stance's phonetic name
     */
    Stance(String key, String displayName, String phoneticName) {
        super(key, displayName, phoneticName);
    }

    /*
     * Overridden {@link Object} operations
     */

    /**
     * Call the parent {@link SkillEntity} object's operation.
     *
     * @return the instance's hash code
     */
    @Override
    public int hashCode() { return super.hashCode(); }
}
