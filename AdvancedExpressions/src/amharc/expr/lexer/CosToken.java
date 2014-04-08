package amharc.expr.lexer;

public class CosToken extends Token {
	private static final CosToken instance = new CosToken();

	private CosToken() { }
	
	public static CosToken getInstance() {
		return instance;
	}
}
