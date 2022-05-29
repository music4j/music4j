package org.music4j.utils;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.NavigableMap;
import java.util.NavigableSet;
import java.util.Set;
import java.util.SortedMap;

public class ForwardingNavigableMap<K,V> extends AbstractMap<K,V> implements NavigableMap<K, V> {

    private final NavigableMap<K, V> map;

    public ForwardingNavigableMap(NavigableMap<K, V> map) {
        this.map = map;
    }

    @Override
    public V put(K key, V value) {
        return map.put(key, value);
    }

    @Override
    public Comparator<? super K> comparator() {
        return map.comparator();
    }

    @Override
    public K firstKey() {
        return map.firstKey();
    }

    @Override
    public K lastKey() {
        return map.lastKey();
    }

    @Override
    public Entry<K, V> firstEntry() {
        return map.firstEntry();
    }

    @Override
    public Entry<K, V> lastEntry() {
        return map.lastEntry();
    }

    @Override
    public Entry<K, V> lowerEntry(K key) {
        return map.lowerEntry(key);
    }

    @Override
    public K lowerKey(K key) {
        return map.lowerKey(key);
    }

    @Override
    public Entry<K, V> floorEntry(K key) {
        return map.floorEntry(key);
    }

    @Override
    public K floorKey(K key) {
        return map.floorKey(key);
    }

    @Override
    public Entry<K, V> ceilingEntry(K key) {
        return map.ceilingEntry(key);
    }

    @Override
    public K ceilingKey(K key) {
        return map.ceilingKey(key);
    }

    @Override
    public Entry<K, V> higherEntry(K key) {
        return map.higherEntry(key);
    }

    @Override
    public K higherKey(K key) {
        return map.higherKey(key);
    }

    @Override
    public Entry<K, V> pollFirstEntry() {
        return map.pollFirstEntry();
    }

    @Override
    public Entry<K, V> pollLastEntry() {
        return map.pollLastEntry();
    }

    @Override
    public NavigableMap<K, V> descendingMap() {
        return map.descendingMap();
    }

    @Override
    public NavigableSet<K> navigableKeySet() {
        return map.navigableKeySet();
    }

    @Override
    public NavigableSet<K> descendingKeySet() {
        return map.descendingKeySet();
    }

    @Override
    public NavigableMap<K, V> subMap(K fromKey, boolean fromInclusive, K toKey, boolean toInclusive) {
        return map.subMap(fromKey, fromInclusive, toKey, toInclusive);
    }

    @Override
    public NavigableMap<K, V> headMap(K toKey, boolean inclusive) {
        return map.headMap(toKey, inclusive);
    }

    @Override
    public NavigableMap<K, V> tailMap(K fromKey, boolean inclusive) {
        return map.tailMap(fromKey, inclusive);
    }

    @Override
    public SortedMap<K, V> subMap(K fromKey, K toKey) {
        return map.subMap(fromKey, toKey);
    }

    @Override
    public SortedMap<K, V> headMap(K toKey) {
        return map.headMap(toKey);
    }

    @Override
    public SortedMap<K, V> tailMap(K fromKey) {
        return map.tailMap(fromKey);
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return map.entrySet();
    }

}
