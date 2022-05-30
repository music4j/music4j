package org.music4j.simple;

import java.util.ArrayList;

import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.music4j.Bar;
import org.music4j.BarTime;
import org.music4j.Voice;
import org.music4j.grammar.RubatoVisitorImpl;
import org.music4j.grammar.gen.RubatoLexer;
import org.music4j.grammar.gen.RubatoParser;
import org.music4j.utils.ForwardingList;

public class ArrayListBar extends ForwardingList<Voice> implements Bar {

    public ArrayListBar() {
        super(new ArrayList<>());
    }

    public static Bar parse(String string) {
        try {
            CharStream input = CharStreams.fromString(string);
            RubatoLexer lexer = new RubatoLexer(input);
            TokenStream tokens = new CommonTokenStream(lexer);
            RubatoParser parser = new RubatoParser(tokens);
            parser.setErrorHandler(new BailErrorStrategy());
            RubatoVisitorImpl interpreter = new RubatoVisitorImpl();
            return interpreter.visitBar(parser.bar());
        } catch (ParseCancellationException e) {
            throw new IllegalArgumentException(String.format("The given input \"%s\" cannot be processed.", string));
        }
    }

    @Override
    public BarTime length() {
        return stream().map(Voice::length).max(BarTime::compareTo).orElse(BarTime.ZERO);
    }

}
