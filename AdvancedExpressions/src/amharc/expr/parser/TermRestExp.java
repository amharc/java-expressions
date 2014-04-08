package amharc.expr.parser;

import amharc.expr.Expression;

class TermRestExp extends AbstractTermRest {
	@Override
	public Expression process(Expression expression) {
		return expression.exp();
	}
}