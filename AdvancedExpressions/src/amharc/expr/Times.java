package amharc.expr;

/**
 * Class representing the product of two expressions
 * @author Krzysztof Pszeniczny
 */
public class Times extends BinaryOperator {
	Times(Expression lhs, Expression rhs) {
		super(lhs, rhs);
	}
	
	@Override
	public double evaluate(double x) {
		return lhs.evaluate(x) * rhs.evaluate(x);
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
