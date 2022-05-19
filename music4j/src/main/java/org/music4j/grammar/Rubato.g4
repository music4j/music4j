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