package org.music4j.utils;

import java.util.AbstractSet;
import java.util.Comparator;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.SortedSet;

/**
 * Forwarding class for NavigableSet implementations.
 *
 * @param <E>
 */
public class ForwardingNavigableSet<E> extends AbstractSet<E> implements NavigableSet<E> {

    private final NavigableSet<E> set;

    public ForwardingNavigableSet(NavigableSet<E> set) {
        this.set = set;
    }

    @Override
    public boolean add(E e) {
        return set.add(e);
    }

    @Override
    public Comparator<? super E> comparator() {
        return set.comparator();
    }

    @Override
    public E first() {
        return set.first();
    }

    @Override
    public E last() {
        return set.last();
    }

    @Override
    public E lower(E e) {
        return set.lower(e);
    }

    @Override
    public E floor(E e) {
        return set.floor(e);
    }

    @Override
    public E ceiling(E e) {
        return set.ceiling(e);
    }

    @Override
    public E higher(E e) {
        return set.higher(e);
    }

    @Override
    public E pollFirst() {
        return set.pollFirst();
    }

    @Override
    public E pollLast() {
        return set.pollLast();
    }

    @Override
    public NavigableSet<E> descendingSet() {
        return set.descendingSet();
    }

    @Override
    public Iterator<E> descendingIterator() {
        return set.descendingIterator();
    }

    @Override
    public NavigableSet<E> subSet(E fromElement, boolean fromInclusive, E toElement, boolean toInclusive) {
        return set.subSet(fromElement, fromInclusive, toElement, toInclusive);
    }

    @Override
    public NavigableSet<E> headSet(E toElement, boolean inclusive) {
        return set.headSet(toElement, inclusive);
    }

    @Override
    public NavigableSet<E> tailSet(E fromElement, boolean inclusive) {
        return set.tailSet(fromElement, inclusive);
    }

    @Override
    public SortedSet<E> subSet(E fromElement, E toElement) {
        return set.subSet(fromElement, toElement);
    }

    @Override
    public SortedSet<E> headSet(E toElement) {
        return set.headSet(toElement);
    }

    @Override
    public SortedSet<E> tailSet(E fromElement) {
        return set.tailSet(fromElement);
    }

    @Override
    public Iterator<E> iterator() {
        return set.iterator();
    }

    @Override
    public int size() {
        return set.size();
    }
}
