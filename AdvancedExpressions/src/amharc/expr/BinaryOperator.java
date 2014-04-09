package amharc.expr;

/**
 * Class representing a binary operator applied to two expressions
 * 
 * @author Krzysztof Pszeniczny
 */
public abstract class BinaryOperator extends Expression {
	protected final Expression lhs, rhs;
	
	BinaryOperator(Expression lhs, Expression rhs) {
		this.lhs = lhs;
		this.rhs = rhs;
	}
	
	/**
	 * Gets textual representation of the operator
	 * 
	 * @return String representing the operator
	 */
	public abstract String operatorString();
	
	/**
	 * Return left-hand-side of the operator 
	 * 
	 * @see {@link BinaryOperator.getRhs()}
	 * @return LHS of the operator
	 */
	public Expression getLhs() {
		return lhs;
	}
	
	/**
	 * Return right-hand-side of the operator 
	 * 
	 * @see {@link BinaryOperator.getLhs()}
	 * @return RHS of the operator
	 */
	public Expression getRhs() {
		return rhs;
	}
	
	public String toString() {
		String str = "";
		
		if(lhs.getPriority() < this.getPriority())
			str += "(" + lhs + ")";
		else
			str += lhs;
		
		str += operatorString();
		
		if(rhs.getPriority() <= this.getPriority())
			str += "(" + rhs + ")";
		else
			str += rhs;
		
		return str;
	}
}
