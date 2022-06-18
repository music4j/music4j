package org.music4j.rubato.token;

import org.music4j.BarTime;

public class DefaultDuration extends AbstractParserToken<BarTime> {

    @Override
    protected BarTime defaultValue() {
        return BarTime.of(1);
    }


}
