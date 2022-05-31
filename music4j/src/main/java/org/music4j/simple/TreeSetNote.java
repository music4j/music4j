package org.music4j.simple;

import java.util.Collection;
import java.util.NavigableSet;
import java.util.TreeSet;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.music4j.BarTime;
import org.music4j.Note;
import org.music4j.Pitch;
import org.music4j.grammar.ErrorCollector;
import org.music4j.grammar.RubatoVisitorImpl;
import org.music4j.grammar.gen.RubatoLexer;
import org.music4j.grammar.gen.RubatoParser;
import org.music4j.utils.ForwardingNavigableSet;

/**
 * Simple implementation for the note interface based on a TreeSet.
 */
public final class TreeSetNote extends ForwardingNavigableSet<Pitch> implements Note {

    private final BarTime duration;

    private Note.Type type;

    private int dots;

    private boolean isTieStart;

    private boolean isTieEnd;

    /*
     * Private constructor is used to covariantly return appropriate subsets. This
     * constructor does not create a new TreeSet but Forwards an existing
     * NavigableSet.
     */
    private TreeSetNote(NavigableSet<Pitch> set, BarTime duration) {
        super(set);
        this.duration = duration;
    }

    public TreeSetNote(BarTime duration) {
        this(new TreeSet<>(), duration);
    }

    public TreeSetNote(BarTime duration, Collection<Pitch> pitches) {
        this(duration);
        addAll(pitches);
    }

    public static Note parse(String string) {
        try {
            CharStream input = CharStreams.fromString(string);
            RubatoLexer lexer = new RubatoLexer(input);
            lexer.removeErrorListeners();
            ErrorCollector errCollector = new ErrorCollector();
            lexer.addErrorListener(errCollector);
            TokenStream tokens = new CommonTokenStream(lexer);
            RubatoParser parser = new RubatoParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(errCollector);
            RubatoVisitorImpl interpreter = new RubatoVisitorImpl();
            Note note = interpreter.visitNote(parser.note());
            errCollector.throwErrors();
            return note;
        } catch (Exception e) {
            throw new IllegalArgumentException(
                    String.format("The given input \"%s\" cannot be processed. %n %s", string, e.getMessage()));
        }
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

    /**
     * @return the isTieStart
     */
    @Override
    public boolean isTieStart() {
        return isTieStart;
    }

    /**
     * @param isTieStart the isTieStart to set
     */
    @Override
    public void setTieStart(boolean isTieStart) {
        this.isTieStart = isTieStart;
    }

    /**
     * @return the isTieEnd
     */
    @Override
    public boolean isTieEnd() {
        return isTieEnd;
    }

    /**
     * @param isTieEnd the isTieEnd to set
     */
    @Override
    public void setTieEnd(boolean isTieEnd) {
        this.isTieEnd = isTieEnd;
    }

    @Override
    public Note descendingSet() {
        Note note = new TreeSetNote(super.descendingSet(), duration);
        note.setType(type);
        note.setDots(dots);
        return note;
    }

    @Override
    public Note headSet(Pitch toElement, boolean inclusive) {
        Note note = new TreeSetNote(super.headSet(toElement, inclusive), duration);
        note.setType(type);
        note.setDots(dots);
        return note;
    }

    @Override
    public Note tailSet(Pitch fromElement, boolean inclusive) {
        Note note = new TreeSetNote(super.tailSet(fromElement, inclusive), duration);
        note.setType(type);
        note.setDots(dots);
        return note;
    }

    @Override
    public Note subSet(Pitch fromElement, boolean fromInclusive, Pitch toElement, boolean toInclusive) {
        Note note = new TreeSetNote(super.subSet(fromElement, fromInclusive, toElement, toInclusive), duration);
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
