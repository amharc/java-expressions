package amharc.expr.parser;

import amharc.expr.Expression;

class NumberTerm extends AbstractTerm {
	private double number;
	
	public NumberTerm(double number) {
		this.number = number;
	}
	
	@Override
	public Expression getExpression() {
		return Expression.getConstant(number);
	}
}