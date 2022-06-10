package org.music4j.grammar;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.music4j.Voice;
import org.music4j.grammar.gen.RubatoParser.StaffVoiceContext;
import org.music4j.grammar.gen.RubatoParser.StaffVoiceEmptyContext;
import org.music4j.grammar.gen.RubatoParser.StaffVoiceNonEmptyContext;

public class StaffVoiceVisitor extends AbstractVisitor {

    public StaffVoiceVisitor() {
        this(null);
    }

    public StaffVoiceVisitor(StaffVisitor staffVisitor) {
        super(staffVisitor);
    }

    @Override
    protected Scope scope() {
        return Scope.STAFFVOICE;
    }

    @SuppressWarnings("unchecked")
    public List<Voice> visitStaffVoice(StaffVoiceContext ctx) {
        return (List<Voice>) visit(ctx);
    }

    @Override
    public List<Voice> visitStaffVoiceEmpty(StaffVoiceEmptyContext ctx) {
        return Collections.emptyList();
    }

    @Override
    public List<Voice> visitStaffVoiceNonEmpty(StaffVoiceNonEmptyContext ctx) {
        return ctx.voice().stream().map(voiceCtx -> {
            VoiceVisitor voiceVisitor = new VoiceVisitor(this);
            return voiceVisitor.visitVoice(voiceCtx);
        }).collect(Collectors.toList());
    }
}
