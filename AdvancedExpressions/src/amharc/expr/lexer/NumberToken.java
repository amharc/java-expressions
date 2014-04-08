package amharc.expr.lexer;

public class NumberToken extends Token {
	private final double value;
	
	public NumberToken(double value) {
		this.value = value;
	}
	
	public double getValue() {
		return value;
	}
}
