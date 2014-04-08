package amharc.expr.lexer;

public class EvaluateToken extends Token {
	private static EvaluateToken instance = new EvaluateToken();
	
	private EvaluateToken() { }
	
	public static EvaluateToken getInstance() {
		return instance;
	}
}
