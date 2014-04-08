package amharc.expr;

public class Div extends BinaryOperator {
	public Div(Expression lhs, Expression rhs) {
		super(lhs, rhs);
	}
	
	@Override
	public double eval(double x) {
		return lhs.eval(x) / rhs.eval(x);
	}

	@Override
	public String operatorString() {
		return "/";
	}

	@Override
	public int getPriority() {
		return 30;
	}

	@Override
	public Expression derivative() {
		Expression numerator = lhs.derivative().multiply(rhs).subtract(lhs.multiply(rhs.derivative()));
		Expression denominator = rhs.multiply(rhs);
		return numerator.divide(denominator);
	}
	
	@Override
	public Object accept(ExpressionVisitor visitor) {
		return visitor.visit(this);
	}
}
