package org.music4j.midi;

import java.util.List;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

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
     * Translate the list of pitches into a midi sequence containing the given pitches as a
     * quarter notes. The division of the returned sequence is {@link Sequence#PPQ}
     * and and the resolution is set at 1
     *
     * @param pitches the pitches that are to be added to the sequence
     * @return a sequence containing the pitches as quarter notes.
     */
    public Sequence translate(List<Pitch> pitches) {
        try {
            Sequence seq = new Sequence(Sequence.PPQ, 16);
            Track track = seq.createTrack();
            int counter = 1;
            for(Pitch pitch : pitches) {
                MidiMessage msgNoteOn = new ShortMessage(ShortMessage.NOTE_ON, 0, pitch.asInt(), 64);
                track.add(new MidiEvent(msgNoteOn, counter));
                counter += 16;
                MidiMessage msgNoteOff = new ShortMessage(ShortMessage.NOTE_OFF, 0, pitch.asInt(), 0);
                track.add(new MidiEvent(msgNoteOff, counter));
            }
            return seq;
        } catch (InvalidMidiDataException e) {
            // Cannot occur.
            throw new RuntimeException();
        }
    }
}
