package org.music4j.grammar;

import org.music4j.grammar.gen.RubatoBaseVisitor;
import org.music4j.grammar.gen.RubatoVisitor;
import org.music4j.grammar.token.AbstractParserToken;
import org.music4j.utils.Container;

public abstract class AbstractVisitor extends RubatoBaseVisitor<Object> implements RubatoVisitor<Object> {

    /**
     * Container stores ParserToken.
     */
    private final Container<AbstractParserToken<?>> container;

    private final AbstractVisitor parent;

    public AbstractVisitor() {
        parent = null;
        container = new Container<>();
    }

    public AbstractVisitor(AbstractVisitor parent) {
        this.parent = parent;
        container = new Container<>();
    }

    /**
     * Get the value of the requested token. If none is present the we look up the
     * parent if provided.
     *
     * @param <E> the value type of the token
     * @param <T> the token type.
     * @param key the class key of the token
     * @return the value of the token.
     */
    protected <E, T extends AbstractParserToken<E>> E get(Class<T> key) {
        T token = container.get(key);
        // If no token was found and a parent is provided we look up the parent.
        if (token == null && parent != null) {
            token = parent.container.get(key);
        }
        return token == null ? null : token.get();
    }

    protected <E, T extends AbstractParserToken<E>> void set(Class<T> key, E value) {
        T token = container.get(key);
        if (token != null) {
            token.accept(value);
        }
    }

    protected <E, T extends AbstractParserToken<E>> void add(T token, E value) {
        token.accept(value);
        add(token);
    }

    protected <E, T extends AbstractParserToken<E>> void add(T token) {
        container.add(token);
    }

    protected <E, T extends AbstractParserToken<E>> void remove(Class<T> key) {
        container.removeType(key);
    }

    protected AbstractVisitor getParent() {
        return parent;
    }
}
