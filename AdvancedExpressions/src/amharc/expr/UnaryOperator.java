package amharc.expr;

public abstract class UnaryOperator extends Expression {
	protected final Expression exp;
	
	public UnaryOperator(Expression exp) {
		this.exp = exp;
	}
	
	@Override
	public int getPriority() {
		return 600;
	}
	
	public Expression getContents() {
		return exp;
	}
	
	public abstract String operatorString();
	
	public String toString() {
		return operatorString() + "(" + exp + ")";
	}
	
	@Override
	public Object accept(ExpressionVisitor visitor) {
		return visitor.visit(this);
	}
}
