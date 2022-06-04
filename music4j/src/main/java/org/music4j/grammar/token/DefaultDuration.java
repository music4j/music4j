package org.music4j.grammar.token;

import org.music4j.BarTime;

public class DefaultDuration extends AbstractParserToken<BarTime> {

    public DefaultDuration() {
        accept(BarTime.of(1));
    }

}
