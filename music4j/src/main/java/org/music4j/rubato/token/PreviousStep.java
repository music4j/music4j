package org.music4j.rubato.token;

import org.music4j.Pitch.Step;

public class PreviousStep extends AbstractParserToken<Step> {

    @Override
    protected Step defaultValue() {
        return Step.C;
    }

}
