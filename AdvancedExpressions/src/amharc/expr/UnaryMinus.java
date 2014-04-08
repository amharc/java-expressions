package amharc.expr;

public class UnaryMinus extends UnaryOperator {
	public UnaryMinus(Expression exp) {
		super(exp);
	}
	
	@Override
	public double eval(double x) {
		return -exp.eval(x);
	}
	
	@Override
	public Expression negate() {
		return exp;
	}

	@Override
	public int getPriority() {
		return 10;
	}
	
	@Override
	public String toString() {
		if(exp.getPriority() > this.getPriority())
			return "-" + exp;
		else
			return "-(" + exp + ")";
	}
	
	@Override
	public Expression derivative() {
		return exp.derivative().negate();
	}
	
	@Override
	public Object accept(ExpressionVisitor visitor) {
		return visitor.visit(this);
	}

	@Override
	public String operatorString() {
		return "-";
	}
}
