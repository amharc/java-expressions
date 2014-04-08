package amharc.expr.parser;

import amharc.expr.Expression;
import amharc.expr.lexer.IllegalExpressionException;

abstract class AbstractTerm {
	public abstract Expression getExpression() throws IllegalExpressionException;
}