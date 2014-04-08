package amharc.expr;

public class Times extends BinaryOperator {
	public Times(Expression lhs, Expression rhs) {
		super(lhs, rhs);
	}
	
	@Override
	public double eval(double x) {
		return lhs.eval(x) * rhs.eval(x);
	}

	@Override
	public String operatorString() {
		return "*";
	}

	@Override
	public int getPriority() {
		return 30;
	}

	@Override
	public Expression derivative() {
		return lhs.derivative().multiply(rhs).add(lhs.multiply(rhs.derivative()));
	}
	
	@Override
	public Object accept(ExpressionVisitor visitor) {
		return visitor.visit(this);
	}
}
