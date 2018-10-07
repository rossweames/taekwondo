package com.eames.taekwondo.model;

import java.util.HashMap;
import java.util.Map;

/**
 * This abstract class is the collection of all possible {@link PatternDiagram}s.
 */
public abstract class PatternDiagrams {

    /**
     * The pattern diagrams
     */
    public static final PatternDiagram CROSS = new PatternDiagram("cross", "Cross or Plus Sign", "cross or plus sign");
    public static final PatternDiagram CAPITAL_I = new PatternDiagram("capital_i", "Capital I", "capital eye");
    public static final PatternDiagram STAIR_STEP = new PatternDiagram("stair_step", "Stair Step or Backward Z", "stair step or backward z");
    public static final PatternDiagram SCHOLAR_SIGN = new PatternDiagram("scholar_sign", "Scholar Sign", "scholar sign");
    public static final PatternDiagram LAND_SIGN = new PatternDiagram("land_sign", "Land Sign", "land sign");
    public static final PatternDiagram HORIZONTAL_LINE = new PatternDiagram("horizontal_line", "Horizontal Line", "horizontal line");
    public static final PatternDiagram VERTICAL_LINE = new PatternDiagram("vertical_line", "Vertical Line", "vertical line");
    public static final PatternDiagram INVERSE_T = new PatternDiagram("inverse_t", "Inverse T", "inverse tee");
    public static final PatternDiagram MOUNTAIN_SIGN = new PatternDiagram("mountain_sign", "Mountain Sign", "mountain sign");
    public static final PatternDiagram CAPITAL_T = new PatternDiagram("capital_t", "Capital T", "capital tee");
    public static final PatternDiagram FANCY_I = new PatternDiagram("fancy_i", "Fancy I", "fancy eye");
    public static final PatternDiagram Z_SIGN = new PatternDiagram("z_sign", "Z Sign", "zee sign");
    public static final PatternDiagram KING_SIGN = new PatternDiagram("king_sign", "King Sign", "king sign");

    /*
     * The pattern diagram map
     */
    private static Map<String, PatternDiagram> patternDiagrams = new HashMap<>();

    /**
     * The static initialization block
     */
    static {
        // Fill the pattern map.
        patternDiagrams.put(CROSS.getKey(), CROSS);
        patternDiagrams.put(CAPITAL_I.getKey(), CAPITAL_I);
        patternDiagrams.put(STAIR_STEP.getKey(), STAIR_STEP);
        patternDiagrams.put(SCHOLAR_SIGN.getKey(), SCHOLAR_SIGN);
        patternDiagrams.put(LAND_SIGN.getKey(), LAND_SIGN);
        patternDiagrams.put(HORIZONTAL_LINE.getKey(), HORIZONTAL_LINE);
        patternDiagrams.put(VERTICAL_LINE.getKey(), VERTICAL_LINE);
        patternDiagrams.put(INVERSE_T.getKey(), INVERSE_T);
        patternDiagrams.put(MOUNTAIN_SIGN.getKey(), MOUNTAIN_SIGN);
        patternDiagrams.put(CAPITAL_T.getKey(), CAPITAL_T);
        patternDiagrams.put(FANCY_I.getKey(), FANCY_I);
        patternDiagrams.put(Z_SIGN.getKey(), Z_SIGN);
        patternDiagrams.put(KING_SIGN.getKey(), KING_SIGN);
    }

    /**
     * Gets the {@link PatternDiagram} with the given key.
     *
     * @param patternDiagramKey the pattern diagram key
     * @return the pattern diagram or {@code null} if not found
     *
     * TODO: Need unit tests for this operation.
     *
     */
    public static PatternDiagram getPatternDiagramByKey(String patternDiagramKey) {

        // Get the pattern diagram from the map and return it.
        return patternDiagrams.get(patternDiagramKey);
    }
}
