package amharc.expr.parser;

import java.util.Stack;

import amharc.expr.Expression;
import amharc.expr.lexer.EvaluateToken;
import amharc.expr.lexer.IllegalExpressionException;
import amharc.expr.lexer.Token;

class ParsingExpressionRest extends NonTerminal {
	private AbstractParsingExpressionRest content = null;
	
	@Override
	public boolean react(Token token, Stack<Symbol> symbols)
			throws IllegalExpressionException {
		if(token.equals(EvaluateToken.getInstance())) {
			ParsingExpression lhs = new ParsingExpression();
			symbols.add(lhs);
			content = new ParsingExpressionRestEvaluate(lhs);
			return true;
		}
		else {
			content = new ParsingExpressionRestNull();
			return false;
		}
	}
	
	public Expression process(Expression ex) throws IllegalExpressionException {
		return content.process(ex);
	}
}