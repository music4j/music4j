package org.music4j.grammar;

import java.nio.file.Path;
import java.nio.file.Paths;

import org.music4j.BarTime;
import org.stringtemplate.v4.ST;
import org.stringtemplate.v4.STGroupFile;

public class RubatoTranslator {

    private final STGroupFile groupFile;

    public RubatoTranslator() {
        Path pathToFile = Paths.get("src", "main", "java", "org", "music4j", "grammar", "Rubato.stg");
        groupFile = new STGroupFile(pathToFile.toString());
    }

    public String translateTime(BarTime time) {
        ST template = groupFile.getInstanceOf("time");
        template.add("t", time);
        return template.render();
    }

}
