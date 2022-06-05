package org.music4j.test.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collections;
import java.util.Set;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.music4j.BarTime;
import org.music4j.Note;
import org.music4j.Note.Type;
import org.music4j.Pitch;
import org.music4j.Pitch.Alter;
import org.music4j.Pitch.Octave;
import org.music4j.Pitch.Step;

class NoteTest {

    /**
     * Return the duration of a Note.
     */
    @Test
    @DisplayName("Return the duration of a note")
    void duration() {
        Note note = Note.of(BarTime.of(2));
        assertEquals(BarTime.of(2), note.getDuration());
    }

    /**
     * Test for getter and setter. Any arbitrary Type can be set.
     */
    @Test
    @DisplayName("Getter and Setter for Type")
    void type() {
        Note note = Note.of(BarTime.of(2));
        note.setType(Type.HALF);
        assertEquals(Type.HALF, note.getType());
    }

    /**
     * Test for getter and setter. Any arbitrary number of dots can be set.
     */
    @Test
    @DisplayName("Getter and Setter for dots")
    void dots() {
        Note note = Note.of(BarTime.of(2));
        note.setDots(2);
        assertEquals(2, note.getDots());
    }

    /**
     * Test for descending set. The same set is returned in descending order.
     * Additionally every operation on the original set is reflected in the
     * descending set.
     */
    @Test
    @DisplayName("Descending set")
    void descendingSet() {
        Pitch p1 = Pitch.of(Step.C, Alter.NATURAL, Octave.ONE_LINED);
        Pitch p2 = Pitch.of(Step.E, Alter.NATURAL, Octave.ONE_LINED);
        Pitch p3 = Pitch.of(Step.G, Alter.NATURAL, Octave.ONE_LINED);
        Pitch p4 = Pitch.of(Step.C, Alter.NATURAL, Octave.TWO_LINED);

        Note note = Note.of(BarTime.of(2), Set.of(p1, p2, p3));
        assertEquals(p1, note.first());
        Note descendingNote = note.descendingSet();
        assertEquals(p3, descendingNote.first());
        descendingNote.add(p4);
        assertEquals(p4, descendingNote.first());
        assertEquals(4, note.size());
        note.remove(p4);
        assertEquals(3, descendingNote.size());
    }

    /**
     * Test for head set. The same set is returned up until a certain border.
     * Additionally every operation on the original set is reflected in the head
     * set.
     */
    @Test
    @DisplayName("Headset")
    void headSet() {
        Pitch p1 = Pitch.of(Step.C, Alter.NATURAL, Octave.ONE_LINED);
        Pitch p2 = Pitch.of(Step.E, Alter.NATURAL, Octave.ONE_LINED);
        Pitch p3 = Pitch.of(Step.G, Alter.NATURAL, Octave.ONE_LINED);
        Pitch p4 = Pitch.of(Step.C, Alter.NATURAL, Octave.TWO_LINED);

        Note note = Note.of(BarTime.of(2), Set.of(p1, p2, p3, p4));
        Note headNote = note.headSet(p3, false);
        assertEquals(2, headNote.size());
        assertTrue(headNote.add(Pitch.of(Step.F, Alter.NATURAL, Octave.ONE_LINED)));
        assertEquals(true, note.contains(Pitch.of(Step.F, Alter.NATURAL, Octave.ONE_LINED)));
    }

