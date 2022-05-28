package org.music4j.midi;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Transmitter;

import org.music4j.Score;

public class MidiPlayer {

    private final Sequencer sequencer;
    private final Transmitter transmitter;
    private final Synthesizer synthesizer;
    private final Receiver receiver;

    public MidiPlayer() throws MidiUnavailableException {
        sequencer = MidiSystem.getSequencer();
        transmitter = sequencer.getTransmitter();
        synthesizer = MidiSystem.getSynthesizer();
        receiver = synthesizer.getReceiver();
    }

    public void play(Score score) throws MidiUnavailableException {
        play(new MidiTranslator(score).getSequence());
    }

    private void play(Sequence seq) throws MidiUnavailableException {
        // Beide öffnen und verbinden
        sequencer.open();
        synthesizer.open();
        transmitter.setReceiver(receiver);

        try {
            sequencer.setSequence(seq);
        } catch (InvalidMidiDataException e1) {
            throw new RuntimeException(e1);
        }
        sequencer.setTempoInBPM(120);

        // Start sequencer
        while (true) {
            try {
                sequencer.start();
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
}
