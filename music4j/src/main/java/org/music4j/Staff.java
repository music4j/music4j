package org.music4j;

import java.util.List;

import org.music4j.simple.ArrayStaff;

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
        return new ArrayStaff();
    }

}
