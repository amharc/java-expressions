package amharc.expr.parser;

import java.util.Stack;

import amharc.expr.Expression;
import amharc.expr.lexer.IllegalExpressionException;
import amharc.expr.lexer.MinusToken;
import amharc.expr.lexer.PlusToken;
import amharc.expr.lexer.Token;

class SumRest extends NonTerminal {
	private AbstractSumRest content = null;
	
	@Override
	public boolean react(Token token, Stack<Symbol> symbols) {
		if(token.equals(PlusToken.getInstance())) {
			// Predict and match
			Sum sum = new Sum();
			content = new SumRestPlus(sum);
			symbols.add(sum);
			return true;
		}
		else if(token.equals(MinusToken.getInstance())) {
			// Predict and match
			Sum sum = new Sum();
			content = new SumRestMinus(sum);
			symbols.add(sum);
			return true;
		}
		else {
			// Predict epsilon
			content = new SumRestNull();
			return false;
		}
	}
	
	public Expression process(Expression ex) throws IllegalExpressionException {
		return content.process(ex);
	}
}