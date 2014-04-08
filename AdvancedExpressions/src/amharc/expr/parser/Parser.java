package amharc.expr.parser;

import java.util.ArrayList;
import java.util.Stack;

import amharc.expr.Expression;
import amharc.expr.lexer.EndOfExpressionToken;
import amharc.expr.lexer.IllegalExpressionException;
import amharc.expr.lexer.Lexer;
import amharc.expr.lexer.Token;

public class Parser {
	private Lexer lexer;
	private Stack<Symbol> symbols;
	private Symbol root;
	
	private Parser(String str) {
		this.lexer = new Lexer(str);
		this.symbols = new Stack<>();
		this.root = new ParsingExpression();
		this.symbols.add(new Terminal(EndOfExpressionToken.getInstance()));
		this.symbols.add(root);
	}
	
	private void parseToken(Token token) throws IllegalExpressionException {
		do {
			if(symbols.empty())
				throw new IllegalExpressionException();
		} while(!symbols.pop().react(token, symbols));
	}
	
	private void parse() throws IllegalExpressionException {
		ArrayList<Token> tokens = new ArrayList<>();
		while(lexer.hasNextToken())
			tokens.add(lexer.getNextToken());
		
		for(int i = tokens.size() - 2; i >= 0; --i)
			parseToken(tokens.get(i));
		parseToken(EndOfExpressionToken.getInstance());
	}
	
	private Expression getExpression() throws IllegalExpressionException {
		if(!symbols.empty())
			parse();
		
		return root.getExpression();
	}
	
	public static Expression parse(String str) throws IllegalExpressionException {
		return new Parser(str).getExpression();
	}
}
