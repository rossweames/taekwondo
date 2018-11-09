package com.eames.taekwondo.model;

import java.util.HashMap;
import java.util.Map;

/**
 * This abstract class is the collection of all possible {@link TeachMode}s.
 */
public abstract class TeachModes {

    /**
     * The teach modes
     */
    public static final TeachMode BRIEF = new TeachMode("brief", "Brief", "brief");
    public static final TeachMode DETAILED = new TeachMode("detailed", "Detailed", "detailed");

    /*
     * The teach mode map
     */
    private static Map<String, TeachMode> teachModes = new HashMap<>();

    /**
     * The static initialization block
     */
    static {
        // Fill the teach mode map.
        teachModes.put(BRIEF.getKey(), BRIEF);
        teachModes.put(DETAILED.getKey(), DETAILED);
    }

    /**
     * Gets the {@link TeachMode} with the given key.
     *
     * @param teachModeKey the teach mode key
     * @return the teach mode or {@code null} if not found
     *
     * TODO: Need unit tests for this operation.
     *
     */
    public static TeachMode getTeachModeByKey(String teachModeKey) {

        // Get the teach mode from the map and return it.
        return teachModes.get(teachModeKey);
    }
}
