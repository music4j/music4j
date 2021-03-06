package org.music4j;

import java.util.Collection;
import java.util.NavigableSet;

import org.music4j.simple.TreeSetNote;

/**
 * A Note is a Set of {@linkplain Pitch Pitches}. Notes are the main part in any
 * voice and can be added to a {@link Voice}. Please take note in the fact that
 * per definition of the {@link Note} interface a note is a {@link NavigableSet}
 * of {@linkplain Pitch Pitches}. As such a Note can represent three different
 * musical objects which are normally semantically separated. In particular we
 * say the given note represents
 * <ul>
 * <li>A <b>Note</b>: If the Note as collection contains exactly one pitch.</li>
 * <li>A <b>Chord</b>: If the Note as collection contains multiple pitches.</li>
 * <li>A <b>Rest</b>: If the Note as collection of pitches is empty.
 * </ul>
 */
public interface Note extends NavigableSet<Pitch>, Measurable {

    /**
     * Static factory for the note interface. Returns a rest of the specified
     * duration.
     *
     * @param duration the duration of the note.
     * @return a rest of the specified duration.
     */
    static Note of(BarTime duration) {
        return new TreeSetNote(duration);
    }

    /**
     * Static factory that creates a new note with the specified duration and
     * pitches.
     *
     * @param duration the duration of the note
     * @param pitches  the pitches of the note
     * @return a note of the specified duration with the given pitches.
     */
    static Note of(BarTime duration, Collection<Pitch> pitches) {
        return new TreeSetNote(duration, pitches);
    }

    /**
     * Static factory method that returns a note equivalent to the given string
     * representation.
     *
     * @param input the given string input.
     * @return a note.
     */
    public static Note of(String string) {
        return TreeSetNote.parse(string);
    }

    /**
     * Returns the duration of the Note as a BarTime. The duration of a Note is
     * immutable and has to be specified upon creation. {@inheritDoc}
     *
     * @return the duration the Note as BarTime.
     */
    @Override
    BarTime getDuration();

    /**
     * Returns true if the note is a rest.
     *
     * @return true if the note is a rest.
     */
    default boolean isRest() {
        return size() == 0;
    }

    /**
     * Returns true if the note is a single note.
     *
     * @return true if the note is a single note.
     */
    default boolean isSingle() {
        return size() == 1;
    }

    /**
     * Returns true if the note is a chord.
     *
     * @return true if the note is a chord.
     */
    default boolean isChord() {
        return size() > 1;
    }

    /**
     * Returns the type of the given note. Please note that the type gives only
     * indication on how the given note is displayed and has no effect on the
     * duration.
     *
     * @return the type of the note.
     */
    Type getType();

    /**
     * Sets the type of the given note. Please note that the type gives only
     * indication on how the given note is displayed and has no effect on the
     * duration.
     */
    void setType(Type type);

    /**
     * Returns the number of dots the note has. Please note that the dots gives only
     * indication on how the given note is displayed and has no effect on the
     * duration.
     */
    int getDots();

    /**
     * Sets the number of dots the note has. Please note that the dots gives only
     * indication on how the given note is displayed and has no effect on the
     * duration.
     */
    void setDots(int i);

    /**
     * Set if the note starts a tie.
     */
    void setTieStart(boolean isTieStart);

    /**
     * Sets if the note ends a tie.
     */
    void setTieEnd(boolean isTieStart);

    /**
     * Returns true if a tie starts with this note.
     *
     * @return true if a tie starts with this note.
     */
    boolean isTieStart();

    /**
     * Returns true if a tie ends with this note.
     *
     * @return true if a tie ends with this note.
     */
    boolean isTieEnd();

    /**
     * Returns a descending map as Note. {@inheritDoc}
     */
    @Override
    Note descendingSet();

    /**
     * Returns a headset as Note. {@inheritDoc}
     */
    @Override
    Note headSet(Pitch toElement, boolean inclusive);

    /**
     * Returns a tailset as Note. {@inheritDoc}
     */
    @Override
    Note tailSet(Pitch fromElement, boolean inclusive);

    /**
     * Returns a subset as Note. {@inheritDoc}
     */
    @Override
    Note subSet(Pitch fromElement, boolean fromInclusive, Pitch toElement, boolean toInclusive);

    /**
     * {@inheritDoc}
     */
    @Override
    Note subSet(Pitch fromElement, Pitch toElement);

    /**
     * {@inheritDoc}
     */
    @Override
    Note tailSet(Pitch fromElement);

    /**
     * {@inheritDoc}
     */
    @Override
    Note headSet(Pitch toElement);

    /**
     * The NoteType of a note indicates how a given note should be displayed. The
     * NoteType does not fully determine the the duration and should not be used to
     * make any assumptions on the duration of any note.
     */
    public enum Type {

        /**
         * Indicates that a note should be displayed as a long note.
         */
        LONG,

        /**
         * Indicates that a note should be displayed as a breve note. A breve is
         * commonly represented in either of two ways: by a hollow oval note head, like
         * a whole note, with one or two vertical lines on either side, as on the left
         * and right of the image, or as the rectangular shape
         */
        BREVE,

        /**
         * Indicates that a note should be displayed as a whole note or whole rest. A
         * whole note has a note head shape of a hollow oval without a stem. A whole
         * rest is drawn as a filled in rectangle thats hanging under the second line of
         * the top.
         */
        WHOLE,

        /**
         * Indicates that a note should be displayed as a half note or half rest. A half
         * note has a note head shape of a hollow oval with a stem. drawn as a filled in
         * rectangle thats lies over the second line of the top.
         */
        HALF,

        /**
         * Indicates that a note is displayed as a quarter note or quarter rest. Quarter
         * notes are notated with a filled-in oval note head and a straight, flagless
         * stem.
         */
        QUARTER,

        /**
         * Indicates that a note should be displayed as a eighth note or eighth rest.
         * Eighth notes are notated with a filled-in oval note head and a straight stem
         * with one flag.
         */
        EIGHTH,

        /**
         * Indicates that a note should be displayed as a sixteenth note or sixteenth
         * rest. Sixteenth notes are notated with a filled-in oval note head and a
         * straight stem with two flags.
         */
        SIXTHEENTH,

        /**
         * Indicates that a note should be displayed as a thirty-second note or
         * thirty-second rest. Thirty-second notes are notated with a filled-in oval
         * note head and a straight stem with three flags.
         */
        THIRTY_SECOND,

        /**
         * Indicates that a note should be displayed as a sixty-fourth note or
         * thirty-second rest. Thirty-second notes are notated with a filled-in oval
         * note head and a straight stem with four flags.
         */
        SIXTY_FOURTH,

        /**
         * Indicates that a note should be displayed as a hundred twenty-eighth note or
         * thirty-second rest. Thirty-second notes are notated with a filled-in oval
         * note head and a straight stem with five flags.
         */
        HUNDRED_TWENTY_EIGHTH,
    }
}
