package org.music4j.midi;

import javax.sound.midi.InvalidMidiDataException;
import javax.sound.midi.MidiSystem;
import javax.sound.midi.MidiUnavailableException;
import javax.sound.midi.Receiver;
import javax.sound.midi.Sequence;
import javax.sound.midi.Sequencer;
import javax.sound.midi.Synthesizer;
import javax.sound.midi.Transmitter;

import org.music4j.Bar;
import org.music4j.Note;
import org.music4j.Pitch;
import org.music4j.Voice;

public class Player {

    private final Sequencer sequencer;
    private final Transmitter transmitter;
    private final Synthesizer synthesizer;
    private final Receiver receiver;

    // Midi translator translates music4j object to midi sequences
    private final MidiTranslator translator;

    public Player() throws MidiUnavailableException {
        sequencer = MidiSystem.getSequencer();
        transmitter = sequencer.getTransmitter();
        synthesizer = MidiSystem.getSynthesizer();
        receiver = synthesizer.getReceiver();
        translator = new MidiTranslator();
    }

    public void play(Pitch pitch) throws MidiUnavailableException {
        play(translator.translate(pitch));
    }

    public void play(Note note) throws MidiUnavailableException {
        play(translator.translate(note));
    }

    public void play(Voice voice) throws MidiUnavailableException {
        play(translator.translate(voice));
    }

    public void play(Bar bar) throws MidiUnavailableException {
        play(translator.translate(bar));
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

    public static void main(String[] args) {
        try {
            Player player = new Player();

            Bar bar = Bar.of();
            Voice voice = Voice.of("C''2 E'' G'' B'3/2 C''/4 D''/4 C''2 A''2 G'' C''' G'' F'' E''");
            bar.add(voice);
            bar.add(Voice.of(
                    "C'/2 G'/2 E'/2 G'/2 C'/2 G'/2 E'/2 G'/2 "
                    + "D'/2 G'/2 F'/2 G'/2 C'/2 G'/2 E'/2 G'/2"
                    + " C'/2 A'/2 F'/2 A'/2 C'/2 G'/2 E'/2 G'/2"
                    + " B/2 G'/2 D'/2 G'/2 C'/2 G'/2 E'/2 G'/2"));

            player.play(bar);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.exit(0);
    }
}
