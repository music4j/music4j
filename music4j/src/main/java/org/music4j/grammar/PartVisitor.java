package org.music4j.grammar;

import org.music4j.Part;
import org.music4j.grammar.gen.RubatoParser.PartContext;
import org.music4j.grammar.gen.RubatoParser.StaffContext;
import org.music4j.grammar.token.OctaveMode;
import org.music4j.grammar.token.TimeMode;

public class PartVisitor extends AbstractVisitor {

    public PartVisitor() {
        // Set default configurations
        add(new OctaveMode(), false);
        add(new TimeMode(), false);
    }

    public PartVisitor(ScoreVisitor scoreVisitor) {
        super(scoreVisitor);
    }

    @Override
    public Part visitPart(PartContext ctx) {
        Part part = Part.of();
        for (StaffContext staffCtx : ctx.staff()) {
            StaffVisitor staffVisitor = new StaffVisitor(this);
            part.add(staffVisitor.visitStaff(staffCtx));
        }
        return part;
    }
}
