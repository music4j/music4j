package org.music4j.test.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.music4j.BarTime;
import org.music4j.Measurable;

class MeasurableTest {

    /**
     * Comparator {@link Measurable#COMPARATOR} throws {@link NullPointerException}
     * if the first argument is null.
     */
    @Test
    void comparatorThrowsNullpointerIfFirstNull() {
        assertThrows(NullPointerException.class, () -> Measurable.COMPARATOR.compare(null, BarTime.ZERO));
    }

    /**
     * Comparator {@link Measurable#COMPARATOR} throws {@link NullPointerException}
     * if the second argument is null.
     */
    @Test
    void comparatorThrowsNullpointerIfSecondNull() {
        assertThrows(NullPointerException.class, () -> Measurable.COMPARATOR.compare(BarTime.ZERO, null));
    }

    /**
     * As per contract the Comparator changes the sign if arguments are switched.
     * This is called antisymmetry.
     */
    @Test
    void compareToAntiSymmetry() {
        BarTime first = BarTime.of(5, 12);
        BarTime second = BarTime.of(2);
        assertTrue(Integer.signum(Measurable.COMPARATOR.compare(first, second)) == -Integer
                .signum(Measurable.COMPARATOR.compare(second, () -> first)));
    }

    /**
     * Every object is equal to itself in comparison. This is called reflexivity.
     */
    @Test
    void compareToReflexivity() {
        BarTime first = BarTime.of(5, 12);
        assertEquals(0, Measurable.COMPARATOR.compare(first, first));
    }

    /**
     * Comparator is trasitiv.
     */
    @Test
    void compareToTransitivety() {
        BarTime first = BarTime.of(-5, 12);
        BarTime second = BarTime.of(-2, 12);
        BarTime third = BarTime.of(3);
        assertEquals(1, Integer.signum(Measurable.COMPARATOR.compare(second, first)));
        assertEquals(1, Integer.signum(Measurable.COMPARATOR.compare(third, first)));
        // This is the transitivety
        assertEquals(1, Integer.signum(Measurable.COMPARATOR.compare(third, first)));
    }

    /**
     * Compares three Measurable objects 2 are equal and one bigger
     */
    @Test
    void isLonger() {
        BarTime first = BarTime.of(1);
        BarTime second = BarTime.of(1);
        BarTime third = BarTime.of(2);
        assertEquals(true, third.isLonger(first));
        assertEquals(true, second.isLonger(first));
    }

    /**
     * Compare Method is greater throws Nullpointer exception when compared to null.
     */
    @Test
    void isLongerThrowsNullpointer() {
        assertThrows(NullPointerException.class, () -> BarTime.of(1).isLonger(null));
    }

    /**
     * Compares three Measurable objects 2 are equal and one bigger
     */
    @Test
    void isShorter() {
        BarTime first = BarTime.of(1);
        BarTime second = BarTime.of(1);
        BarTime third = BarTime.of(2);
        assertEquals(false, third.isShorter(first));
        assertEquals(true, second.isShorter(first));
    }

    /**
     * Compare Method isLess throws Nullpointer exception when compared to null.
     */
    @Test
    void isShorterThrowsNullpointer() {
        assertThrows(NullPointerException.class, () -> BarTime.of(1).isShorter(null));
    }
}
