package org.music4j.grammar;

import org.music4j.BarTime;

public class DefaultDuration extends ParserToken<BarTime> {

    public DefaultDuration() {
        accept(BarTime.of(1));
    }

}
