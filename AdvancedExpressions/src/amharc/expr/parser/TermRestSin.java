package amharc.expr.parser;

import amharc.expr.Expression;

class TermRestSin extends AbstractTermRest {
	@Override
	public Expression process(Expression expression) {
		return expression.sin();
	}
}