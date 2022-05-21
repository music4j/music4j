package org.music4j.test.integration;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.junit.jupiter.api.Test;
import org.music4j.Score;

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
}
