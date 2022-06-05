package org.music4j.grammar;

import org.music4j.BarTime;
import org.music4j.Note;
import org.music4j.grammar.gen.RubatoParser.NoteChordContext;
import org.music4j.grammar.gen.RubatoParser.NoteContext;
import org.music4j.grammar.gen.RubatoParser.NoteRestContext;
import org.music4j.grammar.gen.RubatoParser.NoteSingleContext;
import org.music4j.grammar.token.DefaultDuration;
import org.music4j.grammar.token.OctaveMode;
import org.music4j.grammar.token.TimeMode;

public class NoteVisitor extends AbstractVisitor {

    public NoteVisitor() {
        add(new OctaveMode(), false);
        add(new TimeMode(), false);
        add(new DefaultDuration(), BarTime.of(1));
    }

    public NoteVisitor(VoiceVisitor voiceVisitor) {
        super(voiceVisitor);
    }

    public Note visitNote(NoteContext ctx) {
        Note note = (Note) visit(ctx);
        if (get(TimeMode.class)) {
            // Set Default duration in relative mode
            set(DefaultDuration.class, note.getDuration());
        }
        return note;
    }

    @Override
    public Note visitNoteSingle(NoteSingleContext ctx) {
        BarTime duration = ctx.duration() != null ? new TimeVisitor(this).visitDuration(ctx.duration())
                : get(DefaultDuration.class);
        Note note = Note.of(duration);
        PitchVisitor pitchVisitor = new PitchVisitor(this);
        note.add(pitchVisitor.visitPitch(ctx.pitch()));
        return note;
    }

    @Override
    public Note visitNoteChord(NoteChordContext ctx) {
        BarTime duration = ctx.duration() != null ? new TimeVisitor(this).visitDuration(ctx.duration())
                : get(DefaultDuration.class);
        Note note = Note.of(duration);
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
        return Note.of(duration);
    }

}
