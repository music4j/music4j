package org.music4j.grammar;

import org.music4j.BarTime;
import org.music4j.Note;
import org.music4j.grammar.gen.RubatoParser.NoteChordContext;
import org.music4j.grammar.gen.RubatoParser.NoteContext;
import org.music4j.grammar.gen.RubatoParser.NoteRestContext;
import org.music4j.grammar.gen.RubatoParser.NoteSingleContext;
import org.music4j.grammar.gen.RubatoParser.NoteSuffixTieContext;
import org.music4j.grammar.token.DefaultDuration;
import org.music4j.grammar.token.NoteTieEnd;
import org.music4j.grammar.token.NoteTieStart;
import org.music4j.grammar.token.TimeMode;

public class NoteVisitor extends AbstractVisitor {

    public NoteVisitor() {
        this(null);
    }

    public NoteVisitor(VoiceVisitor voiceVisitor) {
        super(voiceVisitor);
        add(new DefaultDuration(), Scope.STAFFVOICE);
        add(new TimeMode(), Scope.SCORE);
        add(new NoteTieEnd(), Scope.STAFFVOICE);
        add(new NoteTieStart(), Scope.STAFFVOICE);
    }

    @Override
    protected Scope scope() {
        return Scope.NOTE;
    }

    public Note visitNote(NoteContext ctx) {
        Note note = (Note) visit(ctx);
        if (get(TimeMode.class)) {
            // Set Default duration in relative mode
            set(DefaultDuration.class, note.getDuration());
        }
        if(get(NoteTieEnd.class)) {
            note.setTieEnd(true);
            set(NoteTieEnd.class, false);
        }
        if(get(NoteTieStart.class)) {
            note.setTieStart(true);
            set(NoteTieStart.class, false);
            set(NoteTieEnd.class, true);
        }
        return note;
    }

    @Override
    public Note visitNoteSingle(NoteSingleContext ctx) {
        ctx.noteSuffix().forEach(this::visit);
        BarTime duration = ctx.duration() != null ? new TimeVisitor(this).visitDuration(ctx.duration())
                : get(DefaultDuration.class);
        int numberOfDots = ctx.DOT().size();
        Note note = Note.of(durationWithDot(duration, numberOfDots));
        PitchVisitor pitchVisitor = new PitchVisitor(this);
        note.add(pitchVisitor.visitPitch(ctx.pitch()));
        return note;
    }

    @Override
    public Note visitNoteChord(NoteChordContext ctx) {
        ctx.noteSuffix().forEach(this::visit);
        BarTime duration = ctx.duration() != null ? new TimeVisitor(this).visitDuration(ctx.duration())
                : get(DefaultDuration.class);
        int numberOfDots = ctx.DOT().size();
        Note note = Note.of(durationWithDot(duration, numberOfDots));
        ctx.pitch().forEach(pitchCtx -> {
            PitchVisitor pitchVisitor = new PitchVisitor(this);
            note.add(pitchVisitor.visitPitch(pitchCtx));
        });
        return note;
    }

    @Override
    public Note visitNoteRest(NoteRestContext ctx) {
        BarTime duration = ctx.duration() != null ? new TimeVisitor(this).visitDuration(ctx.duration())
                : get(DefaultDuration.class);
        int numberOfDots = ctx.DOT().size();
        Note note = Note.of(durationWithDot(duration, numberOfDots));

        return note;
    }

    private BarTime durationWithDot(BarTime duration, int i) {
        if(i == 0) {
            return duration;
        } else {
            return duration.plus(durationWithDot(duration, i - 1).times(BarTime.of(1,2)));
        }
    }

    @Override
    public Object visitNoteSuffixTie(NoteSuffixTieContext ctx) {
        set(NoteTieStart.class, true);
        return super.visitNoteSuffixTie(ctx);
    }
}
