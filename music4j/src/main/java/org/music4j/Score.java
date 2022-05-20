package org.music4j;

import java.io.File;
import java.io.IOException;

import org.music4j.simple.ArrayScore;

/**
 * A Score is an ordered list of Parts. This interface defines a root object for
 * all musical objects in the api.
 */
public interface Score {

    /**
     * Returns an empty Score.
     *
     * @return an empty Score.
     */
    static Score of() {
        return new ArrayScore();
    }

    /**
     * Static factory that parses the given file input to a score according to the
     * Rubato grammar.
     *
     * @param file the file containing the input.
     * @return a score equivalent to the given file input.
     * @throws IllegalArgumentException if the input cannot be processed.
     * @throws IOException              if the file cannot be found.
     */
    static Score readFile(File file) throws IOException {
        return ArrayScore.readFile(file);
    }

}
