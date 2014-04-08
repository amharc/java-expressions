package amharc.expr.parser;

import amharc.expr.Expression;
import amharc.expr.lexer.IllegalExpressionException;

abstract class AbstractFactorRest {
	public abstract Expression process(Expression ex) throws IllegalExpressionException;
}