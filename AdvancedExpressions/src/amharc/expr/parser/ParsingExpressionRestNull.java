package amharc.expr.parser;

import amharc.expr.Expression;

class ParsingExpressionRestNull extends AbstractParsingExpressionRest {
	@Override
	public Expression process(Expression e) {
		return e;
	}	
}