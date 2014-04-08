package amharc.expr.parser;

import amharc.expr.Expression;
import amharc.expr.lexer.IllegalExpressionException;

class SumRestPlus extends AbstractSumRest {
	private final Sum sum;
	
	public SumRestPlus(Sum sum) {
		this.sum = sum;
	}
	
	public Expression process(Expression ex) throws IllegalExpressionException {
		return sum.getExpression().add(ex);
	}
}