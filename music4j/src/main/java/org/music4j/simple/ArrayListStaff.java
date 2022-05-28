package org.music4j.simple;

import java.util.ArrayList;

import org.music4j.Bar;
import org.music4j.Staff;

public class ArrayListStaff extends ForwardingList<Bar> implements Staff {

    public ArrayListStaff() {
        super(new ArrayList<>());
    }

}
