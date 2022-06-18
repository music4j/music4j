// Generated from Rubato.g4 by ANTLR 4.9.2

    package org.music4j.rubato.gen;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link RubatoParser}.
 */
public interface RubatoListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link RubatoParser#score}.
	 * @param ctx the parse tree
	 */
	void enterScore(RubatoParser.ScoreContext ctx);
	/**
	 * Exit a parse tree produced by {@link RubatoParser#score}.
	 * @param ctx the parse tree
	 */
	void exitScore(RubatoParser.ScoreContext ctx);
	/**
	 * Enter a parse tree produced by {@link RubatoParser#part}.
	 * @param ctx the parse tree
	 */
	void enterPart(RubatoParser.PartContext ctx);
	/**
	 * Exit a parse tree produced by {@link RubatoParser#part}.
	 * @param ctx the parse tree
	 */
	void exitPart(RubatoParser.PartContext ctx);
	/**
	 * Enter a parse tree produced by {@link RubatoParser#staff}.
	 * @param ctx the parse tree
	 */
	void enterStaff(RubatoParser.StaffContext ctx);
	/**
	 * Exit a parse tree produced by {@link RubatoParser#staff}.
	 * @param ctx the parse tree
	 */
	void exitStaff(RubatoParser.StaffContext ctx);
	/**
	 * Enter a parse tree produced by the {@code staffVoiceEmpty}
	 * labeled alternative in {@link RubatoParser#staffVoice}.
	 * @param ctx the parse tree
	 */
	void enterStaffVoiceEmpty(RubatoParser.StaffVoiceEmptyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code staffVoiceEmpty}
	 * labeled alternative in {@link RubatoParser#staffVoice}.
	 * @param ctx the parse tree
	 */
	void exitStaffVoiceEmpty(RubatoParser.StaffVoiceEmptyContext ctx);
	/**
	 * Enter a parse tree produced by the {@code staffVoiceNonEmpty}
	 * labeled alternative in {@link RubatoParser#staffVoice}.
	 * @param ctx the parse tree
	 */
	void enterStaffVoiceNonEmpty(RubatoParser.StaffVoiceNonEmptyContext ctx);
	/**
	 * Exit a parse tree produced by the {@code staffVoiceNonEmpty}
	 * labeled alternative in {@link RubatoParser#staffVoice}.
	 * @param ctx the parse tree
	 */
	void exitStaffVoiceNonEmpty(RubatoParser.StaffVoiceNonEmptyContext ctx);
	/**
	 * Enter a parse tree produced by {@link RubatoParser#voice}.
	 * @param ctx the parse tree
	 */
	void enterVoice(RubatoParser.VoiceContext ctx);
	/**
	 * Exit a parse tree produced by {@link RubatoParser#voice}.
	 * @param ctx the parse tree
	 */
	void exitVoice(RubatoParser.VoiceContext ctx);
	/**
	 * Enter a parse tree produced by the {@code noteSingle}
	 * labeled alternative in {@link RubatoParser#note}.
	 * @param ctx the parse tree
	 */
	void enterNoteSingle(RubatoParser.NoteSingleContext ctx);
	/**
	 * Exit a parse tree produced by the {@code noteSingle}
	 * labeled alternative in {@link RubatoParser#note}.
	 * @param ctx the parse tree
	 */
	void exitNoteSingle(RubatoParser.NoteSingleContext ctx);
	/**
	 * Enter a parse tree produced by the {@code noteChord}
	 * labeled alternative in {@link RubatoParser#note}.
	 * @param ctx the parse tree
	 */
	void enterNoteChord(RubatoParser.NoteChordContext ctx);
	/**
	 * Exit a parse tree produced by the {@code noteChord}
	 * labeled alternative in {@link RubatoParser#note}.
	 * @param ctx the parse tree
	 */
	void exitNoteChord(RubatoParser.NoteChordContext ctx);
	/**
	 * Enter a parse tree produced by the {@code noteRest}
	 * labeled alternative in {@link RubatoParser#note}.
	 * @param ctx the parse tree
	 */
	void enterNoteRest(RubatoParser.NoteRestContext ctx);
	/**
	 * Exit a parse tree produced by the {@code noteRest}
	 * labeled alternative in {@link RubatoParser#note}.
	 * @param ctx the parse tree
	 */
	void exitNoteRest(RubatoParser.NoteRestContext ctx);
	/**
	 * Enter a parse tree produced by {@link RubatoParser#pitch}.
	 * @param ctx the parse tree
	 */
	void enterPitch(RubatoParser.PitchContext ctx);
	/**
	 * Exit a parse tree produced by {@link RubatoParser#pitch}.
	 * @param ctx the parse tree
	 */
	void exitPitch(RubatoParser.PitchContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterFlat}
	 * labeled alternative in {@link RubatoParser#alter}.
	 * @param ctx the parse tree
	 */
	void enterAlterFlat(RubatoParser.AlterFlatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterFlat}
	 * labeled alternative in {@link RubatoParser#alter}.
	 * @param ctx the parse tree
	 */
	void exitAlterFlat(RubatoParser.AlterFlatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterFlatFlat}
	 * labeled alternative in {@link RubatoParser#alter}.
	 * @param ctx the parse tree
	 */
	void enterAlterFlatFlat(RubatoParser.AlterFlatFlatContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterFlatFlat}
	 * labeled alternative in {@link RubatoParser#alter}.
	 * @param ctx the parse tree
	 */
	void exitAlterFlatFlat(RubatoParser.AlterFlatFlatContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterDoubleSharp}
	 * labeled alternative in {@link RubatoParser#alter}.
	 * @param ctx the parse tree
	 */
	void enterAlterDoubleSharp(RubatoParser.AlterDoubleSharpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterDoubleSharp}
	 * labeled alternative in {@link RubatoParser#alter}.
	 * @param ctx the parse tree
	 */
	void exitAlterDoubleSharp(RubatoParser.AlterDoubleSharpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterSharp}
	 * labeled alternative in {@link RubatoParser#alter}.
	 * @param ctx the parse tree
	 */
	void enterAlterSharp(RubatoParser.AlterSharpContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterSharp}
	 * labeled alternative in {@link RubatoParser#alter}.
	 * @param ctx the parse tree
	 */
	void exitAlterSharp(RubatoParser.AlterSharpContext ctx);
	/**
	 * Enter a parse tree produced by the {@code alterNatural}
	 * labeled alternative in {@link RubatoParser#alter}.
	 * @param ctx the parse tree
	 */
	void enterAlterNatural(RubatoParser.AlterNaturalContext ctx);
	/**
	 * Exit a parse tree produced by the {@code alterNatural}
	 * labeled alternative in {@link RubatoParser#alter}.
	 * @param ctx the parse tree
	 */
	void exitAlterNatural(RubatoParser.AlterNaturalContext ctx);
	/**
	 * Enter a parse tree produced by {@link RubatoParser#octave}.
	 * @param ctx the parse tree
	 */
	void enterOctave(RubatoParser.OctaveContext ctx);
	/**
	 * Exit a parse tree produced by {@link RubatoParser#octave}.
	 * @param ctx the parse tree
	 */
	void exitOctave(RubatoParser.OctaveContext ctx);
	/**
	 * Enter a parse tree produced by the {@code durationFraction}
	 * labeled alternative in {@link RubatoParser#duration}.
	 * @param ctx the parse tree
	 */
	void enterDurationFraction(RubatoParser.DurationFractionContext ctx);
	/**
	 * Exit a parse tree produced by the {@code durationFraction}
	 * labeled alternative in {@link RubatoParser#duration}.
	 * @param ctx the parse tree
	 */
	void exitDurationFraction(RubatoParser.DurationFractionContext ctx);
	/**
	 * Enter a parse tree produced by the {@code durationInvertedInteger}
	 * labeled alternative in {@link RubatoParser#duration}.
	 * @param ctx the parse tree
	 */
	void enterDurationInvertedInteger(RubatoParser.DurationInvertedIntegerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code durationInvertedInteger}
	 * labeled alternative in {@link RubatoParser#duration}.
	 * @param ctx the parse tree
	 */
	void exitDurationInvertedInteger(RubatoParser.DurationInvertedIntegerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code durationInteger}
	 * labeled alternative in {@link RubatoParser#duration}.
	 * @param ctx the parse tree
	 */
	void enterDurationInteger(RubatoParser.DurationIntegerContext ctx);
	/**
	 * Exit a parse tree produced by the {@code durationInteger}
	 * labeled alternative in {@link RubatoParser#duration}.
	 * @param ctx the parse tree
	 */
	void exitDurationInteger(RubatoParser.DurationIntegerContext ctx);
	/**
	 * Enter a parse tree produced by the {@code noteSuffixTie}
	 * labeled alternative in {@link RubatoParser#noteSuffix}.
	 * @param ctx the parse tree
	 */
	void enterNoteSuffixTie(RubatoParser.NoteSuffixTieContext ctx);
	/**
	 * Exit a parse tree produced by the {@code noteSuffixTie}
	 * labeled alternative in {@link RubatoParser#noteSuffix}.
	 * @param ctx the parse tree
	 */
	void exitNoteSuffixTie(RubatoParser.NoteSuffixTieContext ctx);
	/**
	 * Enter a parse tree produced by {@link RubatoParser#scoreSettings}.
	 * @param ctx the parse tree
	 */
	void enterScoreSettings(RubatoParser.ScoreSettingsContext ctx);
	/**
	 * Exit a parse tree produced by {@link RubatoParser#scoreSettings}.
	 * @param ctx the parse tree
	 */
	void exitScoreSettings(RubatoParser.ScoreSettingsContext ctx);
	/**
	 * Enter a parse tree produced by the {@code modeTimeAbsolute}
	 * labeled alternative in {@link RubatoParser#modeTime}.
	 * @param ctx the parse tree
	 */
	void enterModeTimeAbsolute(RubatoParser.ModeTimeAbsoluteContext ctx);
	/**
	 * Exit a parse tree produced by the {@code modeTimeAbsolute}
	 * labeled alternative in {@link RubatoParser#modeTime}.
	 * @param ctx the parse tree
	 */
	void exitModeTimeAbsolute(RubatoParser.ModeTimeAbsoluteContext ctx);
	/**
	 * Enter a parse tree produced by the {@code modeTimeRelative}
	 * labeled alternative in {@link RubatoParser#modeTime}.
	 * @param ctx the parse tree
	 */
	void enterModeTimeRelative(RubatoParser.ModeTimeRelativeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code modeTimeRelative}
	 * labeled alternative in {@link RubatoParser#modeTime}.
	 * @param ctx the parse tree
	 */
	void exitModeTimeRelative(RubatoParser.ModeTimeRelativeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code modeOctaveAbsolute}
	 * labeled alternative in {@link RubatoParser#modeOctave}.
	 * @param ctx the parse tree
	 */
	void enterModeOctaveAbsolute(RubatoParser.ModeOctaveAbsoluteContext ctx);
	/**
	 * Exit a parse tree produced by the {@code modeOctaveAbsolute}
	 * labeled alternative in {@link RubatoParser#modeOctave}.
	 * @param ctx the parse tree
	 */
	void exitModeOctaveAbsolute(RubatoParser.ModeOctaveAbsoluteContext ctx);
	/**
	 * Enter a parse tree produced by the {@code modeOctaveRelative}
	 * labeled alternative in {@link RubatoParser#modeOctave}.
	 * @param ctx the parse tree
	 */
	void enterModeOctaveRelative(RubatoParser.ModeOctaveRelativeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code modeOctaveRelative}
	 * labeled alternative in {@link RubatoParser#modeOctave}.
	 * @param ctx the parse tree
	 */
	void exitModeOctaveRelative(RubatoParser.ModeOctaveRelativeContext ctx);
	/**
	 * Enter a parse tree produced by the {@code modeTimeAndOctaveAbsolute}
	 * labeled alternative in {@link RubatoParser#modeTimeAndOctave}.
	 * @param ctx the parse tree
	 */
	void enterModeTimeAndOctaveAbsolute(RubatoParser.ModeTimeAndOctaveAbsoluteContext ctx);
	/**
	 * Exit a parse tree produced by the {@code modeTimeAndOctaveAbsolute}
	 * labeled alternative in {@link RubatoParser#modeTimeAndOctave}.
	 * @param ctx the parse tree
	 */
	void exitModeTimeAndOctaveAbsolute(RubatoParser.ModeTimeAndOctaveAbsoluteContext ctx);
	/**
	 * Enter a parse tree produced by the {@code modeTimeAndOctaveRelative}
	 * labeled alternative in {@link RubatoParser#modeTimeAndOctave}.
	 * @param ctx the parse tree
	 */
	void enterModeTimeAndOctaveRelative(RubatoParser.ModeTimeAndOctaveRelativeContext ctx);
	/**
	 * Exit a parse tree produced by the {@code modeTimeAndOctaveRelative}
	 * labeled alternative in {@link RubatoParser#modeTimeAndOctave}.
	 * @param ctx the parse tree
	 */
	void exitModeTimeAndOctaveRelative(RubatoParser.ModeTimeAndOctaveRelativeContext ctx);
}