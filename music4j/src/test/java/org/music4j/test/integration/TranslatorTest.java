package org.music4j.test.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.music4j.BarTime;
import org.music4j.grammar.RubatoTranslator;

class TranslatorTest {

    private static final RubatoTranslator TRANSLATOR = new RubatoTranslator();

    @Test
    void translateTime() {
        assertEquals("/2", TRANSLATOR.translateTime(BarTime.of(1,2)));
    }
}
