package org.music4j;

import java.util.List;

import org.music4j.simple.ArrayListBar;

/**
 * A Bar is a ordered list of {@linkplain Voice Voices} and an {@link Frame}. We
 * call the Voices the Content of the bar and the Frame the skeleton or frame of
 * the Bar which provides additional information of the bar which does not
 * affect the content of the bar.
 */
public interface Bar extends List<Voice> {

    /**
     * Static factory for the bar interface. Returns an empty bar.
     *
     * @return an empty bar.
     */
    static Bar of() {
        return new ArrayListBar();
    }

    /**
     * Returns the length of the bar.
     *
     * @return the length of the bar.
     */
    BarTime length();
}
