package amharc.expr.parser;

import amharc.expr.Expression;

class TermRestUnaryMinus extends AbstractTermRest {
	@Override
	public Expression process(Expression expression) {
		return expression.negate();
	}
}