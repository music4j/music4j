/**
 * ANTLR grammar file for parsing rubato files.
 */
 grammar Rubato;

 @header {
    package org.music4j.grammar.gen;
}

/**
 * Root rule for all rubato files. A score can either be empty or consist of one or multiple parts.
 */
 score
 :
     'Score' '{' '}'
 ;

 /**
 * A pitch consists of a mandatory step and optional alteration and octave. If the no alteration
 * and octave are given the default values are assumed. These depend on the pitch mode.
 */
 pitch
 :
     STEP alter? octave?
 ;

 /**
 * Alteration is either FLAT, FLAT_FLAT, SHARP, or DOUBLE_SHARP.
 */
 alter
 :
     alterToken =
     (
         FLAT
         | FLAT_FLAT
         | DOUBLE_SHARP
         | SHARP
         | NATURAL
     )
 ;

 /**
 * The small octave has no indication. Every octave under small is marked by commas and every above by lines.
 */
 octave
 :
     LINED+
     | COMMA+
 ;

 /*
 * ---------------------------------------------Lexer rules---------------------------------------------
 */

/**
 * A Step is marked by an uppercase character ranging from A-G.
 */
 STEP
 :
     [A-G]
 ;

 /**
 * The alteration FLAT is marked y a single b.
 */
 FLAT
 :
     'b'
 ;

 /**
 * The alteration FLAT_FLAT is marked by a double b.
 */
 FLAT_FLAT
 :
     'bb'
 ;

 /**
 * The alteration DOUBLE_SHARP is marked by an x
 */
 DOUBLE_SHARP
 :
     'x'
 ;

 /**
 * The alteration SHARP is marked by a hashtag.
 */
 SHARP
 :
     '#'
 ;

 /**
 * The alteration NATURAL is marked by an exclamation mark
 */
 NATURAL
 :
     '!'
 ;

 LINED
 :
     '\''
 ;

 /**
 * A comma
 */
 COMMA
 :
     ','
 ;

 /*
 * ---------------------------------------------Comment rules---------------------------------------------
 */
/**
 * Line comments are ignored
 */
 LINE_COMMENT
 :
     '//' .*? '\n' -> skip
 ;

 /**
  * Multiple line comments are ignored
  */
 COMMENT
 :
     '/*' .*? '*/' -> skip
 ;

 /**
 * Whitespace is ignored.
 */
 WHITESPACE
 :
     (
         ' '
         | '\t'
         | '\n'
         | '\r'
     ) -> skip
 ;