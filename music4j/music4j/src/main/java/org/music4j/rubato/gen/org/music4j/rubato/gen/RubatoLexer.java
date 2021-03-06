// Generated from Rubato.g4 by ANTLR 4.4

    package org.music4j.rubato.gen;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RubatoLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.4", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__17=1, T__16=2, T__15=3, T__14=4, T__13=5, T__12=6, T__11=7, T__10=8, 
		T__9=9, T__8=10, T__7=11, T__6=12, T__5=13, T__4=14, T__3=15, T__2=16, 
		T__1=17, T__0=18, STEP=19, FLAT=20, FLAT_FLAT=21, DOUBLE_SHARP=22, SHARP=23, 
		NATURAL=24, LINED=25, COMMA=26, INT=27, DOT=28, LINE_COMMENT=29, COMMENT=30, 
		WHITESPACE=31;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] tokenNames = {
		"'\\u0000'", "'\\u0001'", "'\\u0002'", "'\\u0003'", "'\\u0004'", "'\\u0005'", 
		"'\\u0006'", "'\\u0007'", "'\b'", "'\t'", "'\n'", "'\\u000B'", "'\f'", 
		"'\r'", "'\\u000E'", "'\\u000F'", "'\\u0010'", "'\\u0011'", "'\\u0012'", 
		"'\\u0013'", "'\\u0014'", "'\\u0015'", "'\\u0016'", "'\\u0017'", "'\\u0018'", 
		"'\\u0019'", "'\\u001A'", "'\\u001B'", "'\\u001C'", "'\\u001D'", "'\\u001E'", 
		"'\\u001F'"
	};
	public static final String[] ruleNames = {
		"T__17", "T__16", "T__15", "T__14", "T__13", "T__12", "T__11", "T__10", 
		"T__9", "T__8", "T__7", "T__6", "T__5", "T__4", "T__3", "T__2", "T__1", 
		"T__0", "STEP", "FLAT", "FLAT_FLAT", "DOUBLE_SHARP", "SHARP", "NATURAL", 
		"LINED", "COMMA", "INT", "DOT", "LINE_COMMENT", "COMMENT", "WHITESPACE"
	};


	public RubatoLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Rubato.g4"; }

	@Override
	public String[] getTokenNames() { return tokenNames; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2!\u00d3\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \3\2"+
		"\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\b\3\b\3\t"+
		"\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\f\3\f\3\r\3\r\3\16\3\16\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\25\3\25\3\26\3\26\3\26\3\27"+
		"\3\27\3\30\3\30\3\31\3\31\3\32\3\32\3\33\3\33\3\34\3\34\3\34\7\34\u00ac"+
		"\n\34\f\34\16\34\u00af\13\34\5\34\u00b1\n\34\3\35\3\35\3\36\3\36\3\36"+
		"\3\36\7\36\u00b9\n\36\f\36\16\36\u00bc\13\36\3\36\3\36\3\36\3\36\3\37"+
		"\3\37\3\37\3\37\7\37\u00c6\n\37\f\37\16\37\u00c9\13\37\3\37\3\37\3\37"+
		"\3\37\3\37\3 \3 \3 \3 \4\u00ba\u00c7\2!\3\3\5\4\7\5\t\6\13\7\r\b\17\t"+
		"\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27"+
		"-\30/\31\61\32\63\33\65\34\67\359\36;\37= ?!\3\2\6\3\2CI\3\2\63;\3\2\62"+
		";\5\2\13\f\17\17\"\"\u00d6\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2"+
		"\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2"+
		"\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3"+
		"\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2"+
		"\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67"+
		"\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\3A\3\2\2\2\5C\3\2"+
		"\2\2\7I\3\2\2\2\tK\3\2\2\2\13W\3\2\2\2\r`\3\2\2\2\17b\3\2\2\2\21d\3\2"+
		"\2\2\23f\3\2\2\2\25k\3\2\2\2\27m\3\2\2\2\31o\3\2\2\2\33q\3\2\2\2\35s\3"+
		"\2\2\2\37|\3\2\2\2!\u0081\3\2\2\2#\u008b\3\2\2\2%\u0091\3\2\2\2\'\u0097"+
		"\3\2\2\2)\u0099\3\2\2\2+\u009b\3\2\2\2-\u009e\3\2\2\2/\u00a0\3\2\2\2\61"+
		"\u00a2\3\2\2\2\63\u00a4\3\2\2\2\65\u00a6\3\2\2\2\67\u00b0\3\2\2\29\u00b2"+
		"\3\2\2\2;\u00b4\3\2\2\2=\u00c1\3\2\2\2?\u00cf\3\2\2\2AB\7\61\2\2B\4\3"+
		"\2\2\2CD\7X\2\2DE\7q\2\2EF\7k\2\2FG\7e\2\2GH\7g\2\2H\6\3\2\2\2IJ\7T\2"+
		"\2J\b\3\2\2\2KL\7O\2\2LM\7Q\2\2MN\7F\2\2NO\7G\2\2OP\7a\2\2PQ\7Q\2\2QR"+
		"\7E\2\2RS\7V\2\2ST\7C\2\2TU\7X\2\2UV\7G\2\2V\n\3\2\2\2WX\7c\2\2XY\7d\2"+
		"\2YZ\7u\2\2Z[\7q\2\2[\\\7n\2\2\\]\7w\2\2]^\7v\2\2^_\7g\2\2_\f\3\2\2\2"+
		"`a\7<\2\2a\16\3\2\2\2bc\7]\2\2c\20\3\2\2\2de\7}\2\2e\22\3\2\2\2fg\7O\2"+
		"\2gh\7Q\2\2hi\7F\2\2ij\7G\2\2j\24\3\2\2\2kl\7~\2\2l\26\3\2\2\2mn\7_\2"+
		"\2n\30\3\2\2\2op\7\177\2\2p\32\3\2\2\2qr\7\u0080\2\2r\34\3\2\2\2st\7t"+
		"\2\2tu\7g\2\2uv\7n\2\2vw\7c\2\2wx\7v\2\2xy\7k\2\2yz\7x\2\2z{\7g\2\2{\36"+
		"\3\2\2\2|}\7R\2\2}~\7c\2\2~\177\7t\2\2\177\u0080\7v\2\2\u0080 \3\2\2\2"+
		"\u0081\u0082\7O\2\2\u0082\u0083\7Q\2\2\u0083\u0084\7F\2\2\u0084\u0085"+
		"\7G\2\2\u0085\u0086\7a\2\2\u0086\u0087\7V\2\2\u0087\u0088\7K\2\2\u0088"+
		"\u0089\7O\2\2\u0089\u008a\7G\2\2\u008a\"\3\2\2\2\u008b\u008c\7U\2\2\u008c"+
		"\u008d\7v\2\2\u008d\u008e\7c\2\2\u008e\u008f\7h\2\2\u008f\u0090\7h\2\2"+
		"\u0090$\3\2\2\2\u0091\u0092\7U\2\2\u0092\u0093\7e\2\2\u0093\u0094\7q\2"+
		"\2\u0094\u0095\7t\2\2\u0095\u0096\7g\2\2\u0096&\3\2\2\2\u0097\u0098\t"+
		"\2\2\2\u0098(\3\2\2\2\u0099\u009a\7d\2\2\u009a*\3\2\2\2\u009b\u009c\7"+
		"d\2\2\u009c\u009d\7d\2\2\u009d,\3\2\2\2\u009e\u009f\7z\2\2\u009f.\3\2"+
		"\2\2\u00a0\u00a1\7%\2\2\u00a1\60\3\2\2\2\u00a2\u00a3\7#\2\2\u00a3\62\3"+
		"\2\2\2\u00a4\u00a5\7)\2\2\u00a5\64\3\2\2\2\u00a6\u00a7\7.\2\2\u00a7\66"+
		"\3\2\2\2\u00a8\u00b1\7\62\2\2\u00a9\u00ad\t\3\2\2\u00aa\u00ac\t\4\2\2"+
		"\u00ab\u00aa\3\2\2\2\u00ac\u00af\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae"+
		"\3\2\2\2\u00ae\u00b1\3\2\2\2\u00af\u00ad\3\2\2\2\u00b0\u00a8\3\2\2\2\u00b0"+
		"\u00a9\3\2\2\2\u00b18\3\2\2\2\u00b2\u00b3\7\60\2\2\u00b3:\3\2\2\2\u00b4"+
		"\u00b5\7\61\2\2\u00b5\u00b6\7\61\2\2\u00b6\u00ba\3\2\2\2\u00b7\u00b9\13"+
		"\2\2\2\u00b8\u00b7\3\2\2\2\u00b9\u00bc\3\2\2\2\u00ba\u00bb\3\2\2\2\u00ba"+
		"\u00b8\3\2\2\2\u00bb\u00bd\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bd\u00be\7\f"+
		"\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c0\b\36\2\2\u00c0<\3\2\2\2\u00c1\u00c2"+
		"\7\61\2\2\u00c2\u00c3\7,\2\2\u00c3\u00c7\3\2\2\2\u00c4\u00c6\13\2\2\2"+
		"\u00c5\u00c4\3\2\2\2\u00c6\u00c9\3\2\2\2\u00c7\u00c8\3\2\2\2\u00c7\u00c5"+
		"\3\2\2\2\u00c8\u00ca\3\2\2\2\u00c9\u00c7\3\2\2\2\u00ca\u00cb\7,\2\2\u00cb"+
		"\u00cc\7\61\2\2\u00cc\u00cd\3\2\2\2\u00cd\u00ce\b\37\2\2\u00ce>\3\2\2"+
		"\2\u00cf\u00d0\t\5\2\2\u00d0\u00d1\3\2\2\2\u00d1\u00d2\b \2\2\u00d2@\3"+
		"\2\2\2\7\2\u00ad\u00b0\u00ba\u00c7\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}