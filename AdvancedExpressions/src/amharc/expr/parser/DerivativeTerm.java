package amharc.expr.parser;

import amharc.expr.Expression;
import amharc.expr.lexer.IllegalExpressionException;

class DerivativeTerm extends AbstractTerm {
	private Term term;
	
	public DerivativeTerm(Term term) {
		this.term = term;
	}
	
	@Override
	public Expression getExpression() throws IllegalExpressionException {
		return term.getExpression().derivative();
	}
}