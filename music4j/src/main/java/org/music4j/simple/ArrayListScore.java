package org.music4j.simple;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.music4j.Part;
import org.music4j.Score;
import org.music4j.grammar.RubatoInterpreter;
import org.music4j.grammar.gen.RubatoLexer;
import org.music4j.grammar.gen.RubatoParser;

/**
 * Simple implementation of a Score based on an ArrayList.
 */
public class ArrayListScore extends ForwardingList<Part> implements Score {

    public ArrayListScore() {
        super(new ArrayList<>());
    }

    public static Score readFile(File file) throws IOException {
        try {
            CharStream input = CharStreams.fromReader(new FileReader(file));
            RubatoLexer lexer = new RubatoLexer(input);
            TokenStream tokens = new CommonTokenStream(lexer);
            RubatoParser parser = new RubatoParser(tokens);
            parser.setErrorHandler(new BailErrorStrategy());
            RubatoInterpreter interpreter = new RubatoInterpreter();
            return interpreter.visitScore(parser.score());
        } catch (ParseCancellationException e) {
            throw new IllegalArgumentException(
                    String.format("The given file %s cannot be processed.", file));
        }
    }

    public static Score parse(String s) {
        try {
            CharStream input = CharStreams.fromString(s);
            RubatoLexer lexer = new RubatoLexer(input);
            TokenStream tokens = new CommonTokenStream(lexer);
            RubatoParser parser = new RubatoParser(tokens);
            parser.setErrorHandler(new BailErrorStrategy());
            RubatoInterpreter interpreter = new RubatoInterpreter();
            return interpreter.visitScore(parser.score());
        } catch (ParseCancellationException e) {
            throw new IllegalArgumentException(
                    String.format("The given input %s cannot be processed.", s));
        }
    }
}
