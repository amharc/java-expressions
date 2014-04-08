package amharc.expr.parser;

import java.util.Stack;

import amharc.expr.Expression;
import amharc.expr.lexer.DerivativeToken;
import amharc.expr.lexer.IllegalExpressionException;
import amharc.expr.lexer.LeftParenToken;
import amharc.expr.lexer.NumberToken;
import amharc.expr.lexer.RightParenToken;
import amharc.expr.lexer.Token;
import amharc.expr.lexer.VariableToken;

class Term extends NonTerminal {
	private AbstractTerm content = null;
	private TermRest rest = null;
			
	@Override
	public boolean react(Token token, Stack<Symbol> symbols)
			throws IllegalExpressionException {
		rest = new TermRest();
		symbols.push(rest);
		
		if(token.equals(VariableToken.getInstance())) {
			// Predict and match
			content = new VariableTerm();
			return true;
		}
		else if(token.equals(DerivativeToken.getInstance())) {
			// Predict and match
			Term term = new Term();
			content = new DerivativeTerm(term);
			symbols.add(term);
			return true;
		}
		else if(token.equals(RightParenToken.getInstance())) {
			// Predict and match
			symbols.add(new Terminal(LeftParenToken.getInstance()));
			ParsingExpression expr = new ParsingExpression();
			symbols.add(expr);
			content = new ParserExpressionTerm(expr);
			return true;
		}
		else if(token instanceof NumberToken) {
			content = new NumberTerm(((NumberToken) token).getValue());
			return true;
		}
		throw new IllegalExpressionException();
	}
	
	@Override
	public Expression getExpression() throws IllegalExpressionException {
		return rest.process(content.getExpression());
	}
}