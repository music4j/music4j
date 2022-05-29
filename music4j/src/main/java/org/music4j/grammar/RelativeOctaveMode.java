package org.music4j.grammar;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class RelativeOctaveMode implements ParserToken, Supplier<Boolean>,  Consumer<Boolean> {

    private boolean isInRelativeOctaveMode;

    public RelativeOctaveMode() {
        isInRelativeOctaveMode = false;
    }

    @Override
    public void accept(Boolean t) {
        isInRelativeOctaveMode = t;
    }

    @Override
    public Boolean get() {
        return isInRelativeOctaveMode;
    }

}
