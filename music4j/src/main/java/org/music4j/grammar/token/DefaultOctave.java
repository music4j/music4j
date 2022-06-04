package org.music4j.grammar.token;

import org.music4j.Pitch.Octave;

public class DefaultOctave extends AbstractParserToken<Octave> {

    public DefaultOctave() {
        accept(Octave.SMALL);
    }

}
