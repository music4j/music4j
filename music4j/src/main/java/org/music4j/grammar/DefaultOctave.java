package org.music4j.grammar;

import org.music4j.Pitch.Octave;

public class DefaultOctave extends ParserToken<Octave> {

    public DefaultOctave() {
        accept(Octave.SMALL);
    }

}
