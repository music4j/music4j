package org.music4j;

import static java.util.Comparator.comparingInt;

import java.util.Comparator;
import java.util.EnumSet;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Set;

import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.music4j.grammar.RubatoInterpreter;
import org.music4j.grammar.gen.RubatoLexer;
import org.music4j.grammar.gen.RubatoParser;

/**
 * A Pitch or Tone-Pitch consists of a {@link Step}, an {@linkplain Alter
 * Alteration} and an {@link Octave}. The definition off all those enum types
 * has been added to the Pitch class for convenience and because all those type
 * are closely tied together. Specific values can be accessed through the
 * methods: {@link #getStep()}, {@link #getAlter()} and {@link #getOctave()}.
 */
public final class Pitch implements Comparable<Pitch> {

    /**
     * A Pitch-Comparator which compares only the music pitch as integer values.
     * This comparator is <b>not</b> consistent with equals and disregards different
     * enharmonic representation of the same pitch. Enharmonic notes are therefore
     * equal in comparison but not equal as objects.
     *
     * @see #asMidiNumber()
     */
    private final static Comparator<Pitch> COMPARATOR = comparingInt(Pitch::asInt);

    /**
     * The step of the pitch
     */
    private final Step step;

    /**
     * The alteration of the pitch
     */
    private final Alter alter;

    /**
     * The octave of the picth
     */
    private final Octave octave;

    /**
     * Private constructor requires every field to be non null.
     *
     * @param step   the step
     * @param alter  the alteration
     * @param octave the octave
     */
    private Pitch(Step step, Alter alter, Octave octave) {
        this.step = Objects.requireNonNull(step);
        this.alter = Objects.requireNonNull(alter);
        this.octave = Objects.requireNonNull(octave);
    }

    /**
     * Static factory method that returns a pitch of the specified Step, Alteration
     * and Octave.
     *
     * @param step   the given step
     * @param alter  the given alteration
     * @param octave the given octave.
     * @return a pitch.
     */
    public static Pitch of(Step step, Alter alter, Octave octave) {
        return new Pitch(step, alter, octave);
    }

    /**
     * Static factory method that returns a pitch equivalent to the given string representation.
     *
     * @param input the given string input.
     * @return a pitch.
     */
    public static Pitch of(String string) {
        try {
            CharStream input = CharStreams.fromString(string);
            RubatoLexer lexer = new RubatoLexer(input);
            TokenStream tokens = new CommonTokenStream(lexer);
            RubatoParser parser = new RubatoParser(tokens);
            parser.setErrorHandler(new BailErrorStrategy());
            RubatoInterpreter interpreter = new RubatoInterpreter();
            return interpreter.visitPitch(parser.pitch());
        } catch (ParseCancellationException e) {
            throw new IllegalArgumentException(
                    String.format("The given input \"%s\" cannot be processed.", string));
        }
    }

    /**
     * Returns the {@linkplain Step step} of the pitch.
     *
     * @return the step of the pitch.
     */
    public Step getStep() {
        return step;
    }

    /**
     * Returns the {@linkplain Alter alteration} of the pitch.
     *
     * @return the alteration of the pitch.
     */
    public Alter getAlter() {
        return alter;
    }

    /**
     * Returns the {@linkplain Octave octave} of the pitch.
     *
     * @return the octave of the pitch
     */
    public Octave getOctave() {
        return octave;
    }

    /**
     * Compares the integer numbers of both pitches. Note that this implementation
     * is <b>not</b> consistent with equals. In particular any two pitches are equal
     * in comparison that are enharmonically equal.
     *
     * @param o the other pitch that is compared with the given instance.
     * @throws NullPointerException if o is null.
     * @see #asInt()
     */
    @Override
    public int compareTo(Pitch o) {
        return COMPARATOR.compare(this, o);
    }

    /**
     * Returns the pitch as integer value. Note that the value of this method is
     * used by the Comparator method and must return a different value for every
     * pitch that is not enharmonic in order for the comparator to work properly.
     *
     * @return the pitch as integer.
     */
    public int asInt() {
        return getStep().asInt() + getAlter().asInt() + getOctave().asInt();
    }

    /**
     * Returns true if the given pitches are enharmonic and false otherwise.
     *
     * @param o the other pitch.
     * @return true if the given pitch is enharmonic to the instance and false
     *         otherwise.
     * @throws NullPointerException if the given pitch is null.
     */
    public boolean isEnharmonicTo(Pitch o) {
        Objects.requireNonNull(o);
        return this.asInt() == o.asInt();
    }

    @Override
    public int hashCode() {
        return Objects.hash(alter, octave, step);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Pitch other = (Pitch) obj;
        return alter == other.alter && octave == other.octave && step == other.step;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(step);
        appendAlter(sb);
        appendOctave(sb);
        return sb.toString();
    }

