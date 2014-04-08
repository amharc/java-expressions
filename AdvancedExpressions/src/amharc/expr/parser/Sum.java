package amharc.expr.parser;

import java.util.Stack;

import amharc.expr.Expression;
import amharc.expr.lexer.IllegalExpressionException;
import amharc.expr.lexer.Token;

class Sum extends NonTerminal {
	private Factor factor = null;
	private SumRest rest = null;
	
	@Override
	public boolean react(Token token, Stack<Symbol> symbols) {
		factor = new Factor();
		rest = new SumRest();
		symbols.add(rest);
		symbols.add(factor);
		return false;
	}
	
	@Override
	public Expression getExpression() throws IllegalExpressionException {
		return rest.process(factor.getExpression());
	}
}