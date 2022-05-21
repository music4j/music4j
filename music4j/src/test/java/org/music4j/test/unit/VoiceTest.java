package org.music4j.test.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;
import java.util.Set;

import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.music4j.BarTime;
import org.music4j.Note;
import org.music4j.Pitch;
import org.music4j.Pitch.Alter;
import org.music4j.Pitch.Octave;
import org.music4j.Pitch.Step;
import org.music4j.Voice;

class VoiceTest {

    /**
     * Puts throws an exception if an invalid input is entered
     */
    @Test
    void putThrows() {
        Voice voice = Voice.of();
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> voice.put(BarTime.of(-1), Note.of(BarTime.ZERO)));
        assertEquals("The key must be greater than zero.", e.getMessage());
    }

    /**
     * Puts throws an exception if an invalid input is entered
     */
    @Test
    void putThrowsLastingEffect() {

        Voice voice = Voice.of();
        voice.put(BarTime.of(1), Note.of(BarTime.of(1)));
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> voice.put(BarTime.of(3, 2), Note.of(BarTime.of(1))));
        assertEquals("The value cannot be inserted as a lower element has still lasting effect.", e.getMessage());
    }

    /**
     * Puts throws an exception if an invalid input is entered
     */
    @Test
    void putThrowsNextInEffect() {
        Voice voice = Voice.of();
        voice.put(BarTime.of(1), Note.of(BarTime.of(1)));
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class,
                () -> voice.put(BarTime.of(1, 2), Note.of(BarTime.of(1))));
        assertEquals("The value cannot be inserted as it is in conflicted with a later item.", e.getMessage());
    }

    /**
     * The length of a voice is the last key plus the duration of the associated
     * entry
     */
    @Test
    void length() {
        Note n1 = Note.of(BarTime.of(1), Set.of(Pitch.of(Step.C, Alter.NATURAL, Octave.ONE_LINED)));
        Voice voice = Voice.of();
        voice.put(BarTime.of(2), n1);
        assertEquals(BarTime.of(3), voice.length());
    }

    /**
     * Any entry blocks the voice for the time of its duration
     */
    @Test
    void lastingFor() {
        Note n1 = Note.of(BarTime.of(1), Set.of(Pitch.of(Step.C, Alter.NATURAL, Octave.ONE_LINED)));
        Voice voice = Voice.of();
        voice.put(BarTime.of(2), n1);
        assertEquals(BarTime.of(0), voice.lastingFor(BarTime.of(2)));
        assertEquals(BarTime.of(1, 4), voice.lastingFor(BarTime.of(11, 4)));
        assertEquals(BarTime.of(0), voice.lastingFor(BarTime.of(3)));
    }

    /**
     * Lasting throws a Nullpointer exception for null arguments.
     */
    @Test
    void lastingForException() {
        Voice voice = Voice.of();
        assertThrows(NullPointerException.class, () -> voice.lastingFor(null));
    }

    /**
     * Any entry blocks the voice for the time of its duration
     */
    @Test
    void nextIn() {
        Note n1 = Note.of(BarTime.of(1), Set.of(Pitch.of(Step.C, Alter.NATURAL, Octave.ONE_LINED)));
        Voice voice = Voice.of();
        voice.put(BarTime.of(2), n1);
        assertEquals(BarTime.of(1, 2), voice.nextIn(BarTime.of(3, 2)));
        assertEquals(BarTime.MAX, voice.nextIn(BarTime.of(11, 4)));
        assertEquals(BarTime.MAX, voice.nextIn(BarTime.of(3)));
    }

    /**
     * nextIn throws a Nullpointer exception for null arguments.
     */
    @Test
    void nextInException() {
        Voice voice = Voice.of();
        assertThrows(NullPointerException.class, () -> voice.nextIn(null));
    }

    /**
     * Fits test
     */
    @Test
    void fits() {
        Note n1 = Note.of(BarTime.of(1), Set.of(Pitch.of(Step.C, Alter.NATURAL, Octave.ONE_LINED)));
        Note n2 = Note.of(BarTime.of(1, 2), Set.of(Pitch.of(Step.C, Alter.NATURAL, Octave.ONE_LINED)));
        Voice voice = Voice.of();
        voice.put(BarTime.of(1), n1);
        voice.put(BarTime.of(5, 2), n2);
        assertTrue(voice.fits(BarTime.of(2), n2));
        assertTrue(voice.fits(BarTime.of(5, 2), n2));
        assertTrue(voice.fits(BarTime.of(1), n2));
        assertFalse(voice.fits(BarTime.of(3, 2), n2));
        assertFalse(voice.fits(BarTime.of(9, 4), n2));
    }

    /**
     * Fits test
     */
    @Test
    void fitsThrowsNullpointerException() {
        Voice voice = Voice.of();
        assertThrows(NullPointerException.class, () -> voice.fits(null, Note.of(BarTime.ZERO)));
        assertThrows(NullPointerException.class, () -> voice.fits(BarTime.ZERO, null));
    }

    /**
     * The submap view of the voice is backed by the original voice
     */
    @Test
    void subMap() {
        Voice voice = Voice.of();
        voice.put(BarTime.of(0), Note.of(BarTime.of(1)));
        voice.put(BarTime.of(1), Note.of(BarTime.of(1)));
        voice.put(BarTime.of(2), Note.of(BarTime.of(1)));
        voice.put(BarTime.of(3), Note.of(BarTime.of(1)));

        Voice subVoice = voice.subMap(BarTime.of(1), true, BarTime.of(3), false);
        assertEquals(BarTime.of(3), subVoice.length());
        assertFalse(subVoice.containsKey(BarTime.of(0)));
        assertTrue(subVoice.containsKey(BarTime.of(1)));
        assertTrue(subVoice.containsKey(BarTime.of(2)));
        assertFalse(subVoice.containsKey(BarTime.of(3)));
        subVoice.remove(BarTime.of(2));
        assertFalse(voice.containsKey(BarTime.of(2)));
    }

    /**
     * The submap view throw an IllegalArgument Exception if the key is out of
     * range.
     */
    @Test
    void subMapException() {
        Voice voice = Voice.of();

        Voice subVoice = voice.subMap(BarTime.of(1), true, BarTime.of(3), false);
        assertThrows(IllegalArgumentException.class, () -> subVoice.put(BarTime.of(3), Note.of(BarTime.of(1))));
    }

    @Test
    void descendingMap() {
        Voice voice = Voice.of();
        voice.put(BarTime.of(0), Note.of(BarTime.of(1)));
        voice.put(BarTime.of(1), Note.of(BarTime.of(1)));

        Voice descendingVoice = voice.descendingMap();
        assertEquals(BarTime.of(1), descendingVoice.firstKey());
        descendingVoice.remove(BarTime.of(1));
        assertFalse(voice.containsKey(BarTime.of(1)));
    }

    /**
     * The tailMap view of the voice is backed by the original voice
     */
    @Test
    void tailMap() {
        Voice voice = Voice.of();
        voice.put(BarTime.of(0), Note.of(BarTime.of(1)));
        voice.put(BarTime.of(1), Note.of(BarTime.of(1)));
        voice.put(BarTime.of(2), Note.of(BarTime.of(1)));
        voice.put(BarTime.of(3), Note.of(BarTime.of(1)));

        Voice tailVoice = voice.tailMap(BarTime.of(1), true);
        assertFalse(tailVoice.containsKey(BarTime.of(0)));
        assertTrue(tailVoice.containsKey(BarTime.of(1)));
        assertTrue(tailVoice.containsKey(BarTime.of(2)));
        assertTrue(tailVoice.containsKey(BarTime.of(3)));
        tailVoice.remove(BarTime.of(2));
        assertFalse(voice.containsKey(BarTime.of(2)));
    }

    /**
     * The tailVoice view throw an IllegalArgument Exception if the key is out of
     * range.
     */
    @Test
    void tailMapException() {
        Voice voice = Voice.of();

        Voice tailVoice = voice.tailMap(BarTime.of(1), true);
        assertThrows(IllegalArgumentException.class, () -> tailVoice.put(BarTime.of(0), Note.of(BarTime.of(1))));
    }

    /**
     * The headMap view of the voice is backed by the original voice
     */
    @Test
    void headMap() {
        Voice voice = Voice.of();
        voice.put(BarTime.of(0), Note.of(BarTime.of(1)));
        voice.put(BarTime.of(1), Note.of(BarTime.of(1)));
        voice.put(BarTime.of(2), Note.of(BarTime.of(1)));
        voice.put(BarTime.of(3), Note.of(BarTime.of(1)));

        Voice headVoice = voice.headMap(BarTime.of(1), true);
        assertTrue(headVoice.containsKey(BarTime.of(0)));
        assertTrue(headVoice.containsKey(BarTime.of(1)));
        assertFalse(headVoice.containsKey(BarTime.of(2)));
        assertFalse(headVoice.containsKey(BarTime.of(3)));
        headVoice.remove(BarTime.of(1));
        assertFalse(voice.containsKey(BarTime.of(1)));
    }

    /**
     * The headMap view throw an IllegalArgument Exception if the key is out of
     * range.
     */
    @Test
    void headMapException() {
        Voice voice = Voice.of();

        Voice headVoice = voice.headMap(BarTime.of(1), true);
        assertThrows(IllegalArgumentException.class, () -> headVoice.put(BarTime.of(2), Note.of(BarTime.of(1))));
    }

    @Test
    void entriesReturnedAreImmutable() {
        Voice voice = Voice.of();
        voice.put(BarTime.ZERO, Note.of(BarTime.of(1)));
        Map.Entry<BarTime, Note> first = voice.firstEntry();
        assertThrows(UnsupportedOperationException.class, () -> first.setValue(Note.of(BarTime.of(2))));
    }

    @Test
    void testToString() {
        Voice actual = Voice.of("C' D' Eb' F#' G2 Ab/2 Ab/2 [B G'] R");
        assertEquals("C' D' Eb' F#' G2 Ab/2 Ab/2 [B G'] R", actual.toString());
    }

    @Nested
    class VoiceParser {

        @Test
        void parseVoice() {
            Voice actual = Voice.of("C' D' Eb' F#' G2 Ab/2 Ab/2 [B G'] R");
            Voice expected= Voice.of(Map.ofEntries(
                        Map.entry(BarTime.of(0), Note.of("C'")),
                        Map.entry(BarTime.of(1), Note.of("D'")),
                        Map.entry(BarTime.of(2), Note.of("Eb'")),
                        Map.entry(BarTime.of(3), Note.of("F#'")),
                        Map.entry(BarTime.of(4), Note.of("G2'")),
                        Map.entry(BarTime.of(6), Note.of("Ab/2'")),
                        Map.entry(BarTime.of(13,2), Note.of("Ab/2'")),
                        Map.entry(BarTime.of(7), Note.of("[B G']")),
                        Map.entry(BarTime.of(8), Note.of("R"))
                    ));
            assertEquals(expected, actual);
        }
    }
}