    private void appendOctave(StringBuilder sb) {
        int rank = octave.rank();
        for (int i = 0; i < Math.abs(rank); i++) {
            if (Integer.signum(rank) == 1) {
                sb.append("'");
            } else if (Integer.signum(rank) == -1) {
                sb.append(",");
            }
        }
    }

    private void appendAlter(StringBuilder sb) {
        switch (alter) {
        case DOUBLE_SHARP:
            sb.append("x");
            return;
        case FLAT:
            sb.append("b");
            return;
        case FLAT_FLAT:
            sb.append("bb");
            return;
        case NATURAL:
            return;
        case SHARP:
            sb.append("#");
            return;
        }
    }

    /**
     * A Step represents one of the seven notes that can be represented in a natural
     * diatonic scale without alteration of the pitch. Those are also called natural
     * notes. All step occur in the following rising order an have an unique integer
     * value associated with it which represents the distance of the step in
     * semitone steps to the {@code C} Step. The {@link Step#C} is also called the
     * root of the natural diatonic scale.
     * <ul>
     * <li><b>C</b>. has an distance of 0 semitone steps to the root.</li>
     * <li><b>D</b>. has an distance of 2 semitone steps to the root.</li>
     * <li><b>E</b>. has an distance of 4 semitone steps to the root.</li>
     * <li><b>F</b>. has an distance of 5 semitone steps to the root.</li>
     * <li><b>G</b>. has an distance of 7 semitone steps to the root.</li>
     * <li><b>A</b>. has an distance of 9 semitone steps to the root.</li>
     * <li><b>B</b>. has an distance of 11 semitone steps to the root.</li>
     * </ul>
     * After {@code B} the next step on a keyboard is again a {@code C} and all step
     * therefore form a cyclic group. Also note that we are using the english
     * notaion for the {@link Step#B} in some languages as in german an dutch this
     * step is denoted by the letter <b>H</b> and the Letter <b>B</b> rather refers
     * to the (English) <b>B-FLAT</b> pitch.
     */
    public enum Step {

        /**
         * The <b>C</b> step is the lowest step in a natural Scale and is called the
         * root of the natural diatonic scale.
         */
        C(0),

        /**
         * <b>D</b> has an distance of 2 semitones steps to the root.
         */
        D(1),

        /**
         * <b>E</b> has an distance of 4 semitones steps to the root.
         */
        E(2),

        /**
         * <b>F</b> has an distance of 5 semitones steps to the root.
         */
        F(3),

        /**
         * <b>G</b> has an distance of 7 semitones steps to the root.
         */
        G(4),

        /**
         * <b>A</b> has an distance of 9 semitones steps to the root.
         */
        A(5),

        /**
         * <b>B</b> has an distance of 11 semitones steps to the root.
         */
        B(6);

        private static final Set<Step> ALL = EnumSet.allOf(Step.class);

        // private field
        private int step;

        // private constructor
        private Step(int i) {
            step = i;
        }

        /**
         * Static factory method that returns a step with exactly the specified distance
         * to the root in half steps.
         *
         * @param rootDistance
         * @return the unique step with the specified distance to the root.
         * @throws NoSuchElementException if there is no such Step with the given
         *                                distance to the root.
         */
        public static Step valueOf(int rootDistance) {
            return ALL.stream().filter(step -> step.asInt() == rootDistance).findFirst().get();
        }

        /**
         * The rank of the step.
         *
         * @return the rank of the step.
         */
        public int rank() {
            return step;
        }

        /**
         * Return the number of step to the root step C in semitone-tones steps.
         *
         * @return the distance to {@link #C} in semitone tones.
         */
        public int asInt() {
            if (step <= 2) {
                return step * 2;
            } else {
                return step * 2 - 1;
            }
        }
    }

    /**
     * All 12 pitches in a normal diatonic scale are represented through a natural
     * {@linkplain Step step} and an {@linkplain Alter alteration}. Each alteration,
     * with the exception of the naural alteration changes the pitch of a tone by
     * either a semitone step or a whole note step. In particular:
     * <ul>
     * <li><b>NATURAL</b>: Leaves the pitch unchanged.</li>
     * <li><b>SHARP</b>: Alters the pitch up by a semitone.</li>
     * <li><b>DOUBLE_SHARP</b>: Alters the pitch up by a whole tone.</li>
     * <li><b>FLAT</b>: Alters the pitch down by a semitone.</li>
     * <li><b>FLAT_FLAT</b>: Alters the pitch down by a whole tone.</li>
     * </ul>
     */
    public enum Alter {

        /**
         * The <b>NATURAL</b> alteration leaves the pitch unchanged.
         */
        NATURAL(0),

