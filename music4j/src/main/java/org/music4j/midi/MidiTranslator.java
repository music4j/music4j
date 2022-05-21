package org.music4j.midi;

import java.util.Collections;
import java.util.Map.Entry;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiEvent;
import javax.sound.midi.MidiMessage;
import javax.sound.midi.Sequence;
import javax.sound.midi.ShortMessage;
import javax.sound.midi.Track;

import org.music4j.Bar;
import org.music4j.BarTime;
import org.music4j.Note;
import org.music4j.Pitch;
import org.music4j.Voice;

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
            addNoteToTrack(track, Note.of(BarTime.of(4), Collections.singleton(pitch)), BarTime.of(1), 1);
            return seq;
        } catch (InvalidMidiDataException e) {
            // Cannot occur.
            throw new RuntimeException();
        }
    }

    /**
     * Translate the note into a midi sequence containing the given note as a with
     * the specified. The division of the returned sequence is {@link Sequence#PPQ}
     *
     * @param note the note that is added to the sequence
     * @return a sequence containing the note.
     */
    public Sequence translate(Note note) {
        try {
            BarTime duration = note.getDuration();
            Sequence seq = new Sequence(Sequence.PPQ, duration.getDenominator());
            Track track = seq.createTrack();
            addNoteToTrack(track, note, BarTime.of(1), duration.getDenominator());
            return seq;
        } catch (InvalidMidiDataException e) {
            // Cannot occur.
            throw new RuntimeException();
        }
    }

    /**
     * Translate the voice into a midi sequence containing the given voice notes.
     * The division of the returned sequence is {@link Sequence#PPQ}.
     *
     * @param voice the voice that is to be added to the sequence
     * @return a sequence containing the voice.
     */
    public Sequence translate(Voice voice) {
        int leastCommonMultiple = voice.keySet().stream().map(BarTime::getDenominator).reduce(1,
                BarTime::leastCommonMultiple);
        BarTime counter = BarTime.of(1);
        try {
            Sequence seq = new Sequence(Sequence.PPQ, leastCommonMultiple);
            Track track = seq.createTrack();
            addVoiceToTrack(track, voice, counter, leastCommonMultiple);
            counter = counter.plus(voice.length());
            return seq;
        } catch (InvalidMidiDataException e) {
            // Cannot occur.
            throw new RuntimeException();
        }
    }

    /**
     * Translate the bar into a midi sequence containing the given bar notes.
     * The division of the returned sequence is {@link Sequence#PPQ}.
     *
     * @param bar the bar that is to be added to the sequence
     * @return a sequence containing the bar.
     */
    public Sequence translate(Bar bar) {
        int resolution = bar.stream().flatMap(v -> v.keySet().stream()).map(BarTime::getDenominator).reduce(1,
                BarTime::leastCommonMultiple);
        try {
            Sequence seq = new Sequence(Sequence.PPQ, resolution);
            for(Voice voice : bar) {
                BarTime counter = BarTime.of(1);
                Track track = seq.createTrack();
                addVoiceToTrack(track, voice, counter, resolution);
            }
            return seq;
        } catch (InvalidMidiDataException e) {
            // Cannot occur.
            throw new RuntimeException();
        }
    }

    /**
     * Adds the voice to the given track att the given counter.
     * @param track
     * @param voice
     * @param counter
     * @param resolution
     */
    private void addVoiceToTrack(Track track, Voice voice, BarTime counter, int resolution) {
        BarTime voiceCounter = counter;
        for (Entry<BarTime, Note> e : voice.entrySet()) {
            Note note = e.getValue();
            addNoteToTrack(track, note, voiceCounter, resolution);
            voiceCounter = voiceCounter.plus(voice.nextIn(e.getKey()));
        }
    }

    /**
     * Adds the note to the given track at the specified counter time.
     *
     * @param track      the given track
     * @param note       the note that is added to the track
     * @param counter    the time at which the note is added
     * @param resolution the resolution of the sequence.
     */
    private void addNoteToTrack(Track track, Note note, BarTime counter, int resolution) {
        BarTime duration = note.getDuration();
        BarTime noteEnd = counter.plus(duration);
        for (Pitch pitch : note) {
            int factor = resolution / counter.getDenominator();
            track.add(new MidiEvent(noteOn(pitch), counter.getNumerator() * factor));
            int factorEnd = resolution / noteEnd.getDenominator();
            track.add(new MidiEvent(noteOff(pitch), noteEnd.getNumerator() * factorEnd));
        }
    }

    private MidiMessage noteOn(Pitch pitch) {
        try {
            return new ShortMessage(ShortMessage.NOTE_ON, 0, pitch.asInt(), 64);
        } catch (InvalidMidiDataException e) {
            throw new RuntimeException(e);
        }
    }

    private MidiMessage noteOff(Pitch pitch) {
        try {
            return new ShortMessage(ShortMessage.NOTE_OFF, 0, pitch.asInt(), 0);
        } catch (InvalidMidiDataException e) {
            throw new RuntimeException(e);
        }
    }
}
