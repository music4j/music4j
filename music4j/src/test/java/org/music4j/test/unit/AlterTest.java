package org.music4j.test.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.music4j.Pitch.Alter;

class AlterTest {

    @ParameterizedTest
    @CsvSource({ "NATURAL, 0, 0", "FLAT, -1, -1", "FLAT_FLAT, -2, -2", "SHARP, 1, 1", "DOUBLE_SHARP, 2, 2", })
    void testRankAnIntegerValue(Alter alter, int rank, int integer) {
        assertEquals(rank, alter.rank());
        assertEquals(integer, alter.asInt());
    }
}
