package amharc.expr.lexer;

public class MinusToken extends Token {
	private final static MinusToken instance = new MinusToken();
	
	private MinusToken() { }
	
	public static MinusToken getInstance() {
		return instance;
	}
}
