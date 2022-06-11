package org.music4j;

import java.util.List;

import org.music4j.simple.ArrayListPart;

/**
 * A Part is a ordered Collection of {@linkplain Staff Staffs}
 */
public interface Part extends List<Staff> {

    /**
     * Static factory for the part interface. Returns an empty part.
     *
     * @return an empty part.
     */
    static Part of() {
        return new ArrayListPart();
    }

    /**
     * Get the bar with the given barNumber form the staff with the given
     * staffNumber.
     *
     * @param staffNumber the number of the staff.
     * @param barNumber   the number of the bar.
     * @return the requested bar.
     */
    Bar get(int staffNumber, int barNumber);

    /**
     * Get the Voice with the given voiceNumber from the staff and bar with
     * the respective number.
     *
     * @param staffNumber the number of the staff.
     * @param barNumber   the number of the bar.
     * @param voiceNumber the number of the voice.
     * @return the requested voice.
     */
    Voice get(int staffNumber, int barNumber, int voiceNumber);

    /**
     * Get the Note at the given time from the staff, bar and Voice with
     * the respective number.
     *
     * @param staffNumber the number of the staff.
     * @param barNumber   the number of the bar.
     * @param voiceNumber the number of the voice.
     * @return the requested voice.
     */
    Note get(int staffNumber, int barNumber, int voiceNumber, BarTime time);

}
