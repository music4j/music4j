package org.music4j.midi;

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
import org.music4j.Part;
import org.music4j.Pitch;
import org.music4j.Score;
import org.music4j.Staff;
import org.music4j.Voice;

/**
 * Class that translates a {@link Score} to a {@link Sequence}
 */
public class MidiTranslator {

	/**
	 * The resolution of the score ist the least common multiple of all parts and
	 * voices
	 */
	private final int resolution;

	/**
	 * The translated Sequence
	 */
	private final Sequence seq;

	/**
	 * Current Track in which midi events are added.
	 */
	private Track track;

	/**
	 * The the current counter position of the sequence.
	 */
	private BarTime counter;

	/**
	 * Constructor translates Score to {@link Sequence}
	 * 
	 * @param pitch
	 */
	public MidiTranslator(Score score) {
		resolution = calculateResolution(score);
		counter = BarTime.ZERO;
		try {
			seq = new Sequence(Sequence.PPQ, resolution);
			translate(score);
		} catch (InvalidMidiDataException e) {
			// Should not happen
			throw new RuntimeException(e);
		}
	}

	public Sequence getSequence() {
		return seq;
	}

	private void translate(Score score) {
		for (Part part : score) {
			track = seq.createTrack();
			translate(part);
		}
	}

	private void translate(Part part) {
		for (Staff staff : part) {
			counter = BarTime.ZERO;
			translate(staff);
		}
	}

	private void translate(Staff staff) {
		for (Bar bar : staff) {
			translate(bar);
			// After the bar is translated increase the counter.
			counter = counter.plus(bar.length());
		}
	}

	private void translate(Bar bar) {
		for (Voice voice : bar) {
			translate(voice);
		}
	}

	private void translate(Voice voice) {
		for (Entry<BarTime, Note> e : voice.entrySet()) {
			addNoteToTrack(counter.plus(e.getKey()), e.getValue());
		}
	}

	private void addNoteToTrack(BarTime time, Note note) {
		try {
			BarTime noteEnd = time.plus(note.getDuration());
			checkDivisibility(time);
			checkDivisibility(noteEnd);
			int timeStart = time.getNumerator() * (resolution / time.getDenominator());
			int timeEnd = noteEnd.getNumerator() * (resolution / noteEnd.getDenominator());
			for (Pitch p : note) {
				// Switch note on
				MidiMessage noteOn = new ShortMessage(ShortMessage.NOTE_ON, 0, p.asInt(), 64);
				track.add(new MidiEvent(noteOn, timeStart));
				// Switch note off
				MidiMessage noteOff = new ShortMessage(ShortMessage.NOTE_OFF, 0, p.asInt(), 0);
				track.add(new MidiEvent(noteOff, timeEnd));
			}

		} catch (InvalidMidiDataException e) {
			// Cannot occur
			throw new RuntimeException(e);
		}
	}

	private void checkDivisibility(BarTime time) {
		if (resolution % time.getDenominator() != 0) {
			throw new RuntimeException("The resolution is not divisible by the denominator");
		}
	}

	private static int calculateResolution(Score score) {
		return score.stream().map(MidiTranslator::calculateResolution).reduce(1, BarTime::leastCommonMultiple);
	}

	private static int calculateResolution(Part part) {
		return part.stream().map(MidiTranslator::calculateResolution).reduce(1, BarTime::leastCommonMultiple);
	}

	private static int calculateResolution(Staff staff) {
		return staff.stream().map(MidiTranslator::calculateResolution).reduce(1, BarTime::leastCommonMultiple);
	}

	private static int calculateResolution(Bar bar) {
		return bar.stream().map(MidiTranslator::calculateResolution).reduce(1, BarTime::leastCommonMultiple);
	}

	private static int calculateResolution(Voice voice) {
		int resolutionKeys = voice.keySet().stream().map(BarTime::getDenominator).reduce(1,
				BarTime::leastCommonMultiple);
		int resolutionValues = voice.values().stream().map(Note::getDuration).map(BarTime::getDenominator).reduce(1,
				BarTime::leastCommonMultiple);
		return BarTime.leastCommonMultiple(resolutionKeys, resolutionValues);
	}

}
