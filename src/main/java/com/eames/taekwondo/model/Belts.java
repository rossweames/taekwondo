package com.eames.taekwondo.model;

import java.util.HashMap;
import java.util.Map;

/**
 * This abstract class is the collection of all possible {@link Belt}s.
 */
public abstract class Belts {

    /**
     * The belts
     */
    public static final Belt TENTH_GUP = new Belt("tenth gup", "Tenth Gup (White)", "tenth gup white");
    public static final Belt NINTH_GUP = new Belt("ninth gup", "Ninth Gup (High White)", "ninth gup high white");
    public static final Belt EIGHTH_GUP = new Belt("eighth gup", "Eighth Gup (Yellow)", "eighth gup yellow");
    public static final Belt SEVENTH_GUP = new Belt("seventh gup", "Seventh Gup (High Yellow)", "seventh gup high yellow)");
    public static final Belt SIXTH_GUP = new Belt("sixth gup", "Sixth Gup (Green)", "sixth gup green");
    public static final Belt FIFTH_GUP = new Belt("fifth gup", "Fifth Gup (High Green)", "fifth gup high green");
    public static final Belt FOURTH_GUP = new Belt("fourth gup", "Fourth Gup (Blue)", "fourth gup blue");
    public static final Belt THIRD_GUP = new Belt("third gup", "Third Gup (High Blue)", "third gup high blue");
    public static final Belt SECOND_GUP = new Belt("second gup", "Second Gup (Red)", "second gup red");
    public static final Belt FIRST_GUP = new Belt("first gup", "First Gup (High Red)", "first gup high red");
    public static final Belt FIRST_DAN = new Belt("first dan", "First Dan", "first dan");
    public static final Belt SECOND_DAN = new Belt("second dan", "Second Dan", "second dan");
    public static final Belt THIRD_DAN = new Belt("third dan", "Third Dan", "third dan");
    public static final Belt FOURTH_DAN = new Belt("fourth dan", "Fourth Dan", "fourth dan");
    public static final Belt FIFTH_DAN = new Belt("fifth dan", "Fifth Dan", "fifth dan");
    public static final Belt SIXTH_DAN = new Belt("sixth dan", "Sixth Dan", "sixth dan");
    public static final Belt SEVENTH_DAN = new Belt("seventh dan", "Seventh Dan", "seventh dan");
    public static final Belt EIGHTH_DAN = new Belt("eighth dan", "Eighth Dan", "eighth dan");
    public static final Belt NINTH_DAN = new Belt("ninth dan", "Ninth Dan", "ninth dan");

    /*
     * The belt map
     */
    private static Map<String, Belt> belts = new HashMap<>();

    /**
     * The static initialization block
     */
    static {
        // Fill the belt map.
        belts.put(TENTH_GUP.getKey(), TENTH_GUP);
        belts.put(NINTH_GUP.getKey(), NINTH_GUP);
        belts.put(EIGHTH_GUP.getKey(), EIGHTH_GUP);
        belts.put(SEVENTH_GUP.getKey(), SEVENTH_GUP);
        belts.put(SIXTH_GUP.getKey(), SIXTH_GUP);
        belts.put(FIFTH_GUP.getKey(), FIFTH_GUP);
        belts.put(FOURTH_GUP.getKey(), FOURTH_GUP);
        belts.put(THIRD_GUP.getKey(), THIRD_GUP);
        belts.put(SECOND_GUP.getKey(), SECOND_GUP);
        belts.put(FIRST_GUP.getKey(), FIRST_GUP);
        belts.put(FIRST_DAN.getKey(), FIRST_DAN);
        belts.put(SECOND_DAN.getKey(), SECOND_DAN);
        belts.put(THIRD_DAN.getKey(), THIRD_DAN);
        belts.put(FOURTH_DAN.getKey(), FOURTH_DAN);
        belts.put(FIFTH_DAN.getKey(), FIFTH_DAN);
        belts.put(SIXTH_DAN.getKey(), SIXTH_DAN);
        belts.put(SEVENTH_DAN.getKey(), SEVENTH_DAN);
        belts.put(EIGHTH_DAN.getKey(), EIGHTH_DAN);
        belts.put(NINTH_DAN.getKey(), NINTH_DAN);
    }

    /**
     * Gets the {@link Belt} with the given key.
     *
     * @param beltKey the belt key
     * @return the belt or {@code null} if not found
     *
     * TODO: Need unit tests for this operation.
     *
     */
    public static Belt getBeltByKey(String beltKey) {

        // Get the belt from the map and return it.
        return belts.get(beltKey);
    }
}
