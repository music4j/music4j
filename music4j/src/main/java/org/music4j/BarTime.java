package org.music4j;

import java.util.Comparator;
import java.util.Objects;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.music4j.grammar.ErrorCollector;
import org.music4j.grammar.ParseException;
import org.music4j.grammar.TimeVisitor;
import org.music4j.grammar.gen.RubatoLexer;
import org.music4j.grammar.gen.RubatoParser;

/**
 * A BarTime is a rational number which represents the duration of a Note or a
 * specific time event in a Voice. We define that a BarTime of 1 represents the
 * duration of a quarter note or to be an event in a Bar or Voice that occurs
 * exactly after the duration of one quarter note. Both, BarTimes and rational
 * numbers are are often used interchangeably.
 * </p>
 * Every BarTime is immutable and stateless. Therefore to ensure uniqueness each
 * BarTime is given in a irreducible form with a strictly positive denominator.
 * Furthermore each BarTime has the singleton property. In particular for every
 * BarTime t1, t2 with {@code t1.equals(t2)} we have {@code t1 == t2}.
 * </p>
 * Additionally the class implements the Comparable interface and the order
 * provided is trivially consistent with equals.
 */
public final class BarTime implements Comparable<BarTime>, Measurable {

    /**
     * Comparator for comparing BarTimes. The comparator compares the objects as
     * fractions.
     */
    private static final Comparator<BarTime> COMPARATOR = (first, second) -> Long
            .compare((long) first.numerator * second.denominator, (long) second.numerator * first.denominator);

    /**
     * BarTime of 0/1
     */
    public static final BarTime ZERO = BarTime.of(0);

    /**
     * BarTime of 0/1
     */
    public static final BarTime QUARTER = BarTime.of(1);

    /**
     * The maximal value of any BarTime
     */
    public static final BarTime MAX = BarTime.of(Integer.MAX_VALUE);

    // Class fields
    /**
     * The numerator of the fraction.
     */
    private final int numerator;

    /**
     * The denominator of the fraction.
     */
    private final int denominator;

    /**
     * Private Constructor should not be used outside of class. The fraction is
     * represented in a completely irreducible form with a positive denominator to
     * gain uniqueness.
     */
    private BarTime(int numerator, int denominator) {
        // The denominator cannot be zero.
        if (denominator == 0) {
            throw new ArithmeticException("A BarTime cannot have denominator zero.");
        }
        // The gcf cannot be zero as both numbers cannot be zero.
        int gcf = greatestCommonFactor(numerator, denominator);
        if (denominator > 0) {
            this.numerator = numerator / gcf;
            this.denominator = denominator / gcf;
        } else {
            // Change the sign of the denominator if the denominator is less than zero to
            // gain uniqueness.
            this.numerator = -numerator / gcf;
            this.denominator = -denominator / gcf;
        }
    }

    /**
     * Calculates the greatest common factor of the integers {@code i} and {@code j}
     * The method relies on the Euclidean algorithm. If one of the integers is zero
     * the biggest absolute value of both integers is returned.
     *
     * @param i the first integer.
     * @param j the second integer.
     * @return the absolute value of the greatest common factor.
     */
    public static int greatestCommonFactor(int i, int j) {
        if (i == 0 || j == 0) {
            return Math.max(Math.abs(i), Math.abs(j));
        } else {
            int r0 = Math.max(Math.abs(i), Math.abs(j));
            int r1 = Math.min(Math.abs(i), Math.abs(j));
            int newR0;
            while ((r0 % r1) != 0) {
                newR0 = r1;
                r1 = (r0 % r1);
                r0 = newR0;
            }
            return Math.abs(r1);
        }
    }

    /**
     * Static factory method that returns a BarTime with a completely irreducible
     * fraction representation that is equal to the fraction of
     * {@code enumerator/denominator}. If such a {@code BarTime} has already been
     * created this same object is returned. Otherwise such a {@code BarTime} is
     * created and cached.
     *
     * @return a BarTime in a completely irreducible form.
     * @throws ArithmeticException if the denominator is zero.
     */
    public static BarTime of(int numerator, int denominator) {
        return new BarTime(numerator, denominator);
    }

    /**
     * Static factory method that returns a BarTime equal to the fraction of
     * {@code i/1}. If such a {@code BarTime} has already been created the same
     * object is returned. Otherwise such a {@code BarTime} is created and cached.
     *
     * @param i the numerator of the BarTime
     * @return a BarTime equal to {@code i/1}.
     */
    public static BarTime of(int i) {
        return BarTime.of(i, 1);
    }

