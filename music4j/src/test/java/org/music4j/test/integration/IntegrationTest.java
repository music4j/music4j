package org.music4j.test.integration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.jupiter.api.Test;
import org.music4j.Bar;
import org.music4j.Part;
import org.music4j.Score;
import org.music4j.Staff;
import org.music4j.Voice;

class IntegrationTest {

    /**
     * Score with no part
     */
    @Test
    void readEmptyScore() throws IOException, URISyntaxException {
        URL filePath = getClass().getResource("001-emptyScore.rubato");
        File file = new File(filePath.toURI());
        Score emptyScore = Score.readFile(file);
        assertNotNull(emptyScore);
    }

    /**
     * Score with two empty parts
     */
    @Test
    void readScoreWithParts() throws IOException, URISyntaxException {
        URL filePath = getClass().getResource("002-scoreWithParts.rubato");
        File file = new File(filePath.toURI());
        Score score = Score.readFile(file);
        assertEquals(2, score.size());
    }

    /**
     * Score with two two parts which each has different number of staffs
     */
    @Test
    void readScoreWithStaffs() throws IOException, URISyntaxException {
        URL filePath = getClass().getResource("003-scoreWithStaff.rubato");
        File file = new File(filePath.toURI());
        Score score = Score.readFile(file);
        Part first = score.get(0);
        Part second = score.get(1);
        assertEquals(1, first.size());
        assertEquals(2, second.size());
    }

    /**
     * Score with two two bars
     */
    @Test
    void readTwoBars() throws IOException, URISyntaxException {
        URL filePath = getClass().getResource("004-CMajorScale.rubato");
        File file = new File(filePath.toURI());
        Score score = Score.readFile(file);
        Part part = score.get(0);
        Staff staff = part.get(0);
        assertEquals(2, staff.size());
        Voice first = staff.get(0).get(0);
        Voice second = staff.get(1).get(0);
        assertEquals(Voice.of("C' D' E' F'"), first);
        assertEquals(Voice.of("G' A' B' C''"), second);
    }

    /**
     * Score with two two bars
     */
    @Test
    void readBarWithTwoVoices() throws IOException, URISyntaxException {
        URL filePath = getClass().getResource("005-MultipleVoices.rubato");
        File file = new File(filePath.toURI());
        Score score = Score.readFile(file);
        Part part = score.get(0);
        Staff staff = part.get(0);

        //First bar
        Bar first = staff.get(0);
        assertEquals(Voice.of("C' D' E' F'"), first.get(0));
        assertEquals(Voice.of("E'/2 G'/2 F'/2 G'/2 C'' B'"), first.get(1));

        //Second bar
        Bar second = staff.get(1);
        assertEquals(Voice.of("G' A' B' C''"), second.get(0));
        assertEquals(Voice.of("G4"), second.get(1));
    }

    /**
     * Score with relative time mode parsing
     */
    @Test
    void readWithRelativeTimeMode() throws IOException, URISyntaxException {
        URL filePath = getClass().getResource("006-RelativeTimeMode.rubato");
        File file = new File(filePath.toURI());
        Score score = Score.readFile(file);
        Part part = score.get(0);
        Staff staff = part.get(0);

        //First bar
        Bar first = staff.get(0);
        assertEquals(Voice.of("C' D' E' F'"), first.get(0));
        assertEquals(Voice.of("E'/2 G'/2 F'/2 G'/2 C'' B'"), first.get(1));

        //Second bar
        Bar second = staff.get(1);
        assertEquals(Voice.of("G' A' B' C''"), second.get(0));
        assertEquals(Voice.of("G4"), second.get(1));
    }

    /**
     * Score with relative time mode parsing
     */
    @Test
    void readWithRelativeOctaveMode() throws IOException, URISyntaxException {
        URL filePath = getClass().getResource("007-RelativeOctaveMode.rubato");
        File file = new File(filePath.toURI());
        Score actual = Score.readFile(file);
        Score expected = Score.of("Score{ Part { Staff { C' D' C' E' | C' F' C' G' | C' A' C' B' | C''4 | G'''2 F'2 }}}");
        assertEquals(expected, actual);
    }
}
