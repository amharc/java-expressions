package amharc.expr;

/**
 * Class representing unary minus applied to some expression
 * 
 * @author Krzysztof Pszeniczny
 */
public class UnaryMinus extends UnaryOperator {
	UnaryMinus(Expression exp) {
		super(exp);
	}
	
	@Override
	public double evaluate(double x) {
		return -content.evaluate(x);
	}
	
	@Override
	public Expression negate() {
		return content;
	}

	@Override
	public int getPriority() {
		return 10;
	}
	
	@Override
	public String toString() {
		if(content.getPriority() > this.getPriority())
			return "-" + content;
		else
			return "-(" + content + ")";
	}
	
	@Override
	public Expression derivative() {
		return content.derivative().negate();
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
