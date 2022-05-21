package org.music4j;

import java.util.List;

import org.music4j.simple.ArrayPart;

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
        return new ArrayPart();
    }

}
