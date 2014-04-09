package amharc.expr;

/**
 * Class representing the sum of two expressions
 * @author Krzysztof Pszeniczny
 */
public class Plus extends BinaryOperator {
	Plus(Expression lhs, Expression rhs) {
		super(lhs, rhs);
	}
	
	@Override
	public double evaluate(double x) {
		return lhs.evaluate(x) + rhs.evaluate(x);
	}

	@Override
	public String operatorString() {
		return "+";
	}

	@Override
	public int getPriority() {
		return 10;
	}

	@Override
	public Expression derivative() {
		return lhs.derivative().add(rhs.derivative());
	}
	
	@Override
	public Object accept(ExpressionVisitor visitor) {
		return visitor.visit(this);
	}
	
}