        /**
         * The <b>SHARP</b> alteration alters the pitch up by a semitone.
         */
        SHARP(1),

        /**
         * The <b>DOUBLE_SHARP</b> alteration alters the pitch up by a whole tone.
         */
        DOUBLE_SHARP(2),

        /**
         * The <b>FLAT</b> alteration alters the pitch down by a semitone.
         */
        FLAT(-1),

        /**
         * The <b>FLAT_FLAT</b> alteration alters the pitch down by a whole tone.
         */
        FLAT_FLAT(-2);

        private static final Set<Alter> ALL = EnumSet.allOf(Alter.class);

        // private field
        private int alter;

        // private constructor
        Alter(int i) {
            alter = i;
        }

        /**
         * Returns an alteration which alters a pitch by the specified integer value
         * {@code i}
         *
         * @param i the number of semitones the alteration alters a pitch.
         * @return a Alter which alters a normal pitch equal to the integer i.
         * @throws NoSuchElementException if there is no such alteration.
         */
        public static Alter valueOf(int i) {
            return ALL.stream().filter(alter -> alter.asInt() == i).findFirst().get();
        }

        /**
         * The rank of the alteration.
         *
         * @return the rank of the alteration.
         */
        public int rank() {
            return alter;
        }

        /**
         * Returns the number of semitone steps the alteration alters a pitch.
         *
         * @return the number of semitone steps the alteration alters a pitch.
         */
        public int asInt() {
            return rank();
        }
    }

    /**
     * An Octave is the interval between two musical pitches which enfolds 8
     * consecutive pitches of a diatonic scale. If a note is lifted or demoted by a
     * octave the step and alteration of stay the same.
     */
    public enum Octave {

        /**
         * The <b>SUBSUBCONTRA</b>-Octave has the rank-number -5 and encompasses the
         * MIDI-notes from 0-11.
         */
        SUBSUBCONTRA(-4),

        /**
         * The <b>SUBCONTRA</b>-Octave has the rank-number -4 and encompasses the
         * MIDI-notes from 12-23.
         */
        SUBCONTRA(-3),

        /**
         * The <b>CONTRA</b>-Octave has the rank-number -3 and encompasses the
         * MIDI-notes from 24-35.
         */
        CONTRA(-2),

        /**
         * The <b>GREAT</b>-Octave has the rank-number -2 and encompasses the MIDI-notes
         * from 36-47.
         */
        GREAT(-1),

        /**
         * The <b>SMALL</b>-Octave has the rank-number -1 and encompasses the MIDI-notes
         * from 48-59.
         */
        SMALL(0),

        /**
         * The <b>ONE_LINED</b>-Octave has the rank-number 0 and encompasses the
         * MIDI-notes from 60-71.
         */
        ONE_LINED(1),

        /**
         * The <b>TWO_LINED</b>-Octave has the rank-number 1 and encompasses the
         * MIDI-notes from 72-83.
         */
        TWO_LINED(2),

        /**
         * The <b>THREE_LINED</b>-Octave has the rank-number 2 and encompasses the
         * MIDI-notes from 84-95.
         */
        THREE_LINED(3),

        /**
         * The <b>FOUR_LINED</b>-Octave has the rank-number 3 and encompasses the
         * MIDI-notes from 96-107.
         */
        FOUR_LINED(4),

        /**
         * The <b>FIVE_LINED</b>-Octave has the rank-number 4 and encompasses the
         * MIDI-notes from 108-119.
         */
        FIVE_LINED(5),

        /**
         * The <b>SIXTH_LINED</b>-Octave has the rank-number 5 and encompasses the
         * MIDI-notes from 119-128.
         */
        SIXTH_LINED(6);

        private static final Set<Octave> ALL = EnumSet.allOf(Octave.class);

        // The rank-number of the associated octave.
        private final int octaveNumber;

        // private constructor
        private Octave(int i) {
            octaveNumber = i;
        }

        /**
         * Static factory method which returns an Octave with a rank that is equal to
         * the given integer.
         *
         * @param i the rank of the given octave.
         * @return an Octave equal to the given rank.
         * @throws NoSuchElementException if there is no octave with the given rank.
         */
        public static Octave valueOf(int i) {
            return ALL.stream().filter(octave -> octave.rank() == i).findFirst().get();
        }

        /**
         * The rank of the octave.
         *
         * @return the rank of the octave.
         */
        public int rank() {
            return octaveNumber;
        }

        /**
         * Returns the order of the octave with the {@link Octave#ONE_LINED} as the
         * middle octave with value zero.
         *
         * @return the order of the octave.
         */
        public int asInt() {
            return (rank() + 4) * 12;
        }
    }
}
