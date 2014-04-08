package amharc.expr.parser;

import amharc.expr.Expression;
import amharc.expr.lexer.IllegalExpressionException;

class ParserExpressionTerm extends AbstractTerm {
	private ParsingExpression expr;
	
	public ParserExpressionTerm(ParsingExpression expr) {
		this.expr = expr;
	}
	
	@Override
	public Expression getExpression() throws IllegalExpressionException {
		return expr.getExpression();
	}
}