    /**
     * Static factory method that returns a BarTime with a completely irreducible
     * fraction representation that is equal to the given string input. If such a
     * {@code BarTime} has already been created this same object is returned.
     * Otherwise such a {@code BarTime} is created and cached.
     *
     * @return a BarTime in a completely irreducible form.
     * @throws ArithmeticException      if the denominator is zero.
     * @throws IllegalArgumentException if the input cannot be processed.
     */
    public static BarTime of(String string) {
        try {
            CharStream input = CharStreams.fromString(string);
            RubatoLexer lexer = new RubatoLexer(input);
            lexer.removeErrorListeners();
            ErrorCollector errCollector = new ErrorCollector();
            lexer.addErrorListener(errCollector);
            TokenStream tokens = new CommonTokenStream(lexer);
            RubatoParser parser = new RubatoParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(errCollector);
            TimeVisitor visitor = new TimeVisitor();
            BarTime duration = visitor.visitDuration(parser.duration());
            errCollector.throwErrors();
            return duration;
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    /**
     * A BarTime is represented by a fraction. This method returns the numerator of
     * this representation.
     *
     * @return the numerator of the BarTime.
     */
    public int getNumerator() {
        return numerator;
    }

    /**
     * A BarTime is represented by a fraction. This method returns the denominator
     * of this representation.
     *
     * @return the denominator of the BarTime.
     */
    public int getDenominator() {
        return denominator;
    }

    /**
     * Adds this instance with the other and returns a BarTime that is equal to the
     * sum of both fraction.
     *
     * @param other the other summand.
     * @return the sum of both BarTime.
     * @throws NullPointerException if the other BarTime is {@code null}.
     */
    public BarTime plus(BarTime other) {
        Objects.requireNonNull(other);

        int lcm = leastCommonMultiple(denominator, other.denominator);
        int firstExpandedEnum = numerator * (lcm / denominator);
        int secondExpandedEnum = other.numerator * (lcm / other.denominator);

        int sumEnumerators = firstExpandedEnum + secondExpandedEnum;
        return BarTime.of(sumEnumerators, lcm);
    }

    /**
     * Calculates the absolute value of the least common multiple of {@code i} and
     * {@code j}.
     *
     * @param i the first integer.
     * @param j the second integer.
     * @return the absolute value of the least common multiple of {@code i} and
     *         {@code j}.
     */
    public static int leastCommonMultiple(int i, int j) {
        return Math.abs((i * j) / greatestCommonFactor(i, j));
    }

    /**
     * Subtract this instance with the other and returns a BarTime that is equal to
     * the difference of both fraction.
     *
     * @param other the other term.
     * @return the difference of both BarTime.
     * @throws NullPointerException if the other BarTime is {@code null}.
     */
    public BarTime minus(BarTime other) {
        Objects.requireNonNull(other);

        int lcm = leastCommonMultiple(denominator, other.denominator);
        int firstExpandedEnum = numerator * (lcm / denominator);
        int secondExpandedEnum = other.numerator * (lcm / other.denominator);

        int diffEnumerators = firstExpandedEnum - secondExpandedEnum;
        return BarTime.of(diffEnumerators, lcm);
    }

    /**
     * Multiplies the instance and the other BarTime.
     *
     * @param other the other BarTime
     * @return the multiplication of both BarTimes.
     * @throws NullPointerException if other is {@code null.}
     */
    public BarTime times(BarTime other) {
        Objects.requireNonNull(other);
        return BarTime.of(numerator * other.numerator, denominator * other.denominator);
    }

    /**
     * Divide the instance and the other BarTime.
     *
     * @param other the other BarTime
     * @return the division of both BarTimes.
     * @throws NullPointerException if other is {@code null.}
     */
    public BarTime dividedBy(BarTime other) {
        Objects.requireNonNull(other);
        if (other == BarTime.ZERO) {
            throw new ArithmeticException("Cannot divide by zero.");
        }
        return BarTime.of(numerator * other.denominator, denominator * other.numerator);
    }

    @Override
    public int compareTo(BarTime o) {
        Objects.requireNonNull(o);
        return COMPARATOR.compare(this, o);
    }

    /**
     * Returns the bigger BarTime.
     *
     * @return the BarTime with the biggest value.
     * @throws NullPointerException if any of the arguments is null.
     */
    public static BarTime max(BarTime first, BarTime second) {
        Objects.requireNonNull(first);
        Objects.requireNonNull(second);
        return COMPARATOR.compare(first, second) >= 0 ? first : second;
    }

    /**
     * Returns the lower BarTime.
     *
     * @return the BarTime with the lower value.
     * @throws NullPointerException if any of the arguments is null.
     */
    public static BarTime min(BarTime first, BarTime second) {
        Objects.requireNonNull(first);
        Objects.requireNonNull(second);
        return COMPARATOR.compare(first, second) <= 0 ? first : second;
    }

    @Override
    public BarTime getDuration() {
        return this;
    }

    /**
     * Returns true if the time is equivalent to a quarter note. As default this
     * time is not displayed in a parse.
     *
     * @return true if the time is equivalent to a quarter note.
     */
    public boolean isTrivial() {
        return equals(QUARTER);
    }

    @Override
    public String toString() {
        if (denominator == 1) {
            return String.valueOf(numerator);
        } else if (numerator == 1) {
            return String.format("/%s", denominator);
        } else {
            return String.format("%d/%d", numerator, denominator);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(denominator, numerator);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        BarTime other = (BarTime) obj;
        return denominator == other.denominator && numerator == other.numerator;
    }
}
