package org.music4j;

import java.util.Comparator;
import java.util.Objects;

/**
 * The interface requires each objects of a class that implements it to
 * associate a unique BarTime to each object of the class. This unique BarTime
 * is called through the {@link #getDuration()} method. Objects that fulfill
 * this requirement are said to be measurable and the associated BarTime is
 * called the measurement of said object. As this interface also allows to be
 * implemented through an anonymous class or a lambda it fulfills the
 * requirement of a functional interface and is annotated as such.
 * </p>
 * The Measurable interface offers a {@linkplain #timeComparator() canonical
 * Comparator} which imposes an natural ordering on all Measurable objects. As
 * this order cannot be guaranteed to be consistent with equals we refrained
 * from extending this interface from the {@link Comparable} interface.
 * Additionally some default methods are given for convenience. Trivially the
 * {@link BarTime} class itself is Measurable.
 */
public interface Measurable {

    /**
     * Returns the associated BarTime to the measurable object. The BarTime is also
     * called the measurement of the measurable object. The implementor must ensure
     * that the null is not returned. Also it is advised but not necessary that the
     * BarTime returned is greater or equal to zero.
     *
     * @return the measured BarTime
     */
    BarTime getDuration();

    /**
     * A {@link Comparator} which compares the measured BarTimes numerically as
     * rational numbers. Note that the comparator also allows for different
     * implementation of the measurable interface to be compared and is <strong>not
     * guaranteed to be consistent with equals</strong>. Nonetheless is the
     * comparator consistent with equals for the BarTime class.
     *
     * @return a {@code comparator} which returns the following for two measurable
     *         objects {@code m1} and {@code m2}:
     *         <ul>
     *         <li>{@code comparator(m1, m2) == 1}, if the measurement of {@code m1}
     *         is numerically greater than the measurement of {@code m2}.
     *         <li>{@code comparator(m1, m2) == -1}, if the measurement of
     *         {@code m1} is numerically less than the measurement of {@code m2}.
     *         <li>{@code comparator(m1, m2) == 0}, if the measurement of {@code m1}
     *         is numerically equal to the measurement of {@code m2}.
     *         </ul>
     *         The comparator throws a {@link NullPointerException} if one of the
     *         arguments is null.
     */
    static Comparator<Measurable> COMPARATOR = ((m1, m2) -> {
        Objects.requireNonNull(m1);
        Objects.requireNonNull(m2);
        return m1.getDuration().compareTo(m2.getDuration());
    });

    /**
     * Returns true if the Measurable {@code other} is longer or equal according to
     * the Comparator given in {@link Measurable} otherwise false.
     *
     * @param other the other given Measurable for comparison.
     * @return {@code true} if {@code this} is longer than {@code other}, otherwise
     *         false.
     * @throws NullpointerException if other is null
     */
    default boolean isLonger(Measurable other) {
        Objects.requireNonNull(other, "The other Measurable is null.");
        return COMPARATOR.compare(this, other) >= 0;
    }

    /**
     * Returns true if the Measurable {@code other} is shorter or equal according to
     * the Comparator given in {@link Measurable} otherwise false.
     *
     * @param other the other given Measurable for comparison.
     * @return {@code true} if {@code this} is shorter than {@code other}, otherwise
     *         false.
     */
    default boolean isShorter(Measurable other) {
        Objects.requireNonNull(other, "The other Measurable is null.");
        return COMPARATOR.compare(this, other) <= 0;
    }
}
