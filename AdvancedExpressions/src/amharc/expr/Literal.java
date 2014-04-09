package amharc.expr;

/**
 * Class representing terms with no arguments, i.e. variables and constants
 * @author Krzysztof Pszeniczny
 */
public abstract class Literal extends Expression {
	@Override
	public int getPriority() {
		return 1000;
	}
	
	@Override
	public Object accept(ExpressionVisitor visitor) {
		return visitor.visit(this);
	}
}
