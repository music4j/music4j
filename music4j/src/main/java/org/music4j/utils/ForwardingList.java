package org.music4j.utils;

import java.util.AbstractList;
import java.util.List;
import java.util.Objects;

public abstract class ForwardingList<E> extends AbstractList<E> implements List<E> {

    private final List<E> list;

    protected ForwardingList(List<E> list) {
        this.list = list;
    }

    @Override
    public E set(int index, E element) {
        Objects.requireNonNull(element);
        return list.set(index, element);
    }

    @Override
    public void add(int index, E element) {
        Objects.requireNonNull(element);
        list.add(index, element);
    }

    @Override
    public E remove(int index) {
        return list.remove(index);
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public int size() {
        return list.size();
    }
}
