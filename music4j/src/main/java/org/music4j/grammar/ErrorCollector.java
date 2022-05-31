package org.music4j.grammar;

import java.util.ArrayList;
import java.util.List;

import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.RecognitionException;
import org.antlr.v4.runtime.Recognizer;

public class ErrorCollector extends BaseErrorListener {

    private List<Exception> errors = new ArrayList<>();

    private StringBuilder errorMessage = new StringBuilder();

    @Override
    public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
            String msg, RecognitionException e) {
        errorMessage.append(String.format("line %d: %d %s %n", line, charPositionInLine, msg));
        errors.add(e);
    }

    public void throwErrors() throws Exception {
        if(!errors.isEmpty()) {
            throw new Exception(errorMessage.toString());
        }
    }

}
