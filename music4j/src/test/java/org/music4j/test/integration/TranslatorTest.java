package org.music4j.test.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.music4j.BarTime;
import org.music4j.Note;
import org.music4j.Pitch;
import org.music4j.rubato.RubatoTranslator;

class TranslatorTest {

    private static final RubatoTranslator TRANSLATOR = new RubatoTranslator();

    @Test
    void translateTime() {
        assertEquals("/2", TRANSLATOR.translateTime(BarTime.of(1,2)));
    }

    @Test
    void translatePitch() {
        assertEquals("C'", TRANSLATOR.translatePitch(Pitch.of("C'")));
    }

    @Test
    void translatePitchWithOctaveAndAlter() {
        assertEquals("C#,,", TRANSLATOR.translatePitch(Pitch.of("C#,,")));
    }

    @Test
    void translateRest() {
        assertEquals("R3", TRANSLATOR.translateNote(Note.of(BarTime.of(3))));
    }

    @Test
    void translateSingle() {
        assertEquals("Cx''3", TRANSLATOR.translateNote(Note.of("Cx''3")));
    }

    @Test
    void translateChord() {
        assertEquals("[Cx'' E'' G#'']3", TRANSLATOR.translateNote(Note.of("[Cx'' E'' G#'']3")));
    }

    @Test
    void translateTiedNote() {
        assertEquals("Cx''3~", TRANSLATOR.translateNote(Note.of("Cx''3~")));
    }
}
