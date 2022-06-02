package org.music4j.test.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.music4j.Pitch;
import org.music4j.Pitch.Alter;
import org.music4j.Pitch.Octave;
import org.music4j.Pitch.Step;

/**
 * Test case for Pitch class.
 */
class PitchTest {

    private static Pitch first = Pitch.of(Step.C, Alter.NATURAL, Octave.SMALL);

    private static Pitch second = Pitch.of(Step.E, Alter.FLAT, Octave.SMALL);

    private static Pitch third = Pitch.of(Step.C, Alter.NATURAL, Octave.ONE_LINED);

    /**
     * Simple test for the getter of step
     */
    @Test
    @DisplayName("Getter for step")
    void getStep() {
        assertEquals(Step.C, first.getStep());
    }

    /**
     * Simple test for the getter of alter
     */
    @Test
    @DisplayName("Getter for alter")
    void getAlter() {
        assertEquals(Alter.NATURAL, first.getAlter());
    }

    /**
     * Simple test for the getter of octave
     */
    @Test
    @DisplayName("Getter for octave")
    void getOctave() {
        assertEquals(Octave.SMALL, first.getOctave());
    }

    /**
     * As per contract the compareTo changes the sign if arguments are switched.
     * This is called antisymmetry.
     */
    @Test
    @DisplayName("Antisymmetry")
    void compareToAntiSymmetry() {
        assertTrue(Integer.signum(first.compareTo(second)) == -Integer.signum(second.compareTo(first)));
    }

    /**
     * Every object is equal to itself in comparison. This is called reflexivity.
     */
    @Test
    @DisplayName("Reflexivity")
    void compareToReflexivity() {
        assertEquals(0, first.compareTo(first));
    }

    /**
     * CompareTo is transitivity.
     */
    @Test
    @DisplayName("Transitivity")
    void compareToTransitivety() {
        assertEquals(1, Integer.signum(second.compareTo(first)));
        assertEquals(1, Integer.signum(third.compareTo(second)));
        // This is the transitivety
        assertEquals(1, Integer.signum(third.compareTo(first)));
    }

    /**
     * CompareTo throws a NullpointerException if the given argument is null.
     */
    @Test
    @DisplayName("CompareTo throws Nullpointer Exception when compared to null.")
    void compareToNullpointerException() {
        assertThrows(NullPointerException.class, () -> first.compareTo(null));
    }

    /**
     * Test for asInt
     */
    @Test
    void asInt() {
        assertEquals(48, first.asInt());
    }

    /**
     * Test for enharmonic notes
     */
    @Test
    @DisplayName("Enharmonic notes")
    void enharmonic() {
        Pitch other = Pitch.of(Step.D, Alter.FLAT_FLAT, Octave.SMALL);
        assertFalse(first.equals(other));
        assertTrue(first.isEnharmonicTo(other));
        assertTrue(first.compareTo(other) == 0);
    }

    /**
     * Test for toString method
     */
    @Test
    @DisplayName("Test for toString method")
    void toStringTest() {
        assertEquals("C", first.toString());
        assertEquals("Eb,,", Pitch.of(Step.E, Alter.FLAT, Octave.CONTRA).toString());
        assertEquals("Dbb'", Pitch.of(Step.D, Alter.FLAT_FLAT, Octave.ONE_LINED).toString());
        assertEquals("F#''''", Pitch.of(Step.F, Alter.SHARP, Octave.FOUR_LINED).toString());
        assertEquals("Gx", Pitch.of(Step.G, Alter.DOUBLE_SHARP, Octave.SMALL).toString());
    }

    @Nested
    class PitchParserTest {

        @Test
        void parseSimpleStep() {
            Pitch actual = Pitch.of("C");
            assertEquals(Pitch.of(Step.C, Alter.NATURAL, Octave.SMALL), actual);
        }

        @Test
        void parseSimpleStepOneLined() {
            Pitch actual = Pitch.of("C'");
            assertEquals(Pitch.of(Step.C, Alter.NATURAL, Octave.ONE_LINED), actual);
        }

        @Test
        void parseSimpleStepContra() {
            Pitch actual = Pitch.of("C,,");
            assertEquals(Pitch.of(Step.C, Alter.NATURAL, Octave.CONTRA), actual);
        }

        @Test
        void parseStepWithFlat() {
            Pitch actual = Pitch.of("Db");
            assertEquals(Pitch.of(Step.D, Alter.FLAT, Octave.SMALL), actual);
        }

        @Test
        void parseStepWithFlatFlat() {
            Pitch actual = Pitch.of("Ebb'");
            assertEquals(Pitch.of(Step.E, Alter.FLAT_FLAT, Octave.ONE_LINED), actual);
        }

        @Test
        void parseStepWithSharp() {
            Pitch actual = Pitch.of("F#''");
            assertEquals(Pitch.of(Step.F, Alter.SHARP, Octave.TWO_LINED), actual);
        }

        @Test
        void parseStepWithDoubleSharp() {
            Pitch actual = Pitch.of("Fx''");
            assertEquals(Pitch.of(Step.F, Alter.DOUBLE_SHARP, Octave.TWO_LINED), actual);
        }

        @Test
        void parseThrowsException() {
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Pitch.of("b"));
            assertEquals("The given input \"b\" cannot be processed. \r\n"
                    + " No enum constant org.music4j.Pitch.Step.<missing STEP>", e.getMessage());
        }
    }
}
