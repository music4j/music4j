package org.music4j;

import java.util.List;

import org.music4j.simple.ArrayListStaff;

/**
 * A Staff is a ordered collection of {@linkplain Bar Bars}.
 */
public interface Staff extends List<Bar> {

    /**
     * Static factory for the Staff interface. Returns an empty staff.
     *
     * @return an empty staff.
     */
    static Staff of() {
        return new ArrayListStaff();
    }

    /**
     * Get the Voice with the given voiceNumber from the bar with
     * the respective number.
     *
     * @param barNumber   the number of the bar.
     * @param voiceNumber the number of the voice.
     * @return the requested voice.
     */
    Voice get(int barNumber, int voiceNumber);

    /**
     * Get the Note at the given time from the staff, bar and Voice with
     * the respective number.
     *
     * @param barNumber   the number of the bar.
     * @param voiceNumber the number of the voice.
     * @return the requested voice.
     */
    Note get(int barNumber, int voiceNumber, BarTime time);

}
