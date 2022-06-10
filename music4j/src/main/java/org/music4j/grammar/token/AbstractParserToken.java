package org.music4j.grammar.token;

import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Supplier;

/**
 * Token class stores a value.
 */
public abstract class AbstractParserToken<T> implements Supplier<T>, Consumer<T> {

    private T tokenValue;

    public AbstractParserToken() {
        tokenValue = Objects.requireNonNull(defaultValue());
    }

    @Override
    public void accept(T t) {
        tokenValue = t;
    }

    @Override
    public T get() {
        return tokenValue;
    }

    protected abstract T defaultValue();
}
