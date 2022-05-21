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

    private final int resolution;

    private Sequence seq;

    public MidiTranslator(Pitch pitch) {
        resolution = 1;
        try {
            seq = new Sequence(Sequence.PPQ, resolution);
            translate(pitch);
        } catch (InvalidMidiDataException e) {
            throw new RuntimeException(e);
        }
    }

    public MidiTranslator(Note note) {
        resolution = note.getDuration().getDenominator();
        try {
            seq = new Sequence(Sequence.PPQ, resolution);
            translate(note);
        } catch (InvalidMidiDataException e) {
            throw new RuntimeException(e);
        }
    }

    public MidiTranslator(Voice voice) {
        resolution = voice.keySet().stream().map(BarTime::getDenominator).reduce(1, BarTime::leastCommonMultiple);
        try {
            seq = new Sequence(Sequence.PPQ, resolution);
            translate(voice);
        } catch (InvalidMidiDataException e) {
            throw new RuntimeException(e);
        }
    }

    public MidiTranslator(Bar bar) {
        resolution = bar.stream().flatMap(v -> v.keySet().stream()).map(BarTime::getDenominator).reduce(1,
                BarTime::leastCommonMultiple);
        try {
            seq = new Sequence(Sequence.PPQ, resolution);
            translate(bar);
        } catch (InvalidMidiDataException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Translate a pitch into a midi sequence containing the given pitch as a
     * quarter note. The division of the returned sequence is {@link Sequence#PPQ}
     * and and the resolution is set at 1
     *
     * @param pitch the pitch that is to be added to the sequence
     * @return a sequence containing the pitch as a single quarter note
     */
    public void translate(Pitch pitch) {
        Track track = seq.createTrack();
        addNoteToTrack(track, Note.of(BarTime.of(4), Collections.singleton(pitch)), BarTime.of(1));
    }

    /**
     * Translate the note into a midi sequence containing the given note as a with
     * the specified. The division of the returned sequence is {@link Sequence#PPQ}
     *
     * @param note the note that is added to the sequence
     * @return a sequence containing the note.
     */
    public void translate(Note note) {
        Track track = seq.createTrack();
        addNoteToTrack(track, note, BarTime.of(1));
    }

    /**
     * Translate the voice into a midi sequence containing the given voice notes.
     * The division of the returned sequence is {@link Sequence#PPQ}.
     *
     * @param voice the voice that is to be added to the sequence
     * @return a sequence containing the voice.
     */
    public void translate(Voice voice) {
        BarTime counter = BarTime.of(1);
        Track track = seq.createTrack();
        addVoiceToTrack(track, voice, counter);
    }

    /**
     * Translate the bar into a midi sequence containing the given bar notes. The
     * division of the returned sequence is {@link Sequence#PPQ}.
     *
     * @param bar the bar that is to be added to the sequence
     * @return a sequence containing the bar.
     */
    public void translate(Bar bar) {
        for (Voice voice : bar) {
            BarTime counter = BarTime.of(1);
            Track track = seq.createTrack();
            addVoiceToTrack(track, voice, counter);
        }
    }

    public Sequence getSequnece() {
        return seq;
    }

    /**
     * Adds the voice to the given track att the given counter.
     *
     * @param track
     * @param voice
     * @param counter
     * @param resolution
     */
    private void addVoiceToTrack(Track track, Voice voice, BarTime counter) {
        BarTime voiceCounter = counter;
        for (Entry<BarTime, Note> e : voice.entrySet()) {
            Note note = e.getValue();
            addNoteToTrack(track, note, voiceCounter);
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
    private void addNoteToTrack(Track track, Note note, BarTime counter) {
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
