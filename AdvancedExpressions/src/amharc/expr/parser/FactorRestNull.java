package amharc.expr.parser;

import amharc.expr.Expression;

class FactorRestNull extends AbstractFactorRest {
	public Expression process(Expression ex) {
		return ex;
	}
}