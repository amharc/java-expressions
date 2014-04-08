package amharc.expr;

public class ExponentialFunction extends UnaryOperator {
	public ExponentialFunction(Expression exp) {
		super(exp);
	}

	@Override
	public double eval(double x) {
		return Math.exp(x);
	}

	@Override
	public Expression derivative() {
		return exp.derivative().multiply(this);
	}
	
	@Override
	public String operatorString() {
		return "exp";
	}
	
	@Override
	public Object accept(ExpressionVisitor visitor) {
		return visitor.visit(this);
	}
}
