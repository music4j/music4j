package org.music4j;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.music4j.simple.ArrayListScore;

/**
 * A Score is an ordered list of Parts. This interface defines a root object for
 * all musical objects in the api.
 */
public interface Score extends List<Part> {

    /**
     * Returns an empty Score.
     *
     * @return an empty Score.
     */
    static Score of() {
        return new ArrayListScore();
    }

    /**
     * Returns an empty Score.
     *
     * @return an empty Score.
     */
    static Score of(String input) {
        return ArrayListScore.parse(input);
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
        return ArrayListScore.readFile(file);
    }

    /**
     * Gets the staffs with the given staffNumber from the part with the given
     * partNumber.
     *
     * @param partNumber  the number of the part
     * @param staffNumber the number of the staff
     * @return the requested staff.
     */
    Staff get(int partNumber, int staffNumber);

    /**
     * Get the bar with the given barNumber form the staff with the given
     * staffNumber and the part with the given partNumber.
     *
     * @param partNumber  the number of the part.
     * @param staffNumber the number of the staff.
     * @param barNumber   the number of the bar.
     * @return the requested bar.
     */
    Bar get(int partNumber, int staffNumber, int barNumber);

    /**
     * Get the Voice with the given voiceNumber from the staff, part,and bar with
     * the respective number.
     *
     * @param partNumber  the number of the part.
     * @param staffNumber the number of the staff.
     * @param barNumber   the number of the bar.
     * @param voiceNumber the number of the voice.
     * @return the requested voice.
     */
    Voice get(int partNumber, int staffNumber, int barNumber, int voiceNumber);

    /**
     * Get the Note at the given time from the staff, part, bar and Voice with
     * the respective number.
     *
     * @param partNumber  the number of the part.
     * @param staffNumber the number of the staff.
     * @param barNumber   the number of the bar.
     * @param voiceNumber the number of the voice.
     * @return the requested voice.
     */
    Note get(int partNumber, int staffNumber, int barNumber, int voiceNumber, BarTime time);

}
