package org.music4j.grammar;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.music4j.Bar;
import org.music4j.BarTime;
import org.music4j.Staff;
import org.music4j.Voice;
import org.music4j.Pitch.Octave;
import org.music4j.Pitch.Step;
import org.music4j.grammar.gen.RubatoParser.StaffContext;
import org.music4j.grammar.gen.RubatoParser.StaffVoiceContext;
import org.music4j.grammar.gen.RubatoParser.StaffVoiceEmptyContext;
import org.music4j.grammar.gen.RubatoParser.StaffVoiceNonEmptyContext;
import org.music4j.grammar.token.DefaultDuration;
import org.music4j.grammar.token.DefaultOctave;
import org.music4j.grammar.token.OctaveMode;
import org.music4j.grammar.token.PreviousStep;
import org.music4j.grammar.token.TimeMode;

public class StaffVisitor extends AbstractVisitor {

    public StaffVisitor() {
        // Set default configurations
        add(new OctaveMode(), false);
        add(new TimeMode(), false);
        add(new DefaultDuration(), BarTime.of(1));
        add(new PreviousStep(), Step.C);
        add(new DefaultOctave(), Octave.SMALL);
    }

    public StaffVisitor(PartVisitor partVisitor) {
        super(partVisitor);
        add(new DefaultDuration(), BarTime.of(1));
        add(new PreviousStep(), Step.C);
        add(new DefaultOctave(), Octave.SMALL);
    }

    @Override
    public Staff visitStaff(StaffContext ctx) {
        Staff staff = Staff.of();

        // Each staff voices represents a single voice which spans over the whole staff
        for (StaffVoiceContext staffVoiceCtx : ctx.staffVoice()) {
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
        return ctx.voice().stream().map(voiceCtx -> {
            VoiceVisitor voiceVisitor = new VoiceVisitor(this);
            return voiceVisitor.visitVoice(voiceCtx);
        }).collect(Collectors.toList());
    }
}
