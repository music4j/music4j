package org.music4j.simple;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.TokenStream;
import org.music4j.Bar;
import org.music4j.BarTime;
import org.music4j.Note;
import org.music4j.Part;
import org.music4j.Score;
import org.music4j.Staff;
import org.music4j.Voice;
import org.music4j.rubato.ErrorCollector;
import org.music4j.rubato.ParseException;
import org.music4j.rubato.ScoreVisitor;
import org.music4j.rubato.gen.RubatoLexer;
import org.music4j.rubato.gen.RubatoParser;
import org.music4j.utils.ForwardingList;
import org.music4j.utils.StringOutput;

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
            lexer.removeErrorListeners();
            ErrorCollector errCollector = new ErrorCollector();
            lexer.addErrorListener(errCollector);
            TokenStream tokens = new CommonTokenStream(lexer);
            RubatoParser parser = new RubatoParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(errCollector);
            ScoreVisitor visitor = new ScoreVisitor();
            Score score = visitor.visitScore(parser.score());
            errCollector.throwErrors();
            return score;
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    public static Score parse(String s) {
        try {
            CharStream input = CharStreams.fromString(s);
            RubatoLexer lexer = new RubatoLexer(input);
            lexer.removeErrorListeners();
            ErrorCollector errCollector = new ErrorCollector();
            lexer.addErrorListener(errCollector);
            TokenStream tokens = new CommonTokenStream(lexer);
            RubatoParser parser = new RubatoParser(tokens);
            parser.removeErrorListeners();
            parser.addErrorListener(errCollector);
            ScoreVisitor visitor = new ScoreVisitor();
            Score score = visitor.visitScore(parser.score());
            errCollector.throwErrors();
            return score;
        } catch (ParseException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override
    public Staff get(int partNumber, int staffNumber) {
        return get(partNumber).get(staffNumber);
    }

    @Override
    public Bar get(int partNumber, int staffNumber, int barNumber) {
        return get(partNumber, staffNumber).get(barNumber);
    }

    @Override
    public Voice get(int partNumber, int staffNumber, int barNumber, int voiceNumber) {
        return get(partNumber, staffNumber, barNumber).get(voiceNumber);
    }

    @Override
    public Note get(int partNumber, int staffNumber, int barNumber, int voiceNumber, BarTime time) {
        return get(partNumber, staffNumber, barNumber, voiceNumber).get(time);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.format("Score { %n"));
        stream().forEach(part -> sb.append(StringOutput.indent(part.toString(), 4)));
        sb.append(String.format("} %n"));
        return sb.toString();
    }
}
