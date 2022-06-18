/**
 * ANTLR grammar file for parsing rubato files.
 */
 grammar Rubato;

 @header {
    package org.music4j.rubato.gen;
}

/**
 * Root rule for all rubato files. A score can either be empty or consist of one or multiple parts.
 */
 score
 :
     'Score' '{' scoreSettings* part* '}'
 ;

 /**
 * A part consist of zero or more staffs
 */
 part
 :
     'Part' '{' staff* '}'
 ;

 /**
 * A Staff consists of one or more staffs
 */
 staff
 :
     'Staff' '{' staffVoice* '}'
 ;

 staffVoice
 :
     'Voice' '{' '}' # staffVoiceEmpty
     | 'Voice' '{' voice
     (
         '|' voice
     )* '}' # staffVoiceNonEmpty
 ;

 /**
 * Rule is only used for the static factory of the Voice object.
 */
 voice
 :
     note*
 ;

 note
 :
     pitch duration? DOT* noteSuffix* # noteSingle
     | '[' pitch+ ']' duration? DOT* noteSuffix* # noteChord
     | 'R' duration? DOT* # noteRest
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
     FLAT # alterFlat
     | FLAT_FLAT # alterFlatFlat
     | DOUBLE_SHARP # alterDoubleSharp
     | SHARP # alterSharp
     | NATURAL # alterNatural
 ;

 /**
 * The small octave has no indication. Every octave under small is marked by commas and every above by lines.
 */
 octave
 :
     LINED+
     | COMMA+
 ;

 duration
 :
     INT '/' INT # durationFraction
     | '/' INT # durationInvertedInteger
     | INT # durationInteger
 ;

 noteSuffix
 :
     '~' # noteSuffixTie
 ;

 scoreSettings
 :
     '['
     (
         modeTime
         | modeOctave
         | modeTimeAndOctave
     ) ']'
 ;

 modeTime
 :
     'MODE_TIME' ':' 'absolute' # modeTimeAbsolute
     | 'MODE_TIME' ':' 'relative' # modeTimeRelative
 ;

 modeOctave
 :
     'MODE_OCTAVE' ':' 'absolute' # modeOctaveAbsolute
     | 'MODE_OCTAVE' ':' 'relative' # modeOctaveRelative
 ;

 modeTimeAndOctave
 :
     'MODE' ':' 'absolute' # modeTimeAndOctaveAbsolute
     | 'MODE' ':' 'relative' # modeTimeAndOctaveRelative
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

 INT
 :
     '0'
     | [1-9] [0-9]*
 ;

 DOT: '.';

 /*
 * ---------------------------------------------Comments and Whitespace handling---------------------------------------------
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