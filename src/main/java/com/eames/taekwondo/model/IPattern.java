package com.eames.taekwondo.model;

/**
 * Specifies the operations that must be supported by all Taekwon-Do pattern concrete classes.
 */
public interface IPattern {

    /**
     * Gets the pattern name.
     *
     * @return the pattern name
     */
    String getName();

    /**
     * Gets the pattern history paragraph.
     *
     * @return the pattern history paragraph
     */
    String getHistory();

    /**
     * Gets the pattern belt level.
     *
     * @return the pattern belt level
     */
    Belt getBeltLevel();

    /**
     * Gets the pattern diagram.
     *
     * @return the pattern diagram
     */
    PatternDiagram getDiagram();

}
