package org.music4j.grammar;

import org.music4j.Score;
import org.music4j.grammar.gen.RubatoBaseVisitor;
import org.music4j.grammar.gen.RubatoParser.ScoreContext;
import org.music4j.grammar.gen.RubatoVisitor;

/**
 * Implementation of a {@link RubatoVisitor} which parses rubato files to
 * appropriate java objects. Note that the generic type is Object since there is
 * no common class other than Object for all Java Objects of the Music4j API.
 * Most of the visitor methods will covariantly return an object of the
 * appropriate type.
 */
public class RubatoInterpreter extends RubatoBaseVisitor<Object> implements RubatoVisitor<Object> {

    @Override
    public Score visitScore(ScoreContext ctx) {
        return Score.of();
    }

}
