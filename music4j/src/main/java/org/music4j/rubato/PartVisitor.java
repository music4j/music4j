package org.music4j.rubato;

import org.music4j.Part;
import org.music4j.rubato.gen.RubatoParser.PartContext;
import org.music4j.rubato.gen.RubatoParser.StaffContext;

public class PartVisitor extends AbstractVisitor {

    public PartVisitor() {
        this(null);
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

    @Override
    protected Scope scope() {
        return Scope.PART;
    }
}
