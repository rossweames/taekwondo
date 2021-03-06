package com.eames.taekwondo.model;

import java.util.HashMap;
import java.util.Map;

/**
 * This abstract class is the collection of all possible {@link Pattern}s.
 */
public abstract class Patterns {

    /**
     * The json
     */
    public static final Pattern SA_JU_JIRU_GI = new Pattern("sajujirugi","Sa-Ju Jiru-Gi", "sah-jew gear-oo-gee", Belts.TENTH_GUP, PatternDiagrams.CROSS, 7, Stances.PARALLEL_READY);
    public static final Pattern SA_JU_MAK_GI = new Pattern("sajumakgi","Sa-Ju Mak-Gi", "sah-jew mock-gee", Belts.TENTH_GUP, PatternDiagrams.CROSS, 8, Stances.PARALLEL_READY);
    public static final Pattern CHON_JI = new Pattern("chonji", "Chon-Ji", "chun-gee", Belts.NINTH_GUP, PatternDiagrams.CROSS, 19, Stances.PARALLEL_READY);
    public static final Pattern DAN_GUN = new Pattern("dangun","Dan-Gun", "don-goon", Belts.EIGHTH_GUP, PatternDiagrams.CAPITAL_I, 21, Stances.PARALLEL_READY);
    public static final Pattern DO_SAN = new Pattern("dosan","Do-San", "doe-sahn", Belts.SEVENTH_GUP, PatternDiagrams.STAIR_STEP, 24, Stances.PARALLEL_READY);
    public static final Pattern WON_HYO = new Pattern("wonhyo","Won-Hyo", "wahn-yo", Belts.SIXTH_GUP, PatternDiagrams.CAPITAL_I, 28, Stances.CLOSED_READY_A);
    public static final Pattern YUL_GOK = new Pattern("yulgok","Yul-Gok", "yule-gook", Belts.FIFTH_GUP, PatternDiagrams.SCHOLAR_SIGN, 38, Stances.PARALLEL_READY);
    public static final Pattern JOONG_GUN = new Pattern("joonggun","Joong-Gun", "joon-goon", Belts.FOURTH_GUP, PatternDiagrams.CAPITAL_I, 32, Stances.CLOSED_READY_B);
    public static final Pattern TOI_GYE = new Pattern("toigye","Toi-Gye", "tay-gay", Belts.THIRD_GUP, PatternDiagrams.SCHOLAR_SIGN, 37, Stances.CLOSED_READY_B);
    public static final Pattern HWA_RANG = new Pattern("hwarang","Hwa-Rang", "wah-rahng", Belts.SECOND_GUP, PatternDiagrams.CAPITAL_I, 29, Stances.CLOSED_READY_C);
    public static final Pattern SA_JU_TUL_GI = new Pattern("sajutulgi","Sa-Ju Tul-Gi", "sah-jew tool-gee", Belts.SECOND_GUP, PatternDiagrams.CROSS, 4, Stances.PARALLEL_READY);
    public static final Pattern CHOONG_MOO = new Pattern("choongmoo","Choong-Moo", "shoo-moo", Belts.FIRST_GUP, PatternDiagrams.CAPITAL_I, 30, Stances.PARALLEL_READY);
    public static final Pattern KWANG_GAE = new Pattern("kwanggae","Kwang-Gae", "kwon-gay", Belts.FIRST_DAN, PatternDiagrams.LAND_SIGN, 39, Stances.PARALLEL_HEAVEN);
    public static final Pattern POE_EUN = new Pattern("poeeun","Poe-Eun", "poe-en", Belts.FIRST_DAN, PatternDiagrams.HORIZONTAL_LINE, 36, Stances.PARALLEL_HEAVEN);
    public static final Pattern GAE_BAEK = new Pattern("gaebaek","Gae-Baek", "gay-beck", Belts.FIRST_DAN, PatternDiagrams.VERTICAL_LINE, 44, Stances.PARALLEL_READY);
    public static final Pattern EUI_AM = new Pattern("euiam","Eui-Am", "ee-ahm", Belts.SECOND_DAN, PatternDiagrams.VERTICAL_LINE, 45, Stances.CLOSED_READY_D);
    public static final Pattern CHOONG_JANG = new Pattern("choongjang","Choong-Jang", "choong-jang", Belts.SECOND_DAN, PatternDiagrams.INVERSE_T, 52, Stances.CLOSED_READY_A);
    public static final Pattern JUCHE = new Pattern("juche","Juche", "jew-chay", Belts.SECOND_DAN, PatternDiagrams.MOUNTAIN_SIGN, 45, Stances.PARALLEL_TWIN);
    public static final Pattern KO_DANG = new Pattern("kodang","Ko-Dang", "ko-dang", Belts.SECOND_DAN, PatternDiagrams.CAPITAL_T, 39, Stances.CLOSED_READY_C);
    public static final Pattern SAM_IL = new Pattern("samil","Sam-Il", "sam-ill", Belts.THIRD_DAN, PatternDiagrams.CROSS, 33, Stances.CLOSED_READY_C);
    public static final Pattern YOO_SIN = new Pattern("yoosin","Yoo-Sin", "you-sin", Belts.THIRD_DAN, PatternDiagrams.FANCY_I, 68, Stances.WARRIOR_READY_A);
    public static final Pattern CHOI_YONG = new Pattern("choiyong","Choi-Yang", "choy-yahng", Belts.THIRD_DAN, PatternDiagrams.CROSS, 46, Stances.CLOSED_READY_C);
    public static final Pattern YON_GAE = new Pattern("yongae","Yon-Gae", "yon-gay", Belts.FOURTH_DAN, PatternDiagrams.CROSS, 49, Stances.WARRIOR_READY_A);
    public static final Pattern UL_JI = new Pattern("ulji","Ul-Ji", "uhl-gee", Belts.FOURTH_DAN, PatternDiagrams.Z_SIGN, 42, Stances.PARALLEL_X);
    public static final Pattern MOON_MOO = new Pattern("moonmoo","Moon-Moo", "moon-moo", Belts.FOURTH_DAN, PatternDiagrams.CROSS, 61, Stances.PARALLEL_READY);
    public static final Pattern SO_SAN = new Pattern("sosan","So-San", "so-sahn", Belts.FIFTH_DAN, PatternDiagrams.CROSS, 72, Stances.CLOSED_READY_A);
    public static final Pattern SE_JONG = new Pattern("sejong","Se-Jong", "say-jong", Belts.FIFTH_DAN, PatternDiagrams.KING_SIGN, 24, Stances.CLOSED_READY_B);
    public static final Pattern TONG_IL = new Pattern("tongil","Tong-Il", "tong-ill", Belts.SIXTH_DAN, PatternDiagrams.VERTICAL_LINE, 56, Stances.PARALLEL_OVER);

