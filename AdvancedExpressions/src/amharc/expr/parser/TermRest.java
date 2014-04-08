package amharc.expr.parser;

import java.util.Stack;

import amharc.expr.Expression;
import amharc.expr.lexer.CosToken;
import amharc.expr.lexer.ExpToken;
import amharc.expr.lexer.IllegalExpressionException;
import amharc.expr.lexer.SinToken;
import amharc.expr.lexer.Token;
import amharc.expr.lexer.UnaryMinusToken;

class TermRest extends NonTerminal {
	private AbstractTermRest content = null;

	@Override
	public boolean react(Token token, Stack<Symbol> symbols)
			throws IllegalExpressionException {
		if(token.equals(UnaryMinusToken.getInstance())) {
			// predict and match
			content = new TermRestUnaryMinus();
			return true;
		}
		else if(token.equals(ExpToken.getInstance())) {
			// predict and match
			content = new TermRestExp();
			return true;
		}
		else if(token.equals(SinToken.getInstance())) {
			// predict and match
			content = new TermRestSin();
			return true;
		}
		else if(token.equals(CosToken.getInstance())) {
			// predict and match
			content = new TermRestCos();
			return true;
		}
		else {
			content = new TermRestNull();
			return false;
		}
	}

	public Expression process(Expression expression) {
		return content.process(expression);
	}
}