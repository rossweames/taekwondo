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
    public static final Belt TENTH_GUP = new Belt("tenthgup", "Tenth Gup (White)", "tenth gup white");
    public static final Belt NINTH_GUP = new Belt("ninthgup", "Ninth Gup (High White)", "ninth gup high white");
    public static final Belt EIGHTH_GUP = new Belt("eighthgup", "Eighth Gup (Yellow)", "eighth gup yellow");
    public static final Belt SEVENTH_GUP = new Belt("seventhgup", "Seventh Gup (High Yellow)", "seventh gup high yellow");
    public static final Belt SIXTH_GUP = new Belt("sixthgup", "Sixth Gup (Green)", "sixth gup green");
    public static final Belt FIFTH_GUP = new Belt("fifthgup", "Fifth Gup (High Green)", "fifth gup high green");
    public static final Belt FOURTH_GUP = new Belt("fourthgup", "Fourth Gup (Blue)", "fourth gup blue");
    public static final Belt THIRD_GUP = new Belt("thirdgup", "Third Gup (High Blue)", "third gup high blue");
    public static final Belt SECOND_GUP = new Belt("secondgup", "Second Gup (Red)", "second gup red");
    public static final Belt FIRST_GUP = new Belt("firstgup", "First Gup (High Red)", "first gup high red");
    public static final Belt FIRST_DAN = new Belt("firstdan", "First Dan", "first don");
    public static final Belt SECOND_DAN = new Belt("seconddan", "Second Dan", "second don");
    public static final Belt THIRD_DAN = new Belt("thirddan", "Third Dan", "third don");
    public static final Belt FOURTH_DAN = new Belt("fourthdan", "Fourth Dan", "fourth don");
    public static final Belt FIFTH_DAN = new Belt("fifthdan", "Fifth Dan", "fifth don");
    public static final Belt SIXTH_DAN = new Belt("sixthdan", "Sixth Dan", "sixth don");
    public static final Belt SEVENTH_DAN = new Belt("seventhdan", "Seventh Dan", "seventh don");
    public static final Belt EIGHTH_DAN = new Belt("eighthdan", "Eighth Dan", "eighth don");
    public static final Belt NINTH_DAN = new Belt("ninthdan", "Ninth Dan", "ninth don");

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
