package com.eames.taekwondo.model;

/**
 * This abstract class is the base class for all patterns.
 * It provides a partial implementation for concrete patterns.
 *
 * TODO: Needs unit tests.
 */
public abstract class AbstractPattern
        implements IPattern {

    /*
     * Attributes
     */

    /**
     * The pattern name
     */
    private String name;

    /**
     * The pattern history paragraph
     */
    private String history;

    /**
     * The TKD belt level where this pattern is learned
     */
    private Belt beltLevel;

    /**
     * The pattern diagram.
     */
    private PatternDiagram patternDiagram;

    /**
     * The pattern movements
     */
    private Movement[] movements;

    /*
     * Constructors
     */

    /**
     * Constructor
     *
     * @param name the pattern name
     * @param history the pattern history paragraph
     * @param beltLevel the TKD belt level that teaches this pattern
     * @param patternDiagram the pattern diagram
     * @param movements the pattern movements
     */
    public AbstractPattern(String name, String history, Belt beltLevel, PatternDiagram patternDiagram, Movement[] movements) {
        this.name = name;
        this.history = history;
        this.beltLevel = beltLevel;
        this.patternDiagram = patternDiagram;
        this.movements = movements;
    }

    /*
     * Getters
     */

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getHistory() {
        return history;
    }

    @Override
    public PatternDiagram getDiagram() {
        return patternDiagram;
    }

    @Override
    public Belt getBeltLevel() {
        return beltLevel;
    }

    // TODO: Need to add a parser.

    // TODO: Need to add helper operations for movements.

}
