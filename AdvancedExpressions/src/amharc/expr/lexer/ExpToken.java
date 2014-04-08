package amharc.expr.lexer;

public class ExpToken extends Token {
	private final static ExpToken instance = new ExpToken();
	
	private ExpToken() { }
	
	public static ExpToken getInstance() {
		return instance;
	}
}
