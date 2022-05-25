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
import org.music4j.Part;
import org.music4j.Score;
import org.music4j.Staff;
import org.music4j.Voice;

public class Player {

    private final Sequencer sequencer;
    private final Transmitter transmitter;
    private final Synthesizer synthesizer;
    private final Receiver receiver;

    public Player() throws MidiUnavailableException {
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
            Staff staff = Staff.of();
            staff.add(bar);
            Part part = Part.of();
            part.add(staff);
            Score score = Score.of();
            score.add(part);

            player.play(score);
        } catch (Exception e) {
            e.printStackTrace();
            System.exit(1);
        }
        System.exit(0);
    }
}
