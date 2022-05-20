package org.music4j.midi;

import java.util.List;

import javax.sound.midi.MidiSystem;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Transmitter;

import org.music4j.Pitch;
import org.music4j.Pitch.Alter;
import org.music4j.Pitch.Octave;
import org.music4j.Pitch.Step;

public class Player {

    private static void playNotes() throws Exception {

        List<Pitch> pitches = List.of(
                Pitch.of(Step.C, Alter.NATURAL, Octave.ONE_LINED),
                Pitch.of(Step.E, Alter.FLAT, Octave.ONE_LINED),
                Pitch.of(Step.G, Alter.NATURAL, Octave.ONE_LINED),
                Pitch.of(Step.F, Alter.SHARP, Octave.ONE_LINED),
                Pitch.of(Step.G, Alter.NATURAL, Octave.ONE_LINED),
                Pitch.of(Step.E, Alter.FLAT, Octave.ONE_LINED),
                Pitch.of(Step.C, Alter.NATURAL, Octave.ONE_LINED)
                );

        // Sequencer und Synthesizer initialisieren
        Sequencer sequencer = MidiSystem.getSequencer();
        Transmitter trans = sequencer.getTransmitter();
        Synthesizer synth = MidiSystem.getSynthesizer();
        Receiver rcvr = synth.getReceiver();
        // Beide öffnen und verbinden
        sequencer.open();
        synth.open();
        trans.setReceiver(rcvr);

        MidiTranslator translator = new MidiTranslator();

        sequencer.setSequence(translator.translate(pitches));
        sequencer.setTempoInBPM(145);
        sequencer.start();
        while (true) {
            try {
                Thread.sleep(100);
            } catch (Exception e) {
                // nothing
            }
            if (!sequencer.isRunning()) {
                break;
            }
        }
        // Sequencer anhalten und Geräte schließen
        sequencer.stop();
        sequencer.close();
        synth.close();
    }

    public static void main(String[] args) {
        try {
            playNotes();
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.exit(0);
    }
}
