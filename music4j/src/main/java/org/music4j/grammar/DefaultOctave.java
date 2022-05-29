package org.music4j.grammar;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.music4j.Pitch.Octave;

public class DefaultOctave implements ParserToken, Supplier<Octave>, Consumer<Octave>{

    private Octave defaultOctave;

    public DefaultOctave() {
        defaultOctave = Octave.SMALL;
    }

    @Override
    public void accept(Octave t) {
        defaultOctave = t;
    }

    @Override
    public Octave get() {
        return defaultOctave;
    }

}
