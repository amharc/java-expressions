package amharc.expr;

/**
 * Class representing cosine of some expression
 * 
 * @author Krzysztof Pszeniczny
 */
public class CosineFunction extends UnaryOperator {
	CosineFunction(Expression content) {
		super(content);
	}

	@Override
	public double evaluate(double x) {
		return Math.cos(content.evaluate(x));
	}

	@Override
	public Expression derivative() {
		return content.derivative().multiply(content.sin().negate());
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
