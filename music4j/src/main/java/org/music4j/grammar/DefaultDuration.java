package org.music4j.grammar;

import java.util.function.Consumer;
import java.util.function.Supplier;

import org.music4j.BarTime;

public class DefaultDuration implements ParserToken, Supplier<BarTime>, Consumer<BarTime> {

    private BarTime defaultDuration;

    public DefaultDuration() {
        defaultDuration = BarTime.of(1);
    }

    @Override
    public BarTime get() {
        return defaultDuration;
    }

    @Override
    public void accept(BarTime t) {
        this.defaultDuration = t;
    }
}
