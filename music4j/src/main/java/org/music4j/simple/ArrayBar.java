package org.music4j.simple;

import java.util.ArrayList;

import org.music4j.Bar;
import org.music4j.Voice;

public class ArrayBar extends ForwardingList<Voice> implements Bar {

    public ArrayBar() {
        super(new ArrayList<>());
    }

    public static Bar parse(String string) {
        return null;
//        try {
//            CharStream input = CharStreams.fromString(string);
//            RubatoLexer lexer = new RubatoLexer(input);
//            TokenStream tokens = new CommonTokenStream(lexer);
//            RubatoParser parser = new RubatoParser(tokens);
//            parser.setErrorHandler(new BailErrorStrategy());
//            RubatoInterpreter interpreter = new RubatoInterpreter();
//            return interpreter.visitBar(parser.bar());
//        } catch (ParseCancellationException e) {
//            throw new IllegalArgumentException(String.format("The given input \"%s\" cannot be processed.", string));
//        }
    }

}
