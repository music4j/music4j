package org.music4j.grammar;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.music4j.Pitch.Step;

public class PreviousStep implements ParserToken, Supplier<Step>, Consumer<Step> {

    private Step step;

    public PreviousStep() {
        step = Step.C;
    }

    @Override
    public void accept(Step t) {
        this.step = t;
    }

    @Override
    public Step get() {
        return step;
    }

}
