package amharc.expr.parser;

import java.util.Stack;

import amharc.expr.Expression;
import amharc.expr.lexer.IllegalExpressionException;
import amharc.expr.lexer.Token;

abstract class Symbol {
	public Expression getExpression() throws IllegalExpressionException {
		return null;
	}
	
	/**
	 * @param token Token to be parsed
	 * @param symbols Stack of parsing symbols, without the current one
	 * @return true iff match occurred, false iff predict
	 * @throws IllegalExpressionException
	 */
	public abstract boolean react(Token token, Stack<Symbol> symbols) throws IllegalExpressionException;
}