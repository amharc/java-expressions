package amharc.expr;

/**
 * Class representing cosine of some expression
 * 
 * @author Krzysztof Pszeniczny
 */
public class SineFunction extends UnaryOperator {
	SineFunction(Expression content) {
		super(content);
	}

	@Override
	public double evaluate(double x) {
		return Math.sin(content.evaluate(x));
	}

	@Override
	public Expression derivative() {
		return content.derivative().multiply(content.cos());
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
