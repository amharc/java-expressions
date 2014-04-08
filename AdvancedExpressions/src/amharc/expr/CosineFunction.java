package amharc.expr;

public class CosineFunction extends UnaryOperator {

	public CosineFunction(Expression exp) {
		super(exp);
	}

	@Override
	public double eval(double x) {
		return Math.cos(exp.eval(x));
	}

	@Override
	public Expression derivative() {
		return exp.derivative().multiply(exp.sin().negate());
	}
	
	@Override
	public Object accept(ExpressionVisitor visitor) {
		return visitor.visit(this);
	}

	@Override
	public String operatorString() {
		return "cos";
	}
}
