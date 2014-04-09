package amharc.expr;

/**
 * Class representing the difference of two expressions
 * @author Krzysztof Pszeniczny
 */
public class Minus extends BinaryOperator {
	Minus(Expression lhs, Expression rhs) {
		super(lhs, rhs);
	}

	@Override
	public double evaluate(double x) {
		return lhs.evaluate(x) - rhs.evaluate(x);
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
