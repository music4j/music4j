package org.music4j;

import java.util.Map;
import java.util.NavigableMap;
import java.util.SortedMap;

import org.music4j.simple.TreeMapVoice;

/**
 * A Voice is a NavigableMap which maps {@linkplain BarTime BarTimes} to
 * {@linkplain Note Notes} As per contract of a map a specific BarTime can only
 * be mapped to one such Note.
 * <p>
 * Special constraints are put upon all Notes with a duration {@code d} that are
 * to be added into a Voice at any given BarTime {@code t} (Note that we use the
 * {@code +} and {@code -} operator as abbreviations for the
 * {@link BarTime#plus(BarTime)} and {@link BarTime#minus(BarTime)} methods):
 * <ul>
 * <li>Notes can only be associated to keys in the interval {@code [0, ∞]}. Here
 * {@code ∞} denotes the BarTime {@link BarTime#MAX}</li>
 * <li>After adding the note to the voice no other note can be added to the
 * voice for all BarTimes {@code t} in the interval {@code (t, t + d)}. Interval
 * boundaries are excluded from this rule.</li>
 * <li>If the voice has a higher key {@code t'} that is higher than t. Then in
 * accordance with the second constraint the BarTime {@code t + d} needs to be
 * less or equal than {@code t'}.</li>
 * </ul>
 * We define a Voice to be consistently packed or well-packed, if
 * {@code lasting(t) == next(t)} for every {@code t} in the interval [0,
 * {@link #length()}].
 * <p>
 * Additionally the interface offers several method of the {@link NavigableMap}
 * and {@link SortedMap} interface which covariantly return a Voice object.
 */
public interface Voice extends NavigableMap<BarTime, Note> {

    /**
     * Static factory for the voice interface. Returns an empty voice.
     *
     * @return an empty voice.
     */
    static Voice of() {
        return new TreeMapVoice();
    }

    /**
     * Static factory for the voice interface. Returns an Voice containing the
     * entries of the specified map if these fit all in the voice. Otherwise an
     * VoicePackException is thrown.
     *
     * @param a map whose entries are to be added to the voice.
     * @return a voice containing all entries of the given map.
     * @throws VoicePackException if the given entries of the map violate the
     *                            conditions placed on a voice.
     */
    static Voice of(Map<BarTime, Note> voice) {
        return new TreeMapVoice(voice);
    }

    /**
     * Static factory for the voice interface. Returns an Voice equivalent to the
     * given string according to the Rubato grammar.
     *
     * @param string the given input.
     * @throws IllegalArgumentException if the input cannot be processed.
     */
    static Voice of(String string) {
        return TreeMapVoice.parse(string);
    }

    /**
     * Associates the key with the given Note if and only if the method
     * {@link #fits(BarTime, Measurable)} returns true for the given key and note.
     * If there is already a Note associated with the given key as per contract of a
     * map the old value is replaced by the new one and the previous mapping is
     * returned.
     *
     * @param key   the BarTime with which the specified Note is to be associated
     * @param value note that is to be associated with the specified key
     * @return the previous note associated with the {@code key} or {@code null} if
     *         there is none.
     * @throws NullPointerException          if the given key or item is
     *                                       {@code null}
     * @throws IllegalArgumentException      if the given key is less than zero.
     * @throws VoicePackException            If the insertion of the given note
     *                                       would result in an inconsistently
     *                                       packed voice.
     * @throws UnsupportedOperationException if the voice does not support the
     *                                       Operation.
     * @see #fits(BarTime, Measurable)
     */
    Note put(BarTime key, Note value);

    /**
     * Returns true if a Note with the same measurement of the given Measurable
     * value would fit in the voice. A Note of the same duration as the the given
     * measurable value fits into the voice if and only if:
     * <li>The key is non negative.</li>
     * <li>No sooner note has still a lasting effect at the BarTime of the given key
     * ({@code lasting(key) == (BarTime.ZERO)}).</li>
     * <li>The duration of the value {@code d} is less than the duration in which
     * the next item occurs ({@code value.isLessOrEqual(next(key)))}
     *
     * @param key   the time at which the value would be added to the voice
     * @param value a measurable object that provides as .
     * @throws NullPointerException if the key or value is null.
     * @return true if the Measurable item would fit into the BarMap otherwise
     *         false.
     * @see #lastingFor(BarTime)
     * @see #nextIn(BarTime)
     */
    boolean fits(BarTime key, Measurable value);

    /**
     * Returns the duration for which a lower note has still a lasting effect in the
     * voice. Any note that is added to a voice prohibits any further entries for
     * the measured duration of the note. Therefore this methods imposes the
     * condition for all note that are to be added to the voice that
     * {@code lasting(key) == BarTime.ZERO}.
     *
     * @param key the time that is checked for any lasting impact of a lower note.
     * @throws NullPointerException if the key is null.
     * @return if {@code l} is the BarTime of the lower key as given by
     *         {@link #lowerEntry(BarTime)} and {@code d} the duration of the
     *         associated Measurable object then:
     *         </p>
     *         {@code max(0, l + d - key)}
     */
    BarTime lastingFor(BarTime key);

    /**
     * The duration in which the next note is occurring or {@link BarTime#MAX} if no
     * higher entry is present.
     *
     * @param key the time that is checked for any higher items.
     * @throws NullPointerException if the key is null.
     * @return the duration in which the next note is about to occur.
     */
    BarTime nextIn(BarTime key);

    /**
     * Returns the length of the voice as BarTime. The length equals exactly the
     * last given key plus the measurement of the associated note or zero if the map
     * is empty.
     *
     * @return the length of the voice as BarTime.
     */
    BarTime length();

    /**
     * Returns a modifiable view of a submap as voice.
     *
     * {@inheritDoc}
     */
    Voice subMap(BarTime fromKey, boolean fromInclusive, BarTime toKey, boolean toInclusive);

    /**
     * Returns a Voice in reversed order.
     *
     * {@inheritDoc}
     */
    Voice descendingMap();

    /**
     * Returns a Voice which ranges from the first key up to {@code toKey}.
     *
     * {@inheritDoc}
     */
    Voice headMap(BarTime toKey, boolean inclusive);

    /**
     * Returns a Voice which ranges from the {@code fromKey} up to the last key of
     * the map.
     *
     * {@inheritDoc}
     */
    Voice tailMap(BarTime fromKey, boolean inclusive);

    /**
     * {@inheritDoc}
     *
     * <p>
     * Equivalent to {@code subMap(fromKey, true, toKey, false)}.
     *
     * @throws ClassCastException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    Voice subMap(BarTime fromKey, BarTime toKey);

    /**
     * {@inheritDoc}
     *
     * <p>
     * Equivalent to {@code headMap(toKey, false)}.
     *
     * @throws ClassCastException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    Voice headMap(BarTime toKey);

    /**
     * {@inheritDoc}
     *
     * <p>
     * Equivalent to {@code tailMap(fromKey, true)}.
     *
     * @throws ClassCastException       {@inheritDoc}
     * @throws NullPointerException     {@inheritDoc}
     * @throws IllegalArgumentException {@inheritDoc}
     */
    Voice tailMap(BarTime fromKey);
}
