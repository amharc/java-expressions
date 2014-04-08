package amharc.expr.parser;

import java.util.Stack;

import amharc.expr.Expression;
import amharc.expr.lexer.IllegalExpressionException;
import amharc.expr.lexer.Token;

class Factor extends NonTerminal {
	private Term term = null;
	private FactorRest rest = null;
	
	@Override
	public Expression getExpression() throws IllegalExpressionException {
		return rest.process(term.getExpression());
	}

	@Override
	public boolean react(Token token, Stack<Symbol> symbols)
			throws IllegalExpressionException {
		term = new Term();
		rest = new FactorRest();
		
		symbols.add(rest);
		symbols.add(term);
		return false;
	}
}