package org.music4j.grammar;

import java.util.List;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.music4j.Pitch;
import org.music4j.Pitch.Alter;
import org.music4j.Pitch.Octave;
import org.music4j.Pitch.Step;
import org.music4j.grammar.gen.RubatoParser.AlterContext;
import org.music4j.grammar.gen.RubatoParser.AlterDoubleSharpContext;
import org.music4j.grammar.gen.RubatoParser.AlterFlatContext;
import org.music4j.grammar.gen.RubatoParser.AlterFlatFlatContext;
import org.music4j.grammar.gen.RubatoParser.AlterNaturalContext;
import org.music4j.grammar.gen.RubatoParser.AlterSharpContext;
import org.music4j.grammar.gen.RubatoParser.OctaveContext;
import org.music4j.grammar.gen.RubatoParser.PitchContext;
import org.music4j.grammar.token.DefaultOctave;
import org.music4j.grammar.token.OctaveMode;
import org.music4j.grammar.token.PreviousStep;

public class PitchVisitor extends AbstractVisitor {

    public PitchVisitor(NoteVisitor noteVisitor) {
        super(noteVisitor);
    }

    @Override
    public Pitch visitPitch(PitchContext ctx) {
        TerminalNode stepNode = ctx.STEP();
        AlterContext alterNode = ctx.alter();
        OctaveContext octaveNode = ctx.octave();

        Step step = Step.valueOf(stepNode.getText());
        Octave octave = octaveNode != null ? visitOctave(octaveNode) : Octave.SMALL;
        Alter alter = alterNode != null ? visitAlter(alterNode) : Alter.NATURAL;

        // If the parser is in relative mode the octave must be adjusted
        if (get(OctaveMode.class)) {
            int octaveShift = step.rank() - get(PreviousStep.class).rank() > 3 ? -1
                    : step.rank() - get(PreviousStep.class).rank() < -3 ? 1 : 0;
            octave = Octave.valueOf(octaveShift + octave.rank() + get(DefaultOctave.class).rank());
            set(DefaultOctave.class, octave);
            set(PreviousStep.class, step);
        }

        return Pitch.of(step, alter, octave);
    }

    /**
     * Returns a Octave to a octave context
     */
    public Octave visitOctave(OctaveContext octaveNode) {
        List<TerminalNode> linedNode = octaveNode.LINED();
        List<TerminalNode> commaNode = octaveNode.COMMA();
        return Octave.valueOf(linedNode.size() - commaNode.size());
    }

    /**
     * Returns a Alteration to a alter context
     */
    public Alter visitAlter(AlterContext alterNode) {
        return (Alter) visit(alterNode);
    }

    @Override
    public Alter visitAlterDoubleSharp(AlterDoubleSharpContext ctx) {
        return Alter.DOUBLE_SHARP;
    }

    @Override
    public Alter visitAlterFlat(AlterFlatContext ctx) {
        return Alter.FLAT;
    }

    @Override
    public Alter visitAlterFlatFlat(AlterFlatFlatContext ctx) {
        return Alter.FLAT_FLAT;
    }

    @Override
    public Alter visitAlterNatural(AlterNaturalContext ctx) {
        return Alter.NATURAL;
    }

    @Override
    public Alter visitAlterSharp(AlterSharpContext ctx) {
        return Alter.SHARP;
    }

}
