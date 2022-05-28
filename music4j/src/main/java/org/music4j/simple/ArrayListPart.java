package org.music4j.simple;

import java.util.ArrayList;

import org.music4j.Part;
import org.music4j.Staff;

public class ArrayListPart extends ForwardingList<Staff> implements Part {

    public ArrayListPart() {
        super(new ArrayList<>());
    }
}
