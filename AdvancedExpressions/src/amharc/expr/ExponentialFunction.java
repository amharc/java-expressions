package amharc.expr;

/**
 * Class representing exp of some expression
 * 
 * @author Krzysztof Pszeniczny
 */
public class ExponentialFunction extends UnaryOperator {
	ExponentialFunction(Expression content) {
		super(content);
	}

	@Override
	public double evaluate(double x) {
		return Math.exp(x);
	}

	@Override
	public Expression derivative() {
		return content.derivative().multiply(this);
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
