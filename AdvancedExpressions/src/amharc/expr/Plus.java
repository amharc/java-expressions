package amharc.expr;

public class Plus extends BinaryOperator {
	public Plus(Expression lhs, Expression rhs) {
		super(lhs, rhs);
	}
	
	@Override
	public double eval(double x) {
		return lhs.eval(x) + rhs.eval(x);
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
