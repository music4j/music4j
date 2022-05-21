package org.music4j.simple;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import org.antlr.v4.runtime.BailErrorStrategy;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.misc.ParseCancellationException;
import org.music4j.Score;
import org.music4j.grammar.RubatoInterpreter;
import org.music4j.grammar.gen.RubatoLexer;
import org.music4j.grammar.gen.RubatoParser;

/**
 * Simple implementation of a Score based on an ArrayList.
 */
public class ArrayScore implements Score {

    /**
     * Read the file and parse the input to a Score.
     *
     * @param file the file that is parsed
     * @return a Score
     * @throws IOException if the file cannot be found or read.
     */
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
            throw new IllegalArgumentException(String.format("The given file %s cannot be processed.", file));
        }
    }
}
