package amharc.expr.lexer;

public class RightParenToken extends Token {
	private final static RightParenToken instance = new RightParenToken();
	
	private RightParenToken() { }
	
	public static RightParenToken getInstance() {
		return instance;
	}
}
