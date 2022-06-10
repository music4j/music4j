package org.music4j.test.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.music4j.BarTime;

/**
 * Testcase for {@link BarTime} class.
 */
class BarTimeTest {

    /**
     * Simple test for the getter of the numerator.
     */
    @Test
    @DisplayName("Getter for Numerator")
    void getNumerator() {
        BarTime t = BarTime.of(1, 2);
        assertEquals(1, t.getNumerator());
    }

    /**
     * Simple test for the getter of the numerator.
     */
    @Test
    @DisplayName("Getter for Denominator")
    void getDenominator() {
        BarTime t = BarTime.of(1, 2);
        assertEquals(2, t.getDenominator());
    }

    /**
     * The fraction is in completely irreducible form.
     */
    @Test
    @DisplayName("Completely irreducible")
    void irreducibleForm() {
        BarTime t = BarTime.of(24, 60);
        assertEquals(2, t.getNumerator());
        assertEquals(5, t.getDenominator());
    }

    /**
     * To gain uniqueness the denominator is always positive.
     */
    @Test
    @DisplayName("Positive denominator")
    void positiveDenominator() {
        BarTime t = BarTime.of(2, -5);
        assertEquals(-2, t.getNumerator());
        assertEquals(5, t.getDenominator());
    }

    /**
     * If the numerator is zero then the denominator is reduced to one. Otherwise
     * there would be an ambiguity.
     */
    @Test
    @DisplayName("Unique zero")
    void UniqueZero() {
        BarTime t = BarTime.of(0, 100);
        assertEquals(0, t.getNumerator());
        assertEquals(1, t.getDenominator());
    }

    /**
     * The denominator cannot be zero as we would then divide with zero.
     */
    @Test
    @DisplayName("Cannot divide with zero")
    void illegalArgumentExceptionDivideWithZero() {
        ArithmeticException e = assertThrows(ArithmeticException.class, () -> BarTime.of(10, 0));
        assertEquals("A BarTime cannot have denominator zero.", e.getMessage());
    }

    /**
     * Plus throws a Nullpointer Exception if the other parameter is null.
     */
    @Test
    @DisplayName("Plus throws Nullpointer Exception")
    void nullpointerExceptionPlus() {
        BarTime t = BarTime.of(1);
        assertThrows(NullPointerException.class, () -> t.plus(null));
    }

    /**
     * Add two BarTimes. The returned BarTimes is also in completely irreducible
     * form.
     */
    @Test
    @DisplayName("Add two BarTimes")
    void plus() {
        BarTime t1 = BarTime.of(1, 3);
        BarTime t2 = BarTime.of(3, 4);
        assertEquals(BarTime.of(13, 12), t1.plus(t2));
    }

    /**
     * Minus throws a Nullpointer Exception if the other parameter is null.
     */
    @Test
    @DisplayName("Minus throws Nullpointer Exception")
    void nullpointerExceptionMinus() {
        BarTime t = BarTime.of(1);
        assertThrows(NullPointerException.class, () -> t.minus(null));
    }

    /**
     * Subtract two BarTimes. The returned BarTimes is also in completely
     * irreducible form.
     */
    @Test
    @DisplayName("Subtract two BarTimes")
    void minus() {
        BarTime t1 = BarTime.of(1, 3);
        BarTime t2 = BarTime.of(3, 4);
        assertEquals(BarTime.of(-5, 12), t1.minus(t2));
    }

    /**
     * Times throws a Nullpointer Exception if the other parameter is null.
     */
    @Test
    @DisplayName("Times throws Nullpointer Exception")
    void nullpointerExceptionTimes() {
        BarTime t = BarTime.of(1);
        assertThrows(NullPointerException.class, () -> t.times(null));
    }

    /**
     * Multiply two BarTimes. The returned BarTimes is also in completely
     * irreducible form.
     */
    @Test
    @DisplayName("Multiply two BarTimes")
    void times() {
        BarTime t1 = BarTime.of(1, 3);
        BarTime t2 = BarTime.of(3, 4);
        assertEquals(BarTime.of(3, 12), t1.times(t2));
    }

    /**
     * DividedBy throws a Nullpointer Exception if the other parameter is null.
     */
    @Test
    @DisplayName("Division throws Nullpointer Exception")
    void nullpointerExceptionDividedBy() {
        BarTime t = BarTime.of(1);
        assertThrows(NullPointerException.class, () -> t.dividedBy(null));
    }

    /**
     * DividedBy throws a AritmeticException if the other parameter is zero.
     */
    @Test
    @DisplayName("Division throws Nullpointer Exception")
    void arithmeticExceptionDividedBy() {
        BarTime t = BarTime.of(1);
        ArithmeticException e = assertThrows(ArithmeticException.class, () -> t.dividedBy(BarTime.ZERO));
        assertEquals("Cannot divide by zero.", e.getMessage());
    }