    /*
     * The pattern map
     */
    private static Map<String, Pattern> patterns = new HashMap<>();

    /**
     * The static initialization block
     */
    static {
        // Fill the pattern map.
        patterns.put(SA_JU_JIRU_GI.getKey(), SA_JU_JIRU_GI);
        patterns.put(SA_JU_MAK_GI.getKey(), SA_JU_MAK_GI);
        patterns.put(CHON_JI.getKey(), CHON_JI);
        patterns.put(DAN_GUN.getKey(), DAN_GUN);
        patterns.put(DO_SAN.getKey(), DO_SAN);
        patterns.put(WON_HYO.getKey(), WON_HYO);
        patterns.put(YUL_GOK.getKey(), YUL_GOK);
        patterns.put(JOONG_GUN.getKey(), JOONG_GUN);
        patterns.put(TOI_GYE.getKey(), TOI_GYE);
        patterns.put(HWA_RANG.getKey(), HWA_RANG);
        patterns.put(SA_JU_TUL_GI.getKey(), SA_JU_TUL_GI);
        patterns.put(CHOONG_MOO.getKey(), CHOONG_MOO);
        patterns.put(KWANG_GAE.getKey(), KWANG_GAE);
        patterns.put(POE_EUN.getKey(), POE_EUN);
        patterns.put(GAE_BAEK.getKey(), GAE_BAEK);
        patterns.put(EUI_AM.getKey(), EUI_AM);
        patterns.put(CHOONG_JANG.getKey(), CHOONG_JANG);
        patterns.put(JUCHE.getKey(), JUCHE);
        patterns.put(KO_DANG.getKey(), KO_DANG);
        patterns.put(SAM_IL.getKey(), SAM_IL);
        patterns.put(YOO_SIN.getKey(), YOO_SIN);
        patterns.put(CHOI_YONG.getKey(), CHOI_YONG);
        patterns.put(YON_GAE.getKey(), YON_GAE);
        patterns.put(UL_JI.getKey(), UL_JI);
        patterns.put(MOON_MOO.getKey(), MOON_MOO);
        patterns.put(SO_SAN.getKey(), SO_SAN);
        patterns.put(SE_JONG.getKey(), SE_JONG);
        patterns.put(TONG_IL.getKey(), TONG_IL);
    }

    /**
     * Gets the {@link Pattern} with the given key.
     *
     * @param patternKey the pattern key
     * @return the pattern or {@code null} if not found
     *
     * TODO: Need unit tests for this operation.
     *
     */
    public static Pattern getPatternByKey(String patternKey) {

        // Get the pattern from the map and return it.
        return patterns.get(patternKey);
    }
}
