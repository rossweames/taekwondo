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
    public static final PatternDiagram CAPITAL_I = new PatternDiagram("capital i", "Capital I", "capital i");
    public static final PatternDiagram STAIR_STEP = new PatternDiagram("stair step", "Stair Step or Backward Z", "stair step or backward z");
    public static final PatternDiagram SCHOLAR_SIGN = new PatternDiagram("scholar sign", "Scholar Sign", "scholar sign");

    /*
     * The pattern diagram map
     */
    private static Map<String, PatternDiagram> patternDiagrams = new HashMap<>();

    /**
     * The static initialization block
     */
    {
        // Fill the pattern map.
        patternDiagrams.put(CROSS.getKey(), CROSS);
        patternDiagrams.put(CAPITAL_I.getKey(), CAPITAL_I);
        patternDiagrams.put(STAIR_STEP.getKey(), STAIR_STEP);
        patternDiagrams.put(SCHOLAR_SIGN.getKey(), SCHOLAR_SIGN);
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
