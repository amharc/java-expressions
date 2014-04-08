package amharc.expr;

public abstract class ExpressionVisitor {
	public abstract Object visit(Expression e);
	
	public Object visit(BinaryOperator e) {
		return visit((Expression) e);
	}
	
	public Object visit(Constant e) {
		return visit((Literal) e);
	}
	
	public Object visit(CosineFunction e) {
		return visit((UnaryOperator) e);
	}
	
	public Object visit(Div e) {
		return visit((BinaryOperator) e);
	}
	
	public Object visit(ExponentialFunction e) {
		return visit((UnaryOperator) e);
	}
	
	public Object visit(Literal e) {
		return visit((Expression) e);
	}
	
	public Object visit(Minus e) {
		return visit((BinaryOperator) e);
	}
	
	public Object visit(OneConstant e) {
		return visit((Constant) e);
	}
	
	public Object visit(Plus e) {
		return visit((BinaryOperator) e);
	}
	
	public Object visit(SineFunction e) {
		return visit((UnaryOperator) e);
	}
	
	public Object visit(Times e) {
		return visit((BinaryOperator) e);
	}
	
	public Object visit(UnaryMinus e) {
		return visit((UnaryOperator) e);
	}
	
	public Object visit(UnaryOperator e) {
		return visit((Expression) e);
	}
	
	public Object visit(Variable e) {
		return visit((Literal) e);
	}
	
	public Object visit(ZeroConstant e) {
		return visit((Constant) e);
	}
}
