package com.eames.taekwondo.model;

/**
 * This class represents the various TKD black belts.
 */
public class Belt extends SkillEntity {

    /**
     * Constructor
     *
     * This constructor is declared package-private to prohibit belts
     * from being instantiated outside this package.
     *
     * @param key the belt's key
     * @param displayName the belt's display name
     * @param phoneticName the belt's phonetic name
     */
    Belt(String key, String displayName, String phoneticName) {
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
