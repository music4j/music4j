package org.music4j.grammar;

import org.music4j.Pitch.Step;

public class PreviousStep extends ParserToken<Step> {

    public PreviousStep() {
        accept(Step.C);
    }

}
