package amharc.expr.parser;

import amharc.expr.Expression;

class TermRestNull extends AbstractTermRest {
	@Override
	public Expression process(Expression expression) {
		return expression;
	}
}