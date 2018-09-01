package com.eames.taekwondo.model;

/**
 * This class represents the various TKD pattern diagrams.
 */
public class PatternDiagram extends SkillEntity {

    /**
     * Constructor
     *
     * This constructor is declared package-private to prohibit pattern diagrams
     * from being instantiated outside this package.
     *
     * @param key the diagram's key
     * @param displayName the diagram's display name
     * @param phoneticName the diagram's phonetic name
     */
    PatternDiagram(String key, String displayName, String phoneticName) {
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
