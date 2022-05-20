package org.music4j;

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

}
