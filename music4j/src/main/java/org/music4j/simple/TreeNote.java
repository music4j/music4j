package org.music4j.simple;

import java.util.Collection;
import java.util.NavigableSet;
import java.util.TreeSet;

import org.music4j.BarTime;
import org.music4j.Note;
import org.music4j.Pitch;

/**
 * Simple implementation for the note interface based on a TreeSet.
 */
public final class TreeNote extends ForwardingNavigableSet<Pitch> implements Note {

    private final BarTime duration;

    private Note.Type type;

    private int dots;

    /*
     * Private constructor is used to covariantly return appropriate subsets. This
     * constructor does not create a new TreeSet but Forwards an existing
     * NavigableSet.
     */
    private TreeNote(NavigableSet<Pitch> set, BarTime duration) {
        super(set);
        this.duration = duration;
    }

    public TreeNote(BarTime duration) {
        this(new TreeSet<>(), duration);
    }

    public TreeNote(BarTime duration, Collection<Pitch> pitches) {
        this(duration);
        addAll(pitches);
    }

    @Override
    public BarTime getDuration() {
        return duration;
    }

    @Override
    public Type getType() {
        return type;
    }

    @Override
    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public int getDots() {
        return dots;
    }

    @Override
    public void setDots(int i) {
        this.dots = i;
    }

    @Override
    public Note descendingSet() {
        Note note = new TreeNote(super.descendingSet(), duration);
        note.setType(type);
        note.setDots(dots);
        return note;
    }

    @Override
    public Note headSet(Pitch toElement, boolean inclusive) {
        Note note = new TreeNote(super.headSet(toElement, inclusive), duration);
        note.setType(type);
        note.setDots(dots);
        return note;
    }

    @Override
    public Note tailSet(Pitch fromElement, boolean inclusive) {
        Note note = new TreeNote(super.tailSet(fromElement, inclusive), duration);
        note.setType(type);
        note.setDots(dots);
        return note;
    }

    @Override
    public Note subSet(Pitch fromElement, boolean fromInclusive, Pitch toElement, boolean toInclusive) {
        Note note = new TreeNote(super.subSet(fromElement, fromInclusive, toElement, toInclusive), duration);
        note.setType(type);
        note.setDots(dots);
        return note;
    }

    @Override
    public Note subSet(Pitch fromElement, Pitch toElement) {
        return subSet(fromElement, false, toElement, true);
    }

    @Override
    public Note tailSet(Pitch fromElement) {
        return tailSet(fromElement, true);
    }

    @Override
    public Note headSet(Pitch toElement) {
        return headSet(toElement, false);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (isEmpty()) {
            sb.append("R");
        } else if (size() == 1) {
            sb.append(first());
        } else {
            sb.append("[");
            stream().forEach(p -> sb.append(String.format(" %s", p)));
            sb.append("]");
            sb.replace(1, 2, "");
        }
        if (!duration.equals(BarTime.of(1))) {
            sb.append(duration);
        }
        return sb.toString();
    }
}
