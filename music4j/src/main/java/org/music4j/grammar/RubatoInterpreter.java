package org.music4j.grammar;

import java.util.List;

import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.music4j.Pitch;
import org.music4j.Pitch.Alter;
import org.music4j.Pitch.Octave;
import org.music4j.Pitch.Step;
import org.music4j.Score;
import org.music4j.grammar.gen.RubatoBaseVisitor;
import org.music4j.grammar.gen.RubatoParser;
import org.music4j.grammar.gen.RubatoParser.AlterContext;
import org.music4j.grammar.gen.RubatoParser.OctaveContext;
import org.music4j.grammar.gen.RubatoParser.PitchContext;
import org.music4j.grammar.gen.RubatoParser.ScoreContext;
import org.music4j.grammar.gen.RubatoVisitor;

/**
 * Implementation of a {@link RubatoVisitor} which parses rubato files to
 * appropriate java objects. Note that the generic type is Object since there is
 * no common class other than Object for all Java Objects of the Music4j API.
 * Most of the visitor methods will covariantly return an object of the
 * appropriate type.
 */
public class RubatoInterpreter extends RubatoBaseVisitor<Object> implements RubatoVisitor<Object> {

    @Override
    public Score visitScore(ScoreContext ctx) {
        return Score.of();
    }

    /**
     * Returns a Pitch to a pitch context
     */
    @Override
    public Pitch visitPitch(PitchContext ctx) {
        TerminalNode stepNode = ctx.STEP();
        AlterContext alterNode = ctx.alter();
        OctaveContext octaveNode = ctx.octave();

        Step step = Step.valueOf(stepNode.getText());
        Octave octave;
        Alter alter;
        octave = octaveNode != null ? visitOctave(octaveNode) : Octave.SMALL;
        alter = alterNode != null ? visitAlter(alterNode) : Alter.NATURAL;

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
        Token alterToken = alterNode.alterToken;
        if (alterToken.getType() == RubatoParser.FLAT) {
            return Alter.FLAT;
        } else if (alterToken.getType() == RubatoParser.FLAT_FLAT) {
            return Alter.FLAT_FLAT;
        } else if (alterToken.getType() == RubatoParser.SHARP) {
            return Alter.SHARP;
        } else if (alterToken.getType() == RubatoParser.DOUBLE_SHARP) {
            return Alter.DOUBLE_SHARP;
        } else if (alterToken.getType() == RubatoParser.NATURAL) {
            return Alter.NATURAL;
        } else {
            return Alter.NATURAL;
        }
    }

}
