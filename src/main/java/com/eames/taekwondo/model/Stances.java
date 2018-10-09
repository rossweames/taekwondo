package com.eames.taekwondo.model;

import java.util.HashMap;
import java.util.Map;

/**
 * This abstract class is the collection of all possible {@link Stance}s.
 */
public class Stances {

    /**
     * The stances
     */
    public static final Stance PARALLEL_READY = new Stance("parallel_ready", "Parallel Ready", "parallel ready");
    public static final Stance PARALLEL_TWIN = new Stance("parallel_twin", "Parallel with a Twin Side Elbow", "parallel with a twin side elbow");
    public static final Stance PARALLEL_X = new Stance("parallel_x", "Parallel with an X-Back Hand", "parallel with an x back hand");
    public static final Stance PARALLEL_OVER = new Stance("parallel_over", "Parallel with an Overlapped Back Hand", "parallel with an overlapped back hand");
    public static final Stance PARALLEL_HEAVEN = new Stance("parallel_heaven", "Parallel Heaven Hands", "parallel heaven hands");
    public static final Stance CLOSED_READY_A = new Stance("closed_ready_a", "Closed Ready Type A", "closed ready type a");
    public static final Stance CLOSED_READY_B = new Stance("closed_ready_b", "Closed Ready Type B", "closed ready type b");
    public static final Stance CLOSED_READY_C = new Stance("closed_ready_c", "Closed Ready Type C", "closed ready type c");
    public static final Stance CLOSED_READY_D = new Stance("closed_ready_d", "Closed Ready Type D", "closed ready type d");
    public static final Stance WARRIOR_READY_A = new Stance("warrior_ready_a", "Warrior Ready Type A", "warrior ready type a");

    /*
     * The stance map
     */
    private static Map<String, Stance> stances = new HashMap<>();

    /**
     * The static initialization block
     */
    static {
        // Fill the stance map.
        stances.put(PARALLEL_READY.getKey(), PARALLEL_READY);
    }

    /**
     * Gets the {@link Stance} with the given key.
     *
     * @param stanceKey the stance key
     * @return the stance or {@code null} if not found
     *
     * TODO: Need unit tests for this operation.
     *
     */
    public static Stance getStanceByKey(String stanceKey) {

        // Get the stance from the map and return it.
        return stances.get(stanceKey);
    }
}
