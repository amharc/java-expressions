package amharc.expr.parser;

import amharc.expr.Expression;
import amharc.expr.lexer.IllegalExpressionException;

abstract class AbstractSumRest { 
	public abstract Expression process(Expression ex) throws IllegalExpressionException;
}