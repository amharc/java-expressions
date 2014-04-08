package amharc.expr.lexer;

public class SinToken extends Token {
	private static final SinToken instance = new SinToken();
	
	private SinToken() { }
	
	public static SinToken getInstance() {
		return instance;
	}
}
