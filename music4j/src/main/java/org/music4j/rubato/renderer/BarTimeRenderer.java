package org.music4j.rubato.renderer;

import java.util.Locale;

import org.music4j.BarTime;
import org.stringtemplate.v4.AttributeRenderer;

public class BarTimeRenderer implements AttributeRenderer<BarTime> {

    @Override
    public String toString(BarTime value, String formatString, Locale locale) {
        if("trivial".equals(formatString) && value.equals(BarTime.QUARTER)) {
            return "";
        }
        int denominator = value.getDenominator();
        int numerator = value.getNumerator();
        if (denominator == 1) {
            return String.valueOf(numerator);
        } else if (numerator == 1) {
            return String.format("/%s", denominator);
        } else {
            return String.format("%d/%d", numerator, denominator);
        }
    }
}
