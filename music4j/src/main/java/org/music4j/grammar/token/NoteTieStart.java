package org.music4j.grammar.token;

public class NoteTieStart extends AbstractParserToken<Boolean> {

    @Override
    protected Boolean defaultValue() {
        return false;
    }

}
