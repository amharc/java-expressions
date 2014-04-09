package amharc.expr;

/**
 * Class representing the quotient of two expressions
 * @author Krzysztof Pszeniczny
 */
public class Div extends BinaryOperator {
	Div(Expression lhs, Expression rhs) {
		super(lhs, rhs);
	}
	
	@Override
	public double evaluate(double x) {
		return lhs.evaluate(x) / rhs.evaluate(x);
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
