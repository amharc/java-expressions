package amharc.expr.parser;

import amharc.expr.Expression;
import amharc.expr.lexer.IllegalExpressionException;

class FactorRestTimes extends AbstractFactorRest {
	private final Factor factor;
	
	public FactorRestTimes(Factor factor) {
		this.factor = factor;
	}
	
	public Expression process(Expression ex) throws IllegalExpressionException {
		return factor.getExpression().multiply(ex);
	}
}