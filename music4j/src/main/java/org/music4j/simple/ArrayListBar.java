package org.music4j.simple;

import java.util.ArrayList;

import org.music4j.Bar;
import org.music4j.BarTime;
import org.music4j.Voice;
import org.music4j.utils.ForwardingList;

public class ArrayListBar extends ForwardingList<Voice> implements Bar {

    public ArrayListBar() {
        super(new ArrayList<>());
    }

    @Override
    public BarTime length() {
        return stream().map(Voice::length).max(BarTime::compareTo).orElse(BarTime.ZERO);
    }

}
