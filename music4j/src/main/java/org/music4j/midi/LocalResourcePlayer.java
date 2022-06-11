package org.music4j.midi;

import java.io.File;
import java.io.IOException;

import javax.sound.midi.MidiUnavailableException;

import org.music4j.Score;

public class LocalResourcePlayer {

    public static void main(String[] args) throws IOException, MidiUnavailableException {
        File rubatoFile = new File(args[0]);
        Score score = Score.readFile(rubatoFile);
        System.out.println(score.toString());
        MidiPlayer player = new MidiPlayer();
        player.play(score);
    }
}
