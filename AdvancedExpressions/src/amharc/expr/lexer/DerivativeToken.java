package amharc.expr.lexer;

public class DerivativeToken extends Token {
	private final static DerivativeToken instance = new DerivativeToken();
	
	private DerivativeToken() { }
	
	public static DerivativeToken getInstance() {
		return instance;
	}
}
