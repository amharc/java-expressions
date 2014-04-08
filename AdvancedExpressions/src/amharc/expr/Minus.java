package amharc.expr;

public class Minus extends BinaryOperator {
	public Minus(Expression lhs, Expression rhs) {
		super(lhs, rhs);
	}

	@Override
	public double eval(double x) {
		return lhs.eval(x) - rhs.eval(x);
	}
	
	@Override
	public String operatorString() {
		return "-";
	}

	@Override
	public int getPriority() {
		return 10;
	}
	
	@Override
	public Expression derivative() {
		return lhs.derivative().subtract(rhs.derivative());
	}
	
	@Override
	public Object accept(ExpressionVisitor visitor) {
		return visitor.visit(this);
	}
}
