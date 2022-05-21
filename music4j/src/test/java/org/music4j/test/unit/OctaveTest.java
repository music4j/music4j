package org.music4j.test.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.music4j.Pitch.Octave;

class OctaveTest {

    @ParameterizedTest
    @CsvSource({
        "SUBSUBCONTRA, -4, 0",
        "SUBCONTRA, -3, 12",
        "CONTRA, -2, 24",
        "GREAT, -1, 36",
        "SMALL, -0, 48",
        "ONE_LINED, 1, 60",
        "TWO_LINED, 2, 72",
        "THREE_LINED, 3, 84",
        "FOUR_LINED, 4, 96",
        "FIVE_LINED, 5, 108",
        "SIXTH_LINED, 6, 120", })
    void testRankAndIntegerNumber(Octave octave, int rank, int integer) {
        assertEquals(rank, octave.rank());
        assertEquals(integer, octave.asInt());
    }

}
