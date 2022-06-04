package org.music4j.grammar.token;

import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Marker interface for container
 */
public abstract class AbstractParserToken<T> implements Supplier<T>, Consumer<T> {

    private T tokenValue;

    @Override
    public void accept(T t) {
        tokenValue = t;
    }

    @Override
    public T get() {
        return tokenValue;
    }
}
