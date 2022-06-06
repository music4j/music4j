package org.music4j.grammar;

import org.music4j.BarTime;
import org.music4j.Note;
import org.music4j.Voice;
import org.music4j.grammar.gen.RubatoParser.NoteContext;
import org.music4j.grammar.gen.RubatoParser.VoiceContext;
import org.music4j.grammar.token.DefaultDuration;
import org.music4j.grammar.token.NoteTieEnd;
import org.music4j.grammar.token.NoteTieStart;
import org.music4j.grammar.token.OctaveMode;
import org.music4j.grammar.token.TimeMode;

public class VoiceVisitor extends AbstractVisitor {

    public VoiceVisitor() {
        add(new OctaveMode(), false);
        add(new TimeMode(), false);
        add(new DefaultDuration(), BarTime.of(1));
        add(new NoteTieEnd(), false);
        add(new NoteTieStart(), false);
    }

    public VoiceVisitor(StaffVisitor staffVisitor) {
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

}
