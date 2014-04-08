package amharc.expr.lexer;

public class DivToken extends Token {
	private final static DivToken instance = new DivToken();
	
	private DivToken() { }
	
	public static DivToken getInstance() {
		return instance;
	}
}
