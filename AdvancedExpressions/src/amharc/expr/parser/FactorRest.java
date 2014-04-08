package amharc.expr.parser;

import java.util.Stack;

import amharc.expr.Expression;
import amharc.expr.lexer.DivToken;
import amharc.expr.lexer.IllegalExpressionException;
import amharc.expr.lexer.TimesToken;
import amharc.expr.lexer.Token;

class FactorRest extends NonTerminal {
	private AbstractFactorRest content = null;
	
	public Expression process(Expression ex) throws IllegalExpressionException {
		return content.process(ex);
	}

	@Override
	public boolean react(Token token, Stack<Symbol> symbols)
			throws IllegalExpressionException {
		if(token.equals(TimesToken.getInstance())) {
			// Predict and match
			Factor factor = new Factor();
			content = new FactorRestTimes(factor);
			symbols.add(factor);
			return true;
		}
		else if(token.equals(DivToken.getInstance())) {
			// Predict and match
			Factor factor = new Factor();
			content = new FactorRestDiv(factor);
			symbols.add(factor);
			return true;
		}
		else {
			// Predict epsilon
			content = new FactorRestNull();
			return false;
		}
	}
}