package org.music4j.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringOutput {

    private final static Pattern WHITESPACE_PATTERN = Pattern.compile("(\\s)+");

    private StringOutput() {
        //Utilities class is not instantiated
    }

    /**
     * Adjusts the indentation of each line of this string based on the value of
     * {@code n}, and normalizes line termination characters.
     * <p>
     * This string is conceptually separated into lines using
     * {@link String#lines()}. Each line is then adjusted as described below
     * and then suffixed with a line feed {@code "\n"} (U+000A). The resulting
     * lines are then concatenated and returned.
     * <p>
     * If {@code n > 0} then {@code n} spaces (U+0020) are inserted at the
     * beginning of each line.
     * <p>
     * If {@code n < 0} then up to {@code n}
     * {@linkplain Character#isWhitespace(int) white space characters} are removed
     * from the beginning of each line. If a given line does not contain
     * sufficient white space then all leading
     * {@linkplain Character#isWhitespace(int) white space characters} are removed.
     * Each white space character is treated as a single character. In
     * particular, the tab character {@code "\t"} (U+0009) is considered a
     * single character; it is not expanded.
     * <p>
     * If {@code n == 0} then the line remains unchanged. However, line
     * terminators are still normalized.
     *
     * @param n  number of leading
     *           {@linkplain Character#isWhitespace(int) white space characters}
     *           to add or remove
     *
     * @return string with indentation adjusted and line endings normalized
     *
     * @see String#lines()
     * @see String#isBlank()
     * @see Character#isWhitespace(int)
     */
    public static String indent(String input, int n) {
        if (input.isEmpty()) {
            return "";
        }
        Stream<String> stream = input.lines();
        if (n > 0) {
            final String spaces = " ".repeat(n);
            stream = stream.map(s -> spaces + s);
        } else if (n == Integer.MIN_VALUE) {
            stream = stream.map(s -> s.stripLeading());
        } else if (n < 0) {
            stream = stream.map(s -> s.substring(Math.min(-n, indexOfNonWhitespace(s))));
        }
        return stream.collect(Collectors.joining("\n", "", "\n"));
    }

    private static int indexOfNonWhitespace(String input) {
        Matcher matcher = WHITESPACE_PATTERN.matcher(input);
        matcher.find();
        return matcher.end();
    }
}
