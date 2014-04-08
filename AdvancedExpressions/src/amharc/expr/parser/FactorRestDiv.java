package amharc.expr.parser;

import amharc.expr.Expression;
import amharc.expr.lexer.IllegalExpressionException;

class FactorRestDiv extends AbstractFactorRest {
	private final Factor factor;
	
	public FactorRestDiv(Factor factor) {
		this.factor = factor;
	}
	
	public Expression process(Expression ex) throws IllegalExpressionException {
		return factor.getExpression().divide(ex);
	}
}