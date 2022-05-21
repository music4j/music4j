package org.music4j.midi;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

import org.music4j.BarTime;
import org.music4j.Note;
import org.music4j.Pitch;

/**
 * Class that translates music4j objects into {@linkplain Sequence Sequences}
 * this sequence can then be played by a midi sequencer.
 */
public class MidiTranslator {

    /**
     * Translate a pitch into a midi sequence containing the given pitch as a
     * quarter note. The division of the returned sequence is {@link Sequence#PPQ}
     * and and the resolution is set at 1
     *
     * @param pitch the pitch that is to be added to the sequence
     * @return a sequence containing the pitch as a single quarter note
     */
    public Sequence translate(Pitch pitch) {
        Sequence seq;
        try {
            seq = new Sequence(Sequence.PPQ, 1);
            Track track = seq.createTrack();
            //Add the Note after one second. Otherwise it can result in the note being played back twice.
            track.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_ON, 0, pitch.asInt(), 64), 1));
            track.add(new MidiEvent(new ShortMessage(ShortMessage.NOTE_OFF, 0, pitch.asInt(), 0), 2));
            return seq;
        } catch (InvalidMidiDataException e) {
            // Cannot occur.
            throw new RuntimeException();
        }
    }

    /**
     * Translate the note into a midi sequence containing the given note as a
     * with the specified. The division of the returned sequence is {@link Sequence#PPQ}
     * and and the resolution is set at 1
     *
     * @param pitches the pitches that are to be added to the sequence
     * @return a sequence containing the pitches as quarter notes.
     */
    public Sequence translate(Note note) {
        try {
            BarTime duration = note.getDuration();
            Sequence seq = new Sequence(Sequence.PPQ, duration.getDenominator());
            Track track = seq.createTrack();
            for(Pitch pitch : note) {
                MidiMessage msgNoteOn = new ShortMessage(ShortMessage.NOTE_ON, 0, pitch.asInt(), 64);
                track.add(new MidiEvent(msgNoteOn, duration.getDenominator()));
                MidiMessage msgNoteOff = new ShortMessage(ShortMessage.NOTE_OFF, 0, pitch.asInt(), 0);
                track.add(new MidiEvent(msgNoteOff, duration.getNumerator() + duration.getDenominator()));
            }
            return seq;
        } catch (InvalidMidiDataException e) {
            // Cannot occur.
            throw new RuntimeException();
        }
    }
}
