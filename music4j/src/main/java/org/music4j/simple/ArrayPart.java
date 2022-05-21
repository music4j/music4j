package org.music4j.simple;

import java.util.ArrayList;

import org.music4j.Part;
import org.music4j.Staff;

public class ArrayPart extends ForwardingList<Staff> implements Part {

    public ArrayPart() {
        super(new ArrayList<>());
    }
}
