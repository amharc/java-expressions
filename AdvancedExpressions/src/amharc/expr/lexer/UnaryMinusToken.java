package amharc.expr.lexer;

public class UnaryMinusToken extends Token {
	private final static UnaryMinusToken instance = new UnaryMinusToken();
	
	private UnaryMinusToken() { }
	
	public static UnaryMinusToken getInstance() {
		return instance;
	}
}
