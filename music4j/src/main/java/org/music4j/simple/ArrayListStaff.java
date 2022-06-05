package org.music4j.simple;

import java.util.ArrayList;
import java.util.List;

import org.music4j.Bar;
import org.music4j.Staff;
import org.music4j.utils.ForwardingList;

public class ArrayListStaff extends ForwardingList<Bar> implements Staff {

    public ArrayListStaff() {
        super(new ArrayList<>());
    }

    @Override
    public String toString() {
        int numberOfVoices = stream().map(Bar::size).max(Integer::compareTo).orElse(1);
        List<StringBuilder> voices = new ArrayList<>();
        for (int i = 0; i < numberOfVoices; i++) {
            voices.add(new StringBuilder().append(String.format("Voice { %n")));
        }
        for (Bar bar : this) {
            for (int i = 0; i < numberOfVoices; i++) {
                StringBuilder voiceBuilder = voices.get(i);
                if (i < bar.size()) {
                    voiceBuilder.append(bar.get(i));
                } else {
                    voiceBuilder.append(String.format("Z%s ", bar.length()));
                }
                if(i < bar.size()-1) {
                    voiceBuilder.append(String.format(" |%n"));
                }
            }
        }
        voices.forEach(sb -> sb.append(String.format("} %n")));

        StringBuilder staffBuilder = new StringBuilder();
        staffBuilder.append(String.format("Staff { %n"));
        voices.forEach(staffBuilder::append);
        staffBuilder.append(String.format("} %n"));

        return staffBuilder.toString();
    }
}
