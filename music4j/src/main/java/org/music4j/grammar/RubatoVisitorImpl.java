package org.music4j.grammar;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.antlr.v4.runtime.tree.TerminalNode;
import org.music4j.Bar;
import org.music4j.BarTime;
import org.music4j.Note;
import org.music4j.Part;
import org.music4j.Pitch;
import org.music4j.Pitch.Alter;
import org.music4j.Pitch.Octave;
import org.music4j.Pitch.Step;
import org.music4j.Score;
import org.music4j.Staff;
import org.music4j.Voice;
import org.music4j.grammar.gen.RubatoBaseVisitor;
import org.music4j.grammar.gen.RubatoParser.AlterContext;
import org.music4j.grammar.gen.RubatoParser.AlterDoubleSharpContext;
import org.music4j.grammar.gen.RubatoParser.AlterFlatContext;
import org.music4j.grammar.gen.RubatoParser.AlterFlatFlatContext;
import org.music4j.grammar.gen.RubatoParser.AlterNaturalContext;
import org.music4j.grammar.gen.RubatoParser.AlterSharpContext;
import org.music4j.grammar.gen.RubatoParser.BarContext;
import org.music4j.grammar.gen.RubatoParser.BarItemContext;
import org.music4j.grammar.gen.RubatoParser.BarItemNoteContext;
import org.music4j.grammar.gen.RubatoParser.BarSliceContext;
import org.music4j.grammar.gen.RubatoParser.DurationContext;
import org.music4j.grammar.gen.RubatoParser.DurationFractionContext;
import org.music4j.grammar.gen.RubatoParser.DurationIntegerContext;
import org.music4j.grammar.gen.RubatoParser.DurationInvertedIntegerContext;
import org.music4j.grammar.gen.RubatoParser.ModeOctaveAbsoluteContext;
import org.music4j.grammar.gen.RubatoParser.ModeOctaveRelativeContext;
import org.music4j.grammar.gen.RubatoParser.ModeTimeAbsoluteContext;
import org.music4j.grammar.gen.RubatoParser.ModeTimeAndOctaveAbsoluteContext;
import org.music4j.grammar.gen.RubatoParser.ModeTimeAndOctaveRelativeContext;
import org.music4j.grammar.gen.RubatoParser.ModeTimeRelativeContext;
import org.music4j.grammar.gen.RubatoParser.NoteChordContext;
import org.music4j.grammar.gen.RubatoParser.NoteContext;
import org.music4j.grammar.gen.RubatoParser.NoteRestContext;
import org.music4j.grammar.gen.RubatoParser.NoteSingleContext;
import org.music4j.grammar.gen.RubatoParser.OctaveContext;
import org.music4j.grammar.gen.RubatoParser.PartContext;
import org.music4j.grammar.gen.RubatoParser.PitchContext;
import org.music4j.grammar.gen.RubatoParser.ScoreContext;
import org.music4j.grammar.gen.RubatoParser.StaffContext;
import org.music4j.grammar.gen.RubatoParser.StaffVoiceContext;
import org.music4j.grammar.gen.RubatoParser.StaffVoiceEmptyContext;
import org.music4j.grammar.gen.RubatoParser.StaffVoiceNonEmptyContext;
import org.music4j.grammar.gen.RubatoParser.VoiceContext;
import org.music4j.grammar.gen.RubatoVisitor;
import org.music4j.grammar.token.AbstractParserToken;
import org.music4j.grammar.token.DefaultDuration;
import org.music4j.grammar.token.DefaultOctave;
import org.music4j.grammar.token.OctaveMode;
import org.music4j.grammar.token.PreviousStep;
import org.music4j.grammar.token.TimeMode;
import org.music4j.utils.Container;

/**
 * Implementation of a {@link RubatoVisitor} which parses rubato files to
 * appropriate java objects. Note that the generic type is Object since there is
 * no common class other than Object for all Java Objects of the Music4j API.
 * Most of the visitor methods will covariantly return an object of the
 * appropriate type.
 */
public class RubatoVisitorImpl extends RubatoBaseVisitor<Object> implements RubatoVisitor<Object> {

    /**
     * Container stores ParserToken.
     */
    private final Container<AbstractParserToken<?>> container;

    public RubatoVisitorImpl() {
        container = new Container<>();
        container.add(new DefaultDuration());
        container.add(new DefaultOctave());
        container.add(new TimeMode());
        container.add(new OctaveMode());
        container.add(new PreviousStep());

        set(TimeMode.class, false);
        set(OctaveMode.class, false);
    }

    private <E, T extends AbstractParserToken<E>> E get(Class<T> key) {
        T token = container.get(key);
        return token == null ? null : token.get();
    }

    private <E, T extends AbstractParserToken<E>> void set(Class<T> key, E value) {
        T token = container.get(key);
        if (token != null) {
            token.accept(value);
        }
    }

