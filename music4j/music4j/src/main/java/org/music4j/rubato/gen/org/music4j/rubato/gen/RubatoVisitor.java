// Generated from Rubato.g4 by ANTLR 4.4

    package org.music4j.rubato.gen;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link RubatoParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface RubatoVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link RubatoParser#voice}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitVoice(@NotNull RubatoParser.VoiceContext ctx);
	/**
	 * Visit a parse tree produced by the {@code modeTimeRelative}
	 * labeled alternative in {@link RubatoParser#modeTime}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModeTimeRelative(@NotNull RubatoParser.ModeTimeRelativeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code modeOctaveAbsolute}
	 * labeled alternative in {@link RubatoParser#modeOctave}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModeOctaveAbsolute(@NotNull RubatoParser.ModeOctaveAbsoluteContext ctx);
	/**
	 * Visit a parse tree produced by the {@code modeTimeAndOctaveAbsolute}
	 * labeled alternative in {@link RubatoParser#modeTimeAndOctave}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModeTimeAndOctaveAbsolute(@NotNull RubatoParser.ModeTimeAndOctaveAbsoluteContext ctx);
	/**
	 * Visit a parse tree produced by {@link RubatoParser#part}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPart(@NotNull RubatoParser.PartContext ctx);
	/**
	 * Visit a parse tree produced by the {@code staffVoiceEmpty}
	 * labeled alternative in {@link RubatoParser#staffVoice}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStaffVoiceEmpty(@NotNull RubatoParser.StaffVoiceEmptyContext ctx);
	/**
	 * Visit a parse tree produced by the {@code durationFraction}
	 * labeled alternative in {@link RubatoParser#duration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDurationFraction(@NotNull RubatoParser.DurationFractionContext ctx);
	/**
	 * Visit a parse tree produced by {@link RubatoParser#score}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScore(@NotNull RubatoParser.ScoreContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterDoubleSharp}
	 * labeled alternative in {@link RubatoParser#alter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterDoubleSharp(@NotNull RubatoParser.AlterDoubleSharpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code modeTimeAndOctaveRelative}
	 * labeled alternative in {@link RubatoParser#modeTimeAndOctave}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModeTimeAndOctaveRelative(@NotNull RubatoParser.ModeTimeAndOctaveRelativeContext ctx);
	/**
	 * Visit a parse tree produced by the {@code noteRest}
	 * labeled alternative in {@link RubatoParser#note}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNoteRest(@NotNull RubatoParser.NoteRestContext ctx);
	/**
	 * Visit a parse tree produced by {@link RubatoParser#pitch}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPitch(@NotNull RubatoParser.PitchContext ctx);
	/**
	 * Visit a parse tree produced by {@link RubatoParser#scoreSettings}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitScoreSettings(@NotNull RubatoParser.ScoreSettingsContext ctx);
	/**
	 * Visit a parse tree produced by the {@code noteChord}
	 * labeled alternative in {@link RubatoParser#note}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNoteChord(@NotNull RubatoParser.NoteChordContext ctx);
	/**
	 * Visit a parse tree produced by the {@code durationInteger}
	 * labeled alternative in {@link RubatoParser#duration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDurationInteger(@NotNull RubatoParser.DurationIntegerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterFlatFlat}
	 * labeled alternative in {@link RubatoParser#alter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterFlatFlat(@NotNull RubatoParser.AlterFlatFlatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code staffVoiceNonEmpty}
	 * labeled alternative in {@link RubatoParser#staffVoice}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStaffVoiceNonEmpty(@NotNull RubatoParser.StaffVoiceNonEmptyContext ctx);
	/**
	 * Visit a parse tree produced by {@link RubatoParser#octave}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitOctave(@NotNull RubatoParser.OctaveContext ctx);
	/**
	 * Visit a parse tree produced by the {@code modeOctaveRelative}
	 * labeled alternative in {@link RubatoParser#modeOctave}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModeOctaveRelative(@NotNull RubatoParser.ModeOctaveRelativeContext ctx);
	/**
	 * Visit a parse tree produced by {@link RubatoParser#staff}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStaff(@NotNull RubatoParser.StaffContext ctx);
	/**
	 * Visit a parse tree produced by the {@code modeTimeAbsolute}
	 * labeled alternative in {@link RubatoParser#modeTime}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitModeTimeAbsolute(@NotNull RubatoParser.ModeTimeAbsoluteContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterFlat}
	 * labeled alternative in {@link RubatoParser#alter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterFlat(@NotNull RubatoParser.AlterFlatContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterNatural}
	 * labeled alternative in {@link RubatoParser#alter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterNatural(@NotNull RubatoParser.AlterNaturalContext ctx);
	/**
	 * Visit a parse tree produced by the {@code noteSingle}
	 * labeled alternative in {@link RubatoParser#note}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNoteSingle(@NotNull RubatoParser.NoteSingleContext ctx);
	/**
	 * Visit a parse tree produced by the {@code alterSharp}
	 * labeled alternative in {@link RubatoParser#alter}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAlterSharp(@NotNull RubatoParser.AlterSharpContext ctx);
	/**
	 * Visit a parse tree produced by the {@code durationInvertedInteger}
	 * labeled alternative in {@link RubatoParser#duration}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDurationInvertedInteger(@NotNull RubatoParser.DurationInvertedIntegerContext ctx);
	/**
	 * Visit a parse tree produced by the {@code noteSuffixTie}
	 * labeled alternative in {@link RubatoParser#noteSuffix}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNoteSuffixTie(@NotNull RubatoParser.NoteSuffixTieContext ctx);
}