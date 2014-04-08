package amharc.expr.parser;

import amharc.expr.Expression;

class TermRestCos extends AbstractTermRest {
	@Override
	public Expression process(Expression expression) {
		return expression.cos();
	}
}