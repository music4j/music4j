// Generated from Rubato.g4 by ANTLR 4.9.2

    package org.music4j.rubato.gen;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RubatoParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, STEP=19, FLAT=20, FLAT_FLAT=21, DOUBLE_SHARP=22, SHARP=23, NATURAL=24, 
		LINED=25, COMMA=26, INT=27, DOT=28, LINE_COMMENT=29, COMMENT=30, WHITESPACE=31;
	public static final int
		RULE_score = 0, RULE_part = 1, RULE_staff = 2, RULE_staffVoice = 3, RULE_voice = 4, 
		RULE_note = 5, RULE_pitch = 6, RULE_alter = 7, RULE_octave = 8, RULE_duration = 9, 
		RULE_noteSuffix = 10, RULE_scoreSettings = 11, RULE_modeTime = 12, RULE_modeOctave = 13, 
		RULE_modeTimeAndOctave = 14;
	private static String[] makeRuleNames() {
		return new String[] {
			"score", "part", "staff", "staffVoice", "voice", "note", "pitch", "alter", 
			"octave", "duration", "noteSuffix", "scoreSettings", "modeTime", "modeOctave", 
			"modeTimeAndOctave"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'Score'", "'{'", "'}'", "'Part'", "'Staff'", "'Voice'", "'|'", 
			"'['", "']'", "'R'", "'/'", "'~'", "'MODE_TIME'", "':'", "'absolute'", 
			"'relative'", "'MODE_OCTAVE'", "'MODE'", null, "'b'", "'bb'", "'x'", 
			"'#'", "'!'", "'''", "','", null, "'.'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, null, null, 
			null, null, null, null, null, null, null, "STEP", "FLAT", "FLAT_FLAT", 
			"DOUBLE_SHARP", "SHARP", "NATURAL", "LINED", "COMMA", "INT", "DOT", "LINE_COMMENT", 
			"COMMENT", "WHITESPACE"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Rubato.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RubatoParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class ScoreContext extends ParserRuleContext {
		public List<ScoreSettingsContext> scoreSettings() {
			return getRuleContexts(ScoreSettingsContext.class);
		}
		public ScoreSettingsContext scoreSettings(int i) {
			return getRuleContext(ScoreSettingsContext.class,i);
		}
		public List<PartContext> part() {
			return getRuleContexts(PartContext.class);
		}
		public PartContext part(int i) {
			return getRuleContext(PartContext.class,i);
		}
		public ScoreContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_score; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).enterScore(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).exitScore(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubatoVisitor ) return ((RubatoVisitor<? extends T>)visitor).visitScore(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScoreContext score() throws RecognitionException {
		ScoreContext _localctx = new ScoreContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_score);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(30);
			match(T__0);
			setState(31);
			match(T__1);
			setState(35);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__7) {
				{
				{
				setState(32);
				scoreSettings();
				}
				}
				setState(37);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(41);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3) {
				{
				{
				setState(38);
				part();
				}
				}
				setState(43);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(44);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PartContext extends ParserRuleContext {
		public List<StaffContext> staff() {
			return getRuleContexts(StaffContext.class);
		}
		public StaffContext staff(int i) {
			return getRuleContext(StaffContext.class,i);
		}
		public PartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_part; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).enterPart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).exitPart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubatoVisitor ) return ((RubatoVisitor<? extends T>)visitor).visitPart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PartContext part() throws RecognitionException {
		PartContext _localctx = new PartContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_part);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			match(T__3);
			setState(47);
			match(T__1);
			setState(51);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__4) {
				{
				{
				setState(48);
				staff();
				}
				}
				setState(53);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(54);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StaffContext extends ParserRuleContext {
		public List<StaffVoiceContext> staffVoice() {
			return getRuleContexts(StaffVoiceContext.class);
		}
		public StaffVoiceContext staffVoice(int i) {
			return getRuleContext(StaffVoiceContext.class,i);
		}
		public StaffContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_staff; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).enterStaff(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).exitStaff(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubatoVisitor ) return ((RubatoVisitor<? extends T>)visitor).visitStaff(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StaffContext staff() throws RecognitionException {
		StaffContext _localctx = new StaffContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_staff);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(56);
			match(T__4);
			setState(57);
			match(T__1);
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__5) {
				{
				{
				setState(58);
				staffVoice();
				}
				}
				setState(63);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(64);
			match(T__2);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class StaffVoiceContext extends ParserRuleContext {
		public StaffVoiceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_staffVoice; }
	 
		public StaffVoiceContext() { }
		public void copyFrom(StaffVoiceContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class StaffVoiceNonEmptyContext extends StaffVoiceContext {
		public List<VoiceContext> voice() {
			return getRuleContexts(VoiceContext.class);
		}
		public VoiceContext voice(int i) {
			return getRuleContext(VoiceContext.class,i);
		}
		public StaffVoiceNonEmptyContext(StaffVoiceContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).enterStaffVoiceNonEmpty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).exitStaffVoiceNonEmpty(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubatoVisitor ) return ((RubatoVisitor<? extends T>)visitor).visitStaffVoiceNonEmpty(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class StaffVoiceEmptyContext extends StaffVoiceContext {
		public StaffVoiceEmptyContext(StaffVoiceContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).enterStaffVoiceEmpty(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).exitStaffVoiceEmpty(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubatoVisitor ) return ((RubatoVisitor<? extends T>)visitor).visitStaffVoiceEmpty(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StaffVoiceContext staffVoice() throws RecognitionException {
		StaffVoiceContext _localctx = new StaffVoiceContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_staffVoice);
		int _la;
		try {
			setState(81);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,5,_ctx) ) {
			case 1:
				_localctx = new StaffVoiceEmptyContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(66);
				match(T__5);
				setState(67);
				match(T__1);
				setState(68);
				match(T__2);
				}
				break;
			case 2:
				_localctx = new StaffVoiceNonEmptyContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(69);
				match(T__5);
				setState(70);
				match(T__1);
				setState(71);
				voice();
				setState(76);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__6) {
					{
					{
					setState(72);
					match(T__6);
					setState(73);
					voice();
					}
					}
					setState(78);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(79);
				match(T__2);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class VoiceContext extends ParserRuleContext {
		public List<NoteContext> note() {
			return getRuleContexts(NoteContext.class);
		}
		public NoteContext note(int i) {
			return getRuleContext(NoteContext.class,i);
		}
		public VoiceContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_voice; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).enterVoice(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).exitVoice(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubatoVisitor ) return ((RubatoVisitor<? extends T>)visitor).visitVoice(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VoiceContext voice() throws RecognitionException {
		VoiceContext _localctx = new VoiceContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_voice);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__7) | (1L << T__9) | (1L << STEP))) != 0)) {
				{
				{
				setState(83);
				note();
				}
				}
				setState(88);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NoteContext extends ParserRuleContext {
		public NoteContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_note; }
	 
		public NoteContext() { }
		public void copyFrom(NoteContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NoteRestContext extends NoteContext {
		public DurationContext duration() {
			return getRuleContext(DurationContext.class,0);
		}
		public List<TerminalNode> DOT() { return getTokens(RubatoParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(RubatoParser.DOT, i);
		}
		public NoteRestContext(NoteContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).enterNoteRest(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).exitNoteRest(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubatoVisitor ) return ((RubatoVisitor<? extends T>)visitor).visitNoteRest(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NoteChordContext extends NoteContext {
		public List<PitchContext> pitch() {
			return getRuleContexts(PitchContext.class);
		}
		public PitchContext pitch(int i) {
			return getRuleContext(PitchContext.class,i);
		}
		public DurationContext duration() {
			return getRuleContext(DurationContext.class,0);
		}
		public List<TerminalNode> DOT() { return getTokens(RubatoParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(RubatoParser.DOT, i);
		}
		public List<NoteSuffixContext> noteSuffix() {
			return getRuleContexts(NoteSuffixContext.class);
		}
		public NoteSuffixContext noteSuffix(int i) {
			return getRuleContext(NoteSuffixContext.class,i);
		}
		public NoteChordContext(NoteContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).enterNoteChord(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).exitNoteChord(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubatoVisitor ) return ((RubatoVisitor<? extends T>)visitor).visitNoteChord(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class NoteSingleContext extends NoteContext {
		public PitchContext pitch() {
			return getRuleContext(PitchContext.class,0);
		}
		public DurationContext duration() {
			return getRuleContext(DurationContext.class,0);
		}
		public List<TerminalNode> DOT() { return getTokens(RubatoParser.DOT); }
		public TerminalNode DOT(int i) {
			return getToken(RubatoParser.DOT, i);
		}
		public List<NoteSuffixContext> noteSuffix() {
			return getRuleContexts(NoteSuffixContext.class);
		}
		public NoteSuffixContext noteSuffix(int i) {
			return getRuleContext(NoteSuffixContext.class,i);
		}
		public NoteSingleContext(NoteContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).enterNoteSingle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).exitNoteSingle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubatoVisitor ) return ((RubatoVisitor<? extends T>)visitor).visitNoteSingle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NoteContext note() throws RecognitionException {
		NoteContext _localctx = new NoteContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_note);
		int _la;
		try {
			setState(137);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case STEP:
				_localctx = new NoteSingleContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(89);
				pitch();
				setState(91);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__10 || _la==INT) {
					{
					setState(90);
					duration();
					}
				}

				setState(96);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT) {
					{
					{
					setState(93);
					match(DOT);
					}
					}
					setState(98);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__11) {
					{
					{
					setState(99);
					noteSuffix();
					}
					}
					setState(104);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case T__7:
				_localctx = new NoteChordContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(105);
				match(T__7);
				setState(107); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(106);
					pitch();
					}
					}
					setState(109); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==STEP );
				setState(111);
				match(T__8);
				setState(113);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__10 || _la==INT) {
					{
					setState(112);
					duration();
					}
				}

				setState(118);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT) {
					{
					{
					setState(115);
					match(DOT);
					}
					}
					setState(120);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				setState(124);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==T__11) {
					{
					{
					setState(121);
					noteSuffix();
					}
					}
					setState(126);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			case T__9:
				_localctx = new NoteRestContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(127);
				match(T__9);
				setState(129);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__10 || _la==INT) {
					{
					setState(128);
					duration();
					}
				}

				setState(134);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==DOT) {
					{
					{
					setState(131);
					match(DOT);
					}
					}
					setState(136);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class PitchContext extends ParserRuleContext {
		public TerminalNode STEP() { return getToken(RubatoParser.STEP, 0); }
		public AlterContext alter() {
			return getRuleContext(AlterContext.class,0);
		}
		public OctaveContext octave() {
			return getRuleContext(OctaveContext.class,0);
		}
		public PitchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_pitch; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).enterPitch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).exitPitch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubatoVisitor ) return ((RubatoVisitor<? extends T>)visitor).visitPitch(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PitchContext pitch() throws RecognitionException {
		PitchContext _localctx = new PitchContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_pitch);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(139);
			match(STEP);
			setState(141);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << FLAT) | (1L << FLAT_FLAT) | (1L << DOUBLE_SHARP) | (1L << SHARP) | (1L << NATURAL))) != 0)) {
				{
				setState(140);
				alter();
				}
			}

			setState(144);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==LINED || _la==COMMA) {
				{
				setState(143);
				octave();
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class AlterContext extends ParserRuleContext {
		public AlterContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_alter; }
	 
		public AlterContext() { }
		public void copyFrom(AlterContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class AlterFlatFlatContext extends AlterContext {
		public TerminalNode FLAT_FLAT() { return getToken(RubatoParser.FLAT_FLAT, 0); }
		public AlterFlatFlatContext(AlterContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).enterAlterFlatFlat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).exitAlterFlatFlat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubatoVisitor ) return ((RubatoVisitor<? extends T>)visitor).visitAlterFlatFlat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AlterDoubleSharpContext extends AlterContext {
		public TerminalNode DOUBLE_SHARP() { return getToken(RubatoParser.DOUBLE_SHARP, 0); }
		public AlterDoubleSharpContext(AlterContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).enterAlterDoubleSharp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).exitAlterDoubleSharp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubatoVisitor ) return ((RubatoVisitor<? extends T>)visitor).visitAlterDoubleSharp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AlterFlatContext extends AlterContext {
		public TerminalNode FLAT() { return getToken(RubatoParser.FLAT, 0); }
		public AlterFlatContext(AlterContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).enterAlterFlat(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).exitAlterFlat(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubatoVisitor ) return ((RubatoVisitor<? extends T>)visitor).visitAlterFlat(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AlterNaturalContext extends AlterContext {
		public TerminalNode NATURAL() { return getToken(RubatoParser.NATURAL, 0); }
		public AlterNaturalContext(AlterContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).enterAlterNatural(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).exitAlterNatural(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubatoVisitor ) return ((RubatoVisitor<? extends T>)visitor).visitAlterNatural(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class AlterSharpContext extends AlterContext {
		public TerminalNode SHARP() { return getToken(RubatoParser.SHARP, 0); }
		public AlterSharpContext(AlterContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).enterAlterSharp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).exitAlterSharp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubatoVisitor ) return ((RubatoVisitor<? extends T>)visitor).visitAlterSharp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final AlterContext alter() throws RecognitionException {
		AlterContext _localctx = new AlterContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_alter);
		try {
			setState(151);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case FLAT:
				_localctx = new AlterFlatContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(146);
				match(FLAT);
				}
				break;
			case FLAT_FLAT:
				_localctx = new AlterFlatFlatContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(147);
				match(FLAT_FLAT);
				}
				break;
			case DOUBLE_SHARP:
				_localctx = new AlterDoubleSharpContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(148);
				match(DOUBLE_SHARP);
				}
				break;
			case SHARP:
				_localctx = new AlterSharpContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(149);
				match(SHARP);
				}
				break;
			case NATURAL:
				_localctx = new AlterNaturalContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(150);
				match(NATURAL);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OctaveContext extends ParserRuleContext {
		public List<TerminalNode> LINED() { return getTokens(RubatoParser.LINED); }
		public TerminalNode LINED(int i) {
			return getToken(RubatoParser.LINED, i);
		}
		public List<TerminalNode> COMMA() { return getTokens(RubatoParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(RubatoParser.COMMA, i);
		}
		public OctaveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_octave; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).enterOctave(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).exitOctave(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubatoVisitor ) return ((RubatoVisitor<? extends T>)visitor).visitOctave(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OctaveContext octave() throws RecognitionException {
		OctaveContext _localctx = new OctaveContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_octave);
		int _la;
		try {
			setState(163);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case LINED:
				enterOuterAlt(_localctx, 1);
				{
				setState(154); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(153);
					match(LINED);
					}
					}
					setState(156); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==LINED );
				}
				break;
			case COMMA:
				enterOuterAlt(_localctx, 2);
				{
				setState(159); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(158);
					match(COMMA);
					}
					}
					setState(161); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==COMMA );
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class DurationContext extends ParserRuleContext {
		public DurationContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_duration; }
	 
		public DurationContext() { }
		public void copyFrom(DurationContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class DurationInvertedIntegerContext extends DurationContext {
		public TerminalNode INT() { return getToken(RubatoParser.INT, 0); }
		public DurationInvertedIntegerContext(DurationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).enterDurationInvertedInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).exitDurationInvertedInteger(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubatoVisitor ) return ((RubatoVisitor<? extends T>)visitor).visitDurationInvertedInteger(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DurationFractionContext extends DurationContext {
		public List<TerminalNode> INT() { return getTokens(RubatoParser.INT); }
		public TerminalNode INT(int i) {
			return getToken(RubatoParser.INT, i);
		}
		public DurationFractionContext(DurationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).enterDurationFraction(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).exitDurationFraction(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubatoVisitor ) return ((RubatoVisitor<? extends T>)visitor).visitDurationFraction(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class DurationIntegerContext extends DurationContext {
		public TerminalNode INT() { return getToken(RubatoParser.INT, 0); }
		public DurationIntegerContext(DurationContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).enterDurationInteger(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).exitDurationInteger(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubatoVisitor ) return ((RubatoVisitor<? extends T>)visitor).visitDurationInteger(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DurationContext duration() throws RecognitionException {
		DurationContext _localctx = new DurationContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_duration);
		try {
			setState(171);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
			case 1:
				_localctx = new DurationFractionContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(165);
				match(INT);
				setState(166);
				match(T__10);
				setState(167);
				match(INT);
				}
				break;
			case 2:
				_localctx = new DurationInvertedIntegerContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(168);
				match(T__10);
				setState(169);
				match(INT);
				}
				break;
			case 3:
				_localctx = new DurationIntegerContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(170);
				match(INT);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NoteSuffixContext extends ParserRuleContext {
		public NoteSuffixContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_noteSuffix; }
	 
		public NoteSuffixContext() { }
		public void copyFrom(NoteSuffixContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class NoteSuffixTieContext extends NoteSuffixContext {
		public NoteSuffixTieContext(NoteSuffixContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).enterNoteSuffixTie(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).exitNoteSuffixTie(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubatoVisitor ) return ((RubatoVisitor<? extends T>)visitor).visitNoteSuffixTie(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NoteSuffixContext noteSuffix() throws RecognitionException {
		NoteSuffixContext _localctx = new NoteSuffixContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_noteSuffix);
		try {
			_localctx = new NoteSuffixTieContext(_localctx);
			enterOuterAlt(_localctx, 1);
			{
			setState(173);
			match(T__11);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ScoreSettingsContext extends ParserRuleContext {
		public ModeTimeContext modeTime() {
			return getRuleContext(ModeTimeContext.class,0);
		}
		public ModeOctaveContext modeOctave() {
			return getRuleContext(ModeOctaveContext.class,0);
		}
		public ModeTimeAndOctaveContext modeTimeAndOctave() {
			return getRuleContext(ModeTimeAndOctaveContext.class,0);
		}
		public ScoreSettingsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_scoreSettings; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).enterScoreSettings(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).exitScoreSettings(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubatoVisitor ) return ((RubatoVisitor<? extends T>)visitor).visitScoreSettings(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScoreSettingsContext scoreSettings() throws RecognitionException {
		ScoreSettingsContext _localctx = new ScoreSettingsContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_scoreSettings);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(175);
			match(T__7);
			setState(179);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__12:
				{
				setState(176);
				modeTime();
				}
				break;
			case T__16:
				{
				setState(177);
				modeOctave();
				}
				break;
			case T__17:
				{
				setState(178);
				modeTimeAndOctave();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(181);
			match(T__8);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ModeTimeContext extends ParserRuleContext {
		public ModeTimeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modeTime; }
	 
		public ModeTimeContext() { }
		public void copyFrom(ModeTimeContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ModeTimeRelativeContext extends ModeTimeContext {
		public ModeTimeRelativeContext(ModeTimeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).enterModeTimeRelative(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).exitModeTimeRelative(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubatoVisitor ) return ((RubatoVisitor<? extends T>)visitor).visitModeTimeRelative(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ModeTimeAbsoluteContext extends ModeTimeContext {
		public ModeTimeAbsoluteContext(ModeTimeContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).enterModeTimeAbsolute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).exitModeTimeAbsolute(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubatoVisitor ) return ((RubatoVisitor<? extends T>)visitor).visitModeTimeAbsolute(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModeTimeContext modeTime() throws RecognitionException {
		ModeTimeContext _localctx = new ModeTimeContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_modeTime);
		try {
			setState(189);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,25,_ctx) ) {
			case 1:
				_localctx = new ModeTimeAbsoluteContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(183);
				match(T__12);
				setState(184);
				match(T__13);
				setState(185);
				match(T__14);
				}
				break;
			case 2:
				_localctx = new ModeTimeRelativeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(186);
				match(T__12);
				setState(187);
				match(T__13);
				setState(188);
				match(T__15);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ModeOctaveContext extends ParserRuleContext {
		public ModeOctaveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modeOctave; }
	 
		public ModeOctaveContext() { }
		public void copyFrom(ModeOctaveContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ModeOctaveAbsoluteContext extends ModeOctaveContext {
		public ModeOctaveAbsoluteContext(ModeOctaveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).enterModeOctaveAbsolute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).exitModeOctaveAbsolute(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubatoVisitor ) return ((RubatoVisitor<? extends T>)visitor).visitModeOctaveAbsolute(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ModeOctaveRelativeContext extends ModeOctaveContext {
		public ModeOctaveRelativeContext(ModeOctaveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).enterModeOctaveRelative(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).exitModeOctaveRelative(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubatoVisitor ) return ((RubatoVisitor<? extends T>)visitor).visitModeOctaveRelative(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModeOctaveContext modeOctave() throws RecognitionException {
		ModeOctaveContext _localctx = new ModeOctaveContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_modeOctave);
		try {
			setState(197);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,26,_ctx) ) {
			case 1:
				_localctx = new ModeOctaveAbsoluteContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(191);
				match(T__16);
				setState(192);
				match(T__13);
				setState(193);
				match(T__14);
				}
				break;
			case 2:
				_localctx = new ModeOctaveRelativeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(194);
				match(T__16);
				setState(195);
				match(T__13);
				setState(196);
				match(T__15);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ModeTimeAndOctaveContext extends ParserRuleContext {
		public ModeTimeAndOctaveContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_modeTimeAndOctave; }
	 
		public ModeTimeAndOctaveContext() { }
		public void copyFrom(ModeTimeAndOctaveContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ModeTimeAndOctaveAbsoluteContext extends ModeTimeAndOctaveContext {
		public ModeTimeAndOctaveAbsoluteContext(ModeTimeAndOctaveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).enterModeTimeAndOctaveAbsolute(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).exitModeTimeAndOctaveAbsolute(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubatoVisitor ) return ((RubatoVisitor<? extends T>)visitor).visitModeTimeAndOctaveAbsolute(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class ModeTimeAndOctaveRelativeContext extends ModeTimeAndOctaveContext {
		public ModeTimeAndOctaveRelativeContext(ModeTimeAndOctaveContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).enterModeTimeAndOctaveRelative(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RubatoListener ) ((RubatoListener)listener).exitModeTimeAndOctaveRelative(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RubatoVisitor ) return ((RubatoVisitor<? extends T>)visitor).visitModeTimeAndOctaveRelative(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ModeTimeAndOctaveContext modeTimeAndOctave() throws RecognitionException {
		ModeTimeAndOctaveContext _localctx = new ModeTimeAndOctaveContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_modeTimeAndOctave);
		try {
			setState(205);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
			case 1:
				_localctx = new ModeTimeAndOctaveAbsoluteContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(199);
				match(T__17);
				setState(200);
				match(T__13);
				setState(201);
				match(T__14);
				}
				break;
			case 2:
				_localctx = new ModeTimeAndOctaveRelativeContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(202);
				match(T__17);
				setState(203);
				match(T__13);
				setState(204);
				match(T__15);
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3!\u00d2\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\7\2$\n\2"+
		"\f\2\16\2\'\13\2\3\2\7\2*\n\2\f\2\16\2-\13\2\3\2\3\2\3\3\3\3\3\3\7\3\64"+
		"\n\3\f\3\16\3\67\13\3\3\3\3\3\3\4\3\4\3\4\7\4>\n\4\f\4\16\4A\13\4\3\4"+
		"\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\7\5M\n\5\f\5\16\5P\13\5\3\5\3\5\5"+
		"\5T\n\5\3\6\7\6W\n\6\f\6\16\6Z\13\6\3\7\3\7\5\7^\n\7\3\7\7\7a\n\7\f\7"+
		"\16\7d\13\7\3\7\7\7g\n\7\f\7\16\7j\13\7\3\7\3\7\6\7n\n\7\r\7\16\7o\3\7"+
		"\3\7\5\7t\n\7\3\7\7\7w\n\7\f\7\16\7z\13\7\3\7\7\7}\n\7\f\7\16\7\u0080"+
		"\13\7\3\7\3\7\5\7\u0084\n\7\3\7\7\7\u0087\n\7\f\7\16\7\u008a\13\7\5\7"+
		"\u008c\n\7\3\b\3\b\5\b\u0090\n\b\3\b\5\b\u0093\n\b\3\t\3\t\3\t\3\t\3\t"+
		"\5\t\u009a\n\t\3\n\6\n\u009d\n\n\r\n\16\n\u009e\3\n\6\n\u00a2\n\n\r\n"+
		"\16\n\u00a3\5\n\u00a6\n\n\3\13\3\13\3\13\3\13\3\13\3\13\5\13\u00ae\n\13"+
		"\3\f\3\f\3\r\3\r\3\r\3\r\5\r\u00b6\n\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\5\16\u00c0\n\16\3\17\3\17\3\17\3\17\3\17\3\17\5\17\u00c8\n\17\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\5\20\u00d0\n\20\3\20\2\2\21\2\4\6\b\n\f\16"+
		"\20\22\24\26\30\32\34\36\2\2\2\u00e4\2 \3\2\2\2\4\60\3\2\2\2\6:\3\2\2"+
		"\2\bS\3\2\2\2\nX\3\2\2\2\f\u008b\3\2\2\2\16\u008d\3\2\2\2\20\u0099\3\2"+
		"\2\2\22\u00a5\3\2\2\2\24\u00ad\3\2\2\2\26\u00af\3\2\2\2\30\u00b1\3\2\2"+
		"\2\32\u00bf\3\2\2\2\34\u00c7\3\2\2\2\36\u00cf\3\2\2\2 !\7\3\2\2!%\7\4"+
		"\2\2\"$\5\30\r\2#\"\3\2\2\2$\'\3\2\2\2%#\3\2\2\2%&\3\2\2\2&+\3\2\2\2\'"+
		"%\3\2\2\2(*\5\4\3\2)(\3\2\2\2*-\3\2\2\2+)\3\2\2\2+,\3\2\2\2,.\3\2\2\2"+
		"-+\3\2\2\2./\7\5\2\2/\3\3\2\2\2\60\61\7\6\2\2\61\65\7\4\2\2\62\64\5\6"+
		"\4\2\63\62\3\2\2\2\64\67\3\2\2\2\65\63\3\2\2\2\65\66\3\2\2\2\668\3\2\2"+
		"\2\67\65\3\2\2\289\7\5\2\29\5\3\2\2\2:;\7\7\2\2;?\7\4\2\2<>\5\b\5\2=<"+
		"\3\2\2\2>A\3\2\2\2?=\3\2\2\2?@\3\2\2\2@B\3\2\2\2A?\3\2\2\2BC\7\5\2\2C"+
		"\7\3\2\2\2DE\7\b\2\2EF\7\4\2\2FT\7\5\2\2GH\7\b\2\2HI\7\4\2\2IN\5\n\6\2"+
		"JK\7\t\2\2KM\5\n\6\2LJ\3\2\2\2MP\3\2\2\2NL\3\2\2\2NO\3\2\2\2OQ\3\2\2\2"+
		"PN\3\2\2\2QR\7\5\2\2RT\3\2\2\2SD\3\2\2\2SG\3\2\2\2T\t\3\2\2\2UW\5\f\7"+
		"\2VU\3\2\2\2WZ\3\2\2\2XV\3\2\2\2XY\3\2\2\2Y\13\3\2\2\2ZX\3\2\2\2[]\5\16"+
		"\b\2\\^\5\24\13\2]\\\3\2\2\2]^\3\2\2\2^b\3\2\2\2_a\7\36\2\2`_\3\2\2\2"+
		"ad\3\2\2\2b`\3\2\2\2bc\3\2\2\2ch\3\2\2\2db\3\2\2\2eg\5\26\f\2fe\3\2\2"+
		"\2gj\3\2\2\2hf\3\2\2\2hi\3\2\2\2i\u008c\3\2\2\2jh\3\2\2\2km\7\n\2\2ln"+
		"\5\16\b\2ml\3\2\2\2no\3\2\2\2om\3\2\2\2op\3\2\2\2pq\3\2\2\2qs\7\13\2\2"+
		"rt\5\24\13\2sr\3\2\2\2st\3\2\2\2tx\3\2\2\2uw\7\36\2\2vu\3\2\2\2wz\3\2"+
		"\2\2xv\3\2\2\2xy\3\2\2\2y~\3\2\2\2zx\3\2\2\2{}\5\26\f\2|{\3\2\2\2}\u0080"+
		"\3\2\2\2~|\3\2\2\2~\177\3\2\2\2\177\u008c\3\2\2\2\u0080~\3\2\2\2\u0081"+
		"\u0083\7\f\2\2\u0082\u0084\5\24\13\2\u0083\u0082\3\2\2\2\u0083\u0084\3"+
		"\2\2\2\u0084\u0088\3\2\2\2\u0085\u0087\7\36\2\2\u0086\u0085\3\2\2\2\u0087"+
		"\u008a\3\2\2\2\u0088\u0086\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008c\3\2"+
		"\2\2\u008a\u0088\3\2\2\2\u008b[\3\2\2\2\u008bk\3\2\2\2\u008b\u0081\3\2"+
		"\2\2\u008c\r\3\2\2\2\u008d\u008f\7\25\2\2\u008e\u0090\5\20\t\2\u008f\u008e"+
		"\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0092\3\2\2\2\u0091\u0093\5\22\n\2"+
		"\u0092\u0091\3\2\2\2\u0092\u0093\3\2\2\2\u0093\17\3\2\2\2\u0094\u009a"+
		"\7\26\2\2\u0095\u009a\7\27\2\2\u0096\u009a\7\30\2\2\u0097\u009a\7\31\2"+
		"\2\u0098\u009a\7\32\2\2\u0099\u0094\3\2\2\2\u0099\u0095\3\2\2\2\u0099"+
		"\u0096\3\2\2\2\u0099\u0097\3\2\2\2\u0099\u0098\3\2\2\2\u009a\21\3\2\2"+
		"\2\u009b\u009d\7\33\2\2\u009c\u009b\3\2\2\2\u009d\u009e\3\2\2\2\u009e"+
		"\u009c\3\2\2\2\u009e\u009f\3\2\2\2\u009f\u00a6\3\2\2\2\u00a0\u00a2\7\34"+
		"\2\2\u00a1\u00a0\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3"+
		"\u00a4\3\2\2\2\u00a4\u00a6\3\2\2\2\u00a5\u009c\3\2\2\2\u00a5\u00a1\3\2"+
		"\2\2\u00a6\23\3\2\2\2\u00a7\u00a8\7\35\2\2\u00a8\u00a9\7\r\2\2\u00a9\u00ae"+
		"\7\35\2\2\u00aa\u00ab\7\r\2\2\u00ab\u00ae\7\35\2\2\u00ac\u00ae\7\35\2"+
		"\2\u00ad\u00a7\3\2\2\2\u00ad\u00aa\3\2\2\2\u00ad\u00ac\3\2\2\2\u00ae\25"+
		"\3\2\2\2\u00af\u00b0\7\16\2\2\u00b0\27\3\2\2\2\u00b1\u00b5\7\n\2\2\u00b2"+
		"\u00b6\5\32\16\2\u00b3\u00b6\5\34\17\2\u00b4\u00b6\5\36\20\2\u00b5\u00b2"+
		"\3\2\2\2\u00b5\u00b3\3\2\2\2\u00b5\u00b4\3\2\2\2\u00b6\u00b7\3\2\2\2\u00b7"+
		"\u00b8\7\13\2\2\u00b8\31\3\2\2\2\u00b9\u00ba\7\17\2\2\u00ba\u00bb\7\20"+
		"\2\2\u00bb\u00c0\7\21\2\2\u00bc\u00bd\7\17\2\2\u00bd\u00be\7\20\2\2\u00be"+
		"\u00c0\7\22\2\2\u00bf\u00b9\3\2\2\2\u00bf\u00bc\3\2\2\2\u00c0\33\3\2\2"+
		"\2\u00c1\u00c2\7\23\2\2\u00c2\u00c3\7\20\2\2\u00c3\u00c8\7\21\2\2\u00c4"+
		"\u00c5\7\23\2\2\u00c5\u00c6\7\20\2\2\u00c6\u00c8\7\22\2\2\u00c7\u00c1"+
		"\3\2\2\2\u00c7\u00c4\3\2\2\2\u00c8\35\3\2\2\2\u00c9\u00ca\7\24\2\2\u00ca"+
		"\u00cb\7\20\2\2\u00cb\u00d0\7\21\2\2\u00cc\u00cd\7\24\2\2\u00cd\u00ce"+
		"\7\20\2\2\u00ce\u00d0\7\22\2\2\u00cf\u00c9\3\2\2\2\u00cf\u00cc\3\2\2\2"+
		"\u00d0\37\3\2\2\2\36%+\65?NSX]bhosx~\u0083\u0088\u008b\u008f\u0092\u0099"+
		"\u009e\u00a3\u00a5\u00ad\u00b5\u00bf\u00c7\u00cf";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}