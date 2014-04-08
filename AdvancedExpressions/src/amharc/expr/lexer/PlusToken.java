package amharc.expr.lexer;

public class PlusToken extends Token {
	private final static PlusToken instance = new PlusToken();
	
	private PlusToken() { }
	
	public static PlusToken getInstance() {
		return instance;
	}
}
