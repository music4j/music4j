package org.music4j.grammar;

import java.util.List;

import org.music4j.Bar;
import org.music4j.Staff;
import org.music4j.Voice;
import org.music4j.grammar.gen.RubatoParser.StaffContext;
import org.music4j.grammar.gen.RubatoParser.StaffVoiceContext;

public class StaffVisitor extends AbstractVisitor {

    public StaffVisitor() {
        this(null);
    }

    public StaffVisitor(PartVisitor partVisitor) {
        super(partVisitor);
    }

    @Override
    public Staff visitStaff(StaffContext ctx) {
        Staff staff = Staff.of();

        // Each staff voices represents a single voice which spans over the whole staff
        for (StaffVoiceContext staffVoiceCtx : ctx.staffVoice()) {

            StaffVoiceVisitor staffVoiceVisitor = new StaffVoiceVisitor(this);
            List<Voice> listOfVoice = staffVoiceVisitor.visitStaffVoice(staffVoiceCtx);
            for (int i = 0; i < listOfVoice.size(); i++) {
                if (i + 1 > staff.size()) {
                    Bar bar = Bar.of();
                    bar.add(listOfVoice.get(i));
                    staff.add(bar);
                } else {
                    Bar bar = staff.get(i);
                    bar.add(listOfVoice.get(i));
                }
            }
        }
        return staff;
    }

    @Override
    protected Scope scope() {
        return Scope.STAFF;
    }
}
