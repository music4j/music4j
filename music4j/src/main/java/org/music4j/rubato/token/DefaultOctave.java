package org.music4j.rubato.token;

import org.music4j.Pitch.Octave;

public class DefaultOctave extends AbstractParserToken<Octave> {

    @Override
    protected Octave defaultValue() {
        return Octave.SMALL;
    }


}
