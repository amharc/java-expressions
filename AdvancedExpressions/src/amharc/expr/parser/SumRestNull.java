package amharc.expr.parser;

import amharc.expr.Expression;

class SumRestNull extends AbstractSumRest {
	public Expression process(Expression ex) {
		return ex;
	}
}