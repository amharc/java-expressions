package amharc.expr;

public class SineFunction extends UnaryOperator {

	public SineFunction(Expression exp) {
		super(exp);
	}

	@Override
	public double eval(double x) {
		return Math.sin(exp.eval(x));
	}

	@Override
	public Expression derivative() {
		return exp.derivative().multiply(exp.cos());
	}

	@Override
	public String operatorString() {
		return "sin";
	}
	
	@Override
	public Object accept(ExpressionVisitor visitor) {
		return visitor.visit(this);
	}
}
