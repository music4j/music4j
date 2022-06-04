package org.music4j.grammar;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.music4j.BarTime;
import org.music4j.grammar.gen.RubatoParser.DurationContext;
import org.music4j.grammar.gen.RubatoParser.DurationFractionContext;
import org.music4j.grammar.gen.RubatoParser.DurationIntegerContext;
import org.music4j.grammar.gen.RubatoParser.DurationInvertedIntegerContext;

public class TimeVisitor extends AbstractVisitor {

    public TimeVisitor() {
    }

    public TimeVisitor(NoteVisitor noteVisitor) {
        super(noteVisitor);
    }

    public BarTime visitDuration(DurationContext ctx) {
        return (BarTime) visit(ctx);
    }

    @Override
    public BarTime visitDurationFraction(DurationFractionContext ctx) {
        TerminalNode num = ctx.INT(0);
        TerminalNode den = ctx.INT(1);
        return BarTime.of(Integer.parseInt(num.getText()), Integer.parseInt(den.getText()));
    }

    @Override
    public BarTime visitDurationInvertedInteger(DurationInvertedIntegerContext ctx) {
        TerminalNode den = ctx.INT();
        return BarTime.of(1, Integer.parseInt(den.getText()));
    }

    @Override
    public BarTime visitDurationInteger(DurationIntegerContext ctx) {
        TerminalNode num = ctx.INT();
        return BarTime.of(Integer.parseInt(num.getText()));
    }

}
