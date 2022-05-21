package org.music4j.midi;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Transmitter;

import org.music4j.Pitch;
import org.music4j.Pitch.Alter;
import org.music4j.Pitch.Octave;
import org.music4j.Pitch.Step;

public class Player {

    private final Sequencer sequencer;
    private final Transmitter transmitter;
    private final Synthesizer synthesizer;
    private final Receiver receiver;

    //Midi translator translates music4j object to midi sequences
    private final MidiTranslator translator;

    public Player() throws MidiUnavailableException {
        sequencer = MidiSystem.getSequencer();
        transmitter = sequencer.getTransmitter();
        synthesizer = MidiSystem.getSynthesizer();
        receiver = synthesizer.getReceiver();
        translator = new MidiTranslator();
    }

    public void play(Pitch pitch) throws MidiUnavailableException {
        // Beide öffnen und verbinden
        sequencer.open();
        synthesizer.open();
        transmitter.setReceiver(receiver);

        try {
            sequencer.setSequence(translator.translate(pitch));
        } catch (InvalidMidiDataException e1) {
            throw new RuntimeException(e1);
        }
        sequencer.setTempoInBPM(120);

        //Start sequencer
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
        synthesizer.close();
    }

    public boolean isRunning() {
        return sequencer.isRunning();
    }

    public static void main(String[] args) {
        try {
            Player player = new Player();
            player.play(Pitch.of(Step.C, Alter.NATURAL, Octave.ONE_LINED));
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.exit(0);
    }
}