    @Override
    public Score visitScore(ScoreContext ctx) {
        // Create score
        Score score = Score.of();

        // Visit score Settings rules
        ctx.scoreSettings().forEach(this::visit);

        // Parse score
        for (PartContext partCtx : ctx.part()) {
            score.add(visitPart(partCtx));
        }
        return score;
    }

    @Override
    public Part visitPart(PartContext ctx) {
        Part part = Part.of();
        for (StaffContext staffCtx : ctx.staff()) {
            part.add(visitStaff(staffCtx));
        }
        return part;
    }

    @Override
    public Staff visitStaff(StaffContext ctx) {
        Staff staff = Staff.of();

        // Each staff voices represents a single voice which spans over the whole staff
        for (StaffVoiceContext staffVoiceCtx : ctx.staffVoice()) {
            set(DefaultDuration.class, BarTime.of(1));
            set(PreviousStep.class, Step.C);
            set(DefaultOctave.class, Octave.SMALL);
            @SuppressWarnings("unchecked")
            List<Voice> listOfVoice = (List<Voice>) visit(staffVoiceCtx);
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
    public List<Voice> visitStaffVoiceEmpty(StaffVoiceEmptyContext ctx) {
        return Collections.emptyList();
    }

    @Override
    public List<Voice> visitStaffVoiceNonEmpty(StaffVoiceNonEmptyContext ctx) {
        return ctx.voice().stream().map(this::visitVoice).collect(Collectors.toList());
    }

    @Override
    public Bar visitBar(BarContext ctx) {
        Bar bar = Bar.of();
        for (BarSliceContext sliceCtx : ctx.barSlice()) {
            Voice voice = Voice.of();
            BarTime counter = BarTime.ZERO;
            for (BarItemContext barItem : sliceCtx.barItem()) {
                if (barItem instanceof BarItemNoteContext) {
                    BarItemNoteContext ctxBarItemNoteCtx = (BarItemNoteContext) barItem;
                    Note note = visitNote(ctxBarItemNoteCtx.note());
                    voice.put(counter, note);
                    counter = counter.plus(note.getDuration());
                }
            }
            bar.add(voice);
        }
        return bar;
    }

    @Override
    public Voice visitVoice(VoiceContext ctx) {
        BarTime counter = BarTime.ZERO;
        Voice voice = Voice.of();
        for (NoteContext noteCtx : ctx.note()) {
            Note note = visitNote(noteCtx);
            voice.put(counter, note);
            counter = counter.plus(note.getDuration());
        }
        return voice;
    }

    /**
     * Returns a Note to a NoteContext.
     */
    public Note visitNote(NoteContext ctx) {
        Note note = (Note) visit(ctx);
        if (get(TimeMode.class)) {
            set(DefaultDuration.class, note.getDuration());
        }
        return note;
    }

    @Override
    public Note visitNoteSingle(NoteSingleContext ctx) {
        BarTime duration = ctx.duration() != null ? visitDuration(ctx.duration()) : get(DefaultDuration.class);
        Note note = Note.of(duration);
        note.add(visitPitch(ctx.pitch()));
        return note;
    }

    @Override
    public Note visitNoteChord(NoteChordContext ctx) {
        BarTime duration = ctx.duration() != null ? visitDuration(ctx.duration()) : get(DefaultDuration.class);
        Note note = Note.of(duration);
        ctx.pitch().forEach(pitchCtx -> note.add(visitPitch(pitchCtx)));
        return note;
    }

    @Override
    public Note visitNoteRest(NoteRestContext ctx) {
        BarTime duration = ctx.duration() != null ? visitDuration(ctx.duration()) : get(DefaultDuration.class);
        return Note.of(duration);
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

    /**
     * Returns a BarTime to a Duration context object
     */
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

    @Override
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
    public Object visitModeOctaveAbsolute(ModeOctaveAbsoluteContext ctx) {
        set(OctaveMode.class, false);
        return super.visitModeOctaveAbsolute(ctx);
    }

    @Override
    public Object visitModeOctaveRelative(ModeOctaveRelativeContext ctx) {
        set(OctaveMode.class, true);
        return super.visitModeOctaveRelative(ctx);
    }

    @Override
    public Object visitModeTimeAndOctaveAbsolute(ModeTimeAndOctaveAbsoluteContext ctx) {
        set(TimeMode.class, false);
        set(OctaveMode.class, false);
        return super.visitModeTimeAndOctaveAbsolute(ctx);
    }

    @Override
    public Object visitModeTimeAndOctaveRelative(ModeTimeAndOctaveRelativeContext ctx) {
        set(TimeMode.class, true);
        set(OctaveMode.class, true);
        return super.visitModeTimeAndOctaveRelative(ctx);
    }
}
