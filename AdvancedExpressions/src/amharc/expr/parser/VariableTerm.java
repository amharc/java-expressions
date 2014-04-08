package amharc.expr.parser;

import amharc.expr.Expression;

class VariableTerm extends AbstractTerm {
	@Override
	public Expression getExpression() {
		return Expression.getVariable();
	}
}