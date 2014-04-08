package amharc.expr.parser;

import java.util.Stack;

import amharc.expr.Expression;
import amharc.expr.lexer.IllegalExpressionException;
import amharc.expr.lexer.Token;

class ParsingExpression extends NonTerminal {
	private Sum sum = null;
	private ParsingExpressionRest rest = null;
	
	@Override
	public Expression getExpression() throws IllegalExpressionException {
		return rest.process(sum.getExpression());
	}
	
	@Override
	public boolean react(Token token, Stack<Symbol> symbols) {
		rest = new ParsingExpressionRest();
		symbols.add(rest);
		sum = new Sum();
		symbols.add(sum);
		return false;
	}
}