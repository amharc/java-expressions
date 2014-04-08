package amharc.expr.lexer;

public class LeftParenToken extends Token {
	private final static LeftParenToken instance = new LeftParenToken();
	
	private LeftParenToken() { }
	
	public static LeftParenToken getInstance() {
		return instance;
	}
}
