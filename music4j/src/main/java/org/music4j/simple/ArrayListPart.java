package org.music4j.simple;

import java.util.ArrayList;

import org.music4j.Bar;
import org.music4j.BarTime;
import org.music4j.Note;
import org.music4j.Part;
import org.music4j.Staff;
import org.music4j.Voice;
import org.music4j.utils.ForwardingList;

public class ArrayListPart extends ForwardingList<Staff> implements Part {

    public ArrayListPart() {
        super(new ArrayList<>());
    }

    @Override
    public Bar get(int staffNumber, int barNumber) {
        return get(staffNumber).get(barNumber);
    }

    @Override
    public Voice get(int staffNumber, int barNumber, int voiceNumber) {
        return get(staffNumber, barNumber).get(voiceNumber);
    }

    @Override
    public Note get(int staffNumber, int barNumber, int voiceNumber, BarTime time) {
        return get(staffNumber, barNumber, voiceNumber).get(time);
    }
}
