package org.music4j.grammar;

import org.music4j.BarTime;
import org.music4j.Note;
import org.music4j.Voice;
import org.music4j.grammar.gen.RubatoParser.NoteContext;
import org.music4j.grammar.gen.RubatoParser.VoiceContext;

public class VoiceVisitor extends AbstractVisitor {

    public VoiceVisitor() {
        this(null);
    }

    public VoiceVisitor(StaffVoiceVisitor staffVisitor) {
        super(staffVisitor);
    }

    @Override
    public Voice visitVoice(VoiceContext ctx) {
        BarTime counter = BarTime.ZERO;
        Voice voice = Voice.of();
        for (NoteContext noteCtx : ctx.note()) {
            NoteVisitor noteVisitor = new NoteVisitor(this);
            Note note = noteVisitor.visitNote(noteCtx);
            voice.put(counter, note);
            counter = counter.plus(note.getDuration());
        }
        return voice;
    }

    @Override
    protected Scope scope() {
        return Scope.BARVOICE;
    }

}
