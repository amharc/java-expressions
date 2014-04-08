package amharc.expr.parser;

import java.util.Stack;

import amharc.expr.lexer.IllegalExpressionException;
import amharc.expr.lexer.Token;

class Terminal extends Symbol {
	private final Token token;
	
	public Terminal(Token token) {
		this.token = token;
	}
	
	@Override
	public boolean react(Token token, Stack<Symbol> symbols) throws IllegalExpressionException {
		if(token.equals(this.token)) {
			return true;
		}
		throw new IllegalExpressionException();
	}
}