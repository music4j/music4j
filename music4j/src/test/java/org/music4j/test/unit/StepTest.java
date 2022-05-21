package org.music4j.test.unit;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.music4j.Pitch.Step;

class StepTest {

    @ParameterizedTest
    @DisplayName("Test Rank and integer number")
    @CsvSource({ "C, 0, 0", "D, 1, 2", "E, 2, 4", "F, 3, 5", "G, 4, 7", "A, 5, 9", "B, 6, 11" })
    void stepRankAndIntegerNumber(Step step, int rank, int rootDistance) {
        assertEquals(rank, step.rank());
        assertEquals(rootDistance, step.asInt());
    }
}
