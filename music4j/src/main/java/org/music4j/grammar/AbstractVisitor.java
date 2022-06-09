package org.music4j.grammar;

import java.lang.reflect.Constructor;

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
            return parent.get(key);
        }
        try {
            return token.get();
        } catch (NullPointerException e) {
            throw new RuntimeException("No token found");
        }
    }

    protected <E, T extends AbstractParserToken<E>> void set(Class<T> key, E value) {
        T token = container.get(key);
        if (token == null && parent != null) {
            parent.set(key, value);
            // Exit method here if no token is found with this visitor.
            return;
        }
        if (token != null) {
            token.accept(value);
        } else {
            add(key);
            set(key, value);
        }
    }

    /**
     * Adds the token in the specified scope. Meaning that if the token is to be
     * added in a lower scope it will be added to a appropriate parent visitor if
     * possible or the highest available level.
     *
     * @param <E>
     * @param <T>
     * @param token
     * @param scope
     */
    protected <T extends AbstractParserToken<?>> void add(Class<T> key, Scope scope) {
        int levelDifference = scope().getLevel() - scope.getLevel();
        if (levelDifference < 0) {
            throw new RuntimeException("The token must be added on a higgher level. Or sibling visitor.");
        } else if (scope == scope() || parent == null) {
            add(key);
        } else {
            parent.add(key, scope);
        }
    }

    protected <T extends AbstractParserToken<?>> void add(Class<T> key) {
        try {
            Constructor<T> constructor = key.getConstructor();
            T token = constructor.newInstance();
            container.add(token);
        } catch (ReflectiveOperationException e) {
            throw new RuntimeException("Reflective creation of the token failed.");
        }
    }

    protected <E, T extends AbstractParserToken<E>> void remove(Class<T> key) {
        container.removeType(key);
    }

    protected AbstractVisitor getParent() {
        return parent;
    }

    protected abstract Scope scope();

    public enum Scope {

        SCORE(0),

        PART(1),

        STAFF(2),

        STAFFVOICE(3),

        BARVOICE(4),

        NOTE(5),

        PITCH(6),

        TIME(6);

        private final int level;

        private Scope(int i) {
            level = i;
        }

        public int getLevel() {
            return level;
        }
    }
}
