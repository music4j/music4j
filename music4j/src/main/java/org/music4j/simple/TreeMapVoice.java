package org.music4j.simple;

import static org.music4j.BarTime.ZERO;

import java.util.Map;
import java.util.NavigableMap;
import java.util.Objects;
import java.util.TreeMap;

import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.music4j.BarTime;
import org.music4j.Measurable;
import org.music4j.Note;
import org.music4j.Voice;
import org.music4j.VoicePackException;
import org.music4j.grammar.RubatoInterpreter;
import org.music4j.grammar.gen.RubatoLexer;
import org.music4j.grammar.gen.RubatoParser;

public final class TreeMapVoice extends ForwardingNavigableMap<BarTime, Note> implements Voice {

    private TreeMapVoice(NavigableMap<BarTime, Note> map) {
        super(map);
    }

    public TreeMapVoice() {
        super(new TreeMap<>());
    }

    public TreeMapVoice(Map<BarTime, Note> voice) {
        this();
        putAll(voice);
    }

    public static Voice parse(String string) {
        try {
            CharStream input = CharStreams.fromString(string);
            RubatoLexer lexer = new RubatoLexer(input);
            TokenStream tokens = new CommonTokenStream(lexer);
            RubatoParser parser = new RubatoParser(tokens);
            parser.setErrorHandler(new BailErrorStrategy());
            RubatoInterpreter interpreter = new RubatoInterpreter();
            return interpreter.visitVoice(parser.voice());
        } catch (ParseCancellationException e) {
            throw new IllegalArgumentException(String.format("The given input \"%s\" cannot be processed.", string));
        }
    }

    @Override
    public Note put(BarTime key, Note value) {
        if (!key.isLonger(BarTime.ZERO)) {
            throw new IllegalArgumentException("The key must be greater than zero.");
        }
        if (!lastingFor(key).equals(BarTime.ZERO)) {
            throw new VoicePackException("The value cannot be inserted as a lower element has still lasting effect.");
        }
        if (!value.isShorter(nextIn(key))) {
            throw new VoicePackException("The value cannot be inserted as it is in conflicted with a later item.");
        }
        return super.put(key, value);
    }

    @Override
    public boolean fits(BarTime key, Measurable value) {
        Objects.requireNonNull(key);
        Objects.requireNonNull(value);
        return key.isLonger(BarTime.ZERO) && lastingFor(key).equals(BarTime.ZERO) && value.isShorter(nextIn(key));
    }

    @Override
    public BarTime lastingFor(BarTime key) {
        Objects.requireNonNull(key);
        Entry<BarTime, Note> lowerEntry = lowerEntry(key);
        if (lowerEntry != null) {
            BarTime lowerKey = lowerEntry.getKey();
            BarTime lowerDuration = lowerEntry.getValue().getDuration();
            return BarTime.max(ZERO, lowerKey.plus(lowerDuration).minus(key));
        } else {
            return ZERO;
        }
    }

    @Override
    public BarTime nextIn(BarTime key) {
        Objects.requireNonNull(key);
        BarTime higher = higherKey(key);
        return higher == null ? BarTime.MAX : higher.minus(key);
    }

    @Override
    public BarTime length() {
        Entry<BarTime, Note> last = lastEntry();
        if (last != null) {
            BarTime key = last.getKey();
            BarTime duration = last.getValue().getDuration();
            return key.plus(duration);
        } else {
            return ZERO;
        }
    }

    @Override
    public Voice subMap(BarTime fromKey, boolean fromInclusive, BarTime toKey, boolean toInclusive) {
        return new TreeMapVoice(super.subMap(fromKey, fromInclusive, toKey, toInclusive));
    }

    @Override
    public Voice descendingMap() {
        return new TreeMapVoice(super.descendingMap());
    }

    @Override
    public Voice headMap(BarTime toKey, boolean inclusive) {
        return new TreeMapVoice(super.headMap(toKey, inclusive));
    }

    @Override
    public Voice tailMap(BarTime fromKey, boolean inclusive) {
        return new TreeMapVoice(super.tailMap(fromKey, inclusive));
    }

    @Override
    public Voice subMap(BarTime fromKey, BarTime toKey) {
        return subMap(fromKey, true, toKey, false);
    }

    @Override
    public Voice headMap(BarTime toKey) {
        return headMap(toKey, false);
    }

    @Override
    public Voice tailMap(BarTime fromKey) {
        return tailMap(fromKey, true);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Entry<BarTime, Note> entry : entrySet()) {
            sb.append(String.format("%s ", entry.getValue()));
        }
        sb.replace(sb.length() - 1, sb.length(), "");
        return sb.toString();
    }
}
