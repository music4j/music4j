package org.music4j.utils;

import java.util.AbstractCollection;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Objects;

/**
 * A Container stores values as token-value pairs. The token determines the
 * class type of the value and it is possible to return the value in a typesafe
 * manner. It is <b>very important to note that the type of values and all
 * subtypes needs to be fully reifiable</b>
 *
 * @param <T> the Type of values that are stored n the container.
 */
public class Container<T> extends AbstractCollection<T> implements Collection<T> {

    private final Map<Class<? extends T>, T> container;

    public Container() {
        container = new HashMap<>();
    }

    public <E extends T> E get(Class<E> type) {
        Objects.requireNonNull(type);
        return container.containsKey(type) ? type.cast(container.get(type)) : null;
    }

    public <E extends T> E removeType(Class<E> type) {
        Objects.requireNonNull(type);
        return container.containsKey(type) ? type.cast(container.remove(type)) : null;
    }

    public boolean containsType(Class<? extends T> key) {
        return container.containsKey(key);
    }

    @Override
    public boolean add(T e) {
        Objects.requireNonNull(e);
        @SuppressWarnings("unchecked")
        // Only works with fully reifiable types
        Class<? extends T> key = (Class<? extends T>) e.getClass();
        return container.containsKey(key) ? false : container.put(key, e) == null;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private final Iterator<Entry<Class<? extends T>, T>> i = container.entrySet().iterator();

            @Override
            public boolean hasNext() {
                return i.hasNext();
            }

            @Override
            public T next() {
                return i.next().getValue();
            }

            @Override
            public void remove() {
                i.remove();
            }
        };
    }

    @Override
    public int size() {
        return container.size();
    }
}