    /**
     * Test for head set. Head set throws an exception if an element is added that
     * exceeds the bound.
     */
    @Test
    @DisplayName("Headset throws Exception")
    void headSetException() {
        Pitch p1 = Pitch.of(Step.C, Alter.NATURAL, Octave.ONE_LINED);
        Pitch p2 = Pitch.of(Step.E, Alter.NATURAL, Octave.ONE_LINED);
        Pitch p3 = Pitch.of(Step.G, Alter.NATURAL, Octave.ONE_LINED);
        Pitch p4 = Pitch.of(Step.C, Alter.NATURAL, Octave.TWO_LINED);

        Note note = Note.of(BarTime.of(2), Set.of(p1, p2, p3, p4));
        Note headNote = note.headSet(p3, false);
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> headNote.add(p4));
        assertEquals("key out of range", e.getMessage());
    }

    /**
     * Test for tail set. The same set is returned up until a certain border.
     * Additionally every operation on the original set is reflected in the tail
     * set.
     */
    @Test
    @DisplayName("Tailset")
    void tailSet() {
        Pitch p1 = Pitch.of(Step.C, Alter.NATURAL, Octave.ONE_LINED);
        Pitch p2 = Pitch.of(Step.E, Alter.NATURAL, Octave.ONE_LINED);
        Pitch p3 = Pitch.of(Step.G, Alter.NATURAL, Octave.ONE_LINED);
        Pitch p4 = Pitch.of(Step.C, Alter.NATURAL, Octave.TWO_LINED);

        Note note = Note.of(BarTime.of(2), Set.of(p1, p2, p3, p4));
        Note tailNote = note.tailSet(p2, true);
        assertEquals(3, tailNote.size());
        assertTrue(tailNote.add(Pitch.of(Step.F, Alter.NATURAL, Octave.ONE_LINED)));
        assertEquals(true, note.contains(Pitch.of(Step.F, Alter.NATURAL, Octave.ONE_LINED)));
    }

    /**
     * Test for tail set. Head set throws an exception if an element is added that
     * exceeds the bound.
     */
    @Test
    @DisplayName("Tailset throws Exception")
    void tailSetException() {
        Pitch p1 = Pitch.of(Step.C, Alter.NATURAL, Octave.ONE_LINED);
        Pitch p2 = Pitch.of(Step.E, Alter.NATURAL, Octave.ONE_LINED);
        Pitch p3 = Pitch.of(Step.G, Alter.NATURAL, Octave.ONE_LINED);
        Pitch p4 = Pitch.of(Step.C, Alter.NATURAL, Octave.TWO_LINED);

        Note note = Note.of(BarTime.of(2), Set.of(p1, p2, p3, p4));
        Note tailNote = note.tailSet(p2, true);
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> tailNote.add(p1));
        assertEquals("key out of range", e.getMessage());
    }

    /**
     * Test for tail set. The same set is returned up until a certain border.
     * Additionally every operation on the original set is reflected in the tail
     * set.
     */
    @Test
    @DisplayName("Subset")
    void subSet() {
        Pitch p1 = Pitch.of(Step.C, Alter.NATURAL, Octave.ONE_LINED);
        Pitch p2 = Pitch.of(Step.E, Alter.NATURAL, Octave.ONE_LINED);
        Pitch p3 = Pitch.of(Step.G, Alter.NATURAL, Octave.ONE_LINED);
        Pitch p4 = Pitch.of(Step.C, Alter.NATURAL, Octave.TWO_LINED);

        Note note = Note.of(BarTime.of(2), Set.of(p1, p2, p3, p4));
        Note subote = note.subSet(p2, true, p4, false);
        assertEquals(2, subote.size());
        assertTrue(subote.add(Pitch.of(Step.F, Alter.NATURAL, Octave.ONE_LINED)));
        assertEquals(true, note.contains(Pitch.of(Step.F, Alter.NATURAL, Octave.ONE_LINED)));
    }

    /**
     * Test for tail set. Head set throws an exception if an element is added that
     * exceeds the bound.
     */
    @Test
    @DisplayName("Subset throws Exception")
    void subSetException() {
        Pitch p1 = Pitch.of(Step.C, Alter.NATURAL, Octave.ONE_LINED);
        Pitch p2 = Pitch.of(Step.E, Alter.NATURAL, Octave.ONE_LINED);
        Pitch p3 = Pitch.of(Step.G, Alter.NATURAL, Octave.ONE_LINED);
        Pitch p4 = Pitch.of(Step.C, Alter.NATURAL, Octave.TWO_LINED);

        Note note = Note.of(BarTime.of(2), Set.of(p1, p2, p3, p4));
        Note subote = note.subSet(p2, true, p4, false);
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> subote.add(p1));
        assertThrows(IllegalArgumentException.class, () -> subote.add(p4));
        assertEquals("key out of range", e.getMessage());
    }

    /**
     * Test for tail set. Head set throws an exception if an element is added that
     * exceeds the bound.
     */
    @Test
    void testToString() {
        Pitch p1 = Pitch.of(Step.C, Alter.SHARP, Octave.TWO_LINED);
        Note note = Note.of(BarTime.of(2), Collections.singleton(p1));
        assertEquals("C#''2", note.toString());
    }

    /**
     * Test for tail set. Head set throws an exception if an element is added that
     * exceeds the bound.
     */
    @Test
    void testToStringRest() {
        Note note = Note.of(BarTime.of(1, 2));
        assertEquals("R/2", note.toString());
    }

    /**
     * Test for tail set. Head set throws an exception if an element is added that
     * exceeds the bound.
     */
    @Test
    void testToStringChord() {
        Note note = Note.of("[C#' E']3/4");
        assertEquals("[C#' E']3/4", note.toString());
    }

    @Nested
    class NoteParser {

        @Test
        void parseSingleNoteQuarter() {
            Note note = Note.of("C");
            assertEquals(Note.of(BarTime.of(1), Collections.singleton(Pitch.of("C"))), note);
        }

        @Test
        void parseSingleNoteHalf() {
            Note note = Note.of("C#'2");
            assertEquals(Note.of(BarTime.of(2), Collections.singleton(Pitch.of("C#'"))), note);
        }

        @Test
        void parseSingleNoteEight() {
            Note note = Note.of("C#'/2");
            assertEquals(Note.of(BarTime.of(1, 2), Collections.singleton(Pitch.of("C#'"))), note);
        }

        @Test
        void parseSingleNoteRandomDuration() {
            Note note = Note.of("C#'7/5");
            assertEquals(Note.of(BarTime.of(7, 5), Collections.singleton(Pitch.of("C#'"))), note);
        }

        @Test
        void parseRest() {
            Note note = Note.of("R/4");
            assertEquals(Note.of(BarTime.of(1, 4)), note);
        }

        @Test
        void parseChord() {
            Note note = Note.of("[C#' E Gx']/2");
            assertEquals(Note.of(BarTime.of(1, 2), Set.of(Pitch.of("C#'"), Pitch.of("E"), Pitch.of("Gx'"))), note);
        }

        @Test
        void parseThrowsException() {
            IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> Note.of("[C#' E Gx'/2"));
            assertEquals(
                    "org.music4j.grammar.ParseException: line 1: 10 extraneous input '/' expecting {']', STEP} \r\n",
                    e.getMessage());
        }
    }
}
