package amharc.expr.lexer;

public class Lexer {
	private String str;
	private Token lastToken;
	private int index;
	
	public Lexer(String string) {
		this.str = string;
		this.lastToken = null;
		this.index = 0;
	}
	
	public boolean hasNextToken() {
		return !EndOfExpressionToken.getInstance().equals(lastToken); 
	}
	
	private boolean isPartOfDouble(char c) {
		return Character.isDigit(c) || c == '.' || c == 'E';
	}
	
	public Token getNextToken() throws IllegalExpressionException {
		skipWhitespace();
		if(index == str.length())
			return lastToken = EndOfExpressionToken.getInstance();
		
		switch(str.charAt(index)) {
		case '+':
			++index;
			return lastToken = PlusToken.getInstance();
		case '-':
			++index;
			if(lastToken == null || lastToken.equals(LeftParenToken.getInstance())
					|| lastToken.equals(EvaluateToken.getInstance()))
				return lastToken = UnaryMinusToken.getInstance();
			else
				return lastToken = MinusToken.getInstance();
		case '*':
			++index;
			return lastToken = TimesToken.getInstance();
		case '/':
			++index;
			return lastToken = DivToken.getInstance();
		case '(':
			++index;
			return lastToken = LeftParenToken.getInstance();
		case ')':
			++index;
			return lastToken = RightParenToken.getInstance();
		case '\'':
			++index;
			return lastToken = DerivativeToken.getInstance();
		case 'x':
			++index;
			return lastToken = VariableToken.getInstance();
		case '$':
			++index;
			return lastToken = EvaluateToken.getInstance();
		default:
			if(Character.isDigit(str.charAt(index))) {
				StringBuilder sb = new StringBuilder();
				while(index < str.length() && isPartOfDouble(str.charAt(index)))
					sb.append(str.charAt(index++));
				return lastToken = new NumberToken(Double.parseDouble(sb.toString()));
			}
			else if(str.startsWith("exp", index)) {
				index += 3;
				return lastToken = ExpToken.getInstance();
			}
			else if(str.startsWith("sin", index)) {
				index += 3;
				return lastToken = SinToken.getInstance();
			}
			else if(str.startsWith("cos", index)) {
				index += 3;
				return lastToken = CosToken.getInstance();
			}
			else {
				throw new IllegalExpressionException();
			}
		}
	}
	
	private void skipWhitespace() {
		while(index < str.length() && Character.isWhitespace(str.charAt(index)))
			++index;
	}
}
