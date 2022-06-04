package org.music4j.grammar;

import org.music4j.Score;
import org.music4j.grammar.gen.RubatoParser.ModeOctaveAbsoluteContext;
import org.music4j.grammar.gen.RubatoParser.ModeOctaveRelativeContext;
import org.music4j.grammar.gen.RubatoParser.ModeTimeAbsoluteContext;
import org.music4j.grammar.gen.RubatoParser.ModeTimeAndOctaveAbsoluteContext;
import org.music4j.grammar.gen.RubatoParser.ModeTimeAndOctaveRelativeContext;
import org.music4j.grammar.gen.RubatoParser.ModeTimeRelativeContext;
import org.music4j.grammar.gen.RubatoParser.PartContext;
import org.music4j.grammar.gen.RubatoParser.ScoreContext;
import org.music4j.grammar.token.OctaveMode;
import org.music4j.grammar.token.TimeMode;

public class ScoreVisitor extends AbstractVisitor {

    public ScoreVisitor() {
        // Set default configurations
        add(new OctaveMode(), false);
        add(new TimeMode(), false);
    }

    /**
     * Method calls visitors on lower level
     */
    @Override
    public Score visitScore(ScoreContext ctx) {
        // Create score
        Score score = Score.of();

        // Visit score Settings rules
        ctx.scoreSettings().forEach(this::visit);

        // Parse score
        for (PartContext partCtx : ctx.part()) {
            PartVisitor partVisitor = new PartVisitor(this);
            score.add(partVisitor.visitPart(partCtx));
        }
        return score;
    }

    @Override
    public Object visitModeOctaveRelative(ModeOctaveRelativeContext ctx) {
        set(OctaveMode.class, true);
        return super.visitModeOctaveRelative(ctx);
    }

    @Override
    public Object visitModeOctaveAbsolute(ModeOctaveAbsoluteContext ctx) {
        set(OctaveMode.class, false);
        return super.visitModeOctaveAbsolute(ctx);
    }

    public Object visitModeTimeAbsolute(ModeTimeAbsoluteContext ctx) {
        set(TimeMode.class, false);
        return super.visitModeTimeAbsolute(ctx);
    }

    @Override
    public Object visitModeTimeRelative(ModeTimeRelativeContext ctx) {
        set(TimeMode.class, true);
        return super.visitModeTimeRelative(ctx);
    }

    @Override
    public Object visitModeTimeAndOctaveRelative(ModeTimeAndOctaveRelativeContext ctx) {
        set(OctaveMode.class, true);
        set(TimeMode.class, true);
        return super.visitModeTimeAndOctaveRelative(ctx);
    }

    @Override
    public Object visitModeTimeAndOctaveAbsolute(ModeTimeAndOctaveAbsoluteContext ctx) {
        set(OctaveMode.class, false);
        set(TimeMode.class, false);
        return super.visitModeTimeAndOctaveAbsolute(ctx);
    }
}