    /**
     * Division two BarTimes. The returned BarTimes is also in completely
     * irreducible form.
     */
    @Test
    @DisplayName("Division of two BarTimes")
    void dividedBy() {
        BarTime t1 = BarTime.of(3, 1);
        BarTime t2 = BarTime.of(3, 4);
        //
        assertEquals(BarTime.of(4), t1.dividedBy(t2));
    }

    /**
     * As per contract the compareTo changes the sign if arguments are switched.
     * This is called antisymmetry.
     */
    @Test
    @DisplayName("Anti Symmetry")
    void compareToAntiSymmetry() {
        BarTime first = BarTime.of(5, 12);
        BarTime second = BarTime.of(2);
        assertTrue(Integer.signum(first.compareTo(second)) == -Integer.signum(second.compareTo(first)));
    }

    /**
     * Every object is equal to itself in comparison. This is called reflexivity.
     */
    @Test
    @DisplayName("Reflexivity")
    void compareToReflexivity() {
        BarTime first = BarTime.of(5, 12);
        assertEquals(0, first.compareTo(first));
    }

    /**
     * CompareTo is trasitiv.
     */
    @Test
    @DisplayName("Transitivety")
    void compareToTransitivety() {
        BarTime first = BarTime.of(-5, 12);
        BarTime second = BarTime.of(-2, 12);
        BarTime third = BarTime.of(3);
        assertEquals(1, Integer.signum(second.compareTo(first)));
        assertEquals(1, Integer.signum(third.compareTo(second)));
        // This is the transitivety
        assertEquals(1, Integer.signum(third.compareTo(first)));
    }

    /**
     * CompareTo throws a NullpointerException if the given argument is null.
     */
    @Test
    @DisplayName("Nullpointer Exception Compare To")
    void compareToNullpointerException() {
        BarTime first = BarTime.of(-5, 12);
        assertThrows(NullPointerException.class, () -> first.compareTo(null));
    }

    /**
     * CompareTo throws a NullpointerException if the given argument is null.
     */
    @Test
    @DisplayName("compareTo exceeds Integer")
    void compareToExceedsInteger() {
        BarTime first = BarTime.of(Integer.MAX_VALUE, 1);
        BarTime second = BarTime.of(1, Integer.MAX_VALUE);
        assertEquals(1, first.compareTo(second));
    }

    /**
     * max gives back the maximum Measurable
     */
    @Test
    void max() {
        BarTime first = BarTime.of(3);
        BarTime second = BarTime.of(4);
        assertEquals(second, BarTime.max(first, second));
    }

    /**
     * max gives back the first if both are equal.
     */
    @Test
    void maxBothEqual() {
        BarTime first = BarTime.of(4);
        BarTime second = BarTime.of(4);
        assertEquals(first, BarTime.max(first, second));
    }

    /**
     * max throws a NullpointerException if the first is null.
     */
    @Test
    void maxThrows() {
        BarTime second = BarTime.of(4);
        assertThrows(NullPointerException.class, () -> BarTime.max(null, second));
    }

    /**
     * max throws a NullpointerException if the second is null.
     */
    @Test
    void maxThrows2() {
        BarTime first = BarTime.of(4);
        assertThrows(NullPointerException.class, () -> BarTime.max(first, null));
    }

    /**
     * min gives back the minimal Measurable
     */
    @Test
    void min() {
        BarTime first = BarTime.of(3);
        BarTime second = BarTime.of(4);
        assertEquals(first, BarTime.min(first, second));
    }

    /**
     * min gives back the first if both are equal.
     */
    @Test
    void minBothEqual() {
        BarTime first = BarTime.of(4);
        BarTime second = BarTime.of(4);
        assertEquals(first, BarTime.min(first, second));
    }

    /**
     * min throws a NullpointerException if the first is null.
     */
    @Test
    void minThrows() {
        BarTime second = BarTime.of(4);
        assertThrows(NullPointerException.class, () -> BarTime.min(null, second));
    }

    /**
     * min throws a NullpointerException if the second is null.
     */
    @Test
    void minThrows2() {
        BarTime first = BarTime.of(4);
        assertThrows(NullPointerException.class, () -> BarTime.min(first, null));
    }

    /**
     * min throws a NullpointerException if the second is null.
     */
    @Test
    void toStringInt() {
        BarTime first = BarTime.of(4);
        assertEquals("4", first.toString());
    }

    @Nested
    class BarTimeParserTest {

        @Test
        void parseInt() {
            BarTime time = BarTime.of("3");
            assertEquals(BarTime.of(3), time);
        }

        @Test
        void parseInvertedInt() {
            BarTime time = BarTime.of("/3");
            assertEquals(BarTime.of(1, 3), time);
        }

        @Test
        void parseFraction() {
            BarTime time = BarTime.of("7/3");
            assertEquals(BarTime.of(7, 3), time);
        }

        @Test
        void parseThrowsException() {
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> BarTime.of("1//2"));
            assertEquals("org.music4j.grammar.ParseException: line 1: 2 extraneous input '/' expecting INT",
                    e.getMessage());
        }
    }
}
