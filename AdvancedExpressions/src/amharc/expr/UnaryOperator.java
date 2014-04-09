package amharc.expr;

/**
 * Class representing application of an unary operator to an expression
 * 
 * @author Krzysztof Pszeniczny
 */
public abstract class UnaryOperator extends Expression {
	protected final Expression content;
	
	UnaryOperator(Expression content) {
		this.content = content;
	}
	
	@Override
	public int getPriority() {
		return 600;
	}
	
	/**
	 * Return the expression that the unary operator is applied to
	 * 
	 * @return Content
	 */
	public Expression getContent() {
		return content;
	}
	
	/**
	 * Gets textual representation of the operator
	 * 
	 * @return String representing the operator
	 */
	public abstract String operatorString();
	
	public String toString() {
		return operatorString() + "(" + content + ")";
	}
	
	@Override
	public Object accept(ExpressionVisitor visitor) {
		return visitor.visit(this);
	}
}
