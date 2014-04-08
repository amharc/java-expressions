package amharc.expr.lexer;

public class VariableToken extends Token {
	private final static VariableToken instance = new VariableToken();

	private VariableToken() { }
	
	public static VariableToken getInstance() {
		return instance;
	}
}
