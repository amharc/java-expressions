package amharc.expr.lexer;


public class EndOfExpressionToken extends Token {
	private static EndOfExpressionToken instance = new EndOfExpressionToken();
	
	private EndOfExpressionToken() { }
	
	public static EndOfExpressionToken getInstance() {
		return instance;
	}
}
