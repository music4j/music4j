package org.music4j.simple;

import java.util.ArrayList;

import org.music4j.Bar;
import org.music4j.Staff;

public class ArrayStaff extends ForwardingList<Bar> implements Staff {

    public ArrayStaff() {
        super(new ArrayList<>());
    }

}
