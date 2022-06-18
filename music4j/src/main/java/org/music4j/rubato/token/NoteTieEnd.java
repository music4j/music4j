package org.music4j.rubato.token;

public class NoteTieEnd extends AbstractParserToken<Boolean> {

    @Override
    protected Boolean defaultValue() {
        return false;
    }

}
