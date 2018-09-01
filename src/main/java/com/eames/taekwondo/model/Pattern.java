package com.eames.taekwondo.model;

/**
 * This class is the base class for all patterns.
 */
public class Pattern extends SkillEntity {

    /**
     * The TKD belt level where this pattern is learned
     */
    private final Belt beltLevel;

    /**
     * The pattern diagram.
     */
    private final PatternDiagram patternDiagram;

    /**
     * The number of movements in the pattern
     */
    private final int movementCount;

    /**
     * The pattern history paragraph
     * Lazily loaded
     */
    private String history;

    /**
     * The pattern movements
     * Lazily loaded
     */
    private Movement[] movements;

    /**
     * Constructor
     *
     * @param key the pattern key name
     * @param displayName the display name
     * @param phoneticName the phonetic name
     * @param beltLevel the TKD belt level that teaches this pattern
     * @param patternDiagram the pattern diagram
     * @param movementCount the number of movements in the pattern
     */
    protected Pattern(String key, String displayName, String phoneticName, Belt beltLevel,
                      PatternDiagram patternDiagram, int movementCount) {

        super(key, displayName, phoneticName);

        this.beltLevel = beltLevel;
        this.patternDiagram = patternDiagram;
        this.movementCount = movementCount;
    }

    /**
     * Gets the pattern diagram.
     *
     * @return the pattern diagram
     */
    public PatternDiagram getDiagram() {
        return patternDiagram;
    }

    /**
     * Gets the pattern belt level.
     *
     * @return the pattern belt level
     */
    public Belt getBeltLevel() {
        return beltLevel;
    }

    /**
     * Gets the pattern's movement count.
     *
     * @return the movement count
     */
    public int getMovementCount()
    {
        return movementCount;
    }
    
    /**
     * Gets the pattern history.
     *
     * TODO: Needs to load the history from the pattern's JSON file.
     *
     * @return the pattern history
     */
    public String getHistory() {
        return history;
    }

    // TODO: Need movement operations.
    // TODO: Needs to load the movements from the pattern's JSON file.

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
