package org.music4j.rubato.token;

public class NoteTieStart extends AbstractParserToken<Boolean> {

    @Override
    protected Boolean defaultValue() {
        return false;
    }

}
