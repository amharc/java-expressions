package amharc.expr.parser;

import amharc.expr.Expression;
import amharc.expr.lexer.IllegalExpressionException;

class SumRestMinus extends AbstractSumRest {
	private final Sum sum;
	
	public SumRestMinus(Sum sum) {
		this.sum = sum;
	}
	
	public Expression process(Expression ex) throws IllegalExpressionException {
		return sum.getExpression().subtract(ex);
	}
}