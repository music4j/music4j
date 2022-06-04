package org.music4j.grammar;

import org.music4j.Staff;
import org.music4j.grammar.gen.RubatoParser.StaffContext;

public class StaffVisitor extends AbstractVisitor {

    public StaffVisitor() {
    }

    public StaffVisitor(PartVisitor partVisitor) {
        super(partVisitor);
    }

    @Override
    public Staff visitStaff(StaffContext ctx) {
        return null;
    }
}
