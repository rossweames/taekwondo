package com.eames.taekwondo.model;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * Tests the {@link Pattern} class.
 * Clears the movement array between tests
 */
public class PatternTest {

    @AfterEach
    public void tearDown() {
        Patterns.CHON_JI.clearMovements();
    }

    /*
     * Chon-Ji
     */

    @Test
    public void TestChonjiGetHistory() {

        String history = Patterns.CHON_JI.getHistory();
        assertNotNull(history);
    }

    @Test
    public void TestChonjiGetNthMovement_TooSmall() {

        Movement movement = Patterns.CHON_JI.getNthMovement(-1);
        assertNull(movement);
    }

    @Test
    public void TestChonjiGetStartMovement() {

        Movement movement = Patterns.CHON_JI.getStartMovement();
        assertNotNull(movement);
    }

    @Test
    public void TestChonjiGetNthMovement_1() {

        Movement movement = Patterns.CHON_JI.getNthMovement(1);
        assertNotNull(movement);
    }

    @Test
    public void TestChonjiGetNthMovement_Count() {

        Movement movement = Patterns.CHON_JI.getNthMovement(Patterns.CHON_JI.getMovementCount());
        assertNotNull(movement);
    }

    @Test
    public void TestChonjiGetNthMovement_TooLarge() {

        Movement movement = Patterns.CHON_JI.getNthMovement(Patterns.CHON_JI.getMovementCount() + 2);
        assertNull(movement);
    }

    @Test
    public void TestChonjiGetFinishMovement() {

        Movement movement = Patterns.CHON_JI.getFinishMovement();
        assertNotNull(movement);
    }
}
