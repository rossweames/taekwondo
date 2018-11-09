package com.eames.taekwondo.model;

/**
 * This class represents the various teaching modes.
 */
public class TeachMode extends SkillEntity {

    /**
     * Constructor
     *
     * This constructor is declared package-private to prohibit teach modes
     * from being instantiated outside this package.
     *
     * @param key the teach mode's key
     * @param displayName the teach mode's display name
     * @param phoneticName the teach mode's phonetic name
     */
    TeachMode(String key, String displayName, String phoneticName) {
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
