package org.music4j.grammar;

import java.util.function.Consumer;
import java.util.function.Supplier;

public class RelativeTimeMode implements ParserToken, Supplier<Boolean>,  Consumer<Boolean> {

    private boolean isInRelativeTimeMode;

    public RelativeTimeMode() {
        isInRelativeTimeMode = false;
    }

    @Override
    public void accept(Boolean t) {
        isInRelativeTimeMode = t;
    }

    @Override
    public Boolean get() {
        return isInRelativeTimeMode;
    }

}
