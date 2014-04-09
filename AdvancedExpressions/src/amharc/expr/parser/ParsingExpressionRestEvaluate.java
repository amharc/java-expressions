package amharc.expr.parser;

import amharc.expr.Constant;
import amharc.expr.Expression;
import amharc.expr.ExpressionVisitor;
import amharc.expr.OneConstant;
import amharc.expr.ZeroConstant;
import amharc.expr.lexer.IllegalExpressionException;

class ParsingExpressionRestEvaluate extends AbstractParsingExpressionRest {
	ParsingExpression pe;
	
	public ParsingExpressionRestEvaluate(ParsingExpression pe) {
		this.pe = pe;
	}
	
	@Override
	public Expression process(Expression e) throws IllegalExpressionException {
		final Expression lhs = pe.getExpression();
		
		Double res = (Double) e.accept(new ExpressionVisitor() {
			@Override
			public Object visit(Expression e) {
				return null;
			}
			
			@Override
			public Object visit(Constant e) {
				return lhs.evaluate(e.getValue());
			}
			
			@Override
			public Object visit(ZeroConstant e) {
				return lhs.evaluate(e.getValue());
			}
			
			@Override
			public Object visit(OneConstant e) {
				return lhs.evaluate(e.getValue());
			}
		});
		
		if(res == null)
			throw new IllegalExpressionException();
		else
			return Expression.getConstant(res);
	}	
}