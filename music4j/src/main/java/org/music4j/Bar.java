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
     * Static factory for the bar interface. Returns an Bar equivalent to the given
     * string according to the Rubato grammar.
     *
     * @param string the given input
     * @throws IllegalArgumentException if the input cannot be processed
     */
    static Bar of(String string) {
        return ArrayListBar.parse(string);
    }

    /**
     * Returns the length of the bar.
     *
     * @return the length of the bar.
     */
    BarTime length();
}